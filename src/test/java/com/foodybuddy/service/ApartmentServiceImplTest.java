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

import com.foodybuddy.model.Apartment;
import com.foodybuddy.model.City;
import com.foodybuddy.model.Country;
import com.foodybuddy.model.Locality;
import com.foodybuddy.model.State;
import com.foodybuddy.service.impl.ApartmentServiceImpl;
import com.foodybuddy.service.impl.CityServiceImpl;
import com.foodybuddy.service.impl.CountryServiceImpl;
import com.foodybuddy.service.impl.LocalityServiceImpl;
import com.foodybuddy.service.impl.StateServiceImpl;
import com.foodybuddy.utils.SessionFactoryUtils;

/**
 * The Class ApartmentServiceImplTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(locations = "/hibernate.cfg.xml")
public class ApartmentServiceImplTest extends TestCase {
    
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
    
    /** The apartment service. */
    private ApartmentService apartmentService = null;
    
    /* (non-Javadoc)
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
        this.apartmentService = new ApartmentServiceImpl(this.sessionFactory);
    }
    
    /* (non-Javadoc)
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
        this.apartmentService.getById(0);
    }

    /**
     * Test get by id negative minus.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testGetByIdNegativeMinus() throws Exception {
        this.apartmentService.getById(-1);
    }

    /**
     * Test get by id negative null.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testGetByIdNegativeNull() throws Exception {
        this.apartmentService.getById(null);
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
        assertNotNull(city);
        
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);
        assertNotNull(locality);
        
        Apartment apartment = this.apartmentService.insert("MARS", locality.getId(), 300);
        apartment = apartmentService.getById(1);
        
        assertNotNull(apartment);
        assertEquals(apartment.getName(), "MARS");
        assertEquals(apartment.getLocality().getName(), "JPNAGAR");
        assertEquals(apartment.getLocality().getCity().getName(), "BANGALORE");
        assertEquals(apartment.getLocality().getCity().getState().getName(), "KARNATAKA");
        assertEquals(apartment.getLocality().getCity().getState().getCountry().getName(), "INDIA");
    }
    
    /**
     * Test get all negative.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testGetAllNegative() throws Exception {
        apartmentService.getAll();
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
        Apartment apartment = this.apartmentService.insert("MARS",locality.getId(), 300);
        apartment = this.apartmentService.getById(1);
        
        assertEquals(this.apartmentService.getAll().size(), 1);

        List<Apartment> apartments = new ArrayList<Apartment>();

        Apartment apartment1 = new Apartment();
        apartment1.setLocality(locality);
        apartment1.setName("FORTUNA");
        apartment1.setBlockNumber(109);
        
        apartments.add(apartment1);
        

        Apartment apartment2 = new Apartment();
        apartment2.setLocality(locality);
        apartment2.setName("LAKSHMI");
        apartment2.setBlockNumber(574);
        apartments.add(apartment2);

        for (Apartment a : apartments) {
            this.apartmentService.insert(a.getName(), a.getLocality().getId(), a.getBlockNumber());
        }

        apartments.add(0, apartment);
        apartments.get(1).setId(2);
        apartments.get(2).setId(3);

        assertTrue(apartments.equals(this.apartmentService.getAll()));
    }
    
    /**
     * Test insert nagative null name.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsertNagativeNullName() throws Exception {
        Country country = this.countryService.insert("INDIA");
        country = this.countryService.getById(1);
        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);
        City city = this.cityService.insert("BANGALORE", state.getId());
        city = this.cityService.getById(1);
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);
        this.apartmentService.insert(null, locality.getId(), 300);
    }
    
    /**
     * Test insert negative empty name.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeEmptyName() throws Exception {
        Country country = this.countryService.insert("INDIA");
        country = this.countryService.getById(1);
        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);
        City city = this.cityService.insert("BANGALORE", state.getId());
        city = this.cityService.getById(1);
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);
        this.apartmentService.insert("", locality.getId(), 345);
    }
    
    /**
     * Test insert negative zero locality id.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeZeroLocalityId() throws Exception {
        this.apartmentService.insert("Test", 0, 300);
    }
    
    /**
     * Test insert negative negative locality id.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeNegativeLocalityId() throws Exception {
        this.apartmentService.insert("Test", -10, 300);
    }
    
    /**
     * Test insert negative null locality id.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeNullLocalityId() throws Exception {
        this.apartmentService.insert("Test", null, 300);
    }
    
    
    /**
     * Test insert negative null locality.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeNullLocality() throws Exception {
        this.apartmentService.insert("Test", 1, 300);
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
        this.apartmentService.insert("MARS", locality.getId(), 300);
        
        Apartment apartment = this.apartmentService.getById(1);
        
        assertNotNull(apartment);
        assertEquals(apartment.getName(), "MARS");
        assertEquals(apartment.getLocality().getName(), "JPNAGAR");
        assertEquals(apartment.getLocality().getCity().getName(), "BANGALORE");
        
    }
    
    /**
     * Test update negative null apartment.
     */
    @Test(expected = Exception.class)
    public void testUpdateNegativeNullApartment() {
        this.apartmentService.update(null);
    }
    
    /**
     * Test update negative null apartment name.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testUpdateNegativeNullApartmentName() throws Exception {
        Country country = this.countryService.insert("INDIA");
        country = this.countryService.getById(1);
        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);
        City city = this.cityService.insert("BANGALORE", state.getId());
        city = this.cityService.getById(1);
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);
        this.apartmentService.insert("MARS", locality.getId(), 300);
        
        Apartment apartment = this.apartmentService.getById(1);
        apartment.setName(null);
        this.apartmentService.update(apartment);
    }
    
    /**
     * Test update negative empty apartment name.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testUpdateNegativeEmptyApartmentName() throws Exception {
        Country country = this.countryService.insert("INDIA");
        country = this.countryService.getById(1);
        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);
        City city = this.cityService.insert("BANGALORE", state.getId());
        city = this.cityService.getById(1);
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);
        this.apartmentService.insert("MARS", locality.getId(), 300);
        
        Apartment apartment = this.apartmentService.getById(1);
        apartment.setName("");
        this.apartmentService.update(apartment);
    }
    
    
    /**
     * Test update negative null locality.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testUpdateNegativeNullLocality() throws Exception {
        Country country = this.countryService.insert("INDIA");
        country = this.countryService.getById(1);
        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);
        City city = this.cityService.insert("BANGALORE", state.getId());
        city = this.cityService.getById(1);
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);
        this.apartmentService.insert("MARS", locality.getId(), 300);
        
        Apartment apartment = this.apartmentService.getById(1);
        apartment.setLocality(null);
        this.apartmentService.update(apartment);
    }
    
    /**
     * Test update negative empty locality name.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testUpdateNegativeEmptyLocalityName() throws Exception {
        Country country = this.countryService.insert("INDIA");
        country = this.countryService.getById(1);
        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);
        City city = this.cityService.insert("BANGALORE", state.getId());
        city = this.cityService.getById(1);
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);
        this.apartmentService.insert("MARS", locality.getId(), 300);
        
        Apartment apartment = this.apartmentService.getById(1);
        apartment.getLocality().setName("");;
        this.apartmentService.update(apartment);
    }
    
    /**
     * Test update negative null city.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testUpdateNegativeNullCity() throws Exception {
        Country country = this.countryService.insert("INDIA");
        country = this.countryService.getById(1);
        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);
        City city = this.cityService.insert("BANGALORE", state.getId());
        city = this.cityService.getById(1);
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);
        this.apartmentService.insert("MARS", locality.getId(), 300);
        
        Apartment apartment = this.apartmentService.getById(1);
        apartment.getLocality().setCity(null);
        this.apartmentService.update(apartment);
    }
    
    /**
     * Test update negative empty city name.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testUpdateNegativeEmptyCityName() throws Exception {
        Country country = this.countryService.insert("INDIA");
        country = this.countryService.getById(1);
        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);
        City city = this.cityService.insert("BANGALORE", state.getId());
        city = this.cityService.getById(1);
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);
        this.apartmentService.insert("MARS", locality.getId(), 300);
        
        Apartment apartment = this.apartmentService.getById(1);
        apartment.getLocality().getCity().setName("");
        this.apartmentService.update(apartment);
    }
    
    /**
     * Test update.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdate() throws Exception {
        Country country = this.countryService.insert("INDIA");
        country = this.countryService.getById(1);
        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);
        City city = this.cityService.insert("BANGALORE", state.getId());
        city = this.cityService.getById(1);
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);
        this.apartmentService.insert("MARS", locality.getId(), 300);
        
        Apartment apartment = this.apartmentService.getById(1);
        apartment.setName("FORTUNA");
        this.apartmentService.update(apartment);
        
        apartment = this.apartmentService.getById(1);
        assertEquals(apartment.getName(), "FORTUNA");
        
    }
    
    /**
     * Test delete negative null.
     */
    @Test(expected = Exception.class)
    public void testDeleteNegativeNull() {
        this.apartmentService.delete(null);
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
        country = this.countryService.getById(1);
        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);
        City city = this.cityService.insert("BANGALORE", state.getId());
        city = this.cityService.getById(1);
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);
        this.apartmentService.insert("MARS", locality.getId(), 300);
        locality = this.localityService.getById(1);
        this.apartmentService.insert("MARS", locality.getId(), 300);
        Apartment apartment = this.apartmentService.getById(1);
        
        assertNotNull(apartment);

        this.apartmentService.delete(apartment);
        assertNull(this.apartmentService.getById(apartment.getId()));

    }
    

    /**
     * Test constructor negative.
     *
     * @throws TransactionException the transaction exception
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testConstructorNegative() throws TransactionException, Exception {
        ApartmentService apartmentService = new ApartmentServiceImpl(null);
    }

}
