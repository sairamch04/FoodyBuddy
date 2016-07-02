package com.foodybuddy.dao.impl;

import com.foodybuddy.dao.ApartmentDAO;
import com.foodybuddy.model.Apartment;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * The Class ApartmentDAOImpl.
 */
@Repository
public class ApartmentDAOImpl implements ApartmentDAO {
	
	/** The session. */
	private Session session;
	
	/** The Object null exception. */
	RuntimeException ObjectNullException = new RuntimeException("Object is null");
	
	/**
	 * Instantiates a new apartment DAO impl.
	 *
	 * @param session the session
	 */
	public ApartmentDAOImpl(Session session){
		if(session == null){
			throw ObjectNullException;
		}
		this.session = session;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.ApartmentDAO#insert(com.foodybuddy.model.Apartment)
	 */
	public Apartment insert(Apartment apartment) {
		if(apartment == null){
			throw ObjectNullException;
		}
		this.session.persist(apartment);
		return apartment;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.ApartmentDAO#update(com.foodybuddy.model.Apartment)
	 */
	public Apartment update(Apartment apartment) {
		if(apartment == null){
			throw ObjectNullException;
		}
		this.session.update(apartment);
		return apartment;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.ApartmentDAO#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Apartment> getAll() {
		String query = "FROM Apartment";
		List<Apartment> citiesList = this.session.createQuery(query).list();
		return citiesList;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.ApartmentDAO#getById(java.lang.Integer)
	 */
	public Apartment getById(Integer id) {	
		if(id == null){
			throw ObjectNullException;
		}
		String query = "FROM Apartment WHERE id = "+id;
		Apartment apartment = (Apartment) this.session.createQuery(query).uniqueResult();
		return apartment;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.ApartmentDAO#delete(com.foodybuddy.model.Apartment)
	 */
	public void delete(Apartment apartment) {
		if(apartment == null){
			throw ObjectNullException;
		}
		this.session.delete(apartment);
	}

}