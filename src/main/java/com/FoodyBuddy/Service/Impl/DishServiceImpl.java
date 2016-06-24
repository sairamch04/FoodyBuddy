package com.FoodyBuddy.Service.Impl;


import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.FoodyBuddy.Dao.DishDAO;
import com.FoodyBuddy.Dao.SellerDAO;
import com.FoodyBuddy.Dao.Impl.DishDAOImpl;
import com.FoodyBuddy.Dao.Impl.SellerDAOImpl;
import com.FoodyBuddy.Model.Dish;
import com.FoodyBuddy.Model.Seller;
import com.FoodyBuddy.Service.DishService;

public class DishServiceImpl implements DishService
{
   @Autowired
	DishDAO DishDao;
	private SessionFactory sessionFactory;

	@SuppressWarnings("null")
	public boolean AddDish(String name, String description, Integer sellerId, String price, Date orderBy, Date dishAvailableStart,
			Date dishAvailableEnd, Boolean isVeg, int quantityAvailable) throws Exception 
	{
		boolean isUpdated = true;
		Transaction transaction = null;
		Session session = null;
		try {
			
			DishDAO DishDAO = new DishDAOImpl(sessionFactory);
			// Getting seller object
			SellerDAO sellerDAO = new SellerDAOImpl(sessionFactory);
			Seller seller = sellerDAO.listBySellerId(sellerId);
			// Inserting order to database
			Dish Dish = new Dish(name, description, seller, price, orderBy, dishAvailableStart, dishAvailableEnd, isVeg, quantityAvailable);
			DishDAO.save(Dish);
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
	
	@SuppressWarnings("null")
	public boolean DeleteDish(Integer id) throws Exception 
	{
		boolean isDeleted = true;
		Transaction transaction = null;
		Session session = null;
		try {
			// Inserting order to database
			DishDAO DishDAO = new DishDAOImpl(sessionFactory);
			DishDAO.delete(id);
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
