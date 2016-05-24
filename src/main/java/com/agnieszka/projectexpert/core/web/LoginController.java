package com.agnieszka.projectexpert.core.web;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.agnieszka.projectexpert.core.domain.User;
import com.agnieszka.projectexpert.core.service.IUserService;

@ManagedBean
@RequestScoped
public class LoginController implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	
	@ManagedProperty(value="#{userSessionController}")
	private UserSessionController session;
	
	public String loginAction()
	{
		IUserService service=JSFUtility.findService(IUserService.class);
		User user=service.login(username, password);
		if(user!=null)//zalogowany
		{
			session.setLoginUser(user);
			return "index";
		}
		else//niezalogowanu
		{
			JSFUtility.sendGlobalMessage(FacesMessage.SEVERITY_ERROR, "Niepoprawne dane logowania");
			return null;
		}
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSession(UserSessionController session) {
		this.session = session;
	}
	
	
}
