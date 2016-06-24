package com.FoodyBuddy.Dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.FoodyBuddy.Dao.OrderDishDAO;
import com.FoodyBuddy.Model.Country;
import com.FoodyBuddy.Model.OrderDish;
import com.FoodyBuddy.Model.OrderDish;
import com.FoodyBuddy.Model.Seller;


public class OrderDishDAOImpl implements OrderDishDAO {

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	public void save(OrderDish c) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(c);
		tx.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderDish> listByDishId(Integer DishId) {
		Session session = this.sessionFactory.openSession();
		List<OrderDish> OrderDishList = session.createQuery("from Order_dish where Order_dish.dish_id =" + DishId).list();
		session.close();
		return OrderDishList;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderDish> listByOrderId(Integer OrderId ) {
		Session session = this.sessionFactory.openSession();
		List<OrderDish> OrderDishList = session.createQuery("from Order_dish where Order_dish.order_id =" + OrderId).list();
		session.close();
		return OrderDishList;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderDish> listById(Integer Id) {
		Session session = this.sessionFactory.openSession();
		List<OrderDish> OrderDishList = session.createQuery("from Order_dish").list();
		session.close();
		return OrderDishList;
	}

	@SuppressWarnings("unchecked")
	public List<OrderDish> list() {
		Session session = this.sessionFactory.openSession();
		List<OrderDish> OrderDishList = session.createQuery("from Order_dish").list();
		session.close();
		return OrderDishList;
	}

	public void update(OrderDish c) {
		Session session = this.sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			session.update(c);
			tx.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.close();	
	}

	public void delete(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		OrderDish OrderDish = (OrderDish) session.load(OrderDish.class, new Integer(id));
		if(null != OrderDish){
			session.delete(OrderDish);
		}
		
	}

}
