package com.FoodyBuddy.Service.Impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import java.lang.RuntimeException;

import com.FoodyBuddy.DAO.CountryDAO;
import com.FoodyBuddy.DAO.Impl.CountryDAOImpl;
import com.FoodyBuddy.Model.Country;



@Service("countryService")
public class CountryServiceIml implements CountryService{

	
	private CountryDAO countryDAO;

	HibernateTransactionException TransactionFailureException = new HibernateTransactionException("Transaction could not be completed and rollback inititated!");
	HibernateTransactionException RollbackFailureException = new HibernateTransactionException("Transaction and Rollback Failed!");

	public Country getCountry(int id) {
		Session session = null;

		session = this.SessionFactory.openSession();
		CountryDAO countryDAO = new CountryDAOImpl(session);
		return countryDAO.getCountry(id);
	}

	public List<Country> getAllCountries(){
		Session session = null;

		session = this.SessionFactory.openSession();

		CountryDAO countryDAO = new CountryDAOImpl(session);
		return countryDAO.getAllCountrys();
	}

	public void insertCountry(String name) throws TransactionException {
		Session session = null;
		Transaction transaction = null;

		try {
				session = this.SessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);


				Country country = new Country(name);

				CountryDAO countryDAO = new CountryDAOImpl(session);
				countryDAO.insertCountry(country);
				transaction.commit();
		}
		
		catch (Exception exception) {
			try {
				transaction.rollback();
				throw TransactionFailureException;
			}
			
			catch (RuntimeException runtimeException) {
				throw RollbackFailureException;
			}
		}

		finally{
			if(session != null){
				session.close();
			}
		}
	}

	public void updateCountry(Country country) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {
				if(country == null) {
					throw ObjectNullexception;
				}

				session = this.SessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				CountryDAO countryDAO = new CountryDAOImpl(session);
				countryDAO.updateCountry(country);
				transaction.commit();
		}
		
		catch (Exception exception) {
			try {
				transaction.rollback();
				throw TransactionFailureException;
			}
			
			catch (RuntimeException runtimeException) {
				throw RollbackFailureException;
			}
		}

		finally{
			if(session != null){
				session.close();
			}
		}
		
	}

	public void deleteCountry(int id) throws TransactionException {
		Session session = null;
		Transaction transaction = null;

		try {
				if(country == null) {
					throw ObjectNullexception;
				}

				session = this.SessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				CountryDAO countryDAO = new CountryDAOImpl(session);
				countryDAO.deleteCountry(country);
				transaction.commit();
		}
		
		catch (Exception exception) {
			try {
				transaction.rollback();
				throw TransactionFailureException;
			}
			
			catch (RuntimeException runtimeException) {
				throw RollbackFailureException;
			}
		}

		finally{
			if(session != null){
				session.close();
			}
		}
	}


}