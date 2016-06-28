package com.FoodyBuddy.DAO;

import java.util.List;

import com.FoodyBuddy.Model.Order;

public interface OrderDAO {
	
	public void save(Order c);
	public List<Order> getListByBuyerId(Integer buyerId);
	public Order getById(Integer id);
	public void update(Order order);	
	public void delete(Order order);

}
