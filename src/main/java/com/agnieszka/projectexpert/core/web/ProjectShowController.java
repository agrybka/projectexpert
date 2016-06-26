package com.agnieszka.projectexpert.core.web;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.agnieszka.projectexpert.core.domain.Comment;
import com.agnieszka.projectexpert.core.domain.Project;
import com.agnieszka.projectexpert.core.service.ICommentService;
import com.agnieszka.projectexpert.core.service.IProjectService;

@ManagedBean
@ViewScoped
public class ProjectShowController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Project project;
	private Comment comment;
	
	@ManagedProperty(value="#{userSessionController}")
	private UserSessionController session;
	
	@PostConstruct
	public void init()
	{
		String paramId=JSFUtility.getRequestParameter("id");
		if(paramId!=null)
		{
			this.project=JSFUtility.findService(IProjectService.class).find(Integer.parseInt(paramId));
			initComment();
		}
	}

	public Project getProject() {
		return project;
	}

	public void initComment()
	{
		comment=new Comment();
		comment.setCreationDate(new Date());
		comment.setProjectId(project);
		comment.setUserMail(session.getLoginUser());
	}
	
	public void createComment()
	{
		JSFUtility.findService(ICommentService.class).createComment(comment);
		initComment();
		project=JSFUtility.findService(IProjectService.class).find(project.getId());
	}

	public void setSession(UserSessionController session) {
		this.session = session;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	
}
