package com.FoodyBuddy.Dao;
import java.util.List;
import com.FoodyBuddy.Model.Seller;

public interface SellerDAO {

	public void save(Seller s);
	public void update(Seller s);
	public void delete(Integer id);
	public List<Seller> listseller();
	public Seller listBySellerId(Integer sellerId);
	public List<Seller> listByApartmentId(Integer apartmentId);
}
