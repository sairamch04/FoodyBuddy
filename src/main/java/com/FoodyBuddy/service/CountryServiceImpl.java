package com.foodybuddy.service;

import com.foodybuddy.dao.CountryDAO;
import com.foodybuddy.dao.impl.CountryDAOImpl;
import com.foodybuddy.model.Country;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

/**
 * The Class CountryServiceImpl.
 */
@Service("countryService")
public class CountryServiceImpl implements CountryService {

	/** The session factory. */
	private SessionFactory sessionFactory;

	/** The Transaction failure exception. */
	TransactionException TransactionFailureException = new TransactionException("Transaction could not be completed and rollback inititated!");
	
	/** The Rollback failure exception. */
	TransactionException RollbackFailureException = new TransactionException("Transaction and Rollback Failed!");
	
	/** The Object null exception. */
	RuntimeException ObjectNullException = new RuntimeException("Object is null");


	/**
	 * Instantiates a new country service impl.
	 *
	 * @param sessionFactory the session factory
	 */
	public CountryServiceImpl(SessionFactory sessionFactory) {
		if (sessionFactory == null) {
			throw ObjectNullException;
		}
		this.sessionFactory = sessionFactory;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.service.CountryService#getById(java.lang.Integer)
	 */
	public Country getById(Integer id) throws Exception{
		if(id == null || id <= 0){
			throw ObjectNullException;
		}
		
		Session session = null;

		try{
			session = this.sessionFactory.openSession();
			CountryDAO countryDAO = new CountryDAOImpl(session);
			return countryDAO.getById(id);
		}
		catch(Exception exception){
			throw exception;
		}
		finally{
			if(session != null){
				session.close();
			}
		}	
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.service.CountryService#getAll()
	 */
	public List<Country> getAll() throws Exception{
		Session session = null;
		try{
			session = this.sessionFactory.openSession();
			CountryDAO countryDAO = new CountryDAOImpl(session);
			return countryDAO.getAll();
		}
		catch(Exception exception){
			throw exception;
		}
		finally{
			if(session != null){
				session.close();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.service.CountryService#insert(java.lang.String)
	 */
	public Country insert(String name) throws TransactionException {

		if (name == null || name.trim().length() == 0 ) {
			throw ObjectNullException;
		}
		Session session = null;
		Transaction transaction = null;

		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			Country country = new Country();
			country.setName(name);

			CountryDAO countryDAO = new CountryDAOImpl(session);
			countryDAO.insert(country);
			transaction.commit();
			return country;
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

		finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.service.CountryService#update(com.foodybuddy.model.Country)
	 */
	public Country update(Country country) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		if (country == null || country.getName().trim().length() == 0) {
			throw ObjectNullException;
		}

		try {

			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			CountryDAO countryDAO = new CountryDAOImpl(session);
			countryDAO.update(country);
			transaction.commit();
			return country;
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

		finally {
			if (session != null) {
				session.close();
			}
		}

	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.service.CountryService#delete(com.foodybuddy.model.Country)
	 */
	public void delete(Country country) throws TransactionException {
		Session session = null;
		Transaction transaction = null;

		if (country == null) {
			throw ObjectNullException;
		}

		try {

			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			CountryDAO countryDAO = new CountryDAOImpl(session);
			countryDAO.delete(country);
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

		finally {
			if (session != null) {
				session.close();
			}
		}
	}

}