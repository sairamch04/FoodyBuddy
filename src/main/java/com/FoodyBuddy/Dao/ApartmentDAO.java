package com.FoodyBuddy.DAO;

import java.util.List;
import com.FoodyBuddy.Model.Apartment;

public interface ApartmentDAO {
	public List<Apartment> getAllApartments();
	public Apartment getApartment(int id);
	public void insertApartment(Apartment apartment);
	public void updateApartment(Apartment apartment);
	public void deleteApartment(int id);
}