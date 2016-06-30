package com.foodybuddy.dao;

import com.foodybuddy.model.State;
import java.util.List;

/**
 * The Interface StateDAO.
 */
public interface StateDAO {
	
	/**
	 * Gets the State by id.
	 *
	 * @param State Id
	 * @return the State by id
	 */
	public State getById(Integer id);
	
	/**
	 * Gets all the States.
	 *
	 * @return all the States
	 */
	public List<State> getAll();
	
	/**
	 * Insert.
	 *
	 * @param State state
	 * @return State state
	 */
	public State insert(State state);
	
	/**
	 * Update.
	 *
	 * @param State state
	 * @return State state
	 */
	public State update(State state);
	
	/**
	 * Delete.
	 *
	 * @param State state
	 */
	public void delete(State state);
}