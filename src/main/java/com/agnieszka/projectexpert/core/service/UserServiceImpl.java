package com.agnieszka.projectexpert.core.service;

import java.util.List;

import org.apache.commons.mail.EmailException;
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
	/**
	 * @author Aga
	 * @throws SendEmailException - blad wysylania potwierdzenia
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)//blad typu RuntimeException domyslnie wycofa zmiany
	public User register(User user) throws ServiceException {
		
		User u=userDao.findById(user.getMail());
		if(u!=null)
			throw new UserLoginAlreadyExistsException("User already exists");
		
		userDao.create(user);
		try
		{
			EmailSender.sendMail("Potwierdzenie rejestract", "Potwierdzenie rejestracji dla "+user.getMail(), user.getMail());
		}
		catch(EmailException e)
		{
			throw new SendEmailException(e);//przechwytujemy wyajtek z biblioteki i opakowuje go we wlasy i wyrzucamy
		}
		return user;
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
	//Query 1 =Insert into User  
	//Query 2 =Insert INTo Addres 
	@Override
	@Transactional(readOnly=true)//ta operacja ma byc wykonywana w ramach transakcji
	public User login(String username, String password) {
		//UserTransaction.begin()  commit() rollback()
		return userDao.findByLoginAndPassword(username, password);
	}

	
}
