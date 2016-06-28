package com.foodybuddy.service;

import com.foodybuddy.dao.CountryDAO;
import com.foodybuddy.dao.StateDAO;
import com.foodybuddy.dao.impl.CountryDAOImpl;
import com.foodybuddy.dao.impl.StateDAOImpl;
import com.foodybuddy.model.Country;
import com.foodybuddy.model.State;
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
public class StateServiceImpl implements StateService{

	/** The Transaction failure exception. */
	TransactionException TransactionFailureException = new TransactionException("Transaction could not be completed and rollback inititated!");
	
	/** The Rollback failure exception. */
	TransactionException RollbackFailureException = new TransactionException("Transaction and Rollback Failed!");
	
	/** The Object null exception. */
	RuntimeException ObjectNullException = new RuntimeException("Object is null");

	/** The session factory. */
	private SessionFactory sessionFactory;
	
	/**
	 * Instantiates a new state service impl.
	 *
	 * @param sessionFactory the session factory
	 */
	public StateServiceImpl(SessionFactory sessionFactory){
		if(sessionFactory == null){
			throw ObjectNullException;
		}
		this.sessionFactory = sessionFactory;
	}
	
	/* (non-Javadoc)
	 * @see com.foodybuddy.service.StateService#getById(java.lang.Integer)
	 */
	public State getById(Integer id) throws Exception{
		if (id == null || id <= 0) {
			throw ObjectNullException;
		}
		
			Session session = null;
			session = this.sessionFactory.openSession();
			StateDAO stateDAO = new StateDAOImpl(session);
			return stateDAO.getById(id);
		
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.service.StateService#getAll()
	 */
	public List<State> getAll() throws Exception{
		Session session = null;

		session = this.sessionFactory.openSession();

		StateDAO stateDAO = new StateDAOImpl(session);
		return stateDAO.getAll();
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.service.StateService#insert(java.lang.String, java.lang.Integer)
	 */
	public State insert(String name, Integer countryId) throws TransactionException {
		
		if(name == null || countryId == null || countryId <= 0 || name.trim().length() == 0){
			throw ObjectNullException;
		}
		
		Session session = null;
		Transaction transaction = null;

		try {
				session = this.sessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				CountryDAO countryDAO = new CountryDAOImpl(session);
				Country country = countryDAO.getById(countryId);

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

	/* (non-Javadoc)
	 * @see com.foodybuddy.service.StateService#update(com.foodybuddy.model.State)
	 */
	public State update(State state) throws TransactionException {

		if(state == null || state.getName().trim().length() == 0 || state.getCountry() == null || state.getCountry().getName().trim().length() == 0) {
			throw ObjectNullException;
		}

		Session session = null;
		Transaction transaction = null;

		try {
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

	/* (non-Javadoc)
	 * @see com.foodybuddy.service.StateService#delete(com.foodybuddy.model.State)
	 */
	public void delete(State state) throws TransactionException {
		if(state == null){
			throw ObjectNullException;
		}
		
		Session session = null;
		Transaction transaction = null;

		try {
				session = this.sessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				StateDAO stateDAO = new StateDAOImpl(session);
				stateDAO.delete(state);
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