package com.foodybuddy.service;

import java.util.List;

import com.foodybuddy.model.Dish;
import com.foodybuddy.model.Order;
import com.foodybuddy.model.OrderDish;

public interface OrderDishService {
	public OrderDish updateOrderDishQuantity(int orderDishId, int updatedQuantity) throws Exception ;
	public List<OrderDish> getListOrderByDishId(int dishId) throws Exception ;
	public List<OrderDish> getListOrderByOrderId(int orderId) throws Exception ;
	public OrderDish insertOrderDish(Order order, Dish dish) throws Exception ;

}
