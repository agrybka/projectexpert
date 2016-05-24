package com.agnieszka.projectexpert.core.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailSender {

	private static final String SMTP_SERVER="smtp.googlemail.com";
	private static final Integer SMTP_PORT=465;
	private static final String USERNAME="projektexpertjava@gmail.com";
	private static final String PASSWORD="projektexpertjava2016";
	private static final boolean SMTP_SSL=true;
	
	public static void sendMail(String title,String text,String address) throws EmailException
	{
		Email email = new SimpleEmail();
		email.setHostName(SMTP_SERVER);
		email.setSmtpPort(SMTP_PORT);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
		email.setSSLOnConnect(SMTP_SSL);
		email.setFrom(USERNAME);
		email.setSubject(title);
		email.setMsg(text);
		email.addTo(address);
		email.send();
	}
	
	public static void main(String[] args) throws EmailException
	{
		/*
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
		email.setSSLOnConnect(true);
		email.setFrom(USERNAME);
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo(USERNAME);
		email.send();
		*/
		
		sendMail("testowy email","Witaj swiecie","agnieszkarybka92@gmail.com");
	}
}
