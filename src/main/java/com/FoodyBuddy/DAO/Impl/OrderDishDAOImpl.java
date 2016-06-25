package com.FoodyBuddy.DAO.Impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.FoodyBuddy.Dao.OrderDishDAO;
import com.FoodyBuddy.Model.OrderDish;

@Repository
public class OrderDishDAOImpl implements OrderDishDAO {

	Session session;
	
	public OrderDishDAOImpl(Session session) {
        this.session = session;
    }
    
    // saves orderdish object to db
	public void insert(OrderDish c) {
		session.persist(c);
	}
	
	// returns list of dishs's by Id
	@SuppressWarnings("unchecked")
	public OrderDish getByDishId(Integer DishId) {
		OrderDish orderDish = (OrderDish) session.createQuery("from Order_dish where Order_dish.dish_id =" + DishId).list().get(0);
		return orderDish;
	}
	
	// returns list of orders by Id
	@SuppressWarnings("unchecked")
	public OrderDish getByOrderId(Integer OrderId ) {
		OrderDish orderDish = (OrderDish) session.createQuery("from Order_dish where Order_dish.order_id =" + OrderId).list().get(0);
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

	public void update(OrderDish c) {
		session.update(c);
		}
	

	public void delete(OrderDish c) {
		session.delete(c);
		}

	public Integer sumDishPrice(Integer OrderId) {
		Integer netDishPrice = (Integer)session.createQuery("SELECT SUM(net_dish_price) FROM order_dish GROUP BY order_id").uniqueResult();
		return netDishPrice;
		}

}