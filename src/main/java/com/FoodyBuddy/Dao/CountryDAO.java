package com.FoodyBuddy.Dao;

import java.util.List;

import com.FoodyBuddy.Model.Country;

public interface CountryDAO {

	public void save(Country c);
	
	public List<Country> list();
}
