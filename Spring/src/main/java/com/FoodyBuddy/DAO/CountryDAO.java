package com.FoodyBuddy.DAO;

import java.util.List;
import com.FoodyBuddy.Model.Country;

public interface CountryDAO {
	public List<Countryr> getAllCountries();
	public Country getCountry(int countryId);
	public boolean insertCountry(Country country);
	public boolean updateCountry(Country country);
	public boolean deleteCountry(int id);
}