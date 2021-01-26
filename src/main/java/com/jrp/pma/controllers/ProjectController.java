package com.jrp.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dto.ProjectTimeLine;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	@Autowired
	ProjectService proService;
	
	@Autowired
	EmployeeService empService;
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects=proService.findAll();
		model.addAttribute("projects", projects);
		return "projects/list-projects";
	}


	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Project aProject=new Project();
		List<Employee> employees =empService.findAll();
		model.addAttribute("allEmployees",employees);
		model.addAttribute("project",aProject);
		return "projects/new-project";
		
	}
	
	@PostMapping("/save")
	public String createProject(Model model,@Valid Project project, Errors errors) {
		
		if(errors.hasErrors()) {
			return "projects/new-project";
		}
		
		proService.save(project);
		
		
		//use a redirect to prevent duplicate submissions
		return "redirect:/projects";
		
	}
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long id, Model model) {
		Project thePro = proService.findByProjectId(id);
		model.addAttribute("project",thePro);
		return "projects/new-project";
		
	}
	@GetMapping("delete")
	public String deleteEmployee(@RequestParam("id") long id, Model model) {
		Project thePro = proService.findByProjectId(id);
		proService.delete(thePro);
		return "redirect:/projects";
	}
	@GetMapping("/timelines")
	public String displayProjectTimelines(Model model) throws JsonProcessingException {
		List<ProjectTimeLine> timeLineData = proService.getTimeData();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTimeLineString = objectMapper.writeValueAsString(timeLineData);
		
		System.out.println("---------- project timelines ----------");
		System.out.println(jsonTimeLineString);
		
		model.addAttribute("projectTimeList",jsonTimeLineString);
		
		return"projects/project-timelines";
	}

	}

