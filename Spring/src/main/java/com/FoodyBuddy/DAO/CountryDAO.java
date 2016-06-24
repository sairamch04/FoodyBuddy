package com.FoodyBuddy.DAO;

import java.util.List;
import com.FoodyBuddy.Model.Country;

public interface CountryDAO {
	public List<Country> getAllCountries();
	public Country getCountry(int countryId);
	public void insertCountry(Country country);
	public void updateCountry(Country country);
	public void deleteCountry(int id);
}