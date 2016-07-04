package com.foodybuddy.service.impl;

import com.foodybuddy.dao.CountryDAO;
import com.foodybuddy.dao.StateDAO;
import com.foodybuddy.dao.impl.CountryDAOImpl;
import com.foodybuddy.dao.impl.StateDAOImpl;
import com.foodybuddy.model.Country;
import com.foodybuddy.model.State;
import com.foodybuddy.service.StateService;
import java.sql.Timestamp;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

/**
 * The Class StateServiceImpl.
 */
@Service("stateService")
public class StateServiceImpl implements StateService {

	/** The Transaction failure exception. */
	TransactionException TransactionFailureException = new TransactionException(
			"Transaction could not be completed and rollback inititated!");

	/** The Rollback failure exception. */
	TransactionException RollbackFailureException = new TransactionException("Transaction and Rollback Failed!");

	/** The session factory. */
	private SessionFactory sessionFactory;
	static Logger log = Logger.getLogger(StateServiceImpl.class.getName());

	/**
	 * Instantiates a new state service impl.
	 *
	 * @param sessionFactory
	 *            the session factory
	 */
	public StateServiceImpl(SessionFactory sessionFactory) {
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
	 * @see com.foodybuddy.service.StateService#getById(java.lang.Integer)
	 */
	public State getById(Integer id) throws Exception {

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
			StateDAO stateDAO = new StateDAOImpl(session);
			State state = stateDAO.getById(id);
			log.info("Retreived state using ID : " + state);
			return state;

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
	 * @see com.foodybuddy.service.StateService#getAll()
	 */
	public List<State> getAll() throws Exception {

		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			StateDAO stateDAO = new StateDAOImpl(session);
			List<State> statesList = stateDAO.getAll();
			log.info("Retreived list of states : " + statesList);
			return statesList;
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
	 * @see com.foodybuddy.service.StateService#insert(java.lang.String,
	 * java.lang.Integer)
	 */
	public State insert(String name, Integer countryId) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {

			if (name == null || countryId == null || countryId <= 0 || name.trim().length() == 0) {

				if (name == null) {
					log.error("Name is null");
					throw new RuntimeException("Name is null");
				}

				else if (countryId == null) {
					log.error("CountryId is null");
					throw new RuntimeException("CountryId is null");
				}

				else if (countryId <= 0) {
					log.error("CountryId is null");
					throw new RuntimeException("CountryId is null");
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

			CountryDAO countryDAO = new CountryDAOImpl(session);
			Country country = countryDAO.getById(countryId);

			if (country == null) {
				log.error("No object with specified Id");
				throw new RuntimeException("No object with specified Id");
			}
			
			log.info("Retrieved Country from database :" + country);
			State state = new State();
			state.setName(name);
			state.setCountry(country);

			StateDAO stateDAO = new StateDAOImpl(session);
			java.util.Date date= new java.util.Date();
			state.setCreatedAt(new Timestamp(date.getTime()));
			state.setIsActive(true);
			stateDAO.insert(state);
			transaction.commit();
			log.info("Committed new state to database : " + state);
			return state;
		}

		catch (Exception exception) {

			if (transaction != null){
				transaction.rollback();
				log.error("Transaction Failed and rollbacked!");
				throw TransactionFailureException;
			}
			else{
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
	 * @see
	 * com.foodybuddy.service.StateService#update(com.foodybuddy.model.State)
	 */
	public State update(State state) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {
			if (state == null || state.getName().trim().length() == 0 || state.getCountry() == null) {
				if (state == null) {
					log.error("State Object is null");
					throw new RuntimeException("State Object is null");
				}

				else if (state.getName().trim().length() == 0) {
					log.error("State Name is Invalid");
					throw new RuntimeException("State Name is Invalid");
				}

				else if (state.getCountry() == null) {
					log.error("Country Object is null");
					throw new RuntimeException("Country Object is null");
				}
			}
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			log.info("Started Transaction.");
			transaction.setTimeout(10);

			StateDAO stateDAO = new StateDAOImpl(session);
			java.util.Date date= new java.util.Date();
			state.setModifiedAt(new Timestamp(date.getTime()));
			stateDAO.update(state);
			transaction.commit();
			log.info("Committed updated State to Database : " + state);
			return state;
		}

		catch (Exception exception) {

			if (transaction != null){
				transaction.rollback();
				log.error("Transaction Failed and rollbacked!");
				throw TransactionFailureException;
			}
			else{
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
	 * @see
	 * com.foodybuddy.service.StateService#delete(com.foodybuddy.model.State)
	 */
	public void delete(State state) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {
			if (state == null) {
				throw new RuntimeException("Country Object is null");
			}
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			log.info("Started Transaction.");
			transaction.setTimeout(10);

			StateDAO stateDAO = new StateDAOImpl(session);
			java.util.Date date= new java.util.Date();
			state.setDeletedAt(new Timestamp(date.getTime()));
			state.setIsActive(false);
			stateDAO.update(state);
			log.info("Soft deleted Object " + state);
			transaction.commit();
		}

		catch (Exception exception) {

			if (transaction != null){
				transaction.rollback();
				log.error("Transaction Failed and rollbacked!");
				throw TransactionFailureException;
			}
			else{
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
