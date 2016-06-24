package com.FoodyBuddy.Dao;

import java.util.List;

import com.FoodyBuddy.Model.Order;

public interface OrderDAO {

	public void save(Order c);
	
	public List<Order> listByBuyerId(Integer buyerId);
	public List<Order> getByOrderId(Integer orderId);
	
	public void update(Order order);
	
	public void delete(Order order);
}
