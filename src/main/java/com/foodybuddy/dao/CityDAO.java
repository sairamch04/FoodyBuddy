package com.foodybuddy.dao;

import java.util.List;
import com.foodybuddy.model.City;

public interface CityDAO {
	public City getById(Integer id);
	public List<City> getAll();
	public City insert(City city);
	public City update(City city);
	public City delete(City city);
}