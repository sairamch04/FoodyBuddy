package com.FoodyBuddy.Service.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.FoodyBuddy.Dao.CountryDao;
import com.FoodyBuddy.Dao.Impl.CountryDaoImpl;
import com.FoodyBuddy.Model.Country;
import com.FoodyBuddy.Service.CountryService;

public class CountryServiceImpl implements CountryService {
	
	private CountryDao countryDao;
	private SessionFactory sessionFactory;
	
	public void saveCountry(Country country) throws Exception {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			if(country == null) {
				throw new Exception("Country Object is null");
			}
		
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);
		
			countryDao = new CountryDaoImpl(session);
			countryDao.saveCountry(country);
			transaction.commit();
		}
		catch(Exception e) {
			try {
				transaction.rollback();
				throw new Exception("Transaction could not be completed will be rollbacked" + e.getMessage());
			}
			catch(RuntimeException ex) {
				throw new Exception("Transaction could not be completed and rollback failed" + ex.getMessage());
			}
		}
		
	}
	
	public void updateCountry(Country country) throws Exception {
		
		Session session = null;
		Transaction transaction = null;
		try {
			if(country == null) {
				throw new Exception("Country Object is null");
			}
		
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);
		
			countryDao = new CountryDaoImpl(session);
			countryDao.updateCountry(country);
			transaction.commit();
		}
		catch(Exception e) {
			try {
				transaction.rollback();
				throw new Exception("Transaction could not be completed will be rollbacked" + e.getMessage());
			}
			catch(RuntimeException ex) {
				throw new Exception("Transaction could not be completed and rollback failed" + ex.getMessage());
			}
		}
	

	}
	
	public void deleteCountry(Country country) throws Exception {
		Session session = null;
		Transaction transaction = null;
		try {
			if(country == null) {
				throw new Exception("Country Object is null");
			}
		
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);
		
			countryDao = new CountryDaoImpl(session);
			countryDao.deleteCountry(country);
			transaction.commit();
		}
		catch(Exception e) {
			try {
				transaction.rollback();
				throw new Exception("Transaction could not be completed will be rollbacked" + e.getMessage());
			}
			catch(RuntimeException ex) {
				throw new Exception("Transaction could not be completed and rollback failed" + ex.getMessage());
			}
		}
	}
	
	public Country getCountry(int id) {
		 Session session = null;
		 session = this.sessionFactory.openSession();
		 countryDao = new CountryDaoImpl(session);
		 Country country = countryDao.getCountry(id);
		 
		 return country;
	}
	
	public List<Country> getAllCountries() {
		Session session = null;
		session = this.sessionFactory.openSession();
		countryDao = new CountryDaoImpl(session);
		List<Country> allCountries = countryDao.getAllCountries();
		 
		return allCountries;
	}
}
