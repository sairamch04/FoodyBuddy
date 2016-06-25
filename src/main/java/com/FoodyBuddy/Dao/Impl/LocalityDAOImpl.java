package com.FoodyBuddy.DAO.Impl;

import com.FoodyBuddy.DAO.Locality;
import com.FoodyBuddy.Model.Locality;

import java.util.List;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class LocalityDAOImpl implements LocalityDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(LocalityDAOImpl.class);

	private Session session = null;
	
	public LocalityDAOImpl(Session session){
		this.session = session;
	}

	@Override
	public void insertLocality(Locality locality) {
		session.persist(locality);
		logger.info("Locality saved successfully, Locality Details = " + locality);
	}

	@Override
	public void updateLocality(Locality locality) {

		session.update(locality);
		logger.info("Locality updated successfully, Locality Details = " + locality );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Locality> getAllLocalities() {
		List<Locality> LocalitiesList = session.createQuery("from Locality").list();
		for(Locality locality : LocalitiesList){
			logger.info("Locality List :: " + locality);
		}
		return LocalitiesList;
	}

	@Override
	public Locality getLocality(int id) {		
		Locality locality = (Locality) session.load(Locality.class, new Integer(id));
		logger.info("Locality loaded successfully, Locality details = " + locality);
		return locality;
	}

	@Override
	public void deleteLocality(int id) {
		Locality locality = (Locality) session.load(Locality.class, new Integer(id));
		if(null != locality){
			session.delete(locality);
		}
		logger.info("Locality deleted successfully, Locality details = " + locality);
	}

}