package com.FoodyBuddy.DAO;

import java.util.List;
import com.FoodyBuddy.Model.City;

public interface CityDAO {
	public List<City> getAllCities();
	public City getCity(int id);
	public void insertCity(City city);
	public void updateCity(City city);
	public void deleteCity(int id);
}