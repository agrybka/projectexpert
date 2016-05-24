package com.agnieszka.projectexpert.core.service;

import java.util.List;

import com.agnieszka.projectexpert.core.domain.User;

public interface IUserService {

	List<User> findAll();
	/**
	 * Rejestruje uzytkownika (tworzy w bazie danych i wysyla maila z potwierdzenie)
	 * @param user
	 * @return
	 * @throws ServiceException wyrzuca dwa wyjatki 
	 */
	User register(User user) throws ServiceException;
	void update(User user);
	void delete(User user);
	User findById(String mail);
	User login(String username,String password);
}
