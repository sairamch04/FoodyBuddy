package com.foodybuddy.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.foodybuddy.dao.BuyerDAO;
import com.foodybuddy.model.Buyer;

/***
 * Interface implementation for accessing Buyer Table
 */
@Repository
public class BuyerDAOImpl implements BuyerDAO {
	private Session session = null;
	
	/***
	 * Constructor
	 * @param session
	 * @throws NullPointerException
	 */
	public BuyerDAOImpl(Session session) throws NullPointerException{
	    if(session == null) {
	        throw new NullPointerException("session passed to Buyer constructor is null");
	    }
		this.session = session;
	}

	/***
	 * Buyer insert
	 * @param buyer
	 * @return buyer
	 * @throws NullPointerException
	 */
	public Buyer insert(Buyer buyer) throws NullPointerException {
	    if(buyer == null) {
	        throw new NullPointerException("null apartment is passed to Buyer insert");
	    }
		session.persist(buyer);
		return buyer;
	}
	
	/***
	 * Buyer update
	 * @param buyer
	 * @return buyer
	 * @throws NullPointerException
	 */
	public Buyer update(Buyer buyer) throws NullPointerException {
	    if(buyer == null) {
            throw new NullPointerException("null apartment is passed to Buyer update");
        }
		session.update(buyer);
		return buyer;
	}
	
	/***
	 * Buyer delete
	 * @param buyer
	 * @return buyer
	 * @throws NullPointerException
	 */
	/*
	public Buyer delete(Buyer buyer) throws NullPointerException {
	    if(buyer == null) {
            throw new NullPointerException("null apartment is passed to Buyer delete");
        }
        session.delete(buyer);
        return buyer;
    }
    */

	/***
	 * Buyer getAll
	 * @return List<Buyer> : list of all buyers
	 */
	@SuppressWarnings("unchecked")
	public List<Buyer> getAll() {
		List<Buyer> buyersList = session.createQuery("FROM Buyer").list();
		return buyersList;
	}
	
	/***
	 * Buyer getById
	 * @param id
	 * @return buyer
	 * @throws NullPointerException
	 */
	public Buyer getById(Integer id) throws NullPointerException {
		String query = "From Buyer WHERE id = " + id;
		Buyer buyer = (Buyer)session.createQuery(query).uniqueResult();
		return buyer;
	}

}