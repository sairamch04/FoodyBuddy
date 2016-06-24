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

    public OrderDishDAOImpl(SessionFactory sessionFactory) {
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
	public OrderDish listById(Integer Id) {
		Session session = this.sessionFactory.openSession();
		List<OrderDish> OrderDishList = session.createQuery("from Order_dish").list();
		session.close();
		return OrderDishList.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<OrderDish> list() {
		Session session = this.sessionFactory.openSession();
		List<OrderDish> OrderDishList = session.createQuery("from Order_dish").list();
		session.close();
		return OrderDishList;
	}

	public void  update(OrderDish c) throws Exception{
		Session session = this.sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			session.update(c);
			tx.commit();
			session.close();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Not updated ");
			
		}
	}

	public void delete(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		OrderDish OrderDish = (OrderDish) session.load(OrderDish.class, new Integer(id));
		if(null != OrderDish){
			session.delete(OrderDish);
		}
		
	}

	public Integer sumDishPrice(Integer OrderId) {
		Session session = this.sessionFactory.openSession();
		int netDishPrice = session.createQuery("SELECT SUM(net_dish_price) FROM order_dish GROUP BY order_id").getFirstResult();
		session.close();
		return netDishPrice;
	}

}
