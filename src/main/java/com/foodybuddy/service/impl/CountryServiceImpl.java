package com.foodybuddy.service.impl;

import com.foodybuddy.dao.CountryDAO;
import com.foodybuddy.dao.impl.CountryDAOImpl;
import com.foodybuddy.model.Country;
import com.foodybuddy.service.CountryService;
import java.sql.Timestamp;
import java.util.List;
import org.apache.log4j.Logger;
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
	TransactionException TransactionFailureException = new TransactionException(
			"Transaction could not be completed and rollback inititated!");

	/** The Rollback failure exception. */
	TransactionException RollbackFailureException = new TransactionException("Transaction and Rollback Failed!");
	static Logger log = Logger.getLogger(CountryServiceImpl.class.getName());

	/**
	 * Instantiates a new country service impl.
	 *
	 * @param sessionFactory
	 *            the session factory
	 */
	public CountryServiceImpl(SessionFactory sessionFactory) {
		if (sessionFactory == null) {
			log.error("Session Factory is null!");
			throw new RuntimeException("Session Factory is Null");
		}
		this.sessionFactory = sessionFactory;
		log.info("Session Factory successfully assigned!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.CountryService#getById(java.lang.Integer)
	 */
	public Country getById(Integer id) throws Exception {

		Session session = null;
		try {

			if (id == null || id <= 0) {
				if (id == null) {
					log.error("ID is null");
					throw new RuntimeException("ID is null");
				} else {
					log.error("ID is invalid");
					throw new RuntimeException("ID is invalid");
				}
			}

			session = this.sessionFactory.openSession();
			CountryDAO countryDAO = new CountryDAOImpl(session);
			Country country = countryDAO.getById(id);
			log.info("Retreiving country using ID : " + country);
			return country;
		} catch (Exception exception) {
			throw exception;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.CountryService#getAll()
	 */
	public List<Country> getAll() throws Exception {
		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			CountryDAO countryDAO = new CountryDAOImpl(session);
			List<Country> countryList = countryDAO.getAll();
			log.info("Retreived list of countries : " + countryList);
			return countryList;
		} catch (Exception exception) {
			throw exception;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.CountryService#insert(java.lang.String)
	 */
	public Country insert(String name) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {

			if (name == null || name.trim().length() == 0) {

				if (name == null) {
					log.error("Name is null");
					throw new RuntimeException("Name is null");
				}

				else {
					log.error("Name is invalid");
					throw new RuntimeException("Name is invalid");
				}
			}

			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			log.info("Started Transaction.");
			transaction.setTimeout(10);

			Country country = new Country();
			country.setName(name);
			country.setIsActive(true);
			java.util.Date date = new java.util.Date();
			country.setCreatedAt(new Timestamp(date.getTime()));

			CountryDAO countryDAO = new CountryDAOImpl(session);
			countryDAO.insert(country);
			transaction.commit();
			log.info("Committed new Country to database : " + country);
			return country;
		}

		catch (Exception exception) {

			if (transaction != null) {
				transaction.rollback();
				log.error("Transaction Failed and rollbacked!");
				throw TransactionFailureException;
			} else {
				log.error("Transaction and rollback Failed!");
				throw RollbackFailureException;
			}

		}

		finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.CountryService#update(com.foodybuddy.model.
	 * Country)
	 */
	public Country update(Country country) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {

			if (country == null || country.getName().trim().length() == 0) {
				if (country == null) {
					log.error("Country Object is null");
					throw new RuntimeException("Country Object is null");
				} else {
					log.error("Updated Country Name is invalid");
					throw new RuntimeException("Updated Country Name is invalid");
				}
			}

			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			log.info("Started Transaction.");
			transaction.setTimeout(10);

			CountryDAO countryDAO = new CountryDAOImpl(session);
			java.util.Date date = new java.util.Date();
			country.setModifiedAt(new Timestamp(date.getTime()));
			countryDAO.update(country);
			transaction.commit();
			log.info("Committed updated Country to Database : " + country);
			return country;
		}

		catch (Exception exception) {

			if (transaction != null) {
				transaction.rollback();
				log.error("Transaction Failed and rollbacked!");
				throw TransactionFailureException;
			} else {
				log.error("Transaction and rollback Failed!");
				throw RollbackFailureException;
			}

		}

		finally {
			if (session != null) {
				session.close();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.CountryService#delete(com.foodybuddy.model.
	 * Country)
	 */
	public void delete(Country country) throws TransactionException {
		Session session = null;
		Transaction transaction = null;

		try {
			if (country == null) {
				throw new RuntimeException("Country Object is null");
			}
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			log.info("Started Transaction.");
			transaction.setTimeout(10);

			CountryDAO countryDAO = new CountryDAOImpl(session);
			java.util.Date date = new java.util.Date();
			country.setIsActive(false);
			country.setDeletedAt(new Timestamp(date.getTime()));
			countryDAO.update(country);
			log.info("Soft deleted Object " + country);
			transaction.commit();
		}

		catch (Exception exception) {

			if (transaction != null) {
				transaction.rollback();
				log.error("Transaction Failed and rollbacked!");
				throw TransactionFailureException;
			} else {
				log.error("Transaction and rollback Failed!");
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
