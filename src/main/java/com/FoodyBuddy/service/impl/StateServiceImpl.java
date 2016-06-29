package com.foodybuddy.service.impl;

import com.foodybuddy.dao.CountryDAO;
import com.foodybuddy.dao.StateDAO;
import com.foodybuddy.dao.impl.CountryDAOImpl;
import com.foodybuddy.dao.impl.StateDAOImpl;
import com.foodybuddy.model.Country;
import com.foodybuddy.model.State;
import com.foodybuddy.service.StateService;
import java.util.List;
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

	/**
	 * Instantiates a new state service impl.
	 *
	 * @param sessionFactory
	 *            the session factory
	 */
	public StateServiceImpl(SessionFactory sessionFactory) {
		if (sessionFactory == null) {
			throw new RuntimeException("Session Factory is Null");
		}
		this.sessionFactory = sessionFactory;
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
					throw new RuntimeException("ID is null");
				} else {
					throw new RuntimeException("ID is invalid");
				}
			}

			session = this.sessionFactory.openSession();
			StateDAO stateDAO = new StateDAOImpl(session);
			return stateDAO.getById(id);

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
			if (statesList.size() == 0)
				throw new RuntimeException("Empty Country List");
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
					throw new RuntimeException("Name is null");
				}

				else if (countryId == null) {
					throw new RuntimeException("CountryId is null");
				}

				else if (countryId <= 0) {
					throw new RuntimeException("CountryId is null");
				}

				else {
					throw new RuntimeException("Name is invalid");
				}
			}
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			CountryDAO countryDAO = new CountryDAOImpl(session);
			Country country = countryDAO.getById(countryId);

			if (country == null) {
				throw new RuntimeException("No object with specified Id");
			}
			State state = new State();
			state.setName(name);
			state.setCountry(country);

			StateDAO stateDAO = new StateDAOImpl(session);
			stateDAO.insert(state);
			transaction.commit();
			return state;
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
	 * @see
	 * com.foodybuddy.service.StateService#update(com.foodybuddy.model.State)
	 */
	public State update(State state) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {
			if (state == null || state.getName().trim().length() == 0 || state.getCountry() == null
					|| state.getCountry().getName().trim().length() == 0) {
				if (state == null) {
					throw new RuntimeException("State Object is null");
				}

				else if (state.getName().trim().length() == 0) {
					throw new RuntimeException("State Name is Invalid");
				}

				else if (state.getCountry() == null) {
					throw new RuntimeException("Country Object is null");
				}

				else {
					throw new RuntimeException("State Name is invalid");
				}
			}
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			StateDAO stateDAO = new StateDAOImpl(session);
			stateDAO.update(state);
			transaction.commit();
			return state;
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
			transaction.setTimeout(10);

			StateDAO stateDAO = new StateDAOImpl(session);
			stateDAO.delete(state);
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