package com.foodybuddy.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.foodybuddy.model.Order;

public interface OrderService{
	/**
	 * Places the order
	 * @param buyerId - the id of buyer 
	 * @param dishIds - list of dish Id's the buyer added
	 * @param orderedQuantitys - list of respective quantity's of dishes
	 * @throws HibernateException
	 */
	public void placeOrder(int buyerId, List<Integer> dishIds, List<Integer> orderedQuantitys) throws HibernateException;
	/**
	 * @param orderId - The Id of order object
	 * @return the corresponding object
	 * @throws HibernateException
	 */
	public Order getOrder(int orderId) throws  HibernateException;
	/**
	 * Soft deletes the order by setting the status flag as CANCELLED
	 * @param orderId - The Id of order
	 * @throws HibernateException
	 */
	public void cancelOrder(int orderId) throws HibernateException;
	
}