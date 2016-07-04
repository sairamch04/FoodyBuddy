package com.foodybuddy.service;

import java.util.List;

import com.foodybuddy.model.Dish;
import com.foodybuddy.model.Order;
import com.foodybuddy.model.OrderDish;


/**
 * The Interface OrderDishService.
 */
public interface OrderDishService {
	
	/**
	 * Update order dish quantity.
	 *
	 * @param orderDishId the order dish id
	 * @param updatedQuantity the updated quantity
	 * @return the order dish
	 * @throws Exception the exception
	 */
	public OrderDish updateOrderDishQuantity(int orderDishId, int updatedQuantity) throws Exception ;
	
	/**
	 * Gets the list order by dish id.
	 *
	 * @param dishId the dish id
	 * @return the list order by dish id
	 * @throws Exception the exception
	 */
	public List<OrderDish> getListOrderByDishId(int dishId) throws Exception ;
	
	/**
	 * Gets the list order by order id.
	 *
	 * @param orderId the order id
	 * @return the list order by order id
	 * @throws Exception the exception
	 */
	public List<OrderDish> getListOrderByOrderId(int orderId) throws Exception ;
	
	/**
	 * Insert order dish.
	 *
	 * @param order the order
	 * @param dish the dish
	 * @return the order dish
	 * @throws Exception the exception
	 */
	public OrderDish insertOrderDish(Order order, Dish dish) throws Exception ;

}
