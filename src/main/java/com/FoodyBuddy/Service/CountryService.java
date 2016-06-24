package com.FoodyBuddy.Service;

import java.util.List;

import com.FoodyBuddy.Model.Country;

public interface CountryService {
	
	public void saveCountry(Country country) throws Exception;
	
	public void updateCountry(Country country) throws Exception;
	
	public void deleteCountry(Country country) throws Exception;
	
	public Country getCountry(int id);
	
	public List<Country> getAllCountries(); 
	
}
