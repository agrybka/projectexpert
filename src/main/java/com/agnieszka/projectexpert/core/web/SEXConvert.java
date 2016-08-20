package com.agnieszka.projectexpert.core.web;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.agnieszka.projectexpert.core.domain.SEX;

@FacesConverter("SEXConvert")
public class SEXConvert implements Converter{

	
	/**
	 * Pop przeslaniu przez przegladarke wartosci w postaci tekstu zamienia tekst na konkretny typ w javie 
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		System.out.println("SEXConverter getAsObject - "+value);
		//"FEMAIL"-->SEX.FEMALE
		if(value==null||value.length()==0)
			return null;
		
		return SEX.valueOf(value);
	}

	/**
	 * Zamienia typ w javie na tekst aby mozna bylo wyswietlic go na stronie html
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		//SEX.FEMALE -> "FEMAIL"
		System.out.println("SEXConverter getAsString - "+value);
		SEX sex=(SEX) value;
		if(sex==null)
			return "";
		else
			return sex.name();
	}

}
