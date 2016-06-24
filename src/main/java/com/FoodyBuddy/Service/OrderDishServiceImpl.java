package com.FoodyBuddy.Service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.FoodyBuddy.Dao.OrderDishDAO;
import com.FoodyBuddy.Dao.Impl.OrderDishDAOImpl;
import com.FoodyBuddy.Model.OrderDish;

public class OrderDishServiceImpl {
	 private SessionFactory sessionFactory;
	public OrderDish OrderDishQuantity(int orderDishId, int updatedQuantity) throws Exception {
		Session session = null;
		Transaction transaction = null;
		try {
			if (orderDishId < 0 || updatedQuantity < 0) {
				throw new RuntimeException("invalid dishId or quantity");
			}
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			OrderDishDAO orderDishDAO = new OrderDishDAOImpl(sessionFactory);
			OrderDish orderDish = orderDishDAO.listById(orderDishId);
			//Check if available quantity is greater than updated quantity
			Dish dish = orderDish.getDish();
			if (dish.getAvailableQuantity() > updatedQuantity){
				orderDish.setQuantity(updatedQuantity);
				dish.setAvailableQuantity(dish.getAvailableQuantity() - updatedQuantity);
				//TODO:: Check whether code below is needed or no
				//write updated dish quantity values
				DishDAO dishDAO = new DishDAOImpl(sessionFactory);
	            dishDAO.save(dish);
	            orderDishDAO.save(orderDish);
			}						
			transaction.commit();
		} catch (Exception ex) {
			try {
				transaction.rollback();
				throw new Exception(
						"Transaction could not be completed will be rollbacked: " + ex.getMessage(), ex);
			} catch (RuntimeException rbe) {
				throw new Exception(
						"Transaction could not be completed and rollback failed: " + ex.getMessage(), ex);
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return orderDish;
	}
}