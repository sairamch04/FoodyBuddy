package com.foodybuddy.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.foodybuddy.dao.DishDAO;
import com.foodybuddy.model.Dish;

// TODO: Auto-generated Javadoc
/**
 * The Class DishDAOImpl.
 */
@Repository
public class DishDAOImpl implements DishDAO {

	/** The session 1. */
	private Session session1;

	/**
	 * Instantiates a new dish DAO impl.
	 *
	 * @param session
	 *            the session
	 * @throws NullPointerException
	 *             the null pointer exception
	 */
	public DishDAOImpl(Session session) throws NullPointerException {
		if (session == null) {
			throw new NullPointerException("Session is null");
		}
		this.session1 = session;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.dao.DishDAO#insert(com.foodybuddy.model.Dish)
	 */
	public Dish insert(Dish d) {
		session1.persist(d);
		return d;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.dao.DishDAO#update(com.foodybuddy.model.Dish)
	 */
	public Dish update(Dish Dish) {
		session1.update(Dish);
		return Dish;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.dao.DishDAO#delete(java.lang.Integer)
	 */
	public void delete(Integer id) {
		session1.createQuery("delete from Dish where id=" + id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.dao.DishDAO#getDishList()
	 */
	@SuppressWarnings("unchecked")
	public List<Dish> getDishList() {
		List<Dish> DishList = session1.createQuery("from Dish").list();
		return DishList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.dao.DishDAO#getByDishId(java.lang.Integer)
	 */
	public Dish getByDishId(Integer DishId) {
		String query = "FROM Dish WHERE id = " + DishId;
		Dish Dish = (Dish) session1.createQuery(query).uniqueResult();
		return Dish;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.dao.DishDAO#getListBySellerId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<Dish> getListBySellerId(Integer SellerId) {
		String query = "FROM Dish WHERE seller = " + SellerId;
		List<Dish> DishList = session1.createQuery(query).list();
		return DishList;
	}
}
