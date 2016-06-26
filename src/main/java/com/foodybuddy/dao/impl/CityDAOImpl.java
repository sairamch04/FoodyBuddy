package com.FoodyBuddy.Dao.Impl;

import com.FoodyBuddy.Dao.CityDao;
import com.FoodyBuddy.Model.City;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;


@Repository
public class CityDaoImpl implements CityDao {
	
	private Session session = null;
	
	public CityDaoImpl(Session session){
		this.session = session;
	}

	public void saveCity(City city) {
		session.persist(city);
	}

	public void updateCity(City city) {

		session.update(city);
	}

	@SuppressWarnings("unchecked")
	public List<City> getAllCities() {
		List<City> CitiesList = session.createQuery("from City").list();
		return CitiesList;
	}

	public City getCity(int id) {		
		City city = (City) session.load(City.class, new Integer(id));
		return city;
	}

	public void deleteCity(int id) {
		City city = (City) session.load(City.class, new Integer(id));
		if(null != city){
			session.delete(city);
		}
	}

}