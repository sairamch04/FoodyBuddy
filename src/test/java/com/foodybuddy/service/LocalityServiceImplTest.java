package com.foodybuddy.service;

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

import com.foodybuddy.model.City;
import com.foodybuddy.model.Country;
import com.foodybuddy.model.Locality;
import com.foodybuddy.model.State;
import com.foodybuddy.service.impl.CityServiceImpl;
import com.foodybuddy.service.impl.CountryServiceImpl;
import com.foodybuddy.service.impl.LocalityServiceImpl;
import com.foodybuddy.service.impl.StateServiceImpl;
import com.foodybuddy.utils.SessionFactoryUtils;

/**
 * The Class LocalityServiceImplTest.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(locations = "/hibernate.cfg.xml")
public class LocalityServiceImplTest extends TestCase {

    /** The session factory. */
    private SessionFactory sessionFactory = null;

    /** The country service. */
    private CountryService countryService = null;

    /** The state service. */
    private StateService stateService = null;

    /** The city service. */
    private CityService cityService = null;

    /** The locality service*/;
    
    /** The locality service. */
    private LocalityService localityService = null;
    
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
        this.localityService = new LocalityServiceImpl(this.sessionFactory);
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
     * Test get by id negative zero.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testGetByIdNegativeZero() throws Exception {
        this.localityService.getById(0);
    }

    /**
     * Test get by id negative minus.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testGetByIdNegativeMinus() throws Exception {
        this.localityService.getById(-1);
    }

    /**
     * Test get by id negative null.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testGetByIdNegativeNull() throws Exception {
        this.localityService.getById(null);
    }
    

    /**
     * Test get by id.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetById() throws Exception {

        assertNull(this.localityService.getById(1));

        Country country = this.countryService.insert("INDIA");
        country = this.countryService.getById(1);
        assertNotNull(country);

        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);
        assertNotNull(state);

        City city = this.cityService.insert("BANGALORE", state.getId());
        city = this.cityService.getById(1);
        
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);

        assertNotNull(locality);
        assertEquals(locality.getName(), "JPNAGAR");
        assertEquals(locality.getCity().getName(), "BANGALORE");
        assertEquals(locality.getCity().getState().getName(), "KARNATAKA");
        assertEquals(locality.getCity().getState().getCountry().getName(), "INDIA");

    }

    /**
     * Test get all negative.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testGetAllNegative() throws Exception {

        List<Locality> locality = this.localityService.getAll();
        
    }

    /**
     * Test get all.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetAll() throws Exception {

        Country country = this.countryService.insert("INDIA");
        country = this.countryService.getById(1);
        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);
        City city = this.cityService.insert("BANGALORE", state.getId());
        city = this.cityService.getById(1);
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);

        assertEquals(this.localityService.getAll().size(), 1);

        List<Locality> localities = new ArrayList<Locality>();

        Locality locality1 = new Locality();
        locality1.setCity(city);
        locality1.setName("BILEKAHALLI");
        locality1.setPincode("4376483");
        localities.add(locality1);
        

        Locality locality2 = new Locality();
        locality2.setCity(city);
        locality2.setName("BANNERGHATTA");
        locality2.setPincode("875878");
        localities.add(locality2);

        for (Locality l : localities) {
            this.localityService.insert(l.getName(), l.getCity().getId(), l.getPincode());
        }

        localities.add(0, locality);
        localities.get(1).setId(2);
        localities.get(2).setId(3);

        assertTrue(localities.equals(this.localityService.getAll()));
    }

    /**
     * Test insert negative null.
     *
     * @throws TransactionException the transaction exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeNull() throws TransactionException {
        this.localityService.insert(null, null, null);
    }

    /**
     * Test insert negative empty name.
     *
     * @throws TransactionException the transaction exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeEmptyName() throws TransactionException {
        this.localityService.insert(" ", 1, "5787888");
    }

    /**
     * Test insert negative negatuve city id.
     *
     * @throws TransactionException the transaction exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeNegatuveCityId() throws TransactionException {
        this.localityService.insert("  ", -1, "4566374");
    }

    /**
     * Test insert negative no city.
     *
     * @throws TransactionException the transaction exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeNoCity() throws TransactionException {
        this.localityService.insert("Test", 1, "57648485");
    }

    /**
     * Test insert negative null city id.
     *
     * @throws TransactionException the transaction exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeNullCityId() throws TransactionException {
        this.localityService.insert("Test", null, "6458365");
    }

    /**
     * Test insert negative null pincode.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeNullPincode() throws Exception {
        Country country = this.countryService.insert("INDIA");
        country = this.countryService.getById(1);
        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);
        City city = this.cityService.insert("BANGALORE", state.getId());
        this.localityService.insert("Test", 1, null);
    }
    
    /**
     * Test insert.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert() throws Exception {

        Country country = this.countryService.insert("INDIA");
        country = this.countryService.getById(1);
        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);
        City city = this.cityService.insert("BANGALORE", state.getId());
        city = this.cityService.getById(1);
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);

        assertNotNull(country);
        assertNotNull(state);
        assertNotNull(city);
        
        assertEquals(city.getName(), "BANGALORE");
        assertEquals(city.getState().getName(), "KARNATAKA");
        assertEquals(city.getState().getCountry().getName(), "INDIA");

    }

    /**
     * Test update negative null.
     *
     * @throws TransactionException the transaction exception
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testUpdateNegativeNull() throws TransactionException, Exception {
        this.localityService.update(null);
    }

    /**
     * Test update negative empty name.
     *
     * @throws TransactionException the transaction exception
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testUpdateNegativeEmptyName() throws TransactionException, Exception {
        Country country = this.countryService.insert("INDIA");
        State state = this.stateService.insert("KARNATAKA", 1);
        City city = this.cityService.insert("BANGALORE", 1);
        Locality locality = this.localityService.insert("JPNAGAR", 1, "56788989999");

        locality = this.localityService.getById(1);
        locality.setName("");
        this.localityService.update(locality);
    }

    /**
     * Test update negative null state.
     *
     * @throws TransactionException the transaction exception
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testUpdateNegativeNullState() throws TransactionException, Exception {

        Country country = this.countryService.insert("INDIA");
        country = this.countryService.getById(1);

        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);

        City city = this.cityService.insert("BANGALORE", state.getId());
        city = this.cityService.getById(1);
        
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);
        
        city.setState(null);
        locality.setCity(city);
        this.localityService.update(locality);
    }

    /**
     * Test update negative empty city name.
     *
     * @throws TransactionException the transaction exception
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testUpdateNegativeEmptyCityName() throws TransactionException, Exception {
        Country country = this.countryService.insert("INDIA");
        State state = this.stateService.insert("KARNATAKA", 1);
        City city = this.cityService.insert("BANGALORE", 1);
        Locality locality = this.localityService.insert("JPNAGAR", 1, "4756365");

        locality = this.localityService.getById(1);
        locality.getCity().setName("");
        this.localityService.update(locality);
    }

    /**
     * Test update negative empty state name.
     *
     * @throws TransactionException the transaction exception
     * @throws Exception the exception
     */

    @Test(expected = Exception.class)
    public void testUpdateNegativeEmptyStateName() throws TransactionException, Exception {
        Country country = this.countryService.insert("INDIA");
        State state = this.stateService.insert("KARNATAKA", 1);
        City city = this.cityService.insert("BANGALORE", 1);
        Locality locality = this.localityService.insert("JPNAGAR", 1, "6785999");

        locality = this.localityService.getById(1);
        locality.getCity().getState().setName("");
        this.localityService.update(locality);
    }

    /**
     * Test update negative city null.
     *
     * @throws TransactionException the transaction exception
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testUpdateNegativeCityNull() throws TransactionException, Exception {
        Country country = this.countryService.insert("INDIA");
        State state = this.stateService.insert("KARNATAKA", 1);
        City city = this.cityService.insert("BANGALORE", 1);
        Locality locality = this.localityService.insert("JPNAGAR", 1, "756487");
        
        locality = this.localityService.getById(1);
        locality.setCity(null);
        this.localityService.update(locality);
    }

    /**
     * Test update.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdate() throws Exception {

        Country country = this.countryService.insert("INDIA");
        State state = this.stateService.insert("KARNATAKA", 1);
        City city = this.cityService.insert("BANGALORE", 1);
        Locality locality = this.localityService.insert("JPNAGAR", 1, "756487");
        
        locality = this.localityService.getById(1);

        locality.setName("BILEKAHALLI");
        this.localityService.update(locality);
        locality = this.localityService.getById(1);

        assertNotNull(locality);
        assertEquals(locality.getName(), "BILEKAHALLI");

    }

    /**
     * Test delete negative null.
     */
    @Test(expected = Exception.class)
    public void testDeleteNegativeNull() {
        this.localityService.delete(null);
    }

    /**
     * Test delete.
     *
     * @throws Exception the exception
     */
    @Test
    public void testDelete() throws Exception {

        assertNull(this.localityService.getById(1));

        Country country = this.countryService.insert("INDIA");
        State state = this.stateService.insert("KARNATAKA", 1);
        City city = this.cityService.insert("BANGALORE", 1);
        Locality locality = this.localityService.insert("JPNAGAR", 1, "756487");
        

        locality = this.localityService.getById(1);
        assertNotNull(locality);

        this.localityService.delete(locality);
        assertNull(this.localityService.getById(locality.getId()));

    }

    /**
     * Test constructor negative.
     *
     * @throws TransactionException the transaction exception
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testConstructorNegative() throws TransactionException, Exception {
        LocalityService localityService = new LocalityServiceImpl(null);
    }

}