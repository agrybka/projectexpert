package com.agnieszka.projectexpert.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.agnieszka.projectexpert.core.domain.Comment;

@Repository
public class JpaCommentDAO implements ICommentDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Comment creat(Comment comment) {
		em.persist(comment);
		return comment;
	}

}
