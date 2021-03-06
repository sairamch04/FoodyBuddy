package com.foodybuddy.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foodybuddy.model.Order;
import com.foodybuddy.service.impl.ApartmentServiceImpl;
import com.foodybuddy.service.impl.BuyerServiceImpl;
import com.foodybuddy.service.impl.CityServiceImpl;
import com.foodybuddy.service.impl.CountryServiceImpl;
import com.foodybuddy.service.impl.DishServiceImpl;
import com.foodybuddy.service.impl.LocalityServiceImpl;
import com.foodybuddy.service.impl.OrderServiceImpl;
import com.foodybuddy.service.impl.SellerServiceImpl;
import com.foodybuddy.service.impl.StateServiceImpl;
import com.foodybuddy.utils.OrderStatusEnum;
import com.foodybuddy.utils.SessionFactoryUtils;

import junit.framework.TestCase;

/**
 * The Class OrderServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/hibernate.cfg.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderServiceTest extends TestCase {

	/** The log. */
	static Log log = LogFactory.getLog(OrderServiceTest.class.getName());

	/** The session factory. */
	SessionFactory sessionFactory = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	@Override
	public void setUp() throws Exception {
		sessionFactory = SessionFactoryUtils.getSessionFactory();
		insertDependentData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	@Override
	public void tearDown() throws Exception {
		// close the session factory
		SessionFactoryUtils.close();
	}

	/**
	 * Test for placeOrder.
	 */
	@Test
	public void placeOrderTest() {
		try {
			OrderService orderService = new OrderServiceImpl(sessionFactory);
			insertOrderUtil(orderService);

			// Fetch the inserted order
			int orderId = 1;
			int buyerId = 1;
			Order order = orderService.getOrder(orderId);

			// Test the fetched object
			assertNotNull(order);
			assertNotNull(order.getBuyer());
			assertEquals(order.getNetOrderAmount(), 200); // 2 biryani's cost =  2*100					
			assertEquals(order.getBuyer().getId(), new Integer(buyerId));
			assertEquals(order.getBuyer().getName(), "Buyer");
			assertEquals(order.getStatus(), OrderStatusEnum.INTIATED.getValue());
		} catch (Exception ex) {
			assertTrue(false);
			log.error(ex);
		}
	}
	/**
	 * Negative Test for placeOrder.
	 * Test with invalid Buyer Id
	 */
	@Test(expected = Exception.class)
	public void placeOrderInvalidBuyerTest() throws Exception {
		try {
			OrderService orderService = new OrderServiceImpl(sessionFactory);
			// set parameters
			int buyerId = 10;
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
	 * Negative test Placing the order for quantity more than available.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void placeInvalidOrderTest() throws Exception {
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
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void negativePlaceOrderTest() throws Exception {
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
	 * Test for canceling the order, basically setting the status flag to
	 * CANCELLED.
	 */
	@Test
	public void cancelOrderTest() {
		try {
			OrderService orderService = new OrderServiceImpl(SessionFactoryUtils.getSessionFactory());
			insertOrderUtil(orderService);

			int orderId = 1;
			Order order = orderService.getOrder(orderId);

			// cancel the order
			orderService.cancelOrder(orderId);

			// Fetch the cancelled order
			order = orderService.getOrder(orderId);

			// Test the fetched object
			assertNotNull(order);
			assertEquals(order.getStatus(), OrderStatusEnum.CANCELLED.getValue());

		} catch (Exception ex) {
			assertTrue(false);
			log.error(ex);
		}

	}

	/**
	 * Negative test Check the action on passing sessionFactory as null.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void negativeServieObjectTest() throws Exception {
		try {
			OrderService orderService = new OrderServiceImpl(null);
		} catch (Exception ex) {
			log.error(ex);
			throw ex;
		}

	}

	/**
	 * Negative test We check with the orderId which is not available in the
	 * database.
	 *
	 * @throws Exception
	 *             the exception
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

		} catch (Exception ex) {
			log.error(ex);
			throw ex;
		}

	}

	/**
	 * Negative test We check with the orderId as negative.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void negativeCancelOrderTest() throws Exception {
		try {
			OrderService orderService = new OrderServiceImpl(sessionFactory);
			insertOrderUtil(orderService);
			// set parameters
			int orderId = -1;

			// cancel the order
			orderService.cancelOrder(orderId);

			// Fetch the cancelled order
			Order order = orderService.getOrder(orderId);

		} catch (Exception ex) {
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
			OrderService orderService = new OrderServiceImpl(sessionFactory);
			insertOrderUtil(orderService);
			// set parameters
			int orderId = 1;
			// get the order
			orderService.getOrder(orderId);
			// Fetch the cancelled order
			Order order = orderService.getOrder(orderId);

			// Test the fetched object
			assertNotNull(order);
			assertEquals(order.getStatus(), OrderStatusEnum.INTIATED.getValue());
			assertNotNull(order.getBuyer());
			assertEquals(order.getBuyer().getId(), new Integer(1));

		} catch (Exception ex) {
			assertTrue(false);
			log.error(ex);
		}

	}
	/**
	 * Test for getting the orders of buyerId.
	 *
	 * @return the order test
	 */
	@Test
	public void getOrdersOfBuyerTest() {
		try {
			OrderService orderService = new OrderServiceImpl(sessionFactory);
			insertOrderUtil(orderService);
			// set parameters
			int buyerId = 1;
			// get the order
			List<Order> orderList = orderService.getOrdersOfBuyer(buyerId);

			// Test the fetched object
			assertNotNull(orderList);
			assertEquals(orderList.get(0).getBuyer().getId(),new Integer( buyerId));
			assertEquals(orderList.get(0).getNetOrderAmount(),200);

		} catch (Exception ex) {
			assertTrue(false);
			log.error(ex);
		}

	}
	/**
	 * Negative Test for getting the orders of buyerId.
	 * Invalid buyer Id
	 * @return the order test
	 */
	@Test
	public void getOrdersOfBuyerWithInvalidBuyerTest(){
		try {
			OrderService orderService = new OrderServiceImpl(sessionFactory);
			insertOrderUtil(orderService);
			// set parameters
			int buyerId = 10;
			// get the order
			List<Order> orderList = orderService.getOrdersOfBuyer(buyerId);
			assertEquals(orderList.size(), 0);
			
		} catch (Exception ex) {
			assertTrue(false);
			log.error(ex);
			
		}
	}

	/**
	 * Negative test Pass the negative orderId.
	 *
	 * @throws Exception
	 *             the exception
	 */

	@Test(expected = Exception.class)
	public void negativeGetOrderTest() throws Exception {
		try {
			OrderService orderService = new OrderServiceImpl(sessionFactory);
			// set parameters
			int orderId = -11;
			// get the order
			orderService.getOrder(orderId);
			// Fetch the cancelled order
			Order order = orderService.getOrder(orderId);
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * Negative test Pass the orderId which is not available in database.
	 *
	 * @throws Exception
	 *             the exception
	 */

	@Test(expected = Exception.class)
	public void negativeInvalidGetOrderTest() throws Exception {
		try {
			OrderService orderService = new OrderServiceImpl(sessionFactory);
			// set parameters
			int orderId = 111;
			// get the order
			orderService.getOrder(orderId);
			// Fetch the cancelled order
			Order order = orderService.getOrder(orderId);
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * Update status test.
	 * Update the status to READY
	 */
	@Test
	public void updateStatusTest() {
		try {
			OrderService orderService = new OrderServiceImpl(SessionFactoryUtils.getSessionFactory());
			insertOrderUtil(orderService);

			int orderId = 1;
			Order order = orderService.getOrder(orderId);

			// update status of order
			orderService.updateOrderStatus(orderId, OrderStatusEnum.READY);

			// Fetch the updated order
			order = orderService.getOrder(orderId);

			// Test the fetched object
			assertNotNull(order);
			assertEquals(order.getStatus(), OrderStatusEnum.READY.getValue());

		} catch (Exception ex) {
			assertTrue(false);
			log.error(ex);
		}
		
	}
	/**
	 * Update status test.
	 * Update the status again to INTIATED
	 */
	@Test
	public void updateWithSameStatusTest() {
		try {
			OrderService orderService = new OrderServiceImpl(SessionFactoryUtils.getSessionFactory());
			insertOrderUtil(orderService);

			int orderId = 1;
			Order order = orderService.getOrder(orderId);

			// update status of order
			orderService.updateOrderStatus(orderId, OrderStatusEnum.INTIATED);

			// Fetch the updated order
			order = orderService.getOrder(orderId);

			// Test the fetched object
			assertNotNull(order);
			assertEquals(order.getStatus(), OrderStatusEnum.INTIATED.getValue());

		} catch (Exception ex) {
			assertTrue(false);
			log.error(ex);
		}
		
	}
	
	/**
	 * Negative update status.
	 * Try to CANCEL the order when it is READY which is not allowed
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void negativeUpdateStatus() throws Exception{
		try {
			OrderService orderService = new OrderServiceImpl(SessionFactoryUtils.getSessionFactory());
			insertOrderUtil(orderService);

			int orderId = 1;
			Order order = orderService.getOrder(orderId);

			// update the order
			orderService.updateOrderStatus(orderId, OrderStatusEnum.READY);

			// test for negative update
			orderService.updateOrderStatus(orderId, OrderStatusEnum.CANCELLED);

		} catch (Exception ex) {
			log.error(ex);
			throw ex;
		}
		
	}
	
	/**
	 * Negative place order timestamp test.
	 * Place the order where the dish offer was already expired
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void negativePlaceOrderTimestampTest() throws Exception{
		try {
			OrderService orderService = new OrderServiceImpl(sessionFactory);
			// set parameters
			int buyerId = 1;
			int dishId = 2;
			int orderedQuantity = 2;

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
	 * Pre-processing This is required for inserting buyer, dish into the
	 * database because placeOrder depends on them.
	 *
	 * @throws Exception
	 *             the exception
	 */
	private void insertDependentData() throws Exception {
		if (sessionFactory == null) {
			throw new NullPointerException("session can't be null");
		}
		try {
			// create required services for inserting dependent data
			CountryService countryService = new CountryServiceImpl(sessionFactory);
			StateService stateService = new StateServiceImpl(sessionFactory);
			CityService cityService = new CityServiceImpl(sessionFactory);
			LocalityService localityService = new LocalityServiceImpl(sessionFactory);
			ApartmentService apartmentService = new ApartmentServiceImpl(sessionFactory);
			BuyerService buyerService = new BuyerServiceImpl(sessionFactory);
			SellerService sellerService = new SellerServiceImpl(sessionFactory);
			DishService dishService = new DishServiceImpl(sessionFactory);

			// use services to create data
			countryService.insert("India");
			stateService.insert("Bangalore", 1);
			cityService.insert("Bangalore", 1);
			localityService.insert("Kalyani Magnum", 1, "500035");
			apartmentService.insert("Rainbow Apartments", 1,23 );
			buyerService.insert("Buyer",1,1,"1234567890","buyer@gmail.com","2101",true);
			sellerService.addSeller("sairam", "sairamch04@foodybuddy.com", "8676903268", "200 B", 1, true);
			//insert dish
			SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
			String orderBy = "05-07-2016 10:20:56";
			String availableFrom = "10-07-2016 10:20:56";
			String availableTill = "11-08-2016 10:20:56";
				
			dishService.addDish("Biryani", "Hyderabadi style ", 1, 100, sdf.parse(orderBy), sdf.parse(availableFrom),
					sdf.parse(availableTill), true, true, 10);
			//insert another dish
			 orderBy = "05-06-2016 10:20:56";
			 availableFrom = "10-07-2016 10:20:56";
			 availableTill = "11-08-2016 10:20:56";				
			 dishService.addDish("Tandoori", "Very spicy ", 1, 100, sdf.parse(orderBy), sdf.parse(availableFrom),
					sdf.parse(availableTill), true, true, 10);			
			
		} catch (Exception ex) {
			
			log.error("Unbale to create dependant data " + ex);
			throw ex;
		}
	}

	/**
	 * Insert order util.
	 *
	 * @param orderService the order service
	 */
	public void insertOrderUtil(OrderService orderService) {
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
	}
}
