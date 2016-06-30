package com.foodybuddy.dao;

import java.util.List;

import com.foodybuddy.model.Apartment;
/***
 * Interface for accessing Apartment Table
 */
public interface ApartmentDAO {
	public Apartment insert(Apartment apartment) throws NullPointerException;
	public Apartment update(Apartment apartment) throws NullPointerException;
	//public Apartment delete(Apartment apartment) throws NullPointerException;
	public List<Apartment> getAll();
    public Apartment getById(Integer id) throws NullPointerException;
}