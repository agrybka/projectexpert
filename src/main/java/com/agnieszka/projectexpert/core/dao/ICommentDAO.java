package com.agnieszka.projectexpert.core.dao;

import com.agnieszka.projectexpert.core.domain.Comment;
/**
 * Klasa do tworzenia komentarzy w bazie dnaych
 * @author Aga
 *
 */
public interface ICommentDAO {
	Comment creat(Comment comment);
}
