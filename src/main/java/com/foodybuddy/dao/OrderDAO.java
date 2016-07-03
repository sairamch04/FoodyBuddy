package com.foodybuddy.dao;

import java.util.List;

import com.foodybuddy.model.Order;

/**
 * The Interface OrderDAO.
 */
public interface OrderDAO {

	/**
	 * Insert.
	 *
	 * @param order the order
	 * @return the order
	 */
	public Order insert(Order order);
	
	/**
	 * Save. Returns the Identifier immediately
	 *
	 * @param order the order
	 * @return the order
	 */
	public Order save(Order order);

	/**
	 * Gets the list by buyer id.
	 *
	 * @param buyerId the buyer id
	 * @return the list by buyer id
	 */
	public List<Order> getListByBuyerId(int buyerId);

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public Order getById(int id);

	/**
	 * Update.
	 *
	 * @param order the order
	 */
	public void update(Order order);

	/**
	 * Delete.
	 *
	 * @param order the order
	 */
	public void delete(Order order);

}