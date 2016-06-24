package com.FoodyBuddy.Dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.FoodyBuddy.Dao.LocalityDao;
import com.FoodyBuddy.Model.Locality;


@Repository
public  class LocalityDaoImpl implements LocalityDao {
	
	private Session session = null;
	
	public LocalityDaoImpl(Session session){
		this.session = session;
	}

	public void saveLocality(Locality locality) {
		session.persist(locality);
	}

	public void updateLocality(Locality locality) {

		session.update(locality);
	}

	@SuppressWarnings("unchecked")
	public List<Locality> getAllLocalities() {
		List<Locality> LocalitiesList = session.createQuery("from Locality").list();
		return LocalitiesList;
	}

	public Locality getLocality(int id) {		
		Locality locality = (Locality) session.load(Locality.class, new Integer(id));
		return locality;
	}

	
	public void deleteLocality(Locality locality) {
		if(null != locality){
			session.delete(locality);
		}
	}

}