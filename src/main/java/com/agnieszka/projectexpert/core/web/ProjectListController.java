package com.agnieszka.projectexpert.core.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.agnieszka.projectexpert.core.domain.Project;
import com.agnieszka.projectexpert.core.service.IProjectService;

@ManagedBean
@RequestScoped
public class ProjectListController implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Project> projects;
	
	@PostConstruct
	public void init(){
		projects=JSFUtility.findService(IProjectService.class).findAll();		
	}

	public List<Project> getProjects() {
		return projects;
	}
	
	public void startProject(Integer projectId)
	{
		JSFUtility.findService(IProjectService.class).startProject(projectId);
		JSFUtility.sendGlobalMessage(FacesMessage.SEVERITY_INFO, "Projekt wystartowano");
		projects=JSFUtility.findService(IProjectService.class).findAll();	
	}
	
	public void finishProject(Integer projectId)
	{
		JSFUtility.findService(IProjectService.class).endProject(projectId);
		JSFUtility.sendGlobalMessage(FacesMessage.SEVERITY_INFO, "Projekt zakończono");
		projects=JSFUtility.findService(IProjectService.class).findAll();	
	}
	
	
}
