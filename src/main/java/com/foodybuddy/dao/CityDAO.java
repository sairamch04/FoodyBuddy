package com.foodybuddy.dao;

import java.util.List;
import com.foodybuddy.model.City;

public interface CityDao {
	public City getById(int id);
	public List<City> getAll();
	public void insert(City city);
	public void update(City city);
	public void delete(City city);
}