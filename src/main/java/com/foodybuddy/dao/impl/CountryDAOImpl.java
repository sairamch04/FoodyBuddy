package com.foodybuddy.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.foodybuddy.dao.CountryDAO;

import com.foodybuddy.model.Country;

public class CountryDAOImpl implements CountryDAO {

	private Session session;

    public CountryDAOImpl(Session session) {
    	this.session = session;
    }
    
	public void insert(Country country) {
		session.persist(country);
	}
	
	@SuppressWarnings("unchecked")
	public List<Country> getAll() {
		String query = "FROM Country";
		List<Country> countryList = session.createQuery(query).list();
		return countryList;
	}
	
	public Country getById(int id) {
		String query = "FROM Country WHERE id = "+id;
		Country country = (Country)session.createQuery(query).uniqueResult();
		return country;
	}
	
	public void update(Country country) {
		session.update(country);
	}

	public void delete(Country country) {
		session.delete(country);
	}

}
