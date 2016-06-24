package com.FoodyBuddy.Dao;

import java.util.List;
import com.FoodyBuddy.Model.Buyer;

public interface BuyerDao{

	public Buyer getBuyer(int id);
	public List<Buyer> getAllBuyers();
	public void saveBuyer(Buyer buyer);
	public void updateBuyer(Buyer buyer);
	public void deleteBuyer(int id);
}