package com.foodybuddy.dao;

import java.util.List;
import com.foodybuddy.model.Buyer;

public interface BuyerDAO{
	public Buyer getById(int id);
	public List<Buyer> getAll();
	public void insert(Buyer buyer);
	public void update(Buyer buyer);
	public void delete(Buyer buyer);
}