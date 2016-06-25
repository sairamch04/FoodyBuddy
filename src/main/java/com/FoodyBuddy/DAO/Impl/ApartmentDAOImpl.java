package com.FoodyBuddy.DAO.Impl;

import com.FoodyBuddy.DAO.Apartment;
import com.FoodyBuddy.Model.Apartment;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class ApartmentDAOImpl implements ApartmentDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ApartmentDAOImpl.class);

	private Session session = null;
	
	public ApartmentDAOImpl(Session session){
		this.session = session;
	}

	@Override
	public void insertApartment(Apartment apartment) {
		session.persist(apartment);
		logger.info("Apartment saved successfully, Apartment Details = " + apartment);
	}

	@Override
	public void updateApartment(Apartment apartment) {

		session.update(apartment);
		logger.info("Apartment updated successfully, Apartment Details = " + apartment );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Apartment> getAllApartments() {
		List<Apartment> ApartmentsList = session.createQuery("from Apartment").list();
		for(Apartment apartment : ApartmentsList){
			logger.info("Apartment List :: " + apartment);
		}
		return ApartmentsList;
	}

	@Override
	public Apartment getApartment(int id) {		
		Apartment apartment = (Apartment) session.load(Apartment.class, new Integer(id));
		logger.info("Apartment loaded successfully, Apartment details = " + apartment);
		return apartment;
	}

	@Override
	public void deleteApartment(int id) {
		Apartment apartment = (Apartment) session.load(Apartment.class, new Integer(id));
		if(null != apartment){
			session.delete(apartment);
		}
		logger.info("Apartment deleted successfully, Apartment details = " + apartment);
	}

}