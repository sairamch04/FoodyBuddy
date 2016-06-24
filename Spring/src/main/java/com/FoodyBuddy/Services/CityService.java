package com.FoodyBuddy.Service;

import com.FoodyBuddy.Model.City;
import java.util.List;

public interface CityService {

	public City getCity(int id);
	public List<City> getAllCities();
	public void insertCity(String name, int stateId) throws TransactionException;
	public void updateCity(City city) throws TransactionException;
	public void deleteCity(int id) throws TransactionException;
}