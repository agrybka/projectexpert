package com.agnieszka.projectexpert.core.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.agnieszka.projectexpert.core.domain.Memeber;
import com.agnieszka.projectexpert.core.domain.Project;
import com.agnieszka.projectexpert.core.domain.ProjectStatus;
import com.agnieszka.projectexpert.core.domain.User;
import com.agnieszka.projectexpert.core.service.IProjectService;
import com.agnieszka.projectexpert.core.service.IUserService;


@ManagedBean
@ViewScoped
public class ProjectCreateController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{userSessionController}")
	private UserSessionController session;
	
	private Project project;
	private Memeber member;
	
	@PostConstruct //ta metoda wywla sie po utworzeniu klasy kontrolera
	public void init()
	{
		project=new Project();
		project.setAuthor(session.getLoginUser());
		project.setStatus(ProjectStatus.NEW);
		project.setMemeberList(new ArrayList<>());
	}

	public Project getProject() {
		return project;
	}
	
	public List<User> getUsers()
	{
		return JSFUtility.findService(IUserService.class).findAll();
	}
	
	public void initMember()
	{
		member=new Memeber();
		member.setProjectId(project);
	}
	
	public void addMemebr()
	{
		
		if(isMemberInList(member))
		{
			JSFUtility.sendGlobalMessage(FacesMessage.SEVERITY_WARN, "Ten użytkownik jest już na liście członków projektu");
		}
		else
		{
			project.getMemeberList().add(member);
		}
	}
	
	private boolean isMemberInList(Memeber member)
	{
		int size=project.getMemeberList().size();
		for(int i=0;i<size;i++)
		{
			if(project.getMemeberList().get(i).getMemberMail().getMail().equals(member.getMemberMail().getMail()))
			{
				return true;
				
			}
		}
		
		return false;
	}
	
	public void cancelMemeber()
	{
		member=null;
	}
	
	public void deleteMember(String mail)
	{
		int size=project.getMemeberList().size();
		for(int i=0;i<size;i++)
		{
			if(project.getMemeberList().get(i).getMemberMail().getMail().equals(mail))
			{
				project.getMemeberList().remove(i);
				break;
			}
		}
	}
	
	public String createAction()
	{
		JSFUtility.findService(IProjectService.class).create(project);
		JSFUtility.sendGlobalMessage(FacesMessage.SEVERITY_INFO, "Pomyślnie utworzono projekt w systemie");
		init();
		return null;
	}

	public void setSession(UserSessionController session) {
		this.session = session;
	}

	public Memeber getMember() {
		return member;
	}
	
	
}
