package com.foodybuddy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foodybuddy.dao.BuyerDAO;
import com.foodybuddy.dao.DishDAO;
import com.foodybuddy.dao.impl.BuyerDAOImpl;
import com.foodybuddy.dao.impl.DishDAOImpl;
import com.foodybuddy.model.Buyer;
import com.foodybuddy.model.Dish;
import com.foodybuddy.model.Order;
import com.foodybuddy.service.impl.OrderServiceImpl;
import com.foodybuddy.utils.OrderStatus;
import com.foodybuddy.utils.SessionFactoryUtils;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/hibernate.cfg.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderServiceTest extends TestCase {

	/** The log. */
	static Log log = LogFactory.getLog(OrderServiceTest.class.getName());
	boolean isFirst = true;	
	/** The session factory. */
	SessionFactory sessionFactory = null;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	@Override
	public void setUp() throws Exception {
		if(sessionFactory == null)
			sessionFactory = SessionFactoryUtils.getSessionFactory();
		if(isFirst == true){
			isFirst = false;
			insertDependentData();	
		}		
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	@Override
	public void tearDown() throws Exception {
		//flush the inserted table
		cleanUpDependentData();	
	}

	/**
	 * Test for placeOrder.
	 */
	@Test
	public void placeOrderTest() {
		try {
			OrderService orderService = new OrderServiceImpl(sessionFactory);
			// set parameters
			int buyerId = 1;
			int dishId = 1;
			int orderedQuantity = 2;

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
			assertEquals(order.getNetOrderAmount(), 200); // 2 biryani's cost = 2*100															
			assertEquals(order.getBuyer().getId(), buyerId);
			assertEquals(order.getBuyer().getName(), "FoodyBuddy");
			assertEquals(order.getStatus(), OrderStatus.INTIATED);
		} catch (Exception ex) {
			assertTrue(false);
			System.out.println(ex);
			log.error(ex);
		}
	}
	/**
	 * Negative test
	 * Placing the order for quantity more than available
	 */
	@Test(expected = Exception.class)
	public void placeInvalidOrderTest() throws Exception{
		try {
			OrderService orderService = new OrderServiceImpl(sessionFactory);
			// set parameters
			int buyerId = 1;
			int dishId = 1;
			int orderedQuantity = 20;

			// prepare the arguments to be passed
			List<Integer> dishIds = new ArrayList<Integer>();
			dishIds.add(dishId);
			List<Integer> orderedQuantitys = new ArrayList<Integer>();
			orderedQuantitys.add(orderedQuantity);

			// place the order
			orderService.placeOrder(buyerId, dishIds, orderedQuantitys);
		} catch (Exception ex) {
			log.error(ex);
			throw ex;
		}
	}
	
	/**
	 * Check by passing negative value of orderedQuanity.
	 *
	 * @throws Exception the exception
	 */	
	@Test(expected = Exception.class)
	public void negativePlaceOrderTest() throws Exception{
		OrderService orderService = new OrderServiceImpl(sessionFactory);
		// set parameters
		int buyerId = 1;
		int dishId = 1;
		int orderedQuantity = -2;

		// prepare the arguments to be passed
		List<Integer> dishIds = new ArrayList<Integer>();
		dishIds.add(dishId);
		List<Integer> orderedQuantitys = new ArrayList<Integer>();
		orderedQuantitys.add(orderedQuantity);

		// place the order
		orderService.placeOrder(buyerId, dishIds, orderedQuantitys);
			
		
	}

	/**
	 * Test for canceling the order, basically setting the status flag to CANCELLED.
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
			assertEquals(order.getStatus(), OrderStatus.CANCELLED);

		} catch (Exception ex) {
			assertTrue(false);
			System.out.println(ex);
			log.error(ex);
		}

	}
	
	/**
	 * Negative test
	 * Check the action on passing sessionFactory as null.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void negativeServieObjectTest() throws Exception{
		try {
			OrderService orderService = new OrderServiceImpl(null);
		} catch (Exception ex) {
			System.out.println(ex);
			log.error(ex);
			throw ex;
		}

	}
	
	/**
	 * Negative test
	 * We check with the orderId which is not available in the database.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void negativeInvalidCancelOrderTest() throws Exception {
		try {
			OrderService orderService = new OrderServiceImpl(SessionFactoryUtils.getSessionFactory());
			// set parameters
			int orderId = 100;

			// cancel the order
			orderService.cancelOrder(orderId);

			// Fetch the cancelled order
			Order order = orderService.getOrder(orderId);
			System.out.println(order);

		} catch (Exception ex) {
			System.out.println(ex);
			log.error(ex);
			throw ex;
		}

	}
	
	/**
	 * Negative test
	 * We check with the orderId as negative.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void negativeCancelOrderTest() throws Exception {
		try {
			OrderService orderService = new OrderServiceImpl(SessionFactoryUtils.getSessionFactory());
			// set parameters
			int orderId = -1;

			// cancel the order
			orderService.cancelOrder(orderId);

			// Fetch the cancelled order
			Order order = orderService.getOrder(orderId);

		} catch (Exception ex) {
			System.out.println(ex);
			log.error(ex);
			throw ex;
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
			assertEquals(order.getStatus(), OrderStatus.INTIATED);
			assertNotNull(order.getBuyer());
			assertEquals(order.getBuyer().getId(), 1);

		} catch (Exception ex) {
			assertTrue(false);
			System.out.println(ex);
			log.error(ex);
		}

	}
	
	/**
	 * Negative test
	 * Pass the negative orderId.
	 *
	 * @throws Exception the exception
	 */
	
	@Test(expected = Exception.class)
	public void negativeGetOrderTest() throws Exception{
		try{
			OrderService orderService = new OrderServiceImpl(SessionFactoryUtils.getSessionFactory());
			// set parameters
			int orderId = -11;
			// get the order
			orderService.getOrder(orderId);	
			// Fetch the cancelled order
			Order order = orderService.getOrder(orderId);
		}
		catch(Exception ex){
			throw ex;		
		}		
	}
	
	/**
	 * Negative test
	 * Pass the  orderId which is not available in database.
	 *
	 * @throws Exception the exception
	 */
	
	@Test(expected = Exception.class)
	public void negativeInvalidGetOrderTest() throws Exception{
		try{
			OrderService orderService = new OrderServiceImpl(SessionFactoryUtils.getSessionFactory());
			// set parameters
			int orderId = 111;
			// get the order
			orderService.getOrder(orderId);	
			// Fetch the cancelled order
			Order order = orderService.getOrder(orderId);
		}
		catch(Exception ex){
			throw ex;		
		}		
	}

	/**
	 * Pre-processing
	 * This is required for inserting buyer, dish into the database because
	 * placeOrder depends on them.
	 *
	 * @throws Exception the exception
	 */
	private void insertDependentData()  throws Exception{
		Session session = null;
		if(sessionFactory == null){
			throw new NullPointerException("session can't be null");
		}
		BuyerService buyerService  = new BuyerServiceImpl(sessionFactory);
		buyerService.addBudder("FoodyBuudy","sairamch04@foodybuddy.com", "8676903268","200 B");
		DishService dishService = new DishServiceImpl(sessionFactory);
		dishService.addDish("Biryani","little sweet", true, 10);	
	}
	/**
	 * Post-processing code : for cleaning up database tables
	 */
	private void cleanUpDependentData() throws Exception{
		if(sessionFactory == null){
			throw new NullPointerException("session can't be null");
		}
		//No cleanup required
	}
}
