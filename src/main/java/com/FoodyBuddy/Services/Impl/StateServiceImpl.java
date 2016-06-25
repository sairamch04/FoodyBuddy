package com.FoodyBuddy.Service.Impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import java.lang.RuntimeException;

import com.FoodyBuddy.DAO.StateDAO;
import com.FoodyBuddy.DAO.Impl.StateDAOImpl;
import com.FoodyBuddy.Model.State;

import com.FoodyBuddy.DAO.CountryDAO;
import com.FoodyBuddy.DAO.Impl.CountryDAOImpl;
import com.FoodyBuddy.Model.Country;


@Service("stateService")
public class StateServiceIml implements StateService{

	HibernateTransactionException TransactionFailureException = new HibernateTransactionException("Transaction could not be completed and rollback inititated!");
	HibernateTransactionException RollbackFailureException = new HibernateTransactionException("Transaction and Rollback Failed!");

	public State getState(int id) {
		Session session = null;

		session = this.SessionFactory.openSession();
		StateDAO stateDAO = new StateDAOImpl(session);
		return stateDAO.getState(id);
	}

	public List<State> getAllStates(){
		Session session = null;

		session = this.SessionFactory.openSession();

		StateDAO stateDAO = new StateDAOImpl(session);
		return stateDAO.getAllStates();
	}

	public void insertState(String name, int countryId) throws TransactionException {
		Session session = null;
		Transaction transaction = null;

		try {
				session = this.SessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				countryDAO = new countryDAOimpl(session);
				Country country = CountryDAO.getCountry(countryId);

				State state = new State(name, country);

				StateDAO stateDAO = new StateDAOImpl(session);
				stateDAO.insertState(buyer);
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

	public void updateState(State state) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {
				if(state == null) {
					throw ObjectNullexception;
				}

				session = this.SessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				StateDAO stateDAO = new StateDAOImpl(session);
				stateDAO.updateState(state);
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

	public void deleteState(int id) throws TransactionException {
		Session session = null;
		Transaction transaction = null;

		try {
				session = this.SessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				StateDAO stateDAO = new StateDAOImpl(session);
				stateDAO.deleteState(state);
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