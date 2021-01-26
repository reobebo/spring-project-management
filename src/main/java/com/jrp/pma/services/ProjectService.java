package com.jrp.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ProjectCount;
import com.jrp.pma.dto.ProjectTimeLine;
import com.jrp.pma.entities.Project;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository proRepo;
	
	public Project save(Project project) {
		return proRepo.save(project);
	}
	
	public List<Project> findAll(){
		return proRepo.findAll();
	}
	
	public List<ProjectCount> projectCount(){
		return proRepo.projectCount();
	}

	public Project findByProjectId(long id) {
	
		return proRepo.findByProjectId(id);
	}

	public void delete(Project thePro) {
		proRepo.delete(thePro);
		
	}
	
	public List<ProjectTimeLine> getTimeData(){
		return proRepo.getTimeData();
	}
}