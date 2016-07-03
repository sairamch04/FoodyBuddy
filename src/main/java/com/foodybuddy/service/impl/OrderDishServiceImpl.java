package com.foodybuddy.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.foodybuddy.dao.DishDAO;
import com.foodybuddy.dao.OrderDishDAO;
import com.foodybuddy.dao.impl.DishDAOImpl;
import com.foodybuddy.dao.impl.OrderDishDAOImpl;
import com.foodybuddy.model.Dish;
import com.foodybuddy.model.Order;
import com.foodybuddy.model.OrderDish;
import com.foodybuddy.service.OrderDishService;

@Service
public class OrderDishServiceImpl implements OrderDishService {
	private SessionFactory sessionFactory;

	public List<OrderDish> getListOrderByDishId(int dishId) throws Exception {
		Session session = null;
		try {
			if (dishId <= 0) {
				throw new Exception("invalid dishId ");
			}
			session = this.sessionFactory.openSession();
			OrderDishDAO orderDishDAO = new OrderDishDAOImpl(session);
			List<OrderDish> orderDishList = orderDishDAO.getListByDishId(dishId);
			return orderDishList;
		} catch (Exception ex) {
			throw new Exception("ListingbyDishid failed: " + ex.getMessage(), ex);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public OrderDishServiceImpl(SessionFactory sessionFactory) throws Exception{
		if(sessionFactory == null) {
			throw new Exception("Sessionfactory is null");
		}
		else
			this.sessionFactory = sessionFactory;
	}

	public List<OrderDish> getListOrderByOrderId(int orderId) throws Exception {
		Session session = null;
		try {
			if (orderId <= 0) {
				throw new Exception("invalid orderId ");
			}
			session = this.sessionFactory.openSession();
			OrderDishDAO orderDishDAO = new OrderDishDAOImpl(session);
			List<OrderDish> orderDishList = orderDishDAO.getListByOrderId(orderId);
			return orderDishList;
		} catch (Exception ex) {
			throw new Exception("Listingby Orderid failed: " + ex.getMessage(), ex);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	public OrderDish insertOrderDish(Order order, Dish dish) throws Exception{
		Session session = null;
		Transaction transaction = null;
		try{
			if(order == null)
				throw new Exception("invalid order");
			if(dish == null)
				throw new Exception("invalid Dish");
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			OrderDishDAO orderDishDAO = new OrderDishDAOImpl(session);
			OrderDish orderDish = new OrderDish();
			orderDish.setDish(dish);
			orderDish.setOrder(order);
			orderDishDAO.insert(orderDish);
			session.getTransaction().commit();
			return orderDish;
		} catch (Exception ex) {
			try {
				if (transaction == null) {
					throw new Exception("Transaction is null " + ex.getMessage(), ex);
				}
				transaction.rollback();
				throw new Exception("Transaction could not be completed will be rollbacked: " + ex.getMessage(), ex);
			} catch (Exception rbe) {
				throw new Exception("Transaction could not be completed and rollback failed: " + ex.getMessage(), ex);
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public OrderDish updateOrderDishQuantity(int orderDishId, int updatedQuantity) throws Exception {
		Session session = null;
		Transaction transaction = null;
		try {
			if (orderDishId <= 0) {
				throw new Exception("invalid orderDishId");
			}
			if (updatedQuantity < 0) {
				throw new Exception("invalid updated quantity");
			}

			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);
			OrderDish orderDish = new OrderDish();
			OrderDishDAO orderDishDAO = new OrderDishDAOImpl(session);
			try{
				orderDish = orderDishDAO.getById(orderDishId);
			} catch (Exception ex) {
				throw new Exception("OrderDish id is invalid" + ex.getMessage(), ex);
			}
			
			Dish dish = orderDish.getDish();

			// Check if available quantity is greater than updated quantity
			if (dish.getQuantityAvailable() > updatedQuantity) {
				orderDish.setQuantity(updatedQuantity);
				dish.setQuantityAvailable(dish.getQuantityAvailable() - updatedQuantity);
				DishDAO dishDAO = new DishDAOImpl(session);
				dishDAO.update(dish);
				orderDishDAO.update(orderDish);
			}
			transaction.commit();
			return orderDish;
		} catch (Exception ex) {
			try {
				if (transaction == null) {
					throw new Exception("Transaction is null " + ex.getMessage(), ex);
				}
				transaction.rollback();
				throw new Exception("Transaction could not be completed will be rollbacked: " + ex.getMessage(), ex);
			} catch (Exception rbe) {
				throw new Exception("Transaction could not be completed and rollback failed: " + ex.getMessage(), ex);
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
