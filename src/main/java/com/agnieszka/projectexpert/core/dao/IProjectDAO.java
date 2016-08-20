package com.agnieszka.projectexpert.core.dao;

import java.util.List;

import com.agnieszka.projectexpert.core.domain.Project;
import com.agnieszka.projectexpert.core.domain.ProjectStatus;

public interface IProjectDAO {

	List<Project> findAll();
	Project create(Project user);
	void update(Project user);
	void delete(Project user);
	Project findById(Integer projectId);
	List<Project> findinProgressProjects();
	long findProjectCountByStatus(ProjectStatus status); 
}
