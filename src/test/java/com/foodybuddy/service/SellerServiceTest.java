package com.foodybuddy.service;

import static org.junit.Assert.*;

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
import com.foodybuddy.model.City;
import com.foodybuddy.model.Locality;
import com.foodybuddy.model.Seller;
import com.foodybuddy.service.impl.SellerServiceImpl;
import com.foodybuddy.utils.SessionFactoryUtils;
import com.foodybuddy.service.impl.ApartmentServiceImpl;
import com.foodybuddy.service.impl.CityServiceImpl;
import com.foodybuddy.service.impl.CountryServiceImpl;
import com.foodybuddy.service.impl.LocalityServiceImpl;
import com.foodybuddy.service.impl.StateServiceImpl;

/**
 * The Class SellerServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/hibernate.cfg.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SellerServiceTest {

	
	/** The log. */
	static Log log = LogFactory.getLog(SellerServiceTest.class.getName());

	
	/** The session factory. */
	SessionFactory sessionFactory = null;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
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
	@After

	public void tearDown() throws Exception {
		
	}

	
	/**
	 * Test 1 insert seller.
	 */
	@Test
	public void test1InsertSeller() {
		try {
			SellerService sellerService = new SellerServiceImpl(SessionFactoryUtils.getSessionFactory());
            Seller newSeller=sellerService.addSeller("sanjana","sa@gmail.com", "9876543210","200 B",1,true);
			assertEquals(newSeller.getName(), "sanjana");
		} catch (Exception ex) {
			System.out.println(ex);
			log.error(ex);
		}
	}

	
	/**
	 * Test 2 update seller.
	 */
	@Test
	public void test2UpdateSeller() {
		String updatedName = "sanj";
		int id=1;
		try {
			SellerService sellerservice = new SellerServiceImpl(SessionFactoryUtils.getSessionFactory());
			
				Seller updatedSeller = sellerservice.updateSeller(id,updatedName);
				assertEquals(updatedSeller.getName(), updatedName);
			
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
			SellerService sellerservice = new SellerServiceImpl(SessionFactoryUtils.getSessionFactory());
			Seller fetchSeller = sellerservice.getById(id);
			if (fetchSeller != null) {
				Seller deactivatedSeller = sellerservice.deactivateSeller(id);
				assertEquals(deactivatedSeller.getIsActive(), false);
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
			SellerService sellerservice = new SellerServiceImpl(SessionFactoryUtils.getSessionFactory());
			Seller fetchSeller = sellerservice.getById(id);
			if (fetchSeller != null) {
				Seller activatedSeller = sellerservice.activateSeller(id);
				assertEquals(activatedSeller.getIsActive(), true);
			} else {
				System.out.println("cant activate, No user with this id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test 5 get seller.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test5getSeller() throws Exception {
		try {
			SellerService sellerService = new SellerServiceImpl(SessionFactoryUtils.getSessionFactory());
			Seller newSeller = sellerService.getById(1);
			assertNotNull(newSeller);
		} catch (Exception ex) {
			System.out.println(ex);
			log.error(ex);
			throw ex;
		}
	}
	
	/**
	 * Test 5 get seller list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test5getSellerList() throws Exception {
		try {
			SellerService sellerService = new SellerServiceImpl(SessionFactoryUtils.getSessionFactory());
			List<Seller> getSeller = sellerService.getList();
			assertNotNull(getSeller);
		} catch (Exception ex) {
			System.out.println(ex);
			log.error(ex);
			throw ex;
		}
	}
	
	
	/**
	 * Test insert seller null negative.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void testInsertSellerNullNegative() throws Exception {
		try {
			SellerService sellerService = new SellerServiceImpl(SessionFactoryUtils.getSessionFactory());
			Seller newSeller = sellerService.addSeller(null, null, null, null, 20, true);
		} catch (Exception ex) {
			System.out.println(ex);
			log.error(ex);
			throw ex;
		}
	}


	/**
	 * Test insert seller negative.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void testInsertSellerNegative() throws Exception{
		try {
			SellerService sellerService = new SellerServiceImpl(SessionFactoryUtils.getSessionFactory());
            Seller newSeller=sellerService.addSeller("sa","sa@gmail.com", "9876543210","200 B",20,true);
			
		} catch (Exception ex) {
			System.out.println(ex);
			log.error(ex);
			throw ex;
		}
	}

	/**
	 * Test 6 update sellernegative.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void test6UpdateSellernegative() throws Exception {
		try {
			SellerService sellerservice = new SellerServiceImpl(SessionFactoryUtils.getSessionFactory());
			Seller updatedSeller = sellerservice.updateSeller(10,"name");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Test 6 update seller nullnegative.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void test6UpdateSellerNullnegative() throws Exception {
		try {
			SellerService sellerservice = new SellerServiceImpl(SessionFactoryUtils.getSessionFactory());
			Seller updatedSeller = sellerservice.updateSeller(0,null);
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
			SellerService sellerservice = new SellerServiceImpl(SessionFactoryUtils.getSessionFactory());
			Seller deactivatedSeller = sellerservice.deactivateSeller(id);

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Test 7 deactivate null negative.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void test7DeactivateNullNegative() throws Exception {
		int id = 0;
		try {
			SellerService sellerservice = new SellerServiceImpl(SessionFactoryUtils.getSessionFactory());
			Seller deactivatedSeller = sellerservice.deactivateSeller(id);

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
			SellerService sellerservice = new SellerServiceImpl(SessionFactoryUtils.getSessionFactory());
			Seller activatedSeller = sellerservice.activateSeller(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
	}


	/**
	 * Test 9 activate null negative.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void test9ActivateNullNegative() throws Exception {
		int id = -1;
		try {
			SellerService sellerservice = new SellerServiceImpl(SessionFactoryUtils.getSessionFactory());
			Seller activatedSeller = sellerservice.activateSeller(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
	}
	
	
	/**
	 * Testget seller null negative.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void testgetSellerNullNegative() throws Exception {
		try {
			SellerService sellerService = new SellerServiceImpl(SessionFactoryUtils.getSessionFactory());
			Seller newSeller = sellerService.getById(0);
		} catch (Exception ex) {
			System.out.println(ex);
			log.error(ex);
			throw ex;
		}
	}
	
	/**
	 * Test 12 negative.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = Exception.class)
	public void test12Negative() throws Exception {
	
		try {
			SellerService sellerservice = new SellerServiceImpl(null);
			Seller activatedSeller = sellerservice.activateSeller(1);

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
			
			//use services to create data
			countryService.insert("India");
			stateService.insert("Bangalore", 1);
			cityService.insert("Bangalore", 1);
			City city = cityService.getById(1);
			localityService.insert("loc1", 1, "5678990");
            apartmentService.insert("Rainbow PG", 1, 1);
			
		}
		catch(Exception ex){
			log.error("Unbale to create dependant data " + ex);
			throw ex;
		}
	}

}