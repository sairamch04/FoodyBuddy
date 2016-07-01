package com.foodybuddy.dao.impl;

import java.util.List;
import org.hibernate.Session;
import com.foodybuddy.dao.DishDAO;
import com.foodybuddy.model.Dish;

/**
 * The Class DishDAOImpl.
 */
public class DishDAOImpl implements DishDAO {
	
	/** The session. */
	private Session session;

	/**
	 * Instantiates a new dish DAO impl.
	 *
	 * @param session the session
	 * @throws NullPointerException the null pointer exception
	 */
	public DishDAOImpl(Session session) throws NullPointerException {
		if (session == null) {
			throw new NullPointerException("Session is null");
		}
		this.session = session;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.DishDAO#insert(com.foodybuddy.model.Dish)
	 */
	public Dish insert(Dish d) {
		session.persist(d);
		return d;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.DishDAO#update(com.foodybuddy.model.Dish)
	 */
	public Dish update(Dish Dish) {
		session.update(Dish);
		return Dish;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.DishDAO#getDishList()
	 */
	@SuppressWarnings("unchecked")
	public List<Dish> getDishList() {
		List<Dish> DishList = session.createQuery("from Dish").list();
		return DishList;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.DishDAO#getListByDishId(java.lang.Integer)
	 */
	public Dish getByDishId(Integer DishId) {
		String query = "FROM Dish WHERE id = " + DishId;
		Dish dish = (Dish) session.createQuery(query).uniqueResult();
		return dish;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.DishDAO#getBySellerId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<Dish> getListBySellerId(Integer sellerid) {
		String query = "FROM Dish WHERE seller_id = " + sellerid;
		List<Dish> Dish_by_seller_id = session.createQuery(query).list();
		return Dish_by_seller_id;
	}

}
