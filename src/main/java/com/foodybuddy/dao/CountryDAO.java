package com.foodybuddy.dao;

import java.util.List;

import com.foodybuddy.model.Country;

public interface CountryDAO {
	public Country getById(int id);
	public List<Country> getAll();
	public void insert(Country country);
	public void update(Country country);
	public void delete(Country country);
}
