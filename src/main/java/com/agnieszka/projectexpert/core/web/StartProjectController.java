package com.agnieszka.projectexpert.core.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import com.agnieszka.projectexpert.core.domain.Project;
import com.agnieszka.projectexpert.core.domain.ProjectStatus;
import com.agnieszka.projectexpert.core.service.IProjectService;


@ManagedBean
@RequestScoped
public class StartProjectController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Project> projectList;
	private long projectNewCount;
	private long projectInProgressCount;
	private long projectEndCount;

	private ScheduleModel eventModel;
	
	@PostConstruct
	public void init()
	{
		System.out.println("Init");
		projectList=JSFUtility.findService(IProjectService.class).findinProgressProjects();
		projectNewCount=JSFUtility.findService(IProjectService.class).findProjectCountByStatus(ProjectStatus.NEW);
		projectInProgressCount=JSFUtility.findService(IProjectService.class).findProjectCountByStatus(ProjectStatus.IN_PROGRESS);
		projectEndCount=JSFUtility.findService(IProjectService.class).findProjectCountByStatus(ProjectStatus.END);
	
		eventModel=new DefaultScheduleModel();
	}
	
	public List<Project> getProjectList() {
		return projectList;
	}

	public long getProjectNewCount() {
		return projectNewCount;
	}

	public long getProjectInProgressCount() {
		return projectInProgressCount;
	}

	public long getProjectEndCount() {
		return projectEndCount;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}
	
}
