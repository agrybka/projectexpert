package com.agnieszka.projectexpert.core.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.agnieszka.projectexpert.core.domain.User;

@ManagedBean
@SessionScoped
public class UserSessionController implements Serializable{

	private static final long serialVersionUID = 1L;

	private User loginUser;

	public User getLoginUser() {
		return loginUser;
	}
	
	public String logoutAction()
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login";
	}

	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}
	
	public boolean isLoggedIn()
	{
		if(loginUser==null)
			return false;
		else
			return true;
	}
	
	
}
