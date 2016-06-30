package com.foodybuddy.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.foodybuddy.dao.LocalityDAO;
import com.foodybuddy.model.Locality;

/***
 * Interface implementation for accessing Locality table
 */
@Repository
public  class LocalityDAOImpl implements LocalityDAO {
	
	private Session session = null;
	
	/***
	 * Constructor
	 * @param session
	 * @throws NullPointerException
	 */
	public LocalityDAOImpl(Session session) throws NullPointerException{
	    if(session == null) {
	        throw new NullPointerException("session passed to Apartment constructor is null");
	    }
		this.session = session;
	}
	
	/***
	 * Locality insert
	 * @param locality
	 * @return locality
	 * @throws NullPointerException
	 */
	public Locality insert(Locality locality) throws NullPointerException{
	    if(locality == null) {
            throw new NullPointerException("null locality is passed to Locality insert");
        }
		session.persist(locality);
		return locality;
	}
	
	/***
     * Locality update
     * @param locality
     * @return locality
     * @throws NullPointerException
     */
	public Locality update(Locality locality) throws NullPointerException{
	    if(locality == null) {
            throw new NullPointerException("null locality is passed to Locality update");
        }
		session.update(locality);
		return locality;
	}
	
	/***
     * Locality delete
     * @param locality
     * @return locality
     * @throws NullPointerException
     */
	/*
	public Locality delete(Locality locality) throws NullPointerException {
	    if(locality == null) {
            throw new NullPointerException("null locality is passed to Locality delete");
        }
	    session.delete(locality);
        return locality;
    }
    */
	
	/***
	 * Locality getAll
	 * @return List<Locality> : List of all localities
	 */
	@SuppressWarnings("unchecked")
	public List<Locality> getAll() {
		String query = "FROM Locality";
		List<Locality> localitiesList = session.createQuery(query).list();
		return localitiesList;
	}

	/***
	 * Locality getById
	 * @param id
	 * @return locality
	 * @throws NullPointerException
	 */
	public Locality getById(Integer id) throws NullPointerException{
	    if(id == null) {
            throw new NullPointerException("null id is passed to Locality getById");
        }
		String query = "FROM Locality where id = "+id;
		Locality locality = (Locality)session.createQuery(query).uniqueResult();
		return locality;
	}
	

}