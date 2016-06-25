package com.FoodyBuddy.Dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.FoodyBuddy.Dao.OrderDishDAO;
import com.FoodyBuddy.Model.OrderDish;

@Repository
public class OrderDishDAOImpl implements OrderDishDAO {

	private SessionFactory sessionFactory;
	Session session;

    public OrderDishDAOImpl(Session session) {
        this.session = session;
    }
    
    // saves orderdish object to db
	public void save(OrderDish c) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(c);
		tx.commit();
	}
	
	// returns list of dishs's by Id
	@SuppressWarnings("unchecked")
	public OrderDish getByDishId(Integer DishId) {
		Session session = this.sessionFactory.openSession();
		OrderDish orderDish = (OrderDish) session.createQuery("from Order_dish where Order_dish.dish_id =" + DishId).list().get(0);
		return orderDish;
	}
	
	// returns list of orders by Id
	@SuppressWarnings("unchecked")
	public OrderDish getByOrderId(Integer OrderId ) {
		Session session = this.sessionFactory.openSession();
		OrderDish orderDish = (OrderDish) session.createQuery("from Order_dish where Order_dish.order_id =" + OrderId).list().get(0);
		return orderDish;
	}
	
	// 
	@SuppressWarnings("unchecked")
	public OrderDish getById(Integer Id) {
		Session session = this.sessionFactory.openSession();
		OrderDish orderDish = (OrderDish) session.createQuery("from Order_dish").list().get(0);
		return orderDish;
	}

	@SuppressWarnings("unchecked")
	public List<OrderDish> list() {
		Session session = this.sessionFactory.openSession();
		List<OrderDish> orderDishList = session.createQuery("from Order_dish").list();
		return orderDishList;
	}

	public void update(OrderDish c) {
		Session session = this.sessionFactory.openSession();
		session.update(c);
		}
	

	public void delete(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		OrderDish orderDish = (OrderDish) session.load(OrderDish.class, new Integer(id));
		if(null != orderDish){
			session.delete(orderDish);
		}
		
	}

	public Integer sumDishPrice(Integer OrderId) {
		Session session = this.sessionFactory.openSession();
		int netDishPrice = session.createQuery("SELECT SUM(net_dish_price) FROM order_dish GROUP BY order_id").getFirstResult();
		return netDishPrice;
	}

}
