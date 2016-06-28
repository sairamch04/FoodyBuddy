package com.foodybuddy.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.foodybuddy.dao.OrderDishDAO;
import com.foodybuddy.model.OrderDish;

@Repository
public class OrderDishDAOImpl implements OrderDishDAO {

	Session session;
	
	public OrderDishDAOImpl(Session session) throws Exception {
		this.session = session;
	    if (this.session == null) {
	      throw new Exception(
	          "Hibernate Session is null in OrderDishDaoImpl");
	    }
    }
    
    // saves OrderDish object to db
	public OrderDish insert(OrderDish orderDish) {
		this.session.persist(orderDish);
		return orderDish;
	}
	
	// returns list of dishs's by Id
	@SuppressWarnings("unchecked")
	public OrderDish getByDishId(Integer DishId) {
		OrderDish orderDish = (OrderDish) session.createQuery("from Order_dish where dish_id =" + DishId).list().get(0);
		return orderDish;
	}
	
	// returns list of orders by Id
	@SuppressWarnings("unchecked")
	public OrderDish getByOrderId(Integer OrderId ) {
		OrderDish orderDish = (OrderDish) session.createQuery("from Order_dish where order_id =" + OrderId).list().get(0);
		return orderDish;
	}
	
	@SuppressWarnings("unchecked")
	public OrderDish getById(Integer Id) {
		OrderDish orderDish = (OrderDish) session.createQuery("from Order_dish").list().get(0);
		return orderDish;
	}

	@SuppressWarnings("unchecked")
	public List<OrderDish> list() {
		List<OrderDish> orderDishList = session.createQuery("from Order_dish").list();
		return orderDishList;
	}

	public OrderDish update(OrderDish orderDish) {
		this.session.update(orderDish);
		return orderDish;
		}
	

	public void delete(OrderDish c) {
		this.session.delete(c);
		return;
		}

	public Integer sumDishPrice(Integer OrderId) {
		Integer netDishPrice = (Integer)this.session.createQuery("SELECT SUM(net_dish_price) FROM order_dish GROUP BY order_id").uniqueResult();
		return netDishPrice;
		}

}
