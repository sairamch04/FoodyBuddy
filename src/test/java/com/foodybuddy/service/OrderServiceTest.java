package com.foodybuddy.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
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
import com.foodybuddy.model.Order;
import com.foodybuddy.service.OrderService;
import com.foodybuddy.service.impl.OrderServiceImpl;
import com.foodybuddy.utils.SessionFactoryUtils;

/**
 * The Class OrderServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/hibernate.cfg.xml")
public class OrderServiceTest {
	
	/** The log. */
	static Log log = LogFactory.getLog(OrderServiceTest.class.getName());
	@Before
	public void setUp() throws Exception{
//		Order order = new Order();
//		order.setBuyer(buyer);
//		Date date = new Date();
//		order.setCreatedAt(date);
//		order.setUpdatedAt(date);
//		order.setNetOrderAmount(100);
//		order.setStatus(1);
//		
	}

	/**
	 * Test for placeOrder.
	 */
	@Test
	public void placeOrderTest() {
		try {
			OrderService orderService = new OrderServiceImpl(SessionFactoryUtils.getSessionFactory());
			// set parameters
			int buyerId = 1;
			int dishId = 1;
			int orderedQuantity = 2;
			// The pre-process phase of adding depending objects to database
			insertRequiredData(buyerId, dishId, orderedQuantity, SessionFactoryUtils.getSessionFactory());

			// prepare the arguments to be passed
			List<Integer> dishIds = new ArrayList<Integer>();
			dishIds.add(dishId);
			List<Integer> orderedQuantitys = new ArrayList<Integer>();
			orderedQuantitys.add(orderedQuantity);

			// place the order
			orderService.placeOrder(buyerId, dishIds, orderedQuantitys);

			// Fetch the inserted order
			int orderId = 1;
			Order order = orderService.getOrder(orderId);

			// Test the fetched object
			assertNotNull(order);
			assertNotNull(order.getBuyer());
			assertEquals(order.getNetOrderAmount(), 200); // 2 biryani's cost
															// 2*100
			assertEquals(order.getBuyer().getId(), buyerId);
			assertEquals(order.getBuyer().getName(), "Indra");
			assertEquals(order.getStatus(), 1);
		} catch (Exception ex) {
			assertTrue(false);
			System.out.println(ex);
			log.error(ex);
		}
	}

	/**
	 * Test for canceling the order, basically setting the status flag to 0.
	 */
	@Test
	public void cancelOrderTest() {
		try {
			OrderService orderService = new OrderServiceImpl(SessionFactoryUtils.getSessionFactory());
			// set parameters
			int orderId = 1;

			// cancel the order
			orderService.cancelOrder(orderId);

			// Fetch the cancelled order
			Order order = orderService.getOrder(orderId);

			// Test the fetched object
			assertNotNull(order);
			assertEquals(order.getStatus(), 0);

		} catch (Exception ex) {
			assertTrue(false);
			System.out.println(ex);
			log.error(ex);
		}

	}

	/**
	 * Test for getting the order object by orderId.
	 *
	 * @return the order test
	 */
	@Test
	public void getOrderTest() {
		try {
			OrderService orderService = new OrderServiceImpl(SessionFactoryUtils.getSessionFactory());
			// set parameters
			int orderId = 1;

			// get the order
			orderService.getOrder(orderId);

			// Fetch the cancelled order
			Order order = orderService.getOrder(orderId);

			// Test the fetched object
			assertNotNull(order);
			assertEquals(order.getStatus(), 1);
			assertNotNull(order.getBuyer());
			System.out.println(order.getBuyer());
			System.out.println(order.getBuyer().getId());
			assertEquals(order.getBuyer().getId(), 1);

		} catch (Exception ex) {
			assertTrue(false);
			System.out.println(ex);
			log.error(ex);
		}

	}

	/**
	 * This is required for inserting buyer, dish into the database because
	 * placeOrder depends on them.
	 *
	 * @param buyerId the buyer id
	 * @param dishId the dish id
	 * @param orderedQuantity the ordered quantity
	 * @param sessionFactory the session factory
	 */
	private void insertRequiredData(int buyerId, int dishId, int orderedQuantity, SessionFactory sessionFactory) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Buyer buyer = new Buyer();
			buyer.setName("Indra");
			//
			Dish dish = new Dish();
			dish.setName("Biryani");
			dish.setPrice(100);
			dish.setQuantityAvailable(10);
			//
			DishDAO dishDAO = new DishDAOImpl(session);
			BuyerDAO buyerDAO = new BuyerDAOImpl(session);
			buyerDAO.insert(buyer);
			dishDAO.insert(dish);
			//
			session.getTransaction().commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
		}
	}
}
