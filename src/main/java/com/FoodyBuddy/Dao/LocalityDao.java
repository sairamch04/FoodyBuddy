package com.FoodyBuddy.Dao;

import java.util.List;
import com.FoodyBuddy.Model.Locality;

public interface LocalityDao {
	public List<Locality> getAllLocalities();
	public Locality getLocality(int id);
	public void saveLocality(Locality locality);
	public void updateLocality(Locality locality);
	public void deleteLocality(Locality locality);
}