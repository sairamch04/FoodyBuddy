package com.FoodyBuddy.Service.Impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import java.lang.RuntimeException;

import com.FoodyBuddy.DAO.LocalityDAO;
import com.FoodyBuddy.DAO.Impl.LocalityDAOImpl;
import com.FoodyBuddy.Model.Locality;

import com.FoodyBuddy.DAO.CityDAO;
import com.FoodyBuddy.DAO.Impl.CityDAOImpl;
import com.FoodyBuddy.Model.City;


@Service("localityService")
public class LocalityServiceIml implements LocalityService{

	HibernateTransactionException TransactionFailureException = new HibernateTransactionException("Transaction could not be completed and rollback inititated!");
	HibernateTransactionException RollbackFailureException = new HibernateTransactionException("Transaction and Rollback Failed!");

	public Locality getLocality(int id) {
		Session session = null;

		session = this.SessionFactory.openSession();
		LocalityDAO localityDAO = new LocalityDAOImpl(session);
		return localityDAO.getLocality(id);
	}

	public List<Locality> getAllLocalities(){
		Session session = null;

		session = this.SessionFactory.openSession();

		LocalityDAO localityDAO = new LocalityDAOImpl(session);
		return localityDAO.getAllLocalitys();
	}

	public void insertLocality(String name, int cityId) throws TransactionException {
		Session session = null;
		Transaction transaction = null;

		try {
				session = this.SessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				cityDAO = new cityDAOimpl(session);
				City city = CityDAO.getCity(cityId);

				Locality locality = new Locality(name, city);

				LocalityDAO localityDAO = new LocalityDAOImpl(session);
				localityDAO.insertLocality(locality);
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

	public void updateLocality(Locality locality) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {
				if(locality == null) {
					throw ObjectNullexception;
				}

				session = this.SessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				LocalityDAO localityDAO = new LocalityDAOImpl(session);
				localityDAO.updateLocality(locality);
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

	public void deleteLocality(int id) throws TransactionException {
		Session session = null;
		Transaction transaction = null;

		try {
				session = this.SessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				LocalityDAO localityDAO = new LocalityDAOImpl(session);
				localityDAO.deleteLocality(locality);
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