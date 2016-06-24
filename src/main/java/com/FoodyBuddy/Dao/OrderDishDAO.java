package com.FoodyBuddy.Dao;
import java.util.List;

import com.FoodyBuddy.Model.OrderDish;

public interface OrderDishDAO {

	public void save(OrderDish s);
	public void update(OrderDish s);
	public void delete(Integer id);
	public List<OrderDish> listByOrderId(Integer OrderId);
	public List<OrderDish> listByDishId(Integer DishId);
}