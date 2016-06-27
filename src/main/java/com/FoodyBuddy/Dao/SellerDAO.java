package com.foodybuddy.dao;
import java.util.List;
import com.foodybuddy.model.Seller;

public interface SellerDAO {

	public void insert(Seller s);
	public void update(Seller s);
	public void delete(Integer id);
	public List<Seller> getListSeller();
	public Seller getListBySellerId(Integer sellerId);
	public List<Seller> getListByApartmentId(Integer apartmentId);
}
