package com.FoodyBuddy.Dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
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

	public void update(Seller seller )
		 {
		      Session session = sessionFactory.openSession();
		      Transaction tx = null;
		      try{
		         tx = session.beginTransaction();
				 session.update(seller); 
		         tx.commit();
		      }
		      finally {
		         session.close(); 
		      }
		 }


	public void delete(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		Seller seller = (Seller) session.load(Seller.class, new Integer(id));
		if(null != seller)
		{
			session.delete(seller);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Seller> listseller() {
		Session session = this.sessionFactory.openSession();
		List<Seller> SellerList = session.createQuery("from Seller").list();
		session.close();
		return SellerList;
	}

	@SuppressWarnings("unchecked")
    public List<Seller> listsellerBySellerId(Integer sellerId){
        Session session = this.sessionFactory.openSession();
        String query= "FROM Seller WHERE id = " + sellerId;
        List<Seller> seller_by_id = session.createQuery(query).list();
        session.close();
        return seller_by_id;    
    }
	
	@SuppressWarnings("unchecked")
    public List<Seller> listsellerByApartmentId(Integer apartmentId){
        Session session = this.sessionFactory.openSession();
        String query= "FROM Seller WHERE apartment_id = " + apartmentId;
        List<Seller> seller_by_apartment_id = session.createQuery(query).list();
        session.close();
        return seller_by_apartment_id;    
    }
	
}
