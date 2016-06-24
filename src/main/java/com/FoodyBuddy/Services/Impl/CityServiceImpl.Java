package com.FoodyBuddy.Service.Impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import java.lang.RuntimeException;

import com.FoodyBuddy.DAO.CityDAO;
import com.FoodyBuddy.DAO.Impl.CityDAOImpl;
import com.FoodyBuddy.Model.City;

import com.FoodyBuddy.DAO.StateDAO;
import com.FoodyBuddy.DAO.Impl.StateDAOImpl;
import com.FoodyBuddy.Model.State;


@Service("cityService")
public class CityServiceIml implements CityService{

	HibernateTransactionException TransactionFailureException = new HibernateTransactionException("Transaction could not be completed and rollback inititated!");
	HibernateTransactionException RollbackFailureException = new HibernateTransactionException("Transaction and Rollback Failed!");

	public City getCity(int id) {
		Session session = null;

		session = this.SessionFactory.openSession();
		CityDAO cityDAO = new CityDAOImpl(session);
		return cityDAO.getCity(id);
	}

	public List<City> getAllCitys(){
		Session session = null;

		session = this.SessionFactory.openSession();

		CityDAO cityDAO = new CityDAOImpl(session);
		return cityDAO.getAllCitys();
	}

	public void insertCity(String name, int stateId) throws TransactionException {
		Session session = null;
		Transaction transaction = null;

		try {
				session = this.SessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				stateDAO = new stateDAOimpl(session);
				State state = StateDAO.getState(stateId);

				City city = new City(name, state);

				CityDAO cityDAO = new CityDAOImpl(session);
				cityDAO.insertCity(buyer);
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

	public void updateCity(City city) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {
				if(city == null) {
					throw ObjectNullexception;
				}

				session = this.SessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				CityDAO cityDAO = new CityDAOImpl(session);
				cityDAO.updateCity(city);
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

	public void deleteCity(int id) throws TransactionException {
		Session session = null;
		Transaction transaction = null;

		try {
				session = this.SessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				CityDAO cityDAO = new CityDAOImpl(session);
				cityDAO.deleteCity(city);
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