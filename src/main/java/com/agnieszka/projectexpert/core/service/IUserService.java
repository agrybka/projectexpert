package com.agnieszka.projectexpert.core.service;

import java.util.List;

import com.agnieszka.projectexpert.core.domain.User;

public interface IUserService {

	List<User> findAll();
	User register(User user);
	void update(User user);
	void delete(User user);
	User findById(String mail);
}
