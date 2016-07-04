package com.foodybuddy.service;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.foodybuddy.model.City;
import com.foodybuddy.model.Dish;
import com.foodybuddy.service.impl.ApartmentServiceImpl;
import com.foodybuddy.service.impl.CityServiceImpl;
import com.foodybuddy.service.impl.CountryServiceImpl;
import com.foodybuddy.service.impl.DishServiceImpl;
import com.foodybuddy.service.impl.LocalityServiceImpl;
import com.foodybuddy.service.impl.SellerServiceImpl;
import com.foodybuddy.service.impl.StateServiceImpl;
import com.foodybuddy.utils.SessionFactoryUtils;
/**
 * The Class DishServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/hibernate.cfg.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DishServiceTest {


	/** The log. */
	static Log log = LogFactory.getLog(DishServiceTest.class.getName());

	/** The session factory. */
	SessionFactory sessionFactory = null;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before

	public void setUp() throws Exception {
		sessionFactory = SessionFactoryUtils.getSessionFactory();
		insertDependentData();	
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After

	public void tearDown() throws Exception {
		//close the session factory
		//SessionFactoryUtils.close();
	}

	
	/**
	 * Test 1 insert dish.
	 */
	@Test
	public void test1InsertDish() {
		try {
			DishService DishService = new DishServiceImpl(SessionFactoryUtils.getSessionFactory());
			String name = "rajma";
			String description = "and chawal";
			Integer sellerId=1;
			Integer price = 100;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date Date1 = sdf.parse("21/12/2012");			
			Date Date2 = sdf.parse("21/12/2012");
			Date Date3 = sdf.parse("21/12/2012");
			Dish newDish = DishService.addDish(name, description, sellerId, price,Date1,Date2,Date3,true, true, 4 );
			assertEquals(newDish.getName(), name);
		} catch (Exception ex) {
			System.out.println(ex);
			log.error(ex);
		}
	}

	
	/**
	 * Test 2 update dish.
	 */
	@Test
	public void test2UpdateDish() {
		String updatedName = "sanj";
		int id=1;
		try {
			DishService Dishservice = new DishServiceImpl(SessionFactoryUtils.getSessionFactory());
			
				Dish updatedDish = Dishservice.updateDish(id,updatedName);
				assertEquals(updatedDish.getName(), updatedName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Test 3 deactivate.
	 */
	@Test
	public void test3Deactivate() {
		int id = 1;
		try {
			DishService Dishservice = new DishServiceImpl(SessionFactoryUtils.getSessionFactory());
			Dish fetchDish = Dishservice.getById(id);
			if (fetchDish != null) {
				Dish deactivatedDish = Dishservice.deactivateDish(id);
				assertEquals(deactivatedDish.getIsActive(), false);
			} else {
				System.out.println("cant deactivate,No user with this id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Test 4 activate.
	 */
	@Test
	public void test4Activate() {
		int id = 1;
		try {
			DishService Dishservice = new DishServiceImpl(SessionFactoryUtils.getSessionFactory());
			Dish fetchDish = Dishservice.getById(id);
			if (fetchDish != null) {
				Dish activatedDish = Dishservice.activateDish(id);
				assertEquals(activatedDish.getIsActive(), true);
			} else {
				System.out.println("cant activate, No user with this id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	/**
	 * Test 6 update dishnegative.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void test6UpdateDishnegative() throws Exception {
		try {
			DishService Dishservice = new DishServiceImpl(SessionFactoryUtils.getSessionFactory());
			Dish updatedDish = Dishservice.updateDish(10,"name");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
	}

	
	/**
	 * Test 7 deactivate negative.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void test7DeactivateNegative() throws Exception {
		int id = 10;
		try {
			DishService Dishservice = new DishServiceImpl(SessionFactoryUtils.getSessionFactory());
			Dish deactivatedDish = Dishservice.deactivateDish(id);

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
	}


	/**
	 * Test 8 activate negative.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void test8ActivateNegative() throws Exception {
		int id = 10;
		try {
			DishService Dishservice = new DishServiceImpl(SessionFactoryUtils.getSessionFactory());
			Dish activatedDish = Dishservice.activateDish(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Test 1 insert dish negative.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void test1InsertDishNegative() throws Exception {
		try {
			DishService DishService = new DishServiceImpl(SessionFactoryUtils.getSessionFactory());
			String name = "rajma";
			String description = "and chawal";
			Integer sellerId=30;
			Integer price = 100;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date Date1 = sdf.parse("21/12/2012");			
			Date Date2 = sdf.parse("21/12/2012");
			Date Date3 = sdf.parse("21/12/2012");
			Dish newDish = DishService.addDish(name, description, sellerId, price,Date1,Date2,Date3,true, true, 4 );
		} catch (Exception ex) {
			System.out.println(ex);
			log.error(ex);
			throw ex;
		}

	}

	/**
	 * Test 1 insert dish negative null.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void test1InsertDishNegativeNull() throws Exception {
		try {
				
			boolean isActive = true;
			DishService DishService = new DishServiceImpl(SessionFactoryUtils.getSessionFactory());
			Dish newDish = DishService.addDish(null, null, null, 0,null,null,null,true, true, 4 );
		} catch (Exception ex) {
			System.out.println(ex);
			log.error(ex);
			throw ex;
		}
	}

	/**
	 * Test negative null deactivate.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void testNegativeNullDeactivate() throws Exception {
		int id = 0;
		try {
			DishService Dishservice = new DishServiceImpl(SessionFactoryUtils.getSessionFactory());
			Dish deactivatedDish = Dishservice.deactivateDish(id);

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Test 6 update dish null negative.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void test6UpdateDishNullNegative() throws Exception {
		try {
			DishService Dishservice = new DishServiceImpl(SessionFactoryUtils.getSessionFactory());
			Dish updatedDish = Dishservice.updateDish(0,null);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
	}

	
	/**
	 * Test negative null activate.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void testNegativeNullActivate() throws Exception {
		int id = 0;
		try {
			DishService Dishservice = new DishServiceImpl(SessionFactoryUtils.getSessionFactory());
			Dish activatedDish = Dishservice.activateDish(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Test 9 negative.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void test9Negative() throws Exception {
	
		try {
			DishService dishservice = new DishServiceImpl(null);
			

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
	}

	/**
	 * Insert dependent data.
	 *
	 * @throws Exception the exception
	 */
	private void insertDependentData()  throws Exception{
		if(sessionFactory == null){
			throw new NullPointerException("session can't be null");
		}
		try{
			//create required services for inserting dependent data
			CountryService countryService = new CountryServiceImpl(sessionFactory);
			StateService stateService = new StateServiceImpl(sessionFactory);
			CityService cityService = new CityServiceImpl(sessionFactory);
			LocalityService localityService = new LocalityServiceImpl(sessionFactory);
			ApartmentService apartmentService = new ApartmentServiceImpl(sessionFactory);
			SellerService sellerService = new SellerServiceImpl(sessionFactory);
			DishService dishService = new DishServiceImpl(sessionFactory);
			
			//use services to create data
			countryService.insert("India");
			stateService.insert("Bangalore", 1);
			cityService.insert("Bangalore", 1);
			City city = cityService.getById(1);
			localityService.insert("loc1", 1, "5678990");
			apartmentService.insert("Rainbow PG", 1, 1);
			sellerService.addSeller("sairam","sairamch04@foodybuddy.com", "8676903268","200 B",1,true);
		}
		catch(Exception ex){
			log.error("Unbale to create dependant data " + ex);
			throw ex;
		}
	}

}