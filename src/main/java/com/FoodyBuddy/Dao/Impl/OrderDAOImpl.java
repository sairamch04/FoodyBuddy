package com.FoodyBuddy.Dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.FoodyBuddy.Dao.OrderDAO;
import com.FoodyBuddy.Model.Order;


public class OrderDAOImpl implements OrderDAO {

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	public void save(Order c) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(c);
		tx.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> listByBuyerId(Integer buyerId){
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String query= "FROM Order O WHERE O.buyer_id = " + buyerId;
		List<Order> orders = session.createQuery(query).list();
		tx.commit();
		session.close();
		return orders;
		
	}
	@SuppressWarnings("unchecked")
	public List<Order> getByOrderId(Integer orderId){
		Session session = this.sessionFactory.openSession();
		String query= "FROM Order O WHERE O.id = " + orderId;
		List<Order> orders = session.createQuery(query).list();
		session.close();
		return orders;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> listById(Integer id) {
		Session session = this.sessionFactory.openSession();
		List<Order> OrderList = session.createQuery("from Order").list();
		session.close();
		return OrderList;
	}
	
	public void update(Order order) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(order);
		tx.commit();
		session.close();	
	}

	public void delete(Order  order) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(order);
		tx.commit();
		session.close();	
		
	}
	
}
