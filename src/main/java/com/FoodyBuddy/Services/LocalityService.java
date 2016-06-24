package com.FoodyBuddy.Service;

import com.FoodyBuddy.Model.Locality;
import java.util.List;
import org.hibernate.TransactionException;

public interface LocalityService {

	public Locality getLocality(int id);
	public List<Locality> getAllLocalities();
	public void insertLocality(String name, String pincode, int cityId) throws TransactionException;
	public void updateLocality(Locality locality) throws TransactionException;
	public void deleteLocality(int id) throws TransactionException;
}