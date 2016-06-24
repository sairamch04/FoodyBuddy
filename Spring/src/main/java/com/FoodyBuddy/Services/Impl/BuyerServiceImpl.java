package com.FoodyBuddy.Service.Impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import java.lang.RuntimeException;

import com.FoodyBuddy.DAO.BuyerDAO;
import com.FoodyBuddy.DAO.Impl.BuyerDAOImpl;
import com.FoodyBuddy.Model.Buyer;

import com.FoodyBuddy.DAO.ApartmentDAO;
import com.FoodyBuddy.DAO.Impl.ApartmentDAOImpl;
import com.FoodyBuddy.Model.Apartment;


@Service("buyerService")
public class BuyerServiceIml implements BuyerService{

	HibernateTransactionException TransactionFailureException = new HibernateTransactionException("Transaction could not be completed and rollback inititated!");
	HibernateTransactionException RollbackFailureException = new HibernateTransactionException("Transaction and Rollback Failed!");

	public Buyer getBuyer(int id) {
		Session session = null;

		session = this.SessionFactory.openSession();
		BuyerDAO buyerDAO = new BuyerDAOImpl(session);
		return buyerDAO.getBuyer(id);
	}

	public List<Buyer> getAllBuyers(){
		Session session = null;

		session = this.SessionFactory.openSession();

		BuyerDAO buyerDAO = new BuyerDAOImpl(session);
		return buyerDAO.getAllBuyers();
	}

	public void insertBuyer(int apartmentId, String name, String mobileNumber, String email, String flatNumber, Date createdAt, Date updatedAt, Date deletedAt, Boolean isActive) throws TransactionException {
		Session session = null;
		Transaction transaction = null;

		try {
				session = this.SessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				ApartmentDAO apartmentDAO = new ApartmentDAOimpl(session);
				Apartment apartment = apartmentDAO.getApartment(apartmentId);

				Buyer buyer = new Buyer(apartment, lastModifiedById, name, mobileNumber, email, flatNumber, createdAt, updatedAt, deletedAt, isActive);

				BuyerDAO buyerDAO = new BuyerDAOImpl(session);
				buyerDAO.insertBuyer(buyer);
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

	public void updateBuyer(Buyer buyer) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {
				if(buyer == null) {
					throw ObjectNullexception;
				}

				session = this.SessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				BuyerDAO buyerDAO = new BuyerDAOImpl(session);
				buyerDAO.updateBuyer(buyer);
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

	public void deleteBuyer(int id) throws TransactionException {
		Session session = null;
		Transaction transaction = null;

		try {
				if(buyer == null) {
					throw ObjectNullexception;
				}

				session = this.SessionFactory.openSession();
				transaction = session.beginTransaction();
				transaction.setTimeout(10);

				BuyerDAO buyerDAO = new BuyerDAOImpl(session);
				buyerDAO.deleteBuyer(buyer);
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