package com.foodybuddy.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

@Service
public class OrderServiceImpl implements OrderService {

	private SessionFactory sessionFactory;
	static Log log = LogFactory.getLog(OrderServiceImpl.class.getName());
	OrderServiceImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	//TODO::javadoc comments
	public void placeOrder(int buyerId, List<Integer> dishIds, List<Integer> orderedQuantitys) throws Exception {
		Transaction transaction = null;
		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = this.sessionFactory.openSession().beginTransaction();
			OrderDAO orderDAO = new OrderDAOImpl(session);
			BuyerDAO buyerDAO = new BuyerDAOImpl(session);
			Order order = new Order();
			Buyer buyer = buyerDAO.getById(buyerId);
			
			for (int index = 0; index < dishIds.size(); index++) {
				int dishId = dishIds.get(index);
				int orderedQuantity = orderedQuantitys.get(index);
				//set properties for Order and also for depended models  OrderDish and Dish
				setPropertiesForOrder(order,buyer,dishId,orderedQuantity,session);
						
			}
			// Inserting order to db			
			orderDAO.insert(order);

			transaction.commit();
			log.info("The transaction for update was done succesfully");
		}
		catch (Exception ex) {
			
			try {
				transaction.rollback();
				log.error("Transaction could not be completed will be rollbacked: " + ex.getMessage());
				throw new Exception("Transaction could not be completed will be rollbacked: " + ex.getMessage(), ex);
			} catch (RuntimeException rbe) {
				log.error("Transaction could not be completed and rollback failed: " + ex.getMessage());
				throw new Exception("Transaction could not be completed and rollback failed: " + ex.getMessage(), ex);
			}
		}
		finally {
			if (session != null) {
				session.close();
			}
		}

	}
	private void setPropertiesForOrder(Order order,Buyer buyer,int dishId,int orderedQuantity,Session session) throws Exception{
		// create the required DAOs
		DishDAO dishDAO = new DishDAOImpl(session);		
		OrderDishDAO orderDishDAO = new OrderDishDAOImpl(session);
								
		Dish dish = dishDAO.getListByDishId(dishId);
		if(dish == null){
			log.info(dishId + " is not valid ");
			throw new Exception("Invalid dishId : " + dishId);
		}

		
		//TODO:: check if ordered times is before AvialaleTill
		if(dish.getQuantityAvailable() >= orderedQuantity){
			OrderDish orderDish = new OrderDish();
			orderDish.setOrder(order);
			dish.setQuantityAvailable(dish.getQuantityAvailable()- orderedQuantity);
			orderDish.setQuantity(orderedQuantity);
			orderDish.setNetDishPrice(orderedQuantity * dish.getPrice());
			order.setBuyer(buyer);
			order.setNetOrderAmount(orderDishDAO.sumDishPrice(order.getId()));
			
			// Inserting orderDish to db				
			orderDishDAO.insert(orderDish);
		}
		else{
			log.info("Quantity not available");
			throw new Exception("Quantity not available");
		}
		
	}

}