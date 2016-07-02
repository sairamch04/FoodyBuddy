package com.foodybuddy.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.foodybuddy.dao.SellerDAO;
import com.foodybuddy.model.Seller;

// TODO: Auto-generated Javadoc
/**
 * The Class SellerDAOImpl.
 */
@Repository
public class SellerDAOImpl implements SellerDAO {

	/** The session 1. */
	private Session session1;

	/**
	 * Instantiates a new seller DAO impl.
	 *
	 * @param session
	 *            the session
	 * @throws NullPointerException
	 *             the null pointer exception
	 */
	public SellerDAOImpl(Session session) throws NullPointerException {
		if (session == null) {
			throw new NullPointerException("Session is null");
		}
		this.session1 = session;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.dao.SellerDAO#insert(com.foodybuddy.model.Seller)
	 */
	public Seller insert(Seller s) {
		session1.persist(s);
		return s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.dao.SellerDAO#update(com.foodybuddy.model.Seller)
	 */
	public Seller update(Seller seller) {
		session1.update(seller);
		return seller;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.dao.SellerDAO#delete(java.lang.Integer)
	 */
	public void delete(Integer id) {
		session1.createQuery("delete from Seller where id=" + id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.dao.SellerDAO#getSellerList()
	 */
	@SuppressWarnings("unchecked")
	public List<Seller> getSellerList() {
		List<Seller> SellerList = session1.createQuery("from Seller").list();
		return SellerList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.dao.SellerDAO#getBySellerId(java.lang.Integer)
	 */
	public Seller getBySellerId(Integer sellerId) {
		String query = "FROM Seller WHERE id = " + sellerId;
		Seller seller = (Seller) session1.createQuery(query).uniqueResult();
		return seller;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.dao.SellerDAO#getListByApartmentId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<Seller> getListByApartmentId(Integer apartmentId) {
		String query = "FROM Seller WHERE apartment = " + apartmentId;
		List<Seller> sellerList = session1.createQuery(query).list();
		return sellerList;
	}
}
