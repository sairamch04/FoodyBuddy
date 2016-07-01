package com.foodybuddy.service;

import com.foodybuddy.model.State;
import java.util.List;
import org.hibernate.TransactionException;

/**
 * The Interface StateService.
 */
public interface StateService {

	/**
	 * Gets the State by id.
	 *
	 * @param Integer id
	 * @return the State by id
	 * @throws Exception
	 */
	public State getById(Integer id) throws Exception;
	
	/**
	 * Gets all the States.
	 *
	 * @return all States
	 * @throws Exception
	 */
	public List<State> getAll() throws Exception;
	
	/**
	 * Insert.
	 *
	 * @param String name
	 * @param Integer countryId the country id
	 * @return the state
	 * @throws TransactionException
	 */
	public State insert(String name, Integer countryId) throws TransactionException;
	
	/**
	 * Update.
	 *
	 * @param State state
	 * @return the state
	 * @throws TransactionException
	 */
	public State update(State state) throws TransactionException;
	
	/**
	 * Delete.
	 *
	 * @param State state
	 * @throws TransactionException
	 */
	public void delete(State state) throws TransactionException;
}