package com.foodybuddy.dao.impl;

import com.foodybuddy.dao.CityDAO;
import com.foodybuddy.model.City;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;


@Repository
public class CityDAOImpl implements CityDAO {
	
	private Session session = null;
	
	public CityDAOImpl(Session session){
		this.session = session;
	}

	public void insert(City city) {
		session.persist(city);
	}

	public void update(City city) {
		session.update(city);
	}

	@SuppressWarnings("unchecked")
	public List<City> getAll() {
		String query = "FROM City";
		List<City> citiesList = session.createQuery(query).list();
		return citiesList;
	}

	public City getById(int id) {		
		String query = "FROM City WHERE id = "+id;
		City city = session.createQuery(query).uniqueResult();
		return city;
	}

	public void delete(City city) {
		session.delete(city);
	}

}