package com.FoodyBuddy.Dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.FoodyBuddy.Dao.SellerDAO;
import com.FoodyBuddy.Model.Seller;


public class SellerDAOImpl implements SellerDAO {

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	public void save(Seller s) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(s);
		tx.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Seller> list() {
		Session session = this.sessionFactory.openSession();
		List<Seller> SellerList = session.createQuery("from Seller").list();
		session.close();
		return SellerList;
	}

	public void update(Seller s) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(s);
		tx.commit();
		session.close();
		
	}

	public void delete(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		Seller seller = (Seller) session.load(Seller.class, new Integer(id));
		if(null != seller)
		{
			session.delete(seller);
		}
	}

}
