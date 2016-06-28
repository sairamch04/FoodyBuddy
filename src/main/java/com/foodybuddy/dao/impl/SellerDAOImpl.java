package com.foodybuddy.dao.impl;
import java.util.List;
import org.hibernate.Session;
import com.foodybuddy.dao.SellerDAO;
import com.foodybuddy.model.Seller;


public class SellerDAOImpl implements SellerDAO {
	private Session session;

    	public SellerDAOImpl(Session session) {
        this.session = session;
    	}
	public void insert(Seller s) {
		session.persist(s);
	}
	public void update(Seller seller ){
		   session.update(seller); 
	}
	public void delete(Integer id) {
		Seller seller = (Seller) session.load(Seller.class, new Integer(id));
		session.delete(seller);
	}
	@SuppressWarnings("unchecked")
	public List<Seller> getListSeller() {
		List<Seller> SellerList = session.createQuery("from Seller").list();
		return SellerList;
	}
    	public Seller getListBySellerId(Integer sellerId){
        String query= "FROM Seller WHERE id = " + sellerId;
        Seller seller = (Seller) session.createQuery(query).uniqueResult();
        return seller;    
    	}
	@SuppressWarnings("unchecked")
    	public List<Seller> getListByApartmentId(Integer apartmentId){
        String query= "FROM Seller WHERE apartment_id = " + apartmentId;
        List<Seller> sellerList = session.createQuery(query).list();
        return sellerList;    
    	}
	public Seller getListByEmail(String email) {
	    String query= "FROM Seller WHERE email = " + email;
	    Seller seller = (Seller) session.createQuery(query).uniqueResult();
        return seller;    
	}
}
