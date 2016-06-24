package com.FoodyBuddy.DAO;

import java.util.List;
import com.FoodyBuddy.Model.Locality;

public interface LocalityDAO {
	public List<Locality> getAllLocalities();
	public Locality getLocality(int id);
	public void insertLocality(Locality locality);
	public void updateLocality(Locality locality);
	public void deleteLocality(int id);
}