package com.agnieszka.projectexpert.core.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.agnieszka.projectexpert.core.domain.Project;

@Repository
public class JpaProjectDAO implements IProjectDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Project> findAll() {
		TypedQuery<Project> query=em.createNamedQuery("Project.findAll", Project.class);//tworzy zapytanie
		return query.getResultList();//zwraca liste wynikow
	}

	@Override
	public Project create(Project project) {
		em.persist(project);
		return project;
	}

	@Override
	public void update(Project project) {
		
		em.merge(project);
	}

	@Override
	public void delete(Project project) {
		Project u=em.merge(project);
		em.remove(u);
		
	}

	@Override
	public Project findById(Integer projectId) {
		
		return em.find(Project.class, projectId);
	}

}
