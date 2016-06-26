package com.FoodyBuddy.Dao;

import java.util.List;
import com.FoodyBuddy.Model.City;

public interface CityDao {
	public List<City> getAllCities();
	public City getCity(int id);
	public void saveCity(City city);
	public void updateCity(City city);
	public void deleteCity(int id);
}