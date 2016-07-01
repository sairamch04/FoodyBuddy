package com.foodybuddy.service;

import com.foodybuddy.model.Country;
import com.foodybuddy.service.impl.CountryServiceImpl;
import com.foodybuddy.utils.SessionFactoryUtils;
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

/**
 * The Class CountryServiceImplTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/hibernate.cfg.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CountryServiceImplTest extends TestCase {

	/** The session factory. */
	private SessionFactory sessionFactory = null;

	/** The country service. */
	private CountryService countryService = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	@Override
	public void setUp() throws Exception {
		this.sessionFactory = SessionFactoryUtils.getSessionFactory();
		this.countryService = new CountryServiceImpl(sessionFactory);
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
		this.countryService.getById(0);
	}

	/**
	 * Test 02 get by id negative.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testGetByIdNegativeMinus() throws Exception {
		this.countryService.getById(-1);
	}

	/**
	 * Test 03 get by id negative.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testGetByIdNegativeNull() throws Exception {
		this.countryService.getById(null);
	}

	/**
	 * Test 04 get by id.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetById() throws Exception {
		assertNull(this.countryService.getById(1));
		Country country = this.countryService.insert("Azkaban");
		assertNotNull(country);
		assertEquals(country.getName(), "Azkaban");
		assertTrue(country.equals(this.countryService.getById(1)));
		country.setName("LaLaLand");
		assertFalse(country.equals(this.countryService.getById(1)));
	}

	/**
	 * Test 05 get all.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testGetAllNegative() throws Exception {
		List<Country> countries = this.countryService.getAll();
	}

	/**
	 * Test 06 get all.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetAll() throws Exception {

		this.countryService.insert("LaLaLand");
		List<Country> countries = this.countryService.getAll();

		assertEquals(countries.size(), 1);
		assertTrue(countries.get(0).getId() > 0);
		assertEquals(countries.get(0).getName(), "LaLaLand");
	}

	/**
	 * Test 06 insert negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 */
	@Test(expected = Exception.class)
	public void testInsertNegativeNull() throws TransactionException {
		this.countryService.insert(null);
	}

	/**
	 * Test 07 insert negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 */
	@Test(expected = Exception.class)
	public void testInsertNegativeEmptyString() throws TransactionException {
		this.countryService.insert("");
	}

	/**
	 * Test 08 insert negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 */
	@Test(expected = Exception.class)
	public void testInsertNegativeWhitespaces() throws TransactionException {
		this.countryService.insert("  ");
	}

	/**
	 * Test 09 insert.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test()
	public void testInsert() throws Exception {

		Country country = this.countryService.insert("Wonderland");

		assertTrue(country.getId() > 0);
		assertEquals(this.countryService.getById(1).getName(), "Wonderland");

		country = new Country();
		country.setName("Timbuktoo");
		country.setId(2);

		this.countryService.insert(country.getName());
		assertTrue(country.equals(this.countryService.getById(2)));
	}

	/**
	 * Test 10 update negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 */
	@Test(expected = Exception.class)
	public void testUpdateNegativeNull() throws TransactionException {
		this.countryService.update(null);
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
	public void testUpdateNegativeEmptyString() throws TransactionException, Exception {
		Country country = this.countryService.insert("Azkaban");
		country.setName("");
		country.setId(1);
		this.countryService.update(country);
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
	public void testUpdateNegativeWhitespaces() throws TransactionException, Exception {
		Country country = this.countryService.getById(5);
		country.setName("  ");
		this.countryService.update(country);
	}

	/**
	 * Test 13 update.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testUpdate() throws Exception {

		Country country = this.countryService.insert("Narnia");
		country.setName("Narnia Rocks");
		country = this.countryService.update(country);

		assertNotNull(country);
		assertNotNull(this.countryService.getById(1));
		assertTrue(country.equals(this.countryService.getById(1)));
	}

	/**
	 * Test 14 delete negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 */
	@Test(expected = Exception.class)
	public void testDeleteNegativeNull() throws TransactionException {
		this.countryService.delete(null);
	}

	/**
	 * Test 14 delete.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testDelete() throws Exception {

		Country country = this.countryService.insert("Narnia");
		this.countryService.delete(country);
		assertNull(this.countryService.getById(1));
	}

	/**
	 * Test 17 negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testConstructorNegative() throws TransactionException, Exception {
		CountryService countryService = new CountryServiceImpl(null);
	}

}