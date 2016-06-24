package com.FoodyBuddy.Dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.FoodyBuddy.Dao.DishDAO;
import com.FoodyBuddy.Model.Dish;


public class DishDAOImpl implements DishDAO {

	private SessionFactory sessionFactory;

    public DishDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	public void save(Dish d) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(d);
		tx.commit();
		session.close();
	}
	
	public void update(Dish Dish )
		 {
		      Session session = sessionFactory.openSession();
		      Transaction tx = null;
		      try{
		         tx = session.beginTransaction();
				 session.update(Dish); 
		         tx.commit();
		      }
		      finally {
		         session.close(); 
		      }
		 }


	public void delete(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		Dish Dish = (Dish) session.load(Dish.class, new Integer(id));
		if(null != Dish)
		{
			session.delete(Dish);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Dish> listdish() {
		Session session = this.sessionFactory.openSession();
		List<Dish> DishList = session.createQuery("from Dish").list();
		session.close();
		return DishList;
	}


	@SuppressWarnings("unchecked")
    public Dish listByDishId(Integer DishId){
        Session session = this.sessionFactory.openSession();
        String query= "FROM Dish WHERE id = " + DishId;
        Dish dish = (Dish) session.createQuery(query).list().get(0);
        session.close();
        return dish;    
    }
	
	@SuppressWarnings("unchecked")
    public List<Dish> listBySellerId(Integer sellerid){
        Session session = this.sessionFactory.openSession();
        String query= "FROM Dish WHERE aseller_id = " + sellerid;
        List<Dish> Dish_by_seller_id = session.createQuery(query).list();
        session.close();
        return Dish_by_seller_id;    
    }
	
}
