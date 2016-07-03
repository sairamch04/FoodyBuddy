package com.foodybuddy.dao.impl;

import com.foodybuddy.dao.LocalityDAO;
import com.foodybuddy.model.Locality;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * The Class LocalityDAOImpl.
 */
@Repository
public class LocalityDAOImpl implements LocalityDAO {
	
	/** The session. */
	private Session session;
	
	/** The Object null exception. */
	RuntimeException ObjectNullException = new RuntimeException("Object is null");
	
	/**
	 * Instantiates a new locality DAO impl.
	 *
	 * @param session the session
	 */
	public LocalityDAOImpl(Session session){
		if(session == null){
			throw ObjectNullException;
		}
		this.session = session;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.LocalityDAO#insert(com.foodybuddy.model.Locality)
	 */
	public Locality insert(Locality locality) {
		if(locality == null){
			throw ObjectNullException;
		}
		this.session.persist(locality);
		return locality;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.LocalityDAO#update(com.foodybuddy.model.Locality)
	 */
	public Locality update(Locality locality) {
		if(locality == null){
			throw ObjectNullException;
		}
		this.session.update(locality);
		return locality;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.LocalityDAO#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Locality> getAll() {
		String query = "FROM Locality";
		List<Locality> citiesList = this.session.createQuery(query).list();
		return citiesList;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.LocalityDAO#getById(java.lang.Integer)
	 */
	public Locality getById(Integer id) {	
		if(id == null){
			throw ObjectNullException;
		}
		String query = "FROM Locality WHERE id = "+id;
		Locality locality = (Locality) this.session.createQuery(query).uniqueResult();
		return locality;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.LocalityDAO#delete(com.foodybuddy.model.Locality)
	 */
	public void delete(Locality locality) {
		if(locality == null){
			throw ObjectNullException;
		}
		this.session.delete(locality);
	}

}