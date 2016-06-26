package com.foodybuddy.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.foodybuddy.dao.BuyerDao;
import com.foodybuddy.model.Buyer;


@Repository
public class BuyerDAOImpl implements BuyerDAO {
	private Session session = null;
	
	public BuyerDAOImpl(Session session){
		this.session = session;
	}

	public void insert(Buyer buyer) {
		session.persist(buyer);
	}

	public void update(Buyer buyer) {
		session.update(buyer);
	}

	@SuppressWarnings("unchecked")
	public List<Buyer> getAll() {
		List<Buyer> buyersList = session.createQuery("FROM Buyer").list();
		return buyersList;
	}

	public Buyer getById(int id) {
		String query = "From Buyer WHERE id = " + id;
		Buyer buyer = (Buyer)session.createQuery(query).uniqueResult();
		return buyer;
	}

	public void delete(Buyer buyer) {
		session.delete(buyer);
	}

}