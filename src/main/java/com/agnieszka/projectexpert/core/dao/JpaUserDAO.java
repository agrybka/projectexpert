package com.agnieszka.projectexpert.core.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.agnieszka.projectexpert.core.domain.User;
//EJB @Singleton - obiekt tylko jeden na aplikacje
//EJB @Stateless - jeden lub maksymlanie kilka obiektow tej klasy na aplikacje
//EJB @Statefull - dla kazego klienta wstrzykiwany jest i tworzony nowy obiekt tej klasy 
//SPRING @Repository - dla klas typu dao, dymslnie maja zasieg Singleton
//Spring @Service - klas klas biznesowych, dymslnie maja zasieg Singleton
//Spring @Component - ogolny typ ,dymslnie maja zasieg Singleton
//Spring @Repository == @Service ==@Component - tworza obiekt w kontenerze springa
//Kazdy obiekt moze byc w dwoch zasiegach Singleton,prototyp - dla kazdego zadania tworzony jest nowy obiekt tej klasy
@Repository //spring utworzy pojedynczy obiekt singleton w kontenerze springa
//@Scope("prototype")//@Scope('singleton') - domyslnie
public class JpaUserDAO implements IUserDAO{

	@PersistenceContext // spring wstrzykuje obiekt tej klasy Entity Managedzer// w ejb tak samo
	private EntityManager em;
	
	@Override
	public List<User> findAll() {
	
		TypedQuery<User> query=em.createNamedQuery("User.findAll", User.class);//tworzy zapytanie
		return query.getResultList();//zwraca liste wynikow
	}

	@Override
	public User create(User user) {
		
		em.persist(user);//tworzy do bazy danych
		return user;
	}

	@Override
	public void update(User user) {
		
		em.merge(user);//zapisuje zmiany 
	}

	@Override
	public void delete(User user) {
		
		User u=em.merge(user);
		em.remove(u);
	}

	@Override
	public User findById(String mail) {
		
		return em.find(User.class, mail);
	}

}
