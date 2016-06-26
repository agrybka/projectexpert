package com.agnieszka.projectexpert.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agnieszka.projectexpert.core.dao.ICommentDAO;
import com.agnieszka.projectexpert.core.domain.Comment;

@Service
public class CommentServiceImpl implements ICommentService{

	@Autowired
	private ICommentDAO dao;
	
	@Transactional
	@Override
	public Comment createComment(Comment comment) {
		
		return dao.creat(comment);
	}

	
}
