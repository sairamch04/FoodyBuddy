package com.FoodyBuddy.Dao;

import java.util.List;

import com.FoodyBuddy.Model.Country;
import com.FoodyBuddy.Model.Seller;

public interface CountryDAO {

	public void save(Country c);
	
	public List<Country> list();
	
	public void update(Country c);
	public void delete(Integer id);
}
