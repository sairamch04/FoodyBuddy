package com.foodybuddy.dao;

import com.foodybuddy.model.City;
import java.util.List;

/**
 * The Interface CityDAO.
 */
public interface CityDAO {
	
	/**
	 * Gets the City by id.
	 *
	 * @param the id
	 * @return the city by id
	 */
	public City getById(Integer id);
	
	/**
	 * Gets all the Cities.
	 *
	 * @return all Cities
	 */
	public List<City> getAll();
	
	/**
	 * Insert.
	 *
	 * @param City city
	 * @return City city
	 */
	public City insert(City city);
	
	/**
	 * Update.
	 *
	 * @param City city
	 * @return City city
	 */
	public City update(City city);
	
	/**
	 * Delete.
	 *
	 * @param City city
	 */
	public void delete(City city);
}