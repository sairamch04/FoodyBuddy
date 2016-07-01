package com.foodybuddy.service;

import com.foodybuddy.model.Country;
import java.util.List;
import org.hibernate.TransactionException;

// TODO: Auto-generated Javadoc
/**
 * The Interface CountryService.
 */
public interface CountryService {
	
	/**
	 * Gets the Country by id.
	 *
	 * @param id the id
	 * @return the country by id
	 * @throws Exception
	 */
	public Country getById(Integer id) throws Exception;
	
	/**
	 * Gets all the Countries.
	 *
	 * @return all the Countries
	 * @throws Exception
	 */
	public List<Country> getAll() throws Exception;
	
	/**
	 * Insert.
	 *
	 * @param name the name
	 * @return the country
	 * @throws TransactionException the transaction exception
	 */
	public Country insert(String name) throws TransactionException;
	
	/**
	 * Update.
	 *
	 * @param country the country
	 * @return Country country
	 * @throws TransactionException the transaction exception
	 */
	public Country update(Country country) throws TransactionException;
	
	/**
	 * Delete.
	 *
	 * @param country the country
	 * @throws TransactionException the transaction exception
	 */
	public void delete(Country country) throws TransactionException;
}