package com.agnieszka.projectexpert.core.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.agnieszka.projectexpert.core.domain.User;
import com.agnieszka.projectexpert.core.service.IUserService;

@ManagedBean
@ViewScoped
public class UserListController implements Serializable{

	private static final long serialVersionUID = 1L;

	public List<User> getList()
	{
		//pobranie obiektu z kontenera springa
		IUserService service=JSFUtility.findService(IUserService.class);
		return service.findAll();
	}
}
