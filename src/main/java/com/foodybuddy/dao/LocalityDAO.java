package com.foodybuddy.dao;

import java.util.List;
import com.foodybuddy.model.Locality;

/***
 * Interface for accessing Locality table
 */
public interface LocalityDAO {
	public Locality insert(Locality locality) throws NullPointerException;
	public Locality update(Locality locality) throws NullPointerException;
	//public Locality delete(Locality locality) throws NullPointerException;
	public List<Locality> getAll();
    public Locality getById(Integer id) throws NullPointerException;
}