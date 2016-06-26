package com.FoodyBuddy.Dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.FoodyBuddy.Dao.BuyerDao;
import com.FoodyBuddy.Model.Buyer;


@Repository
public class BuyerDaoImpl implements BuyerDao {
	
	private Session session = null;
	
	public BuyerDaoImpl(Session session){
		this.session = session;
	}

	public void saveBuyer(Buyer buyer) {
		session.persist(buyer);
	}

	public void updateBuyer(Buyer buyer) {

		session.update(buyer);
	}

	@SuppressWarnings("unchecked")
	public List<Buyer> getAllBuyers() {
		List<Buyer> BuyersList = session.createQuery("from Buyer").list();
		return BuyersList;
	}

	public Buyer getBuyer(int id) {		
		Buyer buyer = (Buyer) session.load(Buyer.class, new Integer(id));
		return buyer;
	}

	public void deleteBuyer(int id) {
		Buyer buyer = (Buyer) session.load(Buyer.class, new Integer(id));
		if(null != buyer){
			session.delete(buyer);
		}
	}

}