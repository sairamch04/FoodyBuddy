package com.foodybuddy.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.foodybuddy.model.Order;
import com.foodybuddy.utils.OrderStatusEnum;

// TODO: Auto-generated Javadoc
/**
 * The Interface OrderService.
 */
public interface OrderService{
	
	/**
	 * Places the order.
	 *
	 * @param buyerId - the id of buyer
	 * @param dishIds - list of dish Id's the buyer added
	 * @param orderedQuantitys - list of respective quantity's of dishes
	 * @throws HibernateException the hibernate exception
	 */
	public void placeOrder(int buyerId, List<Integer> dishIds, List<Integer> orderedQuantitys) throws HibernateException;
	
	/**
	 * Gets the order.
	 *
	 * @param orderId - The Id of order object
	 * @return the corresponding object
	 * @throws HibernateException the hibernate exception
	 */
	public Order getOrder(int orderId) throws  HibernateException;
	
	/**
	 * Soft deletes the order by setting the status flag as CANCELLED.
	 *
	 * @param orderId - The Id of order
	 * @throws HibernateException the hibernate exception
	 */
	public void cancelOrder(int orderId) throws HibernateException;
	
	/**
	 * Update status.
	 *
	 * @param orderId the order id
	 * @param orderStatus the order status
	 */
	public void updateOrderStatus(int orderId, OrderStatusEnum orderStatus) throws HibernateException;
	
	/**
	 * Gets the orders of buyer.
	 *
	 * @param buyerId the buyer id
	 * @return the orders of buyer
	 * @throws HibernateException the hibernate exception
	 */
	public List<Order> getOrdersOfBuyer(int buyerId) throws HibernateException;
	
}