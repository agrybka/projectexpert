package com.agnieszka.projectexpert.core.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.agnieszka.projectexpert.core.domain.Memeber;
import com.agnieszka.projectexpert.core.domain.Project;
import com.agnieszka.projectexpert.core.domain.User;
import com.agnieszka.projectexpert.core.service.IProjectService;
import com.agnieszka.projectexpert.core.service.IUserService;

@ManagedBean
@ViewScoped
public class ProjectEditController implements Serializable{

	private static final long serialVersionUID = 1L;
	private Project project;
	private Memeber member;
	
	@PostConstruct
	public void init()
	{
		String paramId=JSFUtility.getRequestParameter("id");
		if(paramId!=null)
		{
			project=JSFUtility.findService(IProjectService.class).find(Integer.parseInt(paramId));
		}
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
	
	public String updateAction()
	{
		JSFUtility.findService(IProjectService.class).update(project);
		JSFUtility.sendGlobalMessage(FacesMessage.SEVERITY_INFO, "Pomyślnie zapisano zmiany w projekcie");
		
		return null;
	}
	
	public List<User> getUsers()
	{
		return JSFUtility.findService(IUserService.class).findAll();
	}
	
	public Project getProject() {
		return project;
	}
	public Memeber getMember() {
		return member;
	}
	
	
}
