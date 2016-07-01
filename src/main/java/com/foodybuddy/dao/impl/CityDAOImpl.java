package com.foodybuddy.dao.impl;

import com.foodybuddy.dao.CityDAO;
import com.foodybuddy.model.City;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * The Class CityDAOImpl.
 */
@Repository
public class CityDAOImpl implements CityDAO {
	
	/** The session. */
	private Session session;
	
	/** The Object null exception. */
	RuntimeException ObjectNullException = new RuntimeException("Object is null");
	
	/**
	 * Instantiates a new city DAO impl.
	 *
	 * @param Session session
	 */
	public CityDAOImpl(Session session){
		if(session == null){
			throw ObjectNullException;
		}
		this.session = session;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.CityDAO#insert(com.foodybuddy.model.City)
	 */
	public City insert(City city) {
		if(city == null){
			throw ObjectNullException;
		}
		this.session.persist(city);
		return city;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.CityDAO#update(com.foodybuddy.model.City)
	 */
	public City update(City city) {
		if(city == null){
			throw ObjectNullException;
		}
		this.session.update(city);
		return city;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.CityDAO#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List<City> getAll() {
		String query = "FROM City";
		List<City> citiesList = this.session.createQuery(query).list();
		return citiesList;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.CityDAO#getById(java.lang.Integer)
	 */
	public City getById(Integer id) {	
		if(id == null){
			throw ObjectNullException;
		}
		String query = "FROM City WHERE id = "+id;
		City city = (City) this.session.createQuery(query).uniqueResult();
		return city;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.CityDAO#delete(com.foodybuddy.model.City)
	 */
	public void delete(City city) {
		if(city == null){
			throw ObjectNullException;
		}
		this.session.delete(city);
	}

}