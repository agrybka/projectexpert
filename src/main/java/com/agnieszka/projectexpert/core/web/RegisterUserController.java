package com.agnieszka.projectexpert.core.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.agnieszka.projectexpert.core.domain.Address;
import com.agnieszka.projectexpert.core.domain.SEX;
import com.agnieszka.projectexpert.core.domain.User;
import com.agnieszka.projectexpert.core.domain.UserStatus;
import com.agnieszka.projectexpert.core.domain.UserType;
import com.agnieszka.projectexpert.core.service.IUserService;
import com.agnieszka.projectexpert.core.service.SendEmailException;
import com.agnieszka.projectexpert.core.service.ServiceException;
import com.agnieszka.projectexpert.core.service.UserLoginAlreadyExistsException;

@ManagedBean
@ViewScoped
public class RegisterUserController implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private User user;
	private String repassword;
	
	@PostConstruct//ta funkcja wywola sie po utworzenie obiektu kontroler
	public void init()
	{
		user=new User();
		user.setStatus(UserStatus.INACTIVE);
		user.setType(UserType.STANDARD);
		
		Address address=new Address();
		user.setAddress(address);
		
	}
	
	public String registerUser()
	{
		if(!user.getPassword().equals(repassword))
		{
			JSFUtility.sendGlobalMessage(FacesMessage.SEVERITY_ERROR, "Podane hasła są rózne");
			return null;
		}
		
		try {
			JSFUtility.findService(IUserService.class).register(user);
			return "user-register-info";
		} 
		catch(UserLoginAlreadyExistsException e)
		{
			JSFUtility.sendGlobalMessage(FacesMessage.SEVERITY_ERROR, "Podany login juz jest zajety");
			
			//ten login zarezerwowany
		}
		catch(SendEmailException e)
		{
			JSFUtility.sendGlobalMessage(FacesMessage.SEVERITY_ERROR, "Aktualnie nie mozna rejestrowac sie w aplikacji(Blad wysylania maili)");
			e.printStackTrace();
		}
		catch (ServiceException e) {
			
			JSFUtility.sendGlobalMessage(FacesMessage.SEVERITY_ERROR, "Wystapil nieznany blad");
		}
		
		return null;
	}

	public User getUser() {
		return user;
	}
	
	public List<SelectItem> getSexList()
	{
		List<SelectItem> items=new ArrayList<>();
		items.add(new SelectItem(SEX.MALE, "Mezczyzna"));
		items.add(new SelectItem(SEX.FEMALE, "Kobieta"));
		
		return items;
	}
	
	

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	
}
