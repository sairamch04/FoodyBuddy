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
	 * @param c the c
	 * @return 
	 */
	public Order insert(Order c);

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