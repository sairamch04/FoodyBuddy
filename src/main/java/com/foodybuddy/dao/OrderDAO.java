package com.foodybuddy.dao;

import java.util.List;

import com.foodybuddy.model.Order;

public interface OrderDAO {
	
	public void insert(Order c);
	public List<Order> getListByBuyerId(int buyerId);
	public Order getById(int id);
	public void update(Order order);	
	public void delete(Order order);

}