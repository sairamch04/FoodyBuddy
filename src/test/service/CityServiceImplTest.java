package com.foodybuddy.service;

import com.foodybuddy.model.City;
import com.foodybuddy.model.Country;
import com.foodybuddy.model.State;
import com.foodybuddy.service.impl.CityServiceImpl;
import com.foodybuddy.service.impl.CountryServiceImpl;
import com.foodybuddy.service.impl.StateServiceImpl;
import com.foodybuddy.utils.SessionFactoryUtils;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.hibernate.SessionFactory;
import org.hibernate.TransactionException;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// TODO: Auto-generated Javadoc
/**
 * The Class StateServiceImplTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(locations = "/hibernate.cfg.xml")
public class CityServiceImplTest extends TestCase {

	/** The session factory. */
	private SessionFactory sessionFactory = null;

	/** The country service. */
	private CountryService countryService = null;

	/** The state service. */
	private StateService stateService = null;

	/** The city service. */
	private CityService cityService = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	@Override
	public void setUp() throws Exception {
		this.sessionFactory = SessionFactoryUtils.getSessionFactory();
		this.countryService = new CountryServiceImpl(this.sessionFactory);
		this.stateService = new StateServiceImpl(this.sessionFactory);
		this.cityService = new CityServiceImpl(this.sessionFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	@Override
	public void tearDown() throws Exception {
		if (this.sessionFactory != null) {
			this.sessionFactory.close();
		}
	}

	/**
	 * Test 01 get by id negative.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testGetByIdNegativeZero() throws Exception {
		this.cityService.getById(0);
	}

	/**
	 * Test 02 get by id negative.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testGetByIdNegativeMinus() throws Exception {
		this.cityService.getById(-1);
	}

	/**
	 * Test 03 get by id negative.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testGetByIdNegativeNull() throws Exception {
		this.cityService.getById(null);
	}

	/**
	 * Test 04 get by id.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetById() throws Exception {

		assertNull(this.cityService.getById(1));

		Country country = this.countryService.insert("The Seven Kingdoms");
		country = this.countryService.getById(1);
		assertNotNull(country);

		State state = this.stateService.insert("The North", country.getId());
		state = this.stateService.getById(1);
		assertNotNull(state);

		City city = this.cityService.insert("Winterfell", state.getId());
		city = this.cityService.getById(1);

		assertNotNull(city);
		assertEquals(city.getName(), "Winterfell");
		assertEquals(city.getState().getName(), "The North");
		assertEquals(city.getState().getCountry().getName(), "The Seven Kingdoms");

	}

	/**
	 * Test get all negative.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testGetAllNegative() throws Exception {

		List<City> city = this.cityService.getAll();
		System.out.println(city);
	}

	/**
	 * Test 05 get all.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetAll() throws Exception {

		Country country = this.countryService.insert("The Seven Kingdoms");
		country = this.countryService.getById(1);
		State state = this.stateService.insert("The North", country.getId());
		state = this.stateService.getById(1);
		City city = this.cityService.insert("Winterfell", state.getId());
		city = this.cityService.getById(1);

		assertEquals(this.cityService.getAll().size(), 1);

		List<City> cities = new ArrayList<City>();

		City city1 = new City();
		city1.setState(state);
		city1.setName("King's Landing");
		cities.add(city1);

		City city2 = new City();
		city2.setState(state);
		city2.setName("Dorne");
		cities.add(city2);

		for (City c : cities) {
			this.cityService.insert(c.getName(), c.getState().getId());
		}

		// assertEquals(this.stateService.getAll().size(), 3);
		cities.add(0, city);
		cities.get(1).setId(2);
		cities.get(2).setId(3);

		assertTrue(cities.equals(this.cityService.getAll()));
	}

	/**
	 * Test 06 insert negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 */
	@Test(expected = Exception.class)
	public void testInsertNegativeNull() throws TransactionException {
		this.cityService.insert(null, null);
	}

	/**
	 * Test 07 insert negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 */
	@Test(expected = Exception.class)
	public void testInsertNegativeEmptyString() throws TransactionException {
		this.cityService.insert(" ", 1);
	}

	/**
	 * Test 08 insert negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 */
	@Test(expected = Exception.class)
	public void testInsertNegativeMinus() throws TransactionException {
		this.cityService.insert("  ", -1);
	}

	/**
	 * Test 09 insert negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 */
	@Test(expected = Exception.class)
	public void testInsertNegativeNoCountry() throws TransactionException {
		this.cityService.insert("Test", 1);
	}

	/**
	 * Test insert negative null country.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 */
	@Test(expected = Exception.class)
	public void testInsertNegativeNullCountry() throws TransactionException {
		this.cityService.insert("Test", null);
	}

	/**
	 * Test 10 insert.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test()
	public void testInsert() throws Exception {

		Country country = this.countryService.insert("The Seven Kingdoms");
		country = this.countryService.getById(1);
		State state = this.stateService.insert("The North", country.getId());
		state = this.stateService.getById(1);
		City city = this.cityService.insert("Winterfell", state.getId());
		city = this.cityService.getById(1);

		assertNotNull(country);
		assertNotNull(state);
		assertNotNull(city);
		// assertTrue(this..getId() > 0);
		assertEquals(city.getName(), "Winterfell");
		assertEquals(city.getState().getName(), "The North");
		assertEquals(city.getState().getCountry().getName(), "The Seven Kingdoms");

	}

	/**
	 * Test 11 update negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testUpdateNegativeNull() throws TransactionException, Exception {
		this.cityService.update(null);
	}

	/**
	 * Test update negative empty string.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testUpdateNegativeEmptyString() throws TransactionException, Exception {
		Country country = this.countryService.insert("The Seven Kingdoms");
		State state = this.stateService.insert("The North", 1);
		City city = this.cityService.insert("Winterfell", 1);

		city = this.cityService.getById(1);
		city.setName("");
		this.cityService.update(city);
	}

	/**
	 * Test update country negative null.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testUpdateCountryNegativeNull() throws TransactionException, Exception {

		Country country = this.countryService.insert("The Seven Kingdoms");
		country = this.countryService.getById(1);

		State state = this.stateService.insert("The North", country.getId());
		state = this.stateService.getById(1);

		City city = this.cityService.insert("Winterfell", state.getId());
		city = this.cityService.getById(1);

		state.setCountry(null);
		city.setState(state);
		this.cityService.update(city);
	}

	/**
	 * Test 12 update negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testUpdateNegativeStateEmptyString() throws TransactionException, Exception {
		Country country = this.countryService.insert("The Seven Kingdoms");
		State state = this.stateService.insert("The North", 1);
		City city = this.cityService.insert("Winterfell", 1);

		city = this.cityService.getById(1);
		city.getState().setName("");
		this.cityService.update(city);
	}

	/**
	 * Test 13 update negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 * @throws Exception
	 *             the exception
	 */

	@Test(expected = Exception.class)
	public void testUpdateNegativeCountryEmptyString() throws TransactionException, Exception {
		Country country = this.countryService.insert("The Seven Kingdoms");
		State state = this.stateService.insert("The North", 1);
		City city = this.cityService.insert("Winterfell", 1);

		city = this.cityService.getById(1);
		city.getState().getCountry().setName("");
		this.cityService.update(city);
	}

	/**
	 * Test 16 update negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testUpdateNegativeStateNull() throws TransactionException, Exception {
		Country country = this.countryService.insert("The Seven Kingdoms");
		State state = this.stateService.insert("The North", 1);
		City city = this.cityService.insert("Winterfell", 1);

		city = this.cityService.getById(1);
		city.setState(null);
		this.cityService.update(city);
	}

	/**
	 * Test 15 update.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testUpdate() throws Exception {

		Country country = this.countryService.insert("The Seven Kingdoms");
		State state = this.stateService.insert("The North", 1);
		City city = this.cityService.insert("Winterfell", 1);
		city = this.cityService.getById(1);

		city.setName("Stark City");
		this.cityService.update(city);
		city = this.cityService.getById(1);

		assertNotNull(city);
		assertEquals(city.getName(), "Stark City");

	}

	/**
	 * Test 16 delete negative.
	 */
	@Test(expected = Exception.class)
	public void testDeleteNegativeNull() {
		this.cityService.delete(null);
	}

	/**
	 * Test 17 delete.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testDelete() throws Exception {

		assertNull(this.cityService.getById(1));

		Country country = this.countryService.insert("The Seven Kingdoms");
		State state = this.stateService.insert("The North", 1);
		City city = this.cityService.insert("Winterfell", 1);

		city = this.cityService.getById(1);
		assertNotNull(city);

		this.cityService.delete(city);
		assertNull(this.cityService.getById(city.getId()));

	}

	/**
	 * Test constructor negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testConstructorNegative() throws TransactionException, Exception {
		CityService cityServices = new CityServiceImpl(null);
	}

}