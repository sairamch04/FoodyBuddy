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
import com.foodybuddy.model.Buyer;
import com.foodybuddy.model.City;
import com.foodybuddy.model.Country;
import com.foodybuddy.model.Locality;
import com.foodybuddy.model.State;
import com.foodybuddy.service.impl.ApartmentServiceImpl;
import com.foodybuddy.service.impl.BuyerServiceImpl;
import com.foodybuddy.service.impl.CityServiceImpl;
import com.foodybuddy.service.impl.CountryServiceImpl;
import com.foodybuddy.service.impl.LocalityServiceImpl;
import com.foodybuddy.service.impl.StateServiceImpl;
import com.foodybuddy.utils.SessionFactoryUtils;

/**
 * The Class BuyerServiceImplTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(locations = "/hibernate.cfg.xml")
public class BuyerServiceImplTest extends TestCase {
    
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
    
    /** The buyer service. */
    private BuyerService buyerService = null;
    
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
        this.buyerService = new BuyerServiceImpl(this.sessionFactory);
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
        this.buyerService.getById(0);
    }

    /**
     * Test get by id negative minus.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testGetByIdNegativeMinus() throws Exception {
        this.buyerService.getById(-1);
    }

    /**
     * Test get by id negative null.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testGetByIdNegativeNull() throws Exception {
        this.buyerService.getById(null);
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
        apartment = this.apartmentService.getById(1);
        assertNotNull(apartment);
        
        Buyer buyer = this.buyerService.insert("SAINATH", apartment.getId(), 10, "90908219617", "sainathbatthala@gmail.com", "300 C", true);
        buyer = this.buyerService.getById(1);
        
        assertNotNull(buyer);
        assertEquals(buyer.getName(), "SAINATH");
        assertEquals(buyer.getApartment().getName(), "MARS");
        assertEquals(buyer.getApartment().getLocality().getName(), "JPNAGAR");
        assertEquals(buyer.getApartment().getLocality().getCity().getName(), "BANGALORE");
        assertEquals(buyer.getApartment().getLocality().getCity().getState().getName(), "KARNATAKA");
        assertEquals(buyer.getApartment().getLocality().getCity().getState().getCountry().getName(), "INDIA");
    }
    
    /**
     * Test get all negative.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testGetAllNegative() throws Exception {
        buyerService.getAll();
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
        Buyer buyer = this.buyerService.insert("SAINATH", apartment.getId(), 10, "90908219617", "sainathbatthala@gmail.com", "300 C", true);
        buyer = this.buyerService.getById(1);
        
        
        assertEquals(this.buyerService.getAll().size(), 1);

        List<Buyer> buyers = new ArrayList<Buyer>();

        Buyer buyer1 = new Buyer();
        buyer1.setApartment(apartment);
        buyer1.setName("DEEPAK");
        buyer1.setLastModifiedById(9);
        buyer1.setMobileNumber("9876543212");
        buyer1.setEmail("deepak@wytfyrfyt.com");
        buyer1.setFlatNumber("109 B");
        buyer1.setIsActive(true);
        
        buyers.add(buyer1);
        
        Buyer buyer2 = new Buyer();
        buyer2.setApartment(apartment);
        buyer2.setName("SAIRAM");
        buyer2.setLastModifiedById(87);
        buyer2.setMobileNumber("9876545673");
        buyer2.setEmail("sairam@fjguvg.com");
        buyer2.setFlatNumber("19 B");
        buyer2.setIsActive(true);
        
        buyers.add(buyer2);
        
        for (Buyer b : buyers) {
            this.buyerService.insert(b.getName(), b.getApartment().getId(), b.getLastModifiedById(), b.getMobileNumber(), b.getEmail(), b.getFlatNumber(), b.getIsActive());
        }

        buyers.add(0, buyer);
        buyers.get(1).setId(2);
        buyers.get(2).setId(3);

        assertTrue(buyers.equals(this.buyerService.getAll()));
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
        Apartment apartment = this.apartmentService.insert("MARS",locality.getId(), 300);
        apartment = this.apartmentService.getById(1);
        
        Buyer buyer = this.buyerService.insert(null, apartment.getId(), 10, "90908219617", "sainathbatthala@gmail.com", "300 C", true);
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
        Apartment apartment = this.apartmentService.insert("MARS",locality.getId(), 300);
        apartment = this.apartmentService.getById(1);
        
        Buyer buyer = this.buyerService.insert("", apartment.getId(), 10, "90908219617", "sainathbatthala@gmail.com", "300 C", true);
    }
    
    /**
     * Test insert negative zero apartment id.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeZeroApartmentId() throws Exception {
        Buyer buyer = this.buyerService.insert("SAINATH", 0, 10, "90908219617", "sainathbatthala@gmail.com", "300 C", true);
    }
    
    /**
     * Test insert negative negative apartment id.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeNegativeApartmentId() throws Exception {
        Buyer buyer = this.buyerService.insert("SAINATH",-10, 10, "90908219617", "sainathbatthala@gmail.com", "300 C", true);
    }
    
    /**
     * Test insert negative null apartment id.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeNullApartmentId() throws Exception {
        Buyer buyer = this.buyerService.insert("SAINATH", null, 10, "90908219617", "sainathbatthala@gmail.com", "300 C", true);
    }
    
    /**
     * Test insert negative null apartment.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeNullApartment() throws Exception {
        Buyer buyer = this.buyerService.insert("SAINATH", 1, 10, "90908219617", "sainathbatthala@gmail.com", "300 C", true);
    }
    
    /**
     * Test insert negative null last modified by id.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeNullLastModifiedById() throws Exception {
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
        
        Buyer buyer = this.buyerService.insert("SAINATH", apartment.getId(), null, "9908219617", "sainathbatthala@gmail.com", "300 C", true);
    }
    
    /**
     * Test insert negative null mobile number.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeNullMobileNumber() throws Exception {
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
        
        Buyer buyer = this.buyerService.insert("SAINATH", apartment.getId(), 10, null, "sainathbatthala@gmail.com", "300 C", true);
    }
    
    /**
     * Test insert negative null email.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeNullEmail() throws Exception {
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
        
        Buyer buyer = this.buyerService.insert("SAINATH", apartment.getId(), 10, "9908219617", null, "300 C", true);
    }
    
    /**
     * Test insert negative null flat number.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeNullFlatNumber() throws Exception {
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
        
        Buyer buyer = this.buyerService.insert("SAINATH", apartment.getId(), 10, "9908219617", "sainathbatthala@gmail.com", null, true);
    }
    
    /**
     * Test insert negative null is active.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsertNegativeNullIsActive() throws Exception {
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
        
        Buyer buyer = this.buyerService.insert("SAINATH", apartment.getId(), 10, "9908219617", "sainathbatthala@gmail.com", "300 C", null);
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
        Apartment apartment = this.apartmentService.insert("MARS",locality.getId(), 300);
        apartment = this.apartmentService.getById(1);
        Buyer buyer = this.buyerService.insert("SAINATH", apartment.getId(), 10, "90908219617", "sainathbatthala@gmail.com", "300 C", true);
        buyer = this.buyerService.getById(1);
        
        assertNotNull(buyer);
        assertEquals(buyer.getName(), "SAINATH");
        assertEquals(buyer.getApartment().getName(), "MARS");
        assertEquals(buyer.getApartment().getLocality().getName(), "JPNAGAR");
        assertEquals(buyer.getApartment().getLocality().getCity().getName(), "BANGALORE");
        
    }
    
    /**
     * Test update negative null buyer.
     */
    @Test(expected = Exception.class)
    public void testUpdateNegativeNullBuyer() {
        this.buyerService.update(null);
    }
    
    /**
     * Test update negative null buyer name.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testUpdateNegativeNullBuyerName() throws Exception {
        Country country = this.countryService.insert("INDIA");
        country = this.countryService.getById(1);
        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);
        City city = this.cityService.insert("BANGALORE", state.getId());
        city = this.cityService.getById(1);
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);
        Apartment apartment = this.apartmentService.insert("MARS", locality.getId(), 300);
        apartment = this.apartmentService.getById(1);
        Buyer buyer = this.buyerService.insert("SAINATH", apartment.getId(), 10, "90908219617", "sainathbatthala@gmail.com", "300 C", true);
        buyer = this.buyerService.getById(1);
        buyer.setName(null);
        this.buyerService.update(buyer);
    }
    
    /**
     * Test update negative empty buyer name.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testUpdateNegativeEmptyBuyerName() throws Exception {
        Country country = this.countryService.insert("INDIA");
        country = this.countryService.getById(1);
        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);
        City city = this.cityService.insert("BANGALORE", state.getId());
        city = this.cityService.getById(1);
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);
        Apartment apartment = this.apartmentService.insert("MARS", locality.getId(), 300);
        apartment = this.apartmentService.getById(1);
        Buyer buyer = this.buyerService.insert("SAINATH", apartment.getId(), 10, "90908219617", "sainathbatthala@gmail.com", "300 C", true);
        buyer = this.buyerService.getById(1);
        buyer.setName("");
        this.buyerService.update(buyer);
    }
    
    /**
     * Test update negative null apartment.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testUpdateNegativeNullApartment() throws Exception {
        Country country = this.countryService.insert("INDIA");
        country = this.countryService.getById(1);
        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);
        City city = this.cityService.insert("BANGALORE", state.getId());
        city = this.cityService.getById(1);
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);
        Apartment apartment = this.apartmentService.insert("MARS", locality.getId(), 300);
        apartment = this.apartmentService.getById(1);
        Buyer buyer = this.buyerService.insert("SAINATH", apartment.getId(), 10, "90908219617", "sainathbatthala@gmail.com", "300 C", true);
        buyer = this.buyerService.getById(1);
        buyer.setApartment(null);
        this.buyerService.update(buyer);
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
        Apartment apartment = this.apartmentService.insert("MARS", locality.getId(), 300);
        apartment = this.apartmentService.getById(1);
        Buyer buyer = this.buyerService.insert("SAINATH", apartment.getId(), 10, "90908219617", "sainathbatthala@gmail.com", "300 C", true);
        buyer = this.buyerService.getById(1);
        buyer.getApartment().setName("");
        this.buyerService.update(buyer);
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
        Apartment apartment = this.apartmentService.insert("MARS", locality.getId(), 300);
        apartment = this.apartmentService.getById(1);
        Buyer buyer = this.buyerService.insert("SAINATH", apartment.getId(), 10, "90908219617", "sainathbatthala@gmail.com", "300 C", true);
        buyer = this.buyerService.getById(1);
        buyer.getApartment().setLocality(null);
        this.buyerService.update(buyer);

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
        Apartment apartment = this.apartmentService.insert("MARS", locality.getId(), 300);
        apartment = this.apartmentService.getById(1);
        Buyer buyer = this.buyerService.insert("SAINATH", apartment.getId(), 10, "90908219617", "sainathbatthala@gmail.com", "300 C", true);
        buyer = this.buyerService.getById(1);
        buyer.getApartment().getLocality().setName("");
        this.buyerService.update(buyer);
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
        Apartment apartment = this.apartmentService.insert("MARS", locality.getId(), 300);
        apartment = this.apartmentService.getById(1);
        Buyer buyer = this.buyerService.insert("SAINATH", apartment.getId(), 10, "90908219617", "sainathbatthala@gmail.com", "300 C", true);
        buyer = this.buyerService.getById(1);
        buyer.setName("DEEPAK");
        this.buyerService.update(buyer);
        
        assertEquals(buyer.getName(), "DEEPAK");
        
    }
    
    /**
     * Test delete negative null.
     */
    @Test(expected = Exception.class)
    public void testDeleteNegativeNull() {
        this.buyerService.delete(null);
    }
    

    /**
     * Test delete.
     *
     * @throws Exception the exception
     */
    
    @Test
    public void testDelete() throws Exception {

        Country country = this.countryService.insert("INDIA");
        country = this.countryService.getById(1);
        State state = this.stateService.insert("KARNATAKA", country.getId());
        state = this.stateService.getById(1);
        City city = this.cityService.insert("BANGALORE", state.getId());
        city = this.cityService.getById(1);
        Locality locality = this.localityService.insert("JPNAGAR", city.getId(), "564777");
        locality = this.localityService.getById(1);
        Apartment apartment = this.apartmentService.insert("MARS", locality.getId(), 300);
        apartment = this.apartmentService.getById(1);
        Buyer buyer = this.buyerService.insert("SAINATH", apartment.getId(), 10, "90908219617", "sainathbatthala@gmail.com", "300 C", true);
        buyer = this.buyerService.getById(1);

        this.buyerService.delete(buyer);
        assertNull(this.buyerService.getById(buyer.getId()));

    }
    

    /**
     * Test constructor negative.
     *
     * @throws TransactionException the transaction exception
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testConstructorNegative() throws TransactionException, Exception {
        BuyerService buyerService = new BuyerServiceImpl(null);
    }

}
