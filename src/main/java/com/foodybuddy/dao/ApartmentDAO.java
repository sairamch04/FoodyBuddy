package com.foodybuddy.dao;

import com.foodybuddy.model.Apartment;
import java.util.List;

/**
 * The Interface ApartmentDAO.
 */
public interface ApartmentDAO {
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public Apartment getById(Integer id);
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<Apartment> getAll();
	
	/**
	 * Insert.
	 *
	 * @param apartment the apartment
	 * @return the apartment
	 */
	public Apartment insert(Apartment apartment);
	
	/**
	 * Update.
	 *
	 * @param apartment the apartment
	 * @return the apartment
	 */
	public Apartment update(Apartment apartment);
	
	/**
	 * Delete.
	 *
	 * @param apartment the apartment
	 */
	public void delete(Apartment apartment);
}