package com.foodybuddy.service.impl;

import com.foodybuddy.dao.CountryDAO;
import com.foodybuddy.dao.impl.CountryDAOImpl;
import com.foodybuddy.model.Country;
import com.foodybuddy.service.CountryService;
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
	TransactionException TransactionFailureException = new TransactionException(
			"Transaction could not be completed and rollback inititated!");

	/** The Rollback failure exception. */
	TransactionException RollbackFailureException = new TransactionException("Transaction and Rollback Failed!");

	/**
	 * Instantiates a new country service impl.
	 *
	 * @param sessionFactory
	 *            the session factory
	 */
	public CountryServiceImpl(SessionFactory sessionFactory) {
		if (sessionFactory == null) {
			throw new RuntimeException("Session Factory is Null");
		}
		this.sessionFactory = sessionFactory;
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
					throw new RuntimeException("ID is null");
				} else {
					throw new RuntimeException("ID is invalid");
				}
			}

			session = this.sessionFactory.openSession();
			CountryDAO countryDAO = new CountryDAOImpl(session);
			return countryDAO.getById(id);
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
			if (countryList.size() == 0)
				throw new RuntimeException("Empty Country List");
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
					throw new RuntimeException("Name is null");
				}

				else {
					throw new RuntimeException("Name is invalid");
				}
			}

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
				if (transaction != null)
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
					throw new RuntimeException("Country Object is null");
				} else {
					throw new RuntimeException("Updated Country Name is invalid");
				}
			}

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
				if (transaction != null)
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
			transaction.setTimeout(10);

			CountryDAO countryDAO = new CountryDAOImpl(session);
			countryDAO.delete(country);
			transaction.commit();
		}

		catch (Exception exception) {
			try {
				if (transaction != null)
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