package com.foodybuddy.service;

import java.text.SimpleDateFormat;
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
import com.foodybuddy.model.OrderDish;
import com.foodybuddy.service.impl.ApartmentServiceImpl;
import com.foodybuddy.service.impl.BuyerServiceImpl;
import com.foodybuddy.service.impl.CityServiceImpl;
import com.foodybuddy.service.impl.CountryServiceImpl;
import com.foodybuddy.service.impl.DishServiceImpl;
import com.foodybuddy.service.impl.LocalityServiceImpl;
import com.foodybuddy.service.impl.OrderDishServiceImpl;
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
public class OrderDishServiceTest extends TestCase {

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
	 * Positive test listing by dishId
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getListbyDishIdTest() throws Exception {
		try {
			OrderDishService orderDishService = new OrderDishServiceImpl(sessionFactory);
			List<OrderDish> orderDishList = orderDishService.getListOrderByDishId(1);
			assertNotNull(orderDishList);
			assertNotNull(orderDishList.get(0).getOrder());
			assertNotNull(orderDishList.get(0).getDish());
			assertNotNull(orderDishList.get(0).getQuantity());
			assertEquals(orderDishList.get(0).getQuantity(), 2);
		} catch (Exception ex) {
			log.error(ex);
			throw ex;
		}
	}
	
	/**
	 * Negative test listing by dishId
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void negativeGetListbyDishIdTest() throws Exception {
		try {
			OrderDishService orderDishService = new OrderDishServiceImpl(sessionFactory);
			orderDishService.getListOrderByDishId(0);
		} catch (Exception ex) {
			log.error(ex);
			throw ex;
		}
	}

	/**
	 * Positive test listing by orderId
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getListbyOrderIdTest() throws Exception {
		try {
			OrderDishService orderDishService = new OrderDishServiceImpl(sessionFactory);
			List<OrderDish> orderDishList = orderDishService.getListOrderByOrderId(1);
			assertNotNull(orderDishList);
			assertNotNull(orderDishList.get(0).getOrder());
			assertNotNull(orderDishList.get(0).getDish());
			assertNotNull(orderDishList.get(0).getQuantity());
			assertEquals(orderDishList.get(0).getQuantity(), 2);
		} catch (Exception ex) {
			log.error(ex);
			throw ex;
		}
	}
	
	/**
	 * Negative test listing by orderId
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void negativeGetListbyOrderIdTest() throws Exception {
		try {
			OrderDishService orderDishService = new OrderDishServiceImpl(sessionFactory);
			orderDishService.getListOrderByOrderId(0);
		} catch (Exception ex) {
			log.error(ex);
			throw ex;
		}
	}


	/**
	 * Positive test inserting orderdish
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void insertOrderDishTest() throws Exception {
		try {
			OrderDishService orderDishService = new OrderDishServiceImpl(sessionFactory);
			DishService dishService = new DishServiceImpl(sessionFactory);
			OrderService orderService = new OrderServiceImpl(sessionFactory);
			orderDishService.insertOrderDish(orderService.getOrder(1), dishService.getById(1));
		} catch (Exception ex) {
			log.error(ex);
			throw ex;
		}
	}
	
	/**
	 * Negative test inserting orderdish where dish is null
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void negativeInsertOrderDishTest() throws Exception {
		try {
			OrderDishService orderDishService = new OrderDishServiceImpl(sessionFactory);
			OrderService orderService = new OrderServiceImpl(sessionFactory);
			orderDishService.insertOrderDish(orderService.getOrder(1), null);
		} catch (Exception ex) {
			log.error(ex);
			throw ex;
		}
	}
	
	/**
	 * Negative test inserting orderdish where order is null
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void negativeInsertOrderDishTest2() throws Exception {
		try {
			OrderDishService orderDishService = new OrderDishServiceImpl(sessionFactory);
			DishService dishService = new DishServiceImpl(sessionFactory);
			orderDishService.insertOrderDish(null, dishService.getById(1));
		} catch (Exception ex) {
			log.error(ex);
			throw ex;
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
			OrderDishService orderDishService = new OrderDishServiceImpl(null);
		} catch (Exception ex) {
			System.out.println(ex);
			log.error(ex);
			throw ex;
		}

	}


	/**
	 * Positive place order Quantity test.
	 * Place the order where the dish offer was already expired
	 * @throws Exception the exception
	 */
	@Test
	public void updateOrderDishTest() throws Exception{
		try {
			OrderDishService orderDishService = new OrderDishServiceImpl(sessionFactory);
			// set parameters
			int buyerId = 1;
			int dishId = 2;
			int orderedQuantity = 2;

			// prepare the arguments to be passed
			List<Integer> dishIds = new ArrayList<Integer>();
			dishIds.add(dishId);
			List<Integer> orderedQuantitys = new ArrayList<Integer>();
			orderedQuantitys.add(orderedQuantity);

			// update the order quantity
			OrderDish orderDish = orderDishService.updateOrderDishQuantity(1, 2);
			assertEquals(2,orderDish.getQuantity());
		} catch (Exception ex) {
			log.error(ex);
			throw ex;
		}

	}
	
	/**
	 * Negative place order quantity test.
	 * Place the order where the dish offer was already expired
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void negativeUpdateOrderDishTest() throws Exception{
		try {
			OrderDishService orderDishService = new OrderDishServiceImpl(sessionFactory);
			// set parameters
			int buyerId = 1;
			int dishId = 2;
			int orderedQuantity = 2;

			// prepare the arguments to be passed
			List<Integer> dishIds = new ArrayList<Integer>();
			dishIds.add(dishId);
			List<Integer> orderedQuantitys = new ArrayList<Integer>();
			orderedQuantitys.add(orderedQuantity);

			// update the order quantity
			orderDishService.updateOrderDishQuantity(1, -1);
		} catch (Exception ex) {
			log.error(ex);
			throw ex;
		}

	}

	/**
	 * Negative place order quantity test.
	 * Place the order where the dish offer was already expired
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void negativeUpdateOrderDishTest2() throws Exception{
		try {
			OrderDishService orderDishService = new OrderDishServiceImpl(sessionFactory);
			// set parameters
			int buyerId = 1;
			int dishId = 2;
			int orderedQuantity = 2;

			// prepare the arguments to be passed
			List<Integer> dishIds = new ArrayList<Integer>();
			dishIds.add(dishId);
			List<Integer> orderedQuantitys = new ArrayList<Integer>();
			orderedQuantitys.add(orderedQuantity);

			// update the order quantity
			orderDishService.updateOrderDishQuantity(10, 1);
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
			OrderService orderService = new OrderServiceImpl(sessionFactory);


			// use services to create data
			countryService.insert("India");
			stateService.insert("Bangalore", 1);
			cityService.insert("Bangalore", 1);
			//
			localityService.insert("Kalyani Magnum", 1, "560043");
			apartmentService.insert("Rainbow PG", 1, 1);
			buyerService.insert("juanid", 1, 1, "9999999999", "sairamch04@foodybuddy.com", "A123", true);
			// buyerService.addBudder("FoodyBuudy","sairamch04@foodybuddy.com","8676903268","200 B",1 ,true);
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
			dishService.addDish("Tandoori", "Very spicy ", 1, 100, sdf.parse(orderBy), sdf.parse(availableFrom),sdf.parse(availableTill), true, true, 10);
			//insert order
			int dishId = 1;
			int quantity = 2;

			List<Integer> dishIds = new ArrayList<Integer>();
			List<Integer> orderedQuantitys = new ArrayList<Integer>();
			orderedQuantitys.add(quantity);
			dishIds.add(dishId);
			orderService.placeOrder(1, dishIds, orderedQuantitys);

		} catch (Exception ex) {

			log.error("Unbale to create dependant data " + ex);
			throw ex;
		}
	}
}
