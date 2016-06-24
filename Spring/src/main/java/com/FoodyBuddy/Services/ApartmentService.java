package com.FoodyBuddy.Service;

import com.FoodyBuddy.Model.Apartment;
import java.util.List;

public interface ApartmentService {

	public Apartment getApartment(int id);
	public List<Apartment> getAllApartments();
	public void insertApartment(String name, int localityId, int blockNumber) throws TransactionException;
	public void updateApartment(Apartment apartment) throws TransactionException;
	public void deleteApartment(int id) throws TransactionException;
}