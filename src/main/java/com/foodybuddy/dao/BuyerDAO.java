package com.foodybuddy.dao;

import java.util.List;
import com.foodybuddy.model.Buyer;
/***
 * Interface for accessing Buyer Table
 */
public interface BuyerDAO{
	public Buyer insert(Buyer buyer) throws NullPointerException;
	public Buyer update(Buyer buyer) throws NullPointerException;
	//public Buyer delete(Buyer buyer) throws NullPointerException;
	public List<Buyer> getAll();
	public Buyer getById(Integer id) throws NullPointerException;
}