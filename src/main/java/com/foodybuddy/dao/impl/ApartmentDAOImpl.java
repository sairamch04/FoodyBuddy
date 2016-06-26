package com.FoodyBuddy.Dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.FoodyBuddy.Dao.ApartmentDao;
import com.FoodyBuddy.Model.Apartment;


@Repository
public class ApartmentDaoImpl implements ApartmentDao {
	
	private Session session = null;
	
	public ApartmentDaoImpl(Session session){
		this.session = session;
	}

	public void saveApartment(Apartment apartment) {
		session.persist(apartment);
	}

	public void updateApartment(Apartment apartment) {
		session.update(apartment);
	}

	@SuppressWarnings("unchecked")
	public List<Apartment> getAllApartments() {
		List<Apartment> ApartmentsList = session.createQuery("from Apartment").list();
		return ApartmentsList;
	}

	public Apartment getApartment(int id) {		
		Apartment apartment = (Apartment) session.load(Apartment.class, new Integer(id));
		return apartment;
	}

	public void deleteApartment(int id) {
		Apartment apartment = (Apartment) session.load(Apartment.class, new Integer(id));
		if(null != apartment){
			session.delete(apartment);
		}
	}

}