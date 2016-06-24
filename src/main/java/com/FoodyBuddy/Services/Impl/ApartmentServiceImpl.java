package com.FoodyBuddy.Service.Impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import java.lang.RuntimeException;

import com.FoodyBuddy.DAO.ApartmentDAO;
import com.FoodyBuddy.DAO.Impl.ApartmentDAOImpl;
import com.FoodyBuddy.Model.Apartment;

import com.FoodyBuddy.DAO.LocalityDAO;
import com.FoodyBuddy.DAO.Impl.LocalityDAOImpl;
import com.FoodyBuddy.Model.Locality;


@Service("apartmentService")
public class ApartmentServiceIml implements ApartmentService{

	HibernateTransactionException TransactionFailureException = new HibernateTransactionException("Transaction could not be completed and rollback inititated!");
	HibernateTransactionException RollbackFailureException = new HibernateTransactionException("Transaction and Rollback Failed!");

	public Apartment getApartment(int id) {
		Session session = null;

		session = this.SessionFactory.openSession();
		ApartmentDAO apartmentDAO = new ApartmentDAOImpl(session);
		return apartmentDAO.getApartment(id);
	}

	public List<Apartment> getAllApartments(){
		Session session = null;

		session = this.SessionFactory.openSession();

		ApartmentDAO apartmentDAO = new ApartmentDAOImpl(session);
		return apartmentDAO.getAllApartments();
	}

	public void insertApartment(String name, int localityId, int blockNumber) throws TransactionException {
		Session session = null;
		Transaction transaction = null;

		try {
				session = this.SessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				localityDAO = new localityDAOimpl(session);
				Locality locality = LocalityDAO.getLocality(localityId);

				Apartment apartment = new Apartment(name, locality, blockNumber);

				ApartmentDAO apartmentDAO = new ApartmentDAOImpl(session);
				apartmentDAO.insertApartment(apartment);
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

	public void updateApartment(Apartment apartment) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {
				if(apartment == null) {
					throw ObjectNullexception;
				}

				session = this.SessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				ApartmentDAO apartmentDAO = new ApartmentDAOImpl(session);
				apartmentDAO.updateApartment(apartment);
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

	public void deleteApartment(int id) throws TransactionException {
		Session session = null;
		Transaction transaction = null;

		try {
				session = this.SessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				ApartmentDAO apartmentDAO = new ApartmentDAOImpl(session);
				apartmentDAO.deleteApartment(apartment);
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