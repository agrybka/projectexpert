package com.agnieszka.projectexpert.core.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.agnieszka.projectexpert.core.domain.Project;
import com.agnieszka.projectexpert.core.domain.ProjectStatus;

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

	@Override
	public List<Project> findinProgressProjects() {
		TypedQuery<Project> query=em.createQuery("select p from Project p where p.status=:status order by p.startDate DESC", Project.class);
		query.setParameter("status", ProjectStatus.IN_PROGRESS);
		
		return query.getResultList();
	}

	@Override
	public long findProjectCountByStatus(ProjectStatus status) {
		TypedQuery<Long> query=em.createQuery("select COUNT(p.id) from Project p where p.status=:status", Long.class);
		query.setParameter("status", status);
		return query.getSingleResult();
	}

}
