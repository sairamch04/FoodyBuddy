package com.foodybuddy.dao;

import com.foodybuddy.model.Country;
import java.util.List;


/**
 * The Interface CountryDAO.
 */
public interface CountryDAO {
	
	/**
	 * Gets the country by id.
	 *
	 * @param Integer id 
	 * @return the country by id
	 */
	public Country getById(Integer id);
	
	/**
	 * Gets all countries.
	 *
	 * @return all the countries
	 */
	public List<Country> getAll();
	
	/**
	 * Insert.
	 *
	 * @param Country country
	 * @return Country country
	 */
	public Country insert(Country country);
	
	/**
	 * Update.
	 *
	 * @param Country country
	 * @return Country country
	 */
	public Country update(Country country);
	
	/**
	 * Delete.
	 *
	 * @param Country country
	 */
	public void delete(Country country);
}
