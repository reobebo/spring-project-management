package com.jrp.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.dto.ProjectCount;
import com.jrp.pma.entities.Project;

@Controller
public class HomeController {
//	@Value("${version}")
//	private String ver;
	
	@Autowired
	ProjectRepository proRepo;
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
//		model.addAttribute("versionNumber",ver);
		
		Map<String, Object> map = new HashMap<>();
		
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList",projects);
		
		List<ProjectCount> projectsCnt = proRepo.projectCount();
	
		
		// Lets convert projectData object into a json structure for use in javascript
		ObjectMapper objectMapper = new ObjectMapper();	
		String jsonString = objectMapper.writeValueAsString(projectsCnt);
		model.addAttribute("projectStatusCnt", jsonString);
		
		List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
		model.addAttribute("employeesListProjectsCnt",employeesProjectCnt);
		return "main/home";
	}
}
