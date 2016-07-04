package com.foodybuddy.service;

import com.foodybuddy.model.Apartment;
import java.util.List;
import org.hibernate.TransactionException;

/**
 * The Interface ApartmentService.
 */
public interface ApartmentService {

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 * @throws Exception the exception
	 */
	public Apartment getById(Integer id) throws Exception;
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 * @throws Exception the exception
	 */
	public List<Apartment> getAll() throws Exception;
	
	/**
	 * Insert.
	 *
	 * @param name the name
	 * @param localityId the locality id
	 * @param blockNumber the block number
	 * @return the apartment
	 * @throws TransactionException the transaction exception
	 */
	public Apartment insert(String name, Integer localityId, Integer blockNumber) throws TransactionException;
	
	/**
	 * Update.
	 *
	 * @param apartment the apartment
	 * @return the apartment
	 * @throws TransactionException the transaction exception
	 */
	public Apartment update(Apartment apartment) throws TransactionException;
	
	/**
	 * Delete.
	 *
	 * @param apartment the apartment
	 * @throws TransactionException the transaction exception
	 */
	public void delete(Apartment apartment) throws TransactionException;
}