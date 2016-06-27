package com.foodybuddy.dao.impl;

import java.util.List;

import javax.transaction.Status;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.foodybuddy.dao.OrderDAO;
import com.foodybuddy.model.Order;

@Repository
public class OrderDAOImpl implements OrderDAO {

	private Session session;

    public OrderDAOImpl(Session session){
    	 this.session = session;
    }
    
    public void insert(Order order) {
		session.persist(order);
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getListByBuyerId(int buyerId){
		String query= "FROM Order WHERE buyer_id = " + buyerId;
		List<Order> orders = session.createQuery(query).list();
		return orders;		
	}
	
	@SuppressWarnings("unchecked")
	public Order getById(int orderId){
		String query= "FROM Order WHERE id = " + orderId;
		Order order = (Order) session.createQuery(query).list().get(0);
		return order;		
	}
	
	public void update(Order order) {
		session.update(order);	
	}

	public void delete(Order  order) {
		session.delete(order);		
	}

	
}