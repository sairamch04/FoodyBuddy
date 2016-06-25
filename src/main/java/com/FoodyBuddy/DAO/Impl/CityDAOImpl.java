package com.FoodyBuddy.DAO.Impl;

import com.FoodyBuddy.DAO.City;
import com.FoodyBuddy.Model.City;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class CityDAOImpl implements CityDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(CityDAOImpl.class);

	private Session session = null;
	
	public CityDAOImpl(Session session){
		this.session = session;
	}

	@Override
	public void insertCity(City city) {
		session.persist(city);
		logger.info("City saved successfully, City Details = " + city);
	}

	@Override
	public void updateCity(City city) {

		session.update(city);
		logger.info("City updated successfully, City Details = " + city );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> getAllCities() {
		List<City> CitiesList = session.createQuery("from City").list();
		for(City city : CitiesList){
			logger.info("City List :: " + city);
		}
		return CitiesList;
	}

	@Override
	public City getCity(int id) {		
		City city = (City) session.load(City.class, new Integer(id));
		logger.info("City loaded successfully, City details = " + city);
		return city;
	}

	@Override
	public void deleteCity(int id) {
		City city = (City) session.load(City.class, new Integer(id));
		if(null != city){
			session.delete(city);
		}
		logger.info("City deleted successfully, City details = " + city);
	}

}