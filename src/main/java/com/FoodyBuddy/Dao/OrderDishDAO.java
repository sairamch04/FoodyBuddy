package com.FoodyBuddy.Dao;
import java.util.List;

import com.FoodyBuddy.Model.OrderDish;

public interface OrderDishDAO {

	public void save(OrderDish s);
	public void update(OrderDish s);
	public void delete(Integer id);
	public Integer sumDishPrice(Integer OrderId);
	public OrderDish getByOrderId(Integer OrderId);
	public OrderDish getByDishId(Integer DishId);
	public OrderDish getById(Integer Id);
	public List<OrderDish> list();
	
}