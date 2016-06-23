package com.FoodyBuddy.DAO.Impl;

import com.FoodyBuddy.DAO.Buyer;
import com.FoodyBuddy.Model.Buyer;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class BuyerDAOImpl implements BuyerDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(BuyerDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void insertBuyer(Buyer buyer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(buyer);
		logger.info("Buyer saved successfully, Buyer Details=" + buyer);
	}

	@Override
	public void updateBuyer(Buyer buyer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(buyer);
		logger.info("Buyer updated successfully, Buyer Details=" + buyer );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Buyer> getAllBuyers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Buyer> BuyersList = session.createQuery("from Buyer").list();
		for(Buyer buyer : BuyersList){
			logger.info("Buyer List::" + buyer);
		}
		return BuyersList;
	}

	@Override
	public Buyer getBuyer(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Buyer buyer = (Buyer) session.load(Buyer.class, new Integer(id));
		logger.info("Buyer loaded successfully, Buyer details=" + buyer);
		return buyer;
	}

	@Override
	public void deleteBuyer(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Buyer buyer = (Buyer) session.load(Buyer.class, new Integer(id));
		if(null != buyer){
			session.delete(buyer);
		}
		logger.info("Buyer deleted successfully, Buyer details=" + buyer);
	}

}