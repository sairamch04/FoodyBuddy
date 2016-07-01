package com.foodybuddy.service;

import java.util.ArrayList;
import java.util.Date;
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

import com.foodybuddy.model.Apartment;
import com.foodybuddy.model.Buyer;
import com.foodybuddy.model.City;
import com.foodybuddy.model.Locality;
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

	/** The is first. */
	boolean isFirst = true;
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
			assertEquals(order.getBuyer().getId(), buyerId);
			assertEquals(order.getBuyer().getName(), "Buyer");
			assertEquals(order.getStatus(), OrderStatusEnum.INTIATED.getValue());
		} catch (Exception ex) {
			assertTrue(false);
			log.error(ex);
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
			// assertTrue(false);
			System.out.println(ex);
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
			System.out.println(ex);
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
			System.out.println(order);

		} catch (Exception ex) {
			System.out.println(ex);
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
			assertEquals(order.getBuyer().getId(), 1);

		} catch (Exception ex) {
			assertTrue(false);
			System.out.println(ex);
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
	 * Pre-processing This is required for inserting buyer, dish into the
	 * database because placeOrder depends on them.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("deprecation")
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
			City city = cityService.getById(1);
			//
			Locality locality = new Locality("loc1", "a", city);
			localityService.insert(locality);
			locality = localityService.getById(1);
			Apartment apartment = new Apartment("Rainbow PG", locality, 1);
			apartment.setLocality(locality);
			apartmentService.insert(apartment);
			apartment = apartmentService.getById(1);
			Buyer buyer = new Buyer();
			buyer.setApartment(apartment);
			buyer.setEmail("sairamch04@foodybuddy.com");
			buyer.setName("Buyer");
			buyerService.insert(buyer);
			// buyerService.addBudder("FoodyBuudy","sairamch04@foodybuddy.com",
			// "8676903268","200 B",1 ,true);
			sellerService.addSeller("sairam", "sairamch04@foodybuddy.com", "8676903268", "200 B", 1, true);
			dishService.addDish("Biryani", "Hyderabadi style ", 1, 100, new Date(2016, 5, 21), new Date(2016, 5, 22),
					new Date(2016, 5, 22), true, true, 10);
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
