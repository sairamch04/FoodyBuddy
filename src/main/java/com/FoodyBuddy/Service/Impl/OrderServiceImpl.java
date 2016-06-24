package com.FoodyBuddy.Service.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodyBuddy.Dao.OrderDAO;
import com.FoodyBuddy.Dao.Impl.OrderDAOImpl;
import com.FoodyBuddy.Model.Order;
import com.FoodyBuddy.Service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDAO orderDao;
	private SessionFactory sessionFactory;

	public boolean placeOrder(int buyerId, List<Integer> dishIds, List<Integer> quantitys) throws Exception {
		boolean isUpdated = true;
		Transaction transaction = null;
		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = this.sessionFactory.openSession().beginTransaction();
			transaction.setTimeout(10);

			// create the required DAOs
			DishDAO dishDAO = new DishDAOImpl(sessionFactory);
			BuyerDAO buyerDAO = new BuyerDAOImpl(sessionFactory);
			OrderDishDAO orderDishDAO = new OrderDishDAOImpl(sessionFactory);
			OrderDishDAO orderDishDAO = new OrderDishDAOImpl(sessionFactory);
			
			Buyer buyer = buyerDAO.listById(buyerId);
			Order order = new Order();
			
			for (int index = 0; index < dishIds.size(); index++) {
				
				int dishId = dishIds.get(index);
				int quantity = quantitys.get(index);
				
				// Get the Dish Object by dishId				
				Dish dish = dishDAO.listById(dishId);

				// create Order dishDAO
				OrderDish orderDish = new OrderDish();

				// set the data to Orderdish
				orderDish.setOrder(order);
				//TODO:: Check whether  quantity of Dish is available, If available reduce the quantity in Dish table
				orderDish.setQuantity(quantity);
				orderDish.setNetDishPrice(quantity * dish.getPrice());
				orderDish.setDish(dish);
				
				// setting data to order
				order.setBuyer(buyer);
				order.setNetOrderAmount(orderDishDAO.sumDishPrice(order.getId()));
				
				// Inserting orderDish to db				
				orderDishDAO.save(orderDish);
			}
			// Inserting order to db
			OrderDAO orderDAO = new OrderDAOImpl(sessionFactory);
			orderDAO.save(order);

			transaction.commit();
		} catch (Exception ex) {
			isUpdated = false;
			try {
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
		return isUpdated;

	}

}