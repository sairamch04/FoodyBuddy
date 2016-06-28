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
import com.foodybuddy.model.OrderDish;
import com.foodybuddy.service.OrderDishService;
import com.foodybuddy.utils.SessionFactoryUtils;

@Service
public class OrderDishServiceImpl implements OrderDishService {
	private SessionFactory sessionFactory;

	public List<OrderDish> listOrderByDishId(int dishId) {
		sessionFactory = SessionFactoryUtils.getSessionFactory();
		Session session = null;
		Transaction transaction = null;
		try {
			if (dishId < 0) {
				throw new RuntimeException("invalid dishId ");
			}
			session = this.sessionFactory.openSession();
			OrderDishDAO orderDishDAO = new OrderDishDAOImpl(session);
			List<OrderDish> orderDishList = orderDishDAO.getListByDishId(dishId);
			return orderDishList;
		} catch (Exception ex) {
			throw new Exception("ListingbyDishid failed: " + ex.getMessage(), ex);
			return null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<OrderDish> listOrderByOrderId(int orderId) {
		sessionFactory = SessionFactoryUtils.getSessionFactory();
		Session session = null;
		Transaction transaction = null;
		try {
			if (orderId < 0) {
				throw new RuntimeException("invalid orderId ");
			}
			session = this.sessionFactory.openSession();
			OrderDishDAO orderDishDAO = new OrderDishDAOImpl(session);
			List<OrderDish> orderDishList = orderDishDAO.getListByOrderId(orderId);
			return orderDishList;
		} catch (Exception ex) {
			throw new Exception("Listingby Orderid failed: " + ex.getMessage(), ex);
			return null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public OrderDish updateOrderDishQuantity(int orderDishId, int updatedQuantity) throws Exception {
		sessionFactory = SessionFactoryUtils.getSessionFactory();
		Session session = null;
		Transaction transaction = null;
		try {
			if (orderDishId < 0) {
				throw new RuntimeException("invalid orderDishId");
			}
			if (updatedQuantity < 0) {
				throw new RuntimeException("invalid updated quantity");
			}

			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			OrderDishDAO orderDishDAO = new OrderDishDAOImpl(session);
			OrderDish orderDish = orderDishDAO.getById(orderDishId);
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
			} catch (RuntimeException rbe) {
				throw new Exception("Transaction could not be completed and rollback failed: " + ex.getMessage(), ex);
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}