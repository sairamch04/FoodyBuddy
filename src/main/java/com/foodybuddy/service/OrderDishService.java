package com.foodybuddy.service;

import java.util.List;

import com.foodybuddy.model.OrderDish;

public interface OrderDishService {
	public OrderDish updateOrderDishQuantity(int orderDishId, int updatedQuantity) throws Exception ;
	public List<OrderDish> listOrderByDishId(int dishId) throws Exception ;
	public List<OrderDish> listOrderByOrderId(int orderId) throws Exception ;
}