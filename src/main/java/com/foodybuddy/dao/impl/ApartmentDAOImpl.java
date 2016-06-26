package com.foodybuddy.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.foodybuddy.dao.ApartmentDAO;
import com.foodybuddy.model.Apartment;


@Repository
public class ApartmentDAOImpl implements ApartmentDAO {
	
	private Session session = null;
	
	public ApartmentDAOImpl(Session session){
		this.session = session;
	}

	public void insert(Apartment apartment) {
		session.persist(apartment);
	}

	public void update(Apartment apartment) {
		session.update(apartment);
	}

	@SuppressWarnings("unchecked")
	public List<Apartment> getAll() {
		List<Apartment> ApartmentsList = session.createQuery("FROM Apartment").list();
		return ApartmentsList;
	}

	public Apartment getById(int id) {		
		String query = "FROM Apartment WHERE id = " + id;
		Apartment apartment = (Apartment)session.createQuery(query).uniqueResult();
		return apartment;
	}

	public void delete(Apartment apartment) {
		session.delete(apartment);
	}

}