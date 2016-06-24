package com.FoodyBuddy.Service.Impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.FoodyBuddy.Dao.SellerDAO;
import com.FoodyBuddy.Dao.Impl.SellerDAOImpl;
import com.FoodyBuddy.Model.Seller;
import com.FoodyBuddy.Service.SellerService;

public class SellerServiceImpl implements SellerService
{
   @Autowired
	SellerDAO sellerDao;
	private SessionFactory sessionFactory;

	public boolean AddSeller(String name, String email, String mobile, String flat_number, int apartmentId) throws Exception 
	{
		boolean isUpdated = true;
		Transaction transaction = null;
		Session session = null;
		try {
			
			SellerDAO sellerDAO = new SellerDAOImpl(sessionFactory);
			// Getting apartment object
			ApartmentDAO apartmentDAO = new ApartmentDAOImpl(sessionFactory);
			Apartment apartment = apartmentDAO.listById(apartmentId);
			// Inserting order to database
			Seller seller = new Seller(name, email, mobile, flat_number, apartment);
			sellerDAO.save(seller);
			transaction.commit();
		  } 
		catch (Exception ex) 
		{
			isUpdated = false;
			try
			{
				transaction.rollback();
				throw new Exception("Transaction could not be completed will be rollbacked: " + ex.getMessage(), ex);
			} 
			catch (RuntimeException rbe) 
			{
				throw new Exception("Transaction could not be completed and rollback failed: " + ex.getMessage(), ex);
			}
		} 
		finally 
		{
			if (session != null) 
			{
				session.close();
			}
		}
		return isUpdated;

	}
	
	public boolean DeleteSeller(Integer id) throws Exception 
	{
		boolean isDeleted = true;
		Transaction transaction = null;
		Session session = null;
		try {
			// Inserting order to database
			SellerDAO sellerDAO = new SellerDAOImpl(sessionFactory);
			sellerDAO.delete(id);
			transaction.commit();
		  } 
		catch (Exception ex) 
		{
			isDeleted = false;
			try
			{
				transaction.rollback();
				throw new Exception("Transaction could not be completed will be rollbacked: " + ex.getMessage(), ex);
			} 
			catch (RuntimeException rbe) 
			{
				throw new Exception("Transaction could not be completed and rollback failed: " + ex.getMessage(), ex);
			}
		} 
		finally 
		{
			if (session != null) 
			{
				session.close();
			}
		}
		return isDeleted;

	}

	
}
