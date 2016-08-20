package com.agnieszka.projectexpert.core.web;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.agnieszka.projectexpert.core.domain.User;
import com.agnieszka.projectexpert.core.service.IUserService;
import com.agnieszka.projectexpert.core.web.JSFUtility;

@FacesConverter("UserConverter")
public class UserConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		
		if(value==null||value.isEmpty())
			return null;
		
		
		return JSFUtility.findService(IUserService.class).findById(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		
		if(value==null)
			return "";
		
		User user=(User)value;
		return user.getMail();
	}

}
