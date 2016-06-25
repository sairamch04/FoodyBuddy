package com.FoodyBuddy.Service;

import com.FoodyBuddy.Model.Country;
import java.util.List;
import org.hibernate.TransactionException;


public interface CountryService {

	public Country getCountry(int id);
	public List<Country> getAllCountries();
	public void insertCountry(String name) throws TransactionException;
	public void updateCountry(Country country) throws TransactionException;
	public void deleteCountry(int id) throws TransactionException;
}