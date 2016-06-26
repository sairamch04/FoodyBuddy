package com.foodybuddy.dao;

import java.util.List;
import com.foodybuddy.model.Apartment;

public interface ApartmentDAO {
	public List<Apartment> getAll();
	public Apartment getById(int id);
	public void insert(Apartment apartment);
	public void update(Apartment apartment);
	public void delete(Apartment apartment);
}