package com.foodybuddy.service;

import com.foodybuddy.model.City;
import java.util.List;
import org.hibernate.TransactionException;

/**
 * The Interface CityService.
 */
public interface CityService {

	/**
	 * Gets the city.
	 *
	 * @param Integer id
	 * @return the city
	 * @throws Exception
	 */
	public City getById(Integer id) throws Exception;
	
	/**
	 * Gets all the cities.
	 *
	 * @return all the cities
	 * @throws Exception
	 */
	public List<City> getAll() throws Exception;
	
	/**
	 * Insert city.
	 *
	 * @param String name 
	 * @param Integer stateId the state id
	 * @return the city
	 * @throws TransactionException 
	 */
	public City insert(String name, Integer stateId) throws TransactionException;
	
	/**
	 * Update city.
	 *
	 * @param City city
	 * @return the city
	 * @throws TransactionException
	 */
	public City update(City city) throws TransactionException;
	
	/**
	 * Delete city.
	 *
	 * @param City city
	 * @throws TransactionException
	 */
	public void delete(City city) throws TransactionException;
}
