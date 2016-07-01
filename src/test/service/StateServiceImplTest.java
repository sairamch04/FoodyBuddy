package com.foodybuddy.service;

import com.foodybuddy.model.Country;
import com.foodybuddy.model.State;
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

/**
 * The Class StateServiceImplTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/hibernate.cfg.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StateServiceImplTest extends TestCase {

	/** The session factory. */
	private SessionFactory sessionFactory = null;

	/** The country service. */
	private CountryService countryService = null;

	/** The state service. */
	private StateService stateService = null;

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
		this.stateService.getById(0);
	}

	/**
	 * Test 02 get by id negative.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testGetByIdNegativeMinus() throws Exception {
		this.stateService.getById(-1);
	}

	/**
	 * Test 03 get by id negative.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testGetByIdNegativeNull() throws Exception {
		this.stateService.getById(null);
	}

	/**
	 * Test 04 get by id.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetById() throws Exception {
		assertNull(this.stateService.getById(1));

		Country country = this.countryService.insert("Azkaban");
		country = this.countryService.getById(1);

		assertNotNull(country);
		// assertTrue(country.getId() > 0);

		State state = this.stateService.insert("Prison", country.getId());
		state = this.stateService.getById(1);

		assertNotNull(this.stateService.getById(state.getId()));
		assertEquals(this.stateService.getById(state.getId()).getName(), "Prison");
		assertTrue(this.stateService.getById(state.getId()).getCountry()
				.equals(this.countryService.getById(country.getId())));
	}

	/**
	 * Test 05 get all negative.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testGetAllNegative() throws Exception {
		List<State> state = this.stateService.getAll();
	}

	/**
	 * Test 05 get all.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetAll() throws Exception {

		Country country = this.countryService.insert("Azkaban");
		country = this.countryService.getById(1);
		State state = this.stateService.insert("Prison", country.getId());
		assertEquals(this.stateService.getAll().size(), 1);

		List<State> states = new ArrayList<State>();

		State state1 = new State();
		state1.setCountry(country);
		state1.setName("Detention Centre");
		states.add(state1);

		State state2 = new State();
		state2.setCountry(country);
		state2.setName("Cafeteria");
		states.add(state2);

		for (State s : states) {
			this.stateService.insert(s.getName(), s.getCountry().getId());
		}

		assertEquals(this.stateService.getAll().size(), 3);
		state.setId(1);
		states.add(0, state);
		states.get(1).setId(2);
		states.get(2).setId(3);

		assertTrue(states.equals(this.stateService.getAll()));
	}

	/**
	 * Test 06 insert negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 */
	@Test(expected = Exception.class)
	public void testInsertNegativeNull() throws TransactionException {
		this.stateService.insert(null, null);
	}

	/**
	 * Test 07 insert negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 */
	@Test(expected = Exception.class)
	public void testInsertNegativeEmptyString() throws TransactionException {
		this.stateService.insert(" ", 1);
	}

	/**
	 * Test 08 insert negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 */
	@Test(expected = Exception.class)
	public void testInsertNegativeMinus() throws TransactionException {
		this.stateService.insert("  ", -1);
	}

	/**
	 * Test 09 insert negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 */
	@Test(expected = Exception.class)
	public void testInsertNegativeNoCountry() throws TransactionException {
		this.stateService.insert("Test", 1);
	}

	/**
	 * Test 11 insert negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 */
	@Test(expected = Exception.class)
	public void testInsertNegativeNullCountry() throws TransactionException {
		this.stateService.insert("Test", null);
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
		State state = this.stateService.insert("The North", 1);

		assertNotNull(state);
		// assertTrue(this..getId() > 0);
		assertEquals(state.getName(), "The North");
		assertEquals(state.getCountry().getName(), "The Seven Kingdoms");

		country.setId(1);
		state.setName("Kings's Landing");
		state.setCountry(country);

		this.stateService.insert(state.getName(), state.getCountry().getId());
		state.setId(2);
		assertTrue(state.equals(this.stateService.getById(2)));
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
		this.stateService.update(null);
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
	public void testUpdateNegativeEmptyString() throws TransactionException, Exception {
		Country country = this.countryService.insert("The Seven Kingdoms");
		State state = this.stateService.insert("The North", 1);
		state = this.stateService.getById(1);
		state.setName("");
		this.stateService.update(state);
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
	public void testUpdateNegativeWhitespaces() throws TransactionException, Exception {
		Country country = this.countryService.insert("The Seven Kingdoms");
		State state = this.stateService.insert("The North", 1);
		state = this.stateService.getById(1);
		state.setName("  ");
		this.stateService.update(state);
	}

	/**
	 * Test 14 update negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testUpdateNegativeCountry() throws TransactionException, Exception {
		Country country = this.countryService.insert("The Seven Kingdoms");
		State state = this.stateService.insert("The North", 1);
		state = this.stateService.getById(1);
		state.getCountry().setName("");
		this.stateService.update(state);
	}

	/**
	 * Test 15 update negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testUpdateNegativeCountryWhitespaces() throws TransactionException, Exception {
		State state = this.stateService.getById(5);
		state.getCountry().setName("  ");
		this.stateService.update(state);
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
	public void testUpdateNegativeNullCountry() throws TransactionException, Exception {
		Country country = this.countryService.insert("The Seven Kingdoms");
		State state = this.stateService.insert("The North", 1);
		state = this.stateService.getById(1);
		state.setCountry(null);
		this.stateService.update(state);
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
		state.setName("Stark Empire");
		this.stateService.update(state);
		state = this.stateService.getById(1);

		assertNotNull(state);
		assertEquals(state.getName(), "Stark Empire");

		country = this.countryService.insert("The Seven Queendoms");
		country = this.countryService.getById(2);
		state.setCountry(country);

		state = this.stateService.update(state);
		state = this.stateService.getById(1);

		assertNotNull(state);
		assertTrue(state.getCountry().equals(this.countryService.getById(2)));
	}

	/**
	 * Test 16 delete negative.
	 */
	@Test(expected = Exception.class)
	public void testDeleteNegativeNull() {
		this.stateService.delete(null);
	}

	/**
	 * Test 17 delete.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testDelete() throws Exception {

		Country country = this.countryService.insert("The Seven Kingdoms");
		State state = this.stateService.insert("The North", 1);
		state = this.stateService.getById(1);

		this.stateService.delete(state);
		assertNull(this.stateService.getById(1));
	}

	/**
	 * Test 20 negative.
	 *
	 * @throws TransactionException
	 *             the transaction exception
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = Exception.class)
	public void testConstructorNegative() throws TransactionException, Exception {
		StateService stateServices = new StateServiceImpl(null);
	}

}