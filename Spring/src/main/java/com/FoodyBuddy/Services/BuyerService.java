package com.FoodyBuddy.Service;

import com.FoodyBuddy.Model.Buyer;
import java.util.List;

public interface BuyerService {

	public Buyer getBuyer(int id);
	public List<Buyer> getAllBuyers();
	public void insertBuyer(int apartmentId, String name, String mobileNumber, String email, String flatNumber, Date createdAt, Date updatedAt, Date deletedAt, Boolean isActive) throws TransactionException;
	public void updateBuyer(Buyer buyer) throws TransactionException;
	public void deleteBuyer(int id) throws TransactionException;
}