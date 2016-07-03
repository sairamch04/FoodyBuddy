package com.foodybuddy.dao.impl;

import com.foodybuddy.dao.BuyerDAO;
import com.foodybuddy.model.Buyer;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * The Class BuyerDAOImpl.
 */
@Repository
public class BuyerDAOImpl implements BuyerDAO {
	
	/** The session. */
	private Session session;
	
	/** The Object null exception. */
	RuntimeException ObjectNullException = new RuntimeException("Object is null");
	
	/**
	 * Instantiates a new buyer DAO impl.
	 *
	 * @param session the session
	 */
	public BuyerDAOImpl(Session session){
		if(session == null){
			throw ObjectNullException;
		}
		this.session = session;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.BuyerDAO#insert(com.foodybuddy.model.Buyer)
	 */
	public Buyer insert(Buyer buyer) {
		if(buyer == null){
			throw ObjectNullException;
		}
		this.session.persist(buyer);
		return buyer;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.BuyerDAO#update(com.foodybuddy.model.Buyer)
	 */
	public Buyer update(Buyer buyer) {
		if(buyer == null){
			throw ObjectNullException;
		}
		this.session.update(buyer);
		return buyer;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.BuyerDAO#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Buyer> getAll() {
		String query = "FROM Buyer";
		List<Buyer> buyersList = this.session.createQuery(query).list();
		return buyersList;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.BuyerDAO#getById(java.lang.Integer)
	 */
	public Buyer getById(Integer id) {	
		if(id == null){
			throw ObjectNullException;
		}
		String query = "FROM Buyer WHERE id = "+id;
		Buyer buyer = (Buyer) this.session.createQuery(query).uniqueResult();
		return buyer;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.BuyerDAO#delete(com.foodybuddy.model.Buyer)
	 */
	public void delete(Buyer buyer) {
		if(buyer == null){
			throw ObjectNullException;
		}
		this.session.delete(buyer);
	}

}