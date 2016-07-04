package com.foodybuddy.dao;
import java.util.List;

import com.foodybuddy.model.OrderDish;

/**
 * The Interface OrderDishDAO.
 */
public interface OrderDishDAO {

	/**
	 * Insert.
	 *
	 * @param orderDish the order dish
	 * @return the order dish
	 */
	public OrderDish insert(OrderDish orderDish);
	
	/**
	 * Update.
	 *
	 * @param orderDish the order dish
	 * @return the order dish
	 */
	public OrderDish update(OrderDish orderDish);
	
	/**
	 * Delete.
	 *
	 * @param orderDish the order dish
	 */
	public void delete(OrderDish orderDish);
	
	/**
	 * Sum dish price.
	 *
	 * @param OrderId the order id
	 * @return the integer
	 */
	public Integer sumDishPrice(Integer OrderId);
	
	/**
	 * Gets the list by order id.
	 *
	 * @param OrderId the order id
	 * @return the list by order id
	 */
	public List<OrderDish> getListByOrderId(Integer OrderId);
	
	/**
	 * Gets the list by dish id.
	 *
	 * @param DishId the dish id
	 * @return the list by dish id
	 */
	public List<OrderDish> getListByDishId(Integer DishId);
	
	/**
	 * Gets the by id.
	 *
	 * @param Id the id
	 * @return the by id
	 */
	public OrderDish getById(Integer Id);
	
	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<OrderDish> list();
	
}
