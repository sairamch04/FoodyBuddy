package com.foodybuddy.service.impl;

import com.foodybuddy.dao.CityDAO;
import com.foodybuddy.dao.StateDAO;
import com.foodybuddy.dao.impl.CityDAOImpl;
import com.foodybuddy.dao.impl.StateDAOImpl;
import com.foodybuddy.model.City;
import com.foodybuddy.model.State;
import com.foodybuddy.service.CityService;
import java.sql.Timestamp;
import java.util.List;
import org.apache.log4j.Logger;
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
	
	static Logger log = Logger.getLogger(CityServiceImpl.class.getName());

	/**
	 * Instantiates a new city service impl.
	 *
	 * @param sessionFactory
	 *            the session factory
	 */
	public CityServiceImpl(SessionFactory sessionFactory) {
		if (sessionFactory == null) {
			log.error("Session Factory is null!");
			throw ObjectNullException;
		}
		this.sessionFactory = sessionFactory;
		log.info("Session Factory successfully assigned!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.CityService#getById(java.lang.Integer)
	 */
	public City getById(Integer id) throws Exception {

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
			CityDAO cityDAO = new CityDAOImpl(session);
			City city = cityDAO.getById(id);
			log.info("Retreived city using ID : " + city);
			return city;

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
	 * @see com.foodybuddy.service.CityService#getAll()
	 */
	public List<City> getAll() throws Exception {
		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			CityDAO cityDAO = new CityDAOImpl(session);
			List<City> cityList = cityDAO.getAll();
			log.info("Retreived list of cities : " + cityList);
			return cityList;
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
	 * @see com.foodybuddy.service.CityService#insert(java.lang.String,
	 * java.lang.Integer)
	 */
	public City insert(String name, Integer stateId) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {

			if (name == null || stateId == null || stateId <= 0 || name.trim().length() == 0) {

				if (name == null) {
					log.error("Name is null");
					throw new RuntimeException("Name is null");
				}

				else if (stateId == null) {
					log.error("stateId is null");
					throw new RuntimeException("StateId is null");
				}

				else if (stateId <= 0) {
					log.error("stateId is null");
					throw new RuntimeException("StateId is null");
				}

				else {
					log.error("Name is invalid");
					throw new RuntimeException("Name is invalid");
				}
			}
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			StateDAO stateDAO = new StateDAOImpl(session);
			State state = stateDAO.getById(stateId);

			if (state == null) {
				log.error("No object with specified Id");
				throw ObjectNullException;
			}
			log.info("Retrieved state from database :" + state);
			City city = new City();
			city.setName(name);
			city.setState(state);

			CityDAO cityDAO = new CityDAOImpl(session);

			java.util.Date date = new java.util.Date();
			city.setCreatedAt(new Timestamp(date.getTime()));
			city.setIsActive(true);
			cityDAO.insert(city);
			transaction.commit();
			log.info("Committed new city to database : " + city);
			return city;
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
	 * @see com.foodybuddy.service.CityService#update(com.foodybuddy.model.City)
	 */
	public City update(City city) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {
			if (city == null || city.getName().trim().length() == 0 || city.getState() == null
					|| city.getState().getCountry() == null) {

				if (city == null) {
					log.error("City Object is null");
					throw new RuntimeException("City Object is null");
				}

				else if (city.getName().trim().length() == 0) {
					log.error("City Name is Invalid");
					throw new RuntimeException("City Name is Invalid");
				}

				else if (city.getState() == null) {
					log.error("State Object is null");
					throw new RuntimeException("State Object is null");
				}

				else if (city.getState().getCountry() == null) {
					log.error("Country Object is null");
					throw new RuntimeException("Country Object is null");
				}

			}

			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			log.info("Started Transaction.");
			transaction.setTimeout(10);

			CityDAO cityDAO = new CityDAOImpl(session);

			java.util.Date date = new java.util.Date();
			city.setModifiedAt(new Timestamp(date.getTime()));
			cityDAO.update(city);
			transaction.commit();
			log.info("Committed updated City to Database : " + city);
			return city;
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
				System.out.println("Hello");
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

		Session session = null;
		Transaction transaction = null;

		try {
			if (city == null) {
				throw new RuntimeException("Country Object is null");
			}
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			CityDAO cityDAO = new CityDAOImpl(session);

			java.util.Date date = new java.util.Date();
			city.setDeletedAt(new Timestamp(date.getTime()));
			city.setIsActive(false);
			cityDAO.update(city);
			log.info("Soft deleted Object " + city);
			transaction.commit();
		}

		catch (Exception exception) {

			if (transaction != null) {
				transaction.rollback();
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
