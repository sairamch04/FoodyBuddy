package com.foodybuddy.service.impl;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.foodybuddy.dao.SellerDAO;
import com.foodybuddy.dao.DishDAO;
import com.foodybuddy.dao.impl.SellerDAOImpl;
import com.foodybuddy.dao.impl.DishDAOImpl;
import com.foodybuddy.model.Seller;
import com.foodybuddy.model.Dish;
import com.foodybuddy.model.Seller;
import com.foodybuddy.service.DishService;
// TODO: Auto-generated Javadoc

/**
 * The Class DishServiceImpl.
 */
@Service("DishService")
public class DishServiceImpl implements DishService {

	/** The session factory. */
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a new Dish service impl.
	 *
	 * @param sessionFactory
	 *            the session factory
	 */
	public DishServiceImpl(SessionFactory sessionFactory) throws NullPointerException {
		if (sessionFactory == null) {
			throw new NullPointerException("Session is null");
		}
		this.sessionFactory = sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.DishService#AddDish(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, int, boolean)
	 */
	public Dish addDish(String name, String description, Integer sellerId, Integer price, Date orderBy, Date dishAvailableStart,
			Date dishAvailableEnd, Boolean isVeg, Boolean isActive, Integer quantityAvailable) throws Exception {
		Session session = null;
		Transaction transaction = null;
		Dish newDish = null;
		try {
		if(name==null || description==null || sellerId<1 || orderBy==null || price<0 || dishAvailableStart == null ||
				 dishAvailableEnd== null || isVeg== null || isActive== null || quantityAvailable== null ) throw new Exception("Invalid Input");
		
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);
			DishDAO DishDAO = new DishDAOImpl(session);
			// Getting Seller Dishect
			SellerDAO SellerDAO = new SellerDAOImpl(session);
			Seller Seller = SellerDAO.getBySellerId(sellerId);
			// Inserting Dish to database
			Dish Dish = new Dish(name ,description, Seller, price,orderBy, dishAvailableStart,
				dishAvailableEnd, isVeg, isActive, quantityAvailable);
			newDish = DishDAO.insert(Dish);
			transaction.commit();
		} catch (Exception ex) {
			try {
				if (transaction == null) {
                    throw new Exception("Transaction could not be completed: " + ex.getMessage(), ex);
				}
				transaction.rollback();
				throw new Exception("Transaction could not be completed will be rollbacked: " + ex.getMessage(), ex);
			} catch (RuntimeException rbe) {
				throw new Exception("Transaction could not be completed and rollback failed: " + ex.getMessage(), ex);
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return newDish;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.foodybuddy.service.DishService#UpdateDish(com.foodybuddy.model.
	 * Dish)
	 */
	public Dish updateDish(Integer id, String newName) throws Exception {
		Session session = null;
		Transaction transaction = null;
		Dish updatedDish;
		try {
			if(id<1 || newName==null)throw new Exception("Invalid Input");
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);
			DishDAO DishDAO = new DishDAOImpl(session);
			Dish Dish = DishDAO.getByDishId(id);
				Dish.setName(newName);
				updatedDish = DishDAO.update(Dish);
				transaction.commit();
		} catch (Exception ex) {
			try {
				if (transaction == null) {
                    throw new Exception("Transaction could not be completed: " + ex.getMessage(), ex);
				}
				transaction.rollback();
				throw new Exception("Transaction could not be completed will be rollbacked: " + ex.getMessage(), ex);
			} catch (RuntimeException rbe) {
				throw new Exception("Transaction could not be completed and rollback failed: " + ex.getMessage(), ex);
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return updatedDish;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.DishService#getById(java.lang.Integer)
	 */
	public Dish getById(Integer id) throws Exception {
		Session session = null;
		Dish getDish = null;
		try {
			if(id<1)throw new Exception("Invalid Input");
			session = this.sessionFactory.openSession();
			DishDAO DishDAO = new DishDAOImpl(session);
			getDish = DishDAO.getByDishId(id);
		} catch (Exception ex) {
				throw new Exception(ex.getMessage(), ex);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return getDish;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.foodybuddy.service.DishService#deactivateUser(java.lang.Integer)
	 */
	public Dish deactivateDish(Integer id) throws Exception {
		Session session = null;
		Transaction transaction = null;
		Dish deactivaedDish = null;
		try {
			if(id<1)throw new Exception("Invalid Input");
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);
			DishDAO DishDAO = new DishDAOImpl(session);
			deactivaedDish = DishDAO.getByDishId(id);
			deactivaedDish.setIsActive(false);
			DishDAO.update(deactivaedDish);
			transaction.commit();
		} catch (Exception ex) {
			try {
				if (transaction == null) {
                    throw new Exception("Transaction could not be completed: " + ex.getMessage(), ex);
				}
				transaction.rollback();
				throw new Exception("Transaction could not be completed will be rollbacked: " + ex.getMessage(), ex);
			} catch (RuntimeException rbe) {
				throw new Exception("Transaction could not be completed and rollback failed: " + ex.getMessage(), ex);
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return deactivaedDish;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.DishService#activateUser(java.lang.Integer)
	 */
	public Dish activateDish(Integer id) throws Exception {
		Session session = null;
		Transaction transaction = null;
		Dish activaedDish = null;
		Dish getDish = null;
		try {
			if(id<1)throw new Exception("Invalid Input");
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);
			DishDAO DishDAO = new DishDAOImpl(session);
			getDish = DishDAO.getByDishId(id);
			getDish.setIsActive(true);
			activaedDish = DishDAO.update(getDish);
			transaction.commit();
		} catch (Exception ex) {
			try {
				if (transaction == null) {
                    throw new Exception("Transaction could not be completed: " + ex.getMessage(), ex);
				}
				transaction.rollback();
				throw new Exception("Transaction could not be completed will be rollbacked: " + ex.getMessage(), ex);
			} catch (RuntimeException rbe) {
				throw new Exception("Transaction could not be completed and rollback failed: " + ex.getMessage(), ex);
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return activaedDish;
	}

}
