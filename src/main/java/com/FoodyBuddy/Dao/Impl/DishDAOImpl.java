package com.foodybuddy.dao.impl;
import java.util.List;
import org.hibernate.Session;
import com.foodybuddy.dao.DishDAO;
import com.foodybuddy.model.Dish;


public class DishDAOImpl implements DishDAO {
	private Session session;
	public DishDAOImpl(Session session) {
        this.session = session;
    }
	public void insert(Dish d) {
		session.persist(d);
	}
	public void update(Dish Dish ){
		     session.update(Dish); 
	}
	public void delete(Integer id) {
		Dish Dish = (Dish) session.load(Dish.class, new Integer(id));
	}

	@SuppressWarnings("unchecked")
	public List<Dish> getListdish() {
		List<Dish> DishList = session.createQuery("from Dish").list();
		return DishList;
	}
    public Dish getListByDishId(Integer DishId){
        String query= "FROM Dish WHERE id = " + DishId;
        Dish dish = (Dish) session.createQuery(query).list().get(0);
        return dish;    
    }
	@SuppressWarnings("unchecked")
    public List<Dish> getListBySellerId(Integer sellerid){
        String query= "FROM Dish WHERE aseller_id = " + sellerid;
        List<Dish> Dish_by_seller_id = session.createQuery(query).list();
        return Dish_by_seller_id;    
    }
}
