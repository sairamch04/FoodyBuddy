package com.foodybuddy.dao;

import com.foodybuddy.model.Locality;
import java.util.List;

/**
 * The Interface LocalityDAO.
 */
public interface LocalityDAO {
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public Locality getById(Integer id);
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<Locality> getAll();
	
	/**
	 * Insert.
	 *
	 * @param locality the locality
	 * @return the locality
	 */
	public Locality insert(Locality locality);
	
	/**
	 * Update.
	 *
	 * @param locality the locality
	 * @return the locality
	 */
	public Locality update(Locality locality);
	
	/**
	 * Delete.
	 *
	 * @param locality the locality
	 */
	public void delete(Locality locality);
}