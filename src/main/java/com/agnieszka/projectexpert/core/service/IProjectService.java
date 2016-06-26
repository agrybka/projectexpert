package com.agnieszka.projectexpert.core.service;

import java.util.List;

import com.agnieszka.projectexpert.core.domain.Project;

public interface IProjectService {

	List<Project> findAll();
	Project create(Project project);
	void update(Project project);
	void startProject(Integer projectID);
	void endProject(Integer projectID);
	Project find(Integer id);
	
}
