package com.agnieszka.projectexpert.core.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agnieszka.projectexpert.core.dao.IProjectDAO;
import com.agnieszka.projectexpert.core.domain.Document;
import com.agnieszka.projectexpert.core.domain.Project;
import com.agnieszka.projectexpert.core.domain.ProjectStatus;

@Service

public class ProjectService implements IProjectService {

	private static final String FINAL_PATH="C:\\aplikacjajee\\";
	
	@Autowired
	private IProjectDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public List<Project> findAll() {
		
		return dao.findAll();
	}

	@Transactional
	@Override
	public Project create(Project project) {
		
		List<Document> documents=project.getDocumentList();
		project.setDocumentList(null);
		dao.create(project);
		project.setDocumentList(documents);
		changePathDocuments(project);
		dao.update(project);
		return project;
	}

	@Transactional
	@Override
	public void update(Project project) {
		
		
		changePathDocuments(project);
		dao.update(project);
		
	}
	/**
	 * Zmienic sciezke plikow z tymczasowej na docelowa
	 * @param project
	 */
	private void changePathDocuments(Project project)
	{
		if(project.getDocumentList()==null||project.getDocumentList().isEmpty())
			return;
	
		String projectPath=FINAL_PATH+project.getId()+"\\";
		File file=new File(projectPath);
		if(!file.exists())
			file.mkdirs();//tworzy sciezke do plikow
		try
		{
		for(Document document:project.getDocumentList())
		{
			if(document.isTemporal())
			{
				String newFilePath=projectPath+document.getName();
				File target=new File(newFilePath);//sciezka do pliku docelowego
				File source=new File(document.getPath());//sciezka do pliku tymczasowego
				Files.copy(source.toPath(), target.toPath(),StandardCopyOption.REPLACE_EXISTING);
				document.setPath(newFilePath);
				source.delete();//usuwamy plik tymczasowy
			}
			
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public void startProject(Integer projectId) {
		
		Project project=dao.findById(projectId);
		project.setStatus(ProjectStatus.IN_PROGRESS);
		project.setStartDate(new Date());
		dao.update(project);
	}

	@Transactional
	@Override
	public void endProject(Integer projectId) {
		
		Project project=dao.findById(projectId);
		project.setStatus(ProjectStatus.END);
		project.setEndDate(new Date());
	}

	@Transactional(readOnly=true)
	@Override
	public Project find(Integer id) {
		// TODO Auto-generated method stub
		Project project=dao.findById(id);
		project.getMemeberList().size();//automatycznie JPA wykona zapytanie sql aby pobrac czlonkow projektu
		project.getDocumentList().size();
		project.getCommentList().size();
		return dao.findById(id);
	}

}
