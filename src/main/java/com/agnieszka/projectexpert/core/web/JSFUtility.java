package com.agnieszka.projectexpert.core.web;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

public class JSFUtility {

	private JSFUtility(){
		
	}
	
	public static <T> T findService(Class<T> clazz)
	{
		ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		return ctx.getBean(clazz);
	}
	
	public static void sendGlobalMessage(FacesMessage.Severity severity,String message)
	{
		//Utworzenie obiektu wiadomosc
		FacesMessage msg=new FacesMessage(severity, message, message);
		//Pobranie kontekstu JSF(konfiguracji JSF)
		FacesContext ctx=FacesContext.getCurrentInstance();
		//dodajemy wiadomosc do wyswietlenia jako globalna(pierwszy parametr zawsze null)
		ctx.addMessage(null, msg);
	}
	
	public static String getRequestParameter(String name)
	{
		//Pobranie kontekstu JSF
		FacesContext ctx=FacesContext.getCurrentInstance();
		//Pobranie kontesktu serwletow - api serwletow - meody dostepu do zadania http
		ExternalContext ectx=ctx.getExternalContext();
		//Pobieramy mape z wszytskimi parametrami dla aktualnego mzadania http
		Map<String,String> parameters=ectx.getRequestParameterMap();
		//pobranie z mapy parametru o nazwie {name}
		return parameters.get(name);
	}
}
