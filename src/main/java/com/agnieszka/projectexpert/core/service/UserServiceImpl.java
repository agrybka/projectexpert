package com.agnieszka.projectexpert.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agnieszka.projectexpert.core.dao.IUserDAO;
import com.agnieszka.projectexpert.core.domain.User;

@Service
@Transactional //kazda metoda publiczna jest wykonywana w ramach transakcji
public class UserServiceImpl implements IUserService {

	@Autowired // wstrzykiwanie zaleznosci w springu , w ejb odpowiednik @EJB
	private IUserDAO userDao;
	
	@Override
	@Transactional(readOnly=true)//dodaje obsluge transakcji dla metody
	//Uwaga w JPA kazda funkcja powinna byc wykonana w ramach transakcji
	public List<User> findAll() {
		
		return userDao.findAll();
	}

	@Override
	public User register(User user) {
		
		//userDao.create(user);
		//SendMail.sendMail(user.getMail());
		return null;
	}

	@Override
	public void update(User user) {
	
		
	}

	@Override
	public void delete(User user) {
		
		
	}

	@Override
	@Transactional(readOnly=true)//tryb uproszczony transakcji ale tylko dla odczytu
	public User findById(String mail) {
		
		return userDao.findById(mail);
	}

	
}
