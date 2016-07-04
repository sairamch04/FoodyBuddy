package com.foodybuddy.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.foodybuddy.dao.OrderDishDAO;
import com.foodybuddy.model.OrderDish;

/**
 * The Class OrderDishDAOImpl.
 */
@Repository
public class OrderDishDAOImpl implements OrderDishDAO {

	/** The session. */
	Session session;
	
	/**
	 * Instantiates a new order dish DAO impl.
	 *
	 * @param session the session
	 * @throws Exception the exception
	 */
	public OrderDishDAOImpl(Session session) throws Exception {
	    if (session == null) {
	      throw new Exception(
	          "Hibernate Session is null in OrderDishDaoImpl");
	    }
		this.session = session;
    }
    
    /* (non-Javadoc)
     * @see com.foodybuddy.dao.OrderDishDAO#insert(com.foodybuddy.model.OrderDish)
     */
    // saves OrderDish object to database
	public OrderDish insert(OrderDish orderDish) {
		this.session.persist(orderDish);
		return orderDish;
	}
	
	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.OrderDishDAO#getListByDishId(java.lang.Integer)
	 */
	// returns list of dishs's by Id
	@SuppressWarnings("unchecked")
	public List<OrderDish> getListByDishId(Integer DishId) {
		List<OrderDish> orderDishList = session.createQuery("from OrderDish where dish_id =" + DishId).list();
		return orderDishList;
	}
	
	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.OrderDishDAO#getListByOrderId(java.lang.Integer)
	 */
	// returns list of orders by Id
	@SuppressWarnings("unchecked")
	public List<OrderDish> getListByOrderId(Integer OrderId ) {
		List<OrderDish> orderDishList = session.createQuery("from OrderDish where order_id =" + OrderId).list();
		return orderDishList;
	}
	
	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.OrderDishDAO#getById(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public OrderDish getById(Integer Id) {
		OrderDish orderDish = (OrderDish) session.createQuery("from OrderDish where id=" + Id).list().get(0);
		return orderDish;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.OrderDishDAO#list()
	 */
	@SuppressWarnings("unchecked")
	public List<OrderDish> list() {
		List<OrderDish> orderDishList = session.createQuery("from OrderDish").list();
		return orderDishList;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.OrderDishDAO#update(com.foodybuddy.model.OrderDish)
	 */
	public OrderDish update(OrderDish orderDish) {
		this.session.update(orderDish);
		return orderDish;
		}
	

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.OrderDishDAO#delete(com.foodybuddy.model.OrderDish)
	 */
	public void delete(OrderDish c) {
		this.session.delete(c);
		return;
		}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.OrderDishDAO#sumDishPrice(java.lang.Integer)
	 */
	public Integer sumDishPrice(Integer OrderId) {
		Integer netDishPrice = (Integer)this.session.createQuery("SELECT SUM(net_dish_price) FROM order_dish GROUP BY order_id").uniqueResult();
		return netDishPrice;
		}
}
