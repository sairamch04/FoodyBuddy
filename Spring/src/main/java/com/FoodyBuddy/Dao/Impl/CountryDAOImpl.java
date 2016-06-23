package com.FoodyBuddy.Dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.FoodyBuddy.Dao.CountryDAO;
import com.FoodyBuddy.Model.Country;


public class CountryDAOImpl implements CountryDAO {

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	public void save(Country c) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(c);
		tx.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Country> list() {
		Session session = this.sessionFactory.openSession();
		List<Country> CountryList = session.createQuery("from Country").list();
		session.close();
		return CountryList;
	}

}
