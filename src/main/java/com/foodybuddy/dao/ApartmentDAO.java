package com.FoodyBuddy.Dao;

import java.util.List;
import com.FoodyBuddy.Model.Apartment;

public interface ApartmentDao {
	public List<Apartment> getAllApartments();
	public Apartment getApartment(int id);
	public void saveApartment(Apartment apartment);
	public void updateApartment(Apartment apartment);
	public void deleteApartment(int id);
}