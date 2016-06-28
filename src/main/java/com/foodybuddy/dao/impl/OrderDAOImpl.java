package com.foodybuddy.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.foodybuddy.dao.OrderDAO;
import com.foodybuddy.model.Order;

/**
 * The Class OrderDAOImpl.
 */
@Repository
public class OrderDAOImpl implements OrderDAO {

	/** The session. */
	private Session session;

	/**
	 * Instantiates a new order DAO impl.
	 *
	 * @param session the session
	 */
	public OrderDAOImpl(Session session) throws NullPointerException{
		if(session == null){
			throw new  NullPointerException("Session is null");
		}
		this.session = session;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.OrderDAO#insert(com.foodybuddy.model.Order)
	 */
	public Order insert(Order order) {
		session.save(order);
		return order;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.OrderDAO#getListByBuyerId(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Order> getListByBuyerId(int buyerId) {
		String query = "FROM Order WHERE buyer_id = " + buyerId;
		List<Order> orders = session.createQuery(query).list();
		return orders;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.OrderDAO#getById(int)
	 */
	@SuppressWarnings("unchecked")
	public Order getById(int orderId) {
		String query = "FROM Order WHERE id = " + orderId;
		Order order = (Order) session.createQuery(query).list().get(0);
		return order;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.OrderDAO#update(com.foodybuddy.model.Order)
	 */
	public void update(Order order) {
		session.update(order);
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.OrderDAO#delete(com.foodybuddy.model.Order)
	 */
	public void delete(Order order) {
		session.delete(order);
	}

}