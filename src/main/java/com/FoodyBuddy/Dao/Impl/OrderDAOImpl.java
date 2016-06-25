package com.FoodyBuddy.Dao.Impl;

import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.FoodyBuddy.Dao.OrderDAO;
import com.FoodyBuddy.Model.Order;

@Repository
public class OrderDAOImpl implements OrderDAO {

	private Session session;

    public OrderDAOImpl(Session session){
    	 this.session = session;
    }    
    public void save(Order order) {
		session.persist(order);
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getListByBuyerId(Integer buyerId){
		String query= "FROM Order O WHERE O.buyer_id = " + buyerId;
		List<Order> orders = session.createQuery(query).list();
		return orders;		
	}
	@SuppressWarnings("unchecked")
	public Order getByOrderId(Integer orderId){
		String query= "FROM Order O WHERE O.id = " + orderId;
		Order order = (Order) session.createQuery(query).list().get(0);
		return order;
		
	}
	
	@SuppressWarnings("unchecked")
	public Order getById(Integer id) {
		Order order = (Order) session.createQuery("from Order").list().get(0);
		return order;
	}
	
	public void update(Order order) {
		session.update(order);	
	}

	public void delete(Order  order) {
		session.delete(order);
		
	}

	
}
