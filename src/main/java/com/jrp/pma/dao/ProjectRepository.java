package com.jrp.pma.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jrp.pma.dto.ProjectCount;
import com.jrp.pma.dto.ProjectTimeLine;
import com.jrp.pma.entities.Project;
@RepositoryRestResource(collectionResourceRel="apiprojects",path="apiprojects")
public interface ProjectRepository extends PagingAndSortingRepository<Project,Long >{
	@Override
	public List<Project> findAll();
	
	@Query(nativeQuery=true,value="SELECT STAGE AS label, COUNT(*) AS value "
			+ "FROM PROJECT "
			+ "GROUP BY STAGE; ")
	public List<ProjectCount> projectCount();

	public Project findByProjectId(long id);
	
	@Query(nativeQuery=true, value="SELECT name as projectName, start_date as startDate, end_date as endDate"
			+ " FROM project WHERE start_date is not null")
	public List<ProjectTimeLine> getTimeData();
}
 