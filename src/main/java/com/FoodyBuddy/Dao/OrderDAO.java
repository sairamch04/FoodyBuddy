package com.FoodyBuddy.Dao;

import java.util.List;

import com.FoodyBuddy.Model.Order;

public interface OrderDAO {

	public void save(Order c);
	public List<Order> getListByBuyerId(Integer buyerId);
	public Order getByOrderId(Integer orderId);	
	public Order getById(Integer id);
	public void update(Order order);	
	public void delete(Order order);

}
