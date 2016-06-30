package com.foodybuddy.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.foodybuddy.dao.ApartmentDAO;
import com.foodybuddy.model.Apartment;

/***
 * Interface implementation for accessing Apartment Table
 */
@Repository
public class ApartmentDAOImpl implements ApartmentDAO {
	
	private Session session = null;
	
	/***
	 * Constructor
	 * @param session
	 * @throws NullPointerException
	 */
	public ApartmentDAOImpl(Session session) throws NullPointerException{
	    if(session == null) {
	        throw new NullPointerException("session passed to Apartment constructor is null");
	    }
		this.session = session;
	}
	
	/***
	 * Apartment insert
	 * @param apartment
	 * @throws NullPointerException
	 * @return apartment 
	 */
	public Apartment insert(Apartment apartment) throws NullPointerException {
	    if(apartment == null) {
            throw new NullPointerException("null apartment is passed to Apartment insert");
        }
		session.persist(apartment);
		return apartment;
	}
	
	/***
     * Apartment update
     * @param apartment
     * @throws NullPointerException
     * @return apartment 
     */
	public Apartment update(Apartment apartment) throws NullPointerException {
	    if(apartment == null) {
            throw new NullPointerException("null apartment is passed to Apartment update");
        }
		session.update(apartment);
		return apartment;
	}
	

	/***
     * Apartment delete
     * @param apartment
     * @throws NullPointerException
     * @return apartment 
     */
	/*
	public Apartment delete(Apartment apartment) throws NullPointerException {
	    if(apartment == null) {
            throw new NullPointerException("null apartment is passed to Apartment delete");
        }
        session.delete(apartment);
        return apartment;
	}
	*/
	
	/***
     * Apartment getAll
     * @return List<Apartment> : list of all apartments 
     */
	@SuppressWarnings("unchecked")
	public List<Apartment> getAll() {
		List<Apartment> ApartmentsList = session.createQuery("FROM Apartment").list();
		return ApartmentsList;
	}
	
	/***
     * Apartment getById
     * @param id
     * @throws NullPointerException
     * @return apartment 
     */
	public Apartment getById(Integer id) throws NullPointerException {
	    if(id == null) {
            throw new NullPointerException("null id is passed to Apartment getById");
        }
		String query = "FROM Apartment WHERE id = " + id;
		Apartment apartment = (Apartment)session.createQuery(query).uniqueResult();
		return apartment;
	}

}