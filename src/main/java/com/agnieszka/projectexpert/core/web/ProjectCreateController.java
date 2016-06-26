package com.agnieszka.projectexpert.core.web;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.agnieszka.projectexpert.core.domain.Document;
import com.agnieszka.projectexpert.core.domain.Memeber;
import com.agnieszka.projectexpert.core.domain.Project;
import com.agnieszka.projectexpert.core.domain.ProjectStatus;
import com.agnieszka.projectexpert.core.domain.User;
import com.agnieszka.projectexpert.core.service.IProjectService;
import com.agnieszka.projectexpert.core.service.IUserService;


@ManagedBean
@ViewScoped
public class ProjectCreateController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{userSessionController}")
	private UserSessionController session;
	
	private Project project;
	private Memeber member;
	
	@PostConstruct //ta metoda wywla sie po utworzeniu klasy kontrolera
	public void init()
	{
		project=new Project();
		project.setAuthor(session.getLoginUser());
		project.setStatus(ProjectStatus.NEW);
		project.setMemeberList(new ArrayList<>());
	}

	public Project getProject() {
		return project;
	}
	
	public List<User> getUsers()
	{
		return JSFUtility.findService(IUserService.class).findAll();
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
	
	public String createAction()
	{
		JSFUtility.findService(IProjectService.class).create(project);
		JSFUtility.sendGlobalMessage(FacesMessage.SEVERITY_INFO, "Pomyślnie utworzono projekt w systemie");
		init();
		return null;
	}

	public void setSession(UserSessionController session) {
		this.session = session;
	}

	public Memeber getMember() {
		return member;
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		try
		{
			UploadedFile uploadedFile = event.getFile();
			File file=File.createTempFile("project", "tmp");//tworzymy nowy tymczasowy plik w systemie
			Files.copy(uploadedFile.getInputstream(), file.toPath(),StandardCopyOption.REPLACE_EXISTING);//kopiujemy z pliku zaldowane przez uzytkownika dane do pliku tymczasowego
		
			Document document=new Document();
			document.setName(getUniqueFileName(uploadedFile.getFileName(),project.getDocumentList()));//name.txt , name.txt->name(1).txt
			document.setCreationDate(new Date());
			document.setUserMail(session.getLoginUser());
			
			document.setPath(file.getPath());//tymczasowa sciezka
			project.addDocument(document);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		//C://aplikacja/projektId/nazwaPliku
	}
	
	private String getUniqueFileName(String fileName,List<Document> documents)
	{
		if(documents==null||documents.isEmpty())
			return fileName;
		try
		{
		boolean exists=false;
		for(Document document:documents)
		{
			if(fileName.equalsIgnoreCase(document.getName()))
				exists=true;
		}
		
		if(!exists)//nie odnaleziono pliku o takiej samej nazwie
			return fileName;
		//name.txt
		int indeks=fileName.lastIndexOf(".");//4
		String extension=fileName.substring(indeks+1);//txt
		String name=fileName.substring(0,indeks);//name
		
		int i=1;
		boolean notAcceptable=false;
		String newFileName=null;
		while(true)
		{
			notAcceptable=false;
			System.out.println("Sprawdzam dla i="+i);
			newFileName=name+"("+i+")"+"."+extension;//name(1).txt
			for(Document document:documents)
			{
				if(newFileName.equalsIgnoreCase(document.getName()))
					notAcceptable=true;
			}
			
			if(notAcceptable)
				i++;
			else
				break;
		}
		
		return newFileName;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
		
	}

}
