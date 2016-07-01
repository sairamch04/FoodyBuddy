package com.foodybuddy.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.foodybuddy.dao.BuyerDAO;
import com.foodybuddy.dao.DishDAO;
import com.foodybuddy.dao.OrderDAO;
import com.foodybuddy.dao.OrderDishDAO;
import com.foodybuddy.dao.impl.BuyerDAOImpl;
import com.foodybuddy.dao.impl.DishDAOImpl;
import com.foodybuddy.dao.impl.OrderDAOImpl;
import com.foodybuddy.dao.impl.OrderDishDAOImpl;
import com.foodybuddy.model.Buyer;
import com.foodybuddy.model.Dish;
import com.foodybuddy.model.Order;
import com.foodybuddy.model.OrderDish;
import com.foodybuddy.service.OrderService;
import com.foodybuddy.utils.OrderStatus;

/**
 * The Class OrderServiceImpl.
 */
@Service
public class OrderServiceImpl implements OrderService {

	/** The session factory. */
	private SessionFactory sessionFactory;
	
	/** The log. */
	static Logger log = Logger.getLogger(OrderServiceImpl.class.getName());

	/**
	 * Instantiates a new order service impl.
	 *
	 * @param sessionFactory the session factory
	 */
	public OrderServiceImpl(SessionFactory sessionFactory) throws NullPointerException{
		if(sessionFactory == null){
			throw new NullPointerException("sessionFactory can't be null");
			
		}
		this.sessionFactory = sessionFactory;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.service.OrderService#placeOrder(int, java.util.List, java.util.List)
	 */
	public void placeOrder(int buyerId, List<Integer> dishIds, List<Integer> orderedQuantitys)
			throws HibernateException {
		if(buyerId <=0){
			throw new HibernateException("Invalid buyerId " + buyerId);
		}
		Transaction transaction = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			OrderDAO orderDAO = new OrderDAOImpl(session);
			BuyerDAO buyerDAO = new BuyerDAOImpl(session);
			Order order = new Order();
			order.setStatus(OrderStatus.INTIATED);
			Buyer buyer = buyerDAO.getById(buyerId);
			if(buyer == null){
				log.error("Invalid buyerId " + buyerId);
				throw new Exception("Invalid buyer Id " + buyerId);
			}

			for (int index = 0; index < dishIds.size(); index++) {
				int dishId = dishIds.get(index);
				int orderedQuantity = orderedQuantitys.get(index);
				if(dishId <= 0){
					throw new HibernateException("Invalid buyerId " + buyerId);
				}
				if(orderedQuantity <= 0){
					throw new HibernateException("Invalid buyerId " + buyerId);
					
				}
				// set properties for Order and also for depended models
				// OrderDish and Dish
				setPropertiesForOrder(order, buyer, dishId, orderedQuantity, session);
			}
			// Inserting order to db
			orderDAO.insert(order);
			transaction.commit();
			log.info("The transaction for update was done succesfully");
		} catch (Exception ex) {

			try {
				if (transaction == null) {
					log.error("Transaction could not be completed" + ex.getMessage());
					throw new HibernateException("Transaction could not be completed: " + ex.getMessage(), ex);
				}
				transaction.rollback();
				log.error("Transaction could not be completed will be rollbacked: " + ex.getMessage());
				throw new HibernateException(
						"Transaction could not be completed will be rollbacked: " + ex.getMessage(), ex);
			} catch (RuntimeException rbe) {
				log.error("Transaction could not be completed and rollback failed: " + ex.getMessage());
				throw new HibernateException(
						"Transaction could not be completed and rollback failed: " + ex.getMessage(), ex);
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.service.OrderService#getOrder(int)
	 */
	public Order getOrder(int orderId) throws HibernateException {
		if(orderId <= 0){
			throw new HibernateException("Invalid orderId" + orderId);
		}
		Session session = null;
		Order order = null;
		try {
			session = sessionFactory.openSession();
			OrderDAO orderDAO = new OrderDAOImpl(session);
			order = orderDAO.getById(orderId);
			log.info("seccesfully obtained object for " + orderId);

		} catch (Exception ex) {

			throw new HibernateException("getOrder failed due to unavialble quanity or invalid orderId",ex);
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return order;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.service.OrderService#cancelOrder(int)
	 */
	// TODO:: check if the action is within availableTill
	public void cancelOrder(int orderId) throws HibernateException {
		if(orderId <= 0){
			throw new HibernateException("Invalid orderId" + orderId);
		}
		Transaction transaction = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			OrderDAO orderDAO = new OrderDAOImpl(session);
			Order order = orderDAO.getById(orderId);
			order.setStatus(OrderStatus.CANCELLED);
			orderDAO.update(order);
			transaction.commit();
			log.info("cancel of order was succesfull" + orderId);
		} catch (Exception ex) {

			try {
				transaction.rollback();
				log.error("Transaction could not be completed will be rollbacked: " + ex.getMessage());
				throw new HibernateException(
						"Transaction could not be completed will be rollbacked: " + ex.getMessage(), ex);
			} catch (RuntimeException rbe) {
				log.error("Transaction could not be completed and rollback failed: " + ex.getMessage());
				throw new HibernateException(
						"Transaction could not be completed and rollback failed: " + ex.getMessage(), ex);
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * Sets the properties for order.
	 *
	 * @param order the order
	 * @param buyer the buyer
	 * @param dishId the dish id
	 * @param orderedQuantity the ordered quantity
	 * @param session the session
	 * @throws Exception the exception
	 */
	private void setPropertiesForOrder(Order order, Buyer buyer, int dishId, int orderedQuantity, Session session)
			throws Exception {
		// create the required DAOs
		DishDAO dishDAO = new DishDAOImpl(session);
		OrderDishDAO orderDishDAO = new OrderDishDAOImpl(session);
		Dish dish = dishDAO.getByDishId(dishId);
		if (dish == null) {
			log.info(dishId + " is not valid ");
			throw new Exception("Invalid dishId : " + dishId);
		}
		// TODO:: check if ordered times is before AvialaleTill
		if (dish.getQuantityAvailable() >= orderedQuantity) {
			OrderDish orderDish = new OrderDish();
			orderDish.setOrder(order);
			orderDish.setDish(dish);
			dish.setQuantityAvailable(dish.getQuantityAvailable() - orderedQuantity);
			orderDish.setQuantity(orderedQuantity);
			orderDish.setNetDishPrice(orderedQuantity * dish.getPrice());
			order.setBuyer(buyer);

			orderDishDAO.insert(orderDish);
			order.setNetOrderAmount(order.getNetOrderAmount() + orderDish.getNetDishPrice());
		} else {
			log.info("Quantity not available");
			throw new Exception("Quantity not available");
		}

	}

}