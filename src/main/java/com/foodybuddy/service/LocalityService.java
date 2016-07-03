package com.foodybuddy.service;

import com.foodybuddy.model.Locality;
import java.util.List;
import org.hibernate.TransactionException;

/**
 * The Interface LocalityService.
 */
public interface LocalityService {

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 * @throws Exception the exception
	 */
	public Locality getById(Integer id) throws Exception;
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 * @throws Exception the exception
	 */
	public List<Locality> getAll() throws Exception;
	
	/**
	 * Insert.
	 *
	 * @param name the name
	 * @param cityId the city id
	 * @param pincode the pincode
	 * @return the locality
	 * @throws TransactionException the transaction exception
	 */
	public Locality insert(String name, Integer cityId, String pincode) throws TransactionException;
	
	/**
	 * Update.
	 *
	 * @param locality the locality
	 * @return the locality
	 * @throws TransactionException the transaction exception
	 */
	public Locality update(Locality locality) throws TransactionException;
	
	/**
	 * Delete.
	 *
	 * @param locality the locality
	 * @throws TransactionException the transaction exception
	 */
	public void delete(Locality locality) throws TransactionException;
}