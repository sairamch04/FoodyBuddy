package com.FoodyBuddy.DAO.Impl;

import com.FoodyBuddy.DAO.Country;
import com.FoodyBuddy.Model.Country;

import java.util.List;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class CountryDAOImpl implements CountryDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(CountryDAOImpl.class);

	private Session session = null;
	
	public CountryDAOImpl(Session session){
		this.session = session;
	}

	@Override
	public void insertCountry(Country country) {
		session.persist(country);
		logger.info("Country saved successfully, Country Details = " + country);
	}

	@Override
	public void updateCountry(Country country) {

		session.update(country);
		logger.info("Country updated successfully, Country Details = " + country );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Country> getAllCountries() {
		List<Country> CountriesList = session.createQuery("from Country").list();
		for(Country country : CountriesList){
			logger.info("Country List :: " + country);
		}
		return CountriesList;
	}

	@Override
	public Country getCountry(int id) {		
		Country country = (Country) session.load(Country.class, new Integer(id));
		logger.info("Country loaded successfully, Country details = " + country);
		return country;
	}

	@Override
	public void deleteCountry(int id) {
		Country country = (Country) session.load(Country.class, new Integer(id));
		if(null != country){
			session.delete(country);
		}
		logger.info("Country deleted successfully, Country details = " + country);
	}

}