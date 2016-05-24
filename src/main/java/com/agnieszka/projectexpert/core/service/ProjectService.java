package com.agnieszka.projectexpert.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agnieszka.projectexpert.core.dao.IProjectDAO;
import com.agnieszka.projectexpert.core.domain.Project;
import com.agnieszka.projectexpert.core.domain.ProjectStatus;

@Service

public class ProjectService implements IProjectService {

	@Autowired
	private IProjectDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public List<Project> findAll() {
		
		return dao.findAll();
	}

	@Transactional
	@Override
	public Project create(Project project) {
		
		return dao.create(project);
	}

	@Transactional
	@Override
	public void update(Project project) {
		dao.update(project);
		
	}

	@Transactional
	@Override
	public void startProject(Integer projectId) {
		
		Project project=dao.findById(projectId);
		project.setStatus(ProjectStatus.IN_PROGRESS);
		project.setStartDate(new Date());
		dao.update(project);
	}

	@Transactional
	@Override
	public void endProject(Integer projectId) {
		
		Project project=dao.findById(projectId);
		project.setStatus(ProjectStatus.END);
		project.setEndDate(new Date());
	}

	@Transactional(readOnly=true)
	@Override
	public Project find(Integer id) {
		// TODO Auto-generated method stub
		Project project=dao.findById(id);
		project.getMemeberList().size();//automatycznie JPA wykona zapytanie sql aby pobrac czlonkow projektu
		return dao.findById(id);
	}

}
