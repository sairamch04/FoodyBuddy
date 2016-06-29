package com.foodybuddy.service.impl;

import com.foodybuddy.dao.CityDAO;
import com.foodybuddy.dao.StateDAO;
import com.foodybuddy.dao.impl.CityDAOImpl;
import com.foodybuddy.dao.impl.StateDAOImpl;
import com.foodybuddy.model.City;
import com.foodybuddy.model.State;
import com.foodybuddy.service.CityService;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

/**
 * The Class CityServiceImpl.
 */
@Service("cityService")
public class CityServiceImpl implements CityService {

	/** The Transaction failure exception. */
	TransactionException TransactionFailureException = new TransactionException(
			"Transaction could not be completed and rollback inititated!");

	/** The Rollback failure exception. */
	TransactionException RollbackFailureException = new TransactionException("Transaction and Rollback Failed!");

	/** The Object null exception. */
	RuntimeException ObjectNullException = new RuntimeException("Object is null");

	/** The session factory. */
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a new city service impl.
	 *
	 * @param sessionFactory
	 *            the session factory
	 */
	public CityServiceImpl(SessionFactory sessionFactory) {
		if (sessionFactory == null) {
			throw ObjectNullException;
		}
		this.sessionFactory = sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.CityService#getById(java.lang.Integer)
	 */
	public City getById(Integer id) {

		if (id == null || id <= 0) {
			throw ObjectNullException;
		}

		Session session = null;

		session = this.sessionFactory.openSession();
		CityDAO cityDAO = new CityDAOImpl(session);
		return cityDAO.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.CityService#getAll()
	 */
	public List<City> getAll() {
		Session session = null;
		session = this.sessionFactory.openSession();
		CityDAO cityDAO = new CityDAOImpl(session);
		return cityDAO.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.CityService#insert(java.lang.String,
	 * java.lang.Integer)
	 */
	public City insert(String name, Integer stateId) throws TransactionException {

		if (name == null || stateId == null || stateId <= 0 || name.trim().length() == 0) {
			throw ObjectNullException;
		}

		Session session = null;
		Transaction transaction = null;

		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			StateDAO stateDAO = new StateDAOImpl(session);
			State state = stateDAO.getById(stateId);

			if (state == null) {
				throw ObjectNullException;
			}

			City city = new City();
			city.setName(name);
			city.setState(state);

			CityDAO cityDAO = new CityDAOImpl(session);
			cityDAO.insert(city);
			transaction.commit();
			return city;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.CityService#update(com.foodybuddy.model.City)
	 */
	public City update(City city) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {
			if (city == null || city.getName().trim().length() == 0 || city.getState() == null) {
				throw ObjectNullException;
			}

			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			CityDAO cityDAO = new CityDAOImpl(session);
			cityDAO.update(city);
			transaction.commit();
			return city;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.CityService#delete(com.foodybuddy.model.City)
	 */
	public void delete(City city) throws TransactionException {
		if (city == null) {
			throw ObjectNullException;
		}

		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			CityDAO cityDAO = new CityDAOImpl(session);
			cityDAO.delete(city);
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