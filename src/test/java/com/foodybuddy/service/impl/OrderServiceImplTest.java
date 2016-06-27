package com.foodybuddy.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foodybuddy.dao.BuyerDAO;
import com.foodybuddy.dao.DishDAO;
import com.foodybuddy.dao.impl.BuyerDAOImpl;
import com.foodybuddy.dao.impl.DishDAOImpl;
import com.foodybuddy.model.Buyer;
import com.foodybuddy.model.Dish;
import com.foodybuddy.service.OrderService;
import com.foodybuddy.utils.SessionFactoryUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/hibernate.cfg.xml")
public class OrderServiceImplTest {
	static Log log = LogFactory.getLog(OrderServiceImplTest.class.getName());
	@Test
	public void testSample() {
		try{
			OrderService orderService = new OrderServiceImpl(SessionFactoryUtils.getSessionFactory());
			//set parameters
			int buyerId = 1;
			int dishId =1;
			int orderedQuantity = 1;
			
			insertRequiredData(buyerId, dishId, orderedQuantity,SessionFactoryUtils.getSessionFactory());
			
			
			//prepare the arguments to be passed
			List<Integer> dishIds = new ArrayList<Integer>();
			dishIds.add(dishId);
			List<Integer> orderedQuantitys = new ArrayList<Integer>();
			orderedQuantitys.add(orderedQuantity);
			//place the order
			orderService.placeOrder(buyerId, dishIds, orderedQuantitys);
			
			assertTrue(true);
		}
		catch(Exception ex){
			assertTrue(false);
			log.error(ex);			
			
		}

	}
	private void insertRequiredData(int buyerId, int dishId, int orderedQuantity,SessionFactory sessionFcatory){
		Session session = sessionFcatory.openSession();
		session.beginTransaction();
		//create dependent models before placing order
		Buyer buyer = new Buyer();
		Dish dish = new Dish();
		buyer.setName("Indra");
		dish.setName("Biryani");
		dish.setPrice(100);
		dish.setQuantityAvailable(10);
		DishDAO dishDAO = new DishDAOImpl(session);
		BuyerDAO buyerDAO = new BuyerDAOImpl(session);
		buyerDAO.insert(buyer);
		dishDAO.insert(dish);
		session.getTransaction().commit();
		session.close();
		
	}

}


