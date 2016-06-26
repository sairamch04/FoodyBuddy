package com.foodybuddy.dao;

import java.util.List;
import com.foodybuddy.model.Locality;

public interface LocalityDAO {
	public List<Locality> getAll();
	public Locality getById(int id);
	public void save(Locality locality);
	public void update(Locality locality);
	public void delete(Locality locality);
}