package com.foodybuddy.dao;

import com.foodybuddy.model.Buyer;
import java.util.List;

/**
 * The Interface BuyerDAO.
 */
public interface BuyerDAO {
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public Buyer getById(Integer id);
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<Buyer> getAll();
	
	/**
	 * Insert.
	 *
	 * @param buyer the buyer
	 * @return the buyer
	 */
	public Buyer insert(Buyer buyer);
	
	/**
	 * Update.
	 *
	 * @param buyer the buyer
	 * @return the buyer
	 */
	public Buyer update(Buyer buyer);
	
	/**
	 * Delete.
	 *
	 * @param buyer the buyer
	 */
	public void delete(Buyer buyer);
}