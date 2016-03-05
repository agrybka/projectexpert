package com.agnieszka.projectexpert.core.dao;

import java.util.List;

import com.agnieszka.projectexpert.core.domain.User;

public interface IUserDAO {

	List<User> findAll();
	User create(User user);
	void update(User user);
	void delete(User user);
	User findById(String mail);
	
}
