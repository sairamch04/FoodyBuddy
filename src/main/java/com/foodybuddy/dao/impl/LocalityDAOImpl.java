package com.foodybuddy.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.foodybuddy.dao.LocalityDAO;
import com.foodybuddy.model.Locality;


@Repository
public  class LocalityDAOImpl implements LocalityDAO {
	
	private Session session = null;
	
	public LocalityDAOImpl(Session session){
		this.session = session;
	}

	public void insert(Locality locality) {
		session.persist(locality);
	}

	public void update(Locality locality) {
		session.update(locality);
	}

	@SuppressWarnings("unchecked")
	public List<Locality> getAll() {
		String query = "FROM Locality";
		List<Locality> localitiesList = session.createQuery(query).list();
		return localitiesList;
	}

	public Locality getById(int id) {		
		String query = "FROM Locality where id = "+id;
		Locality locality = (Locality)session.createQuery(query).uniqueResult();
		return locality;
	}
	
	public void delete(Locality locality) {
		session.delete(locality);
	}

}