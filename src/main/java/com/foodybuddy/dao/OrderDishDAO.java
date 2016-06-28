package com.foodybuddy.dao;
import java.util.List;

import com.foodybuddy.model.OrderDish;

public interface OrderDishDAO {

	public OrderDish insert(OrderDish orderDish);
	public OrderDish update(OrderDish orderDish);
	public void delete(OrderDish orderDish);
	public Integer sumDishPrice(Integer OrderId);
	public OrderDish getByOrderId(Integer OrderId);
	public OrderDish getByDishId(Integer DishId);
	public OrderDish getById(Integer Id);
	public List<OrderDish> list();
	
}
