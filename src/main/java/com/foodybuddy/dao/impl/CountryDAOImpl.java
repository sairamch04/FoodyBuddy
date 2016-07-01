package com.foodybuddy.dao.impl;

import com.foodybuddy.dao.CountryDAO;
import com.foodybuddy.model.Country;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


/**
 * The Class CountryDAOImpl.
 */
@Repository
public class CountryDAOImpl implements CountryDAO {

	/** The session. */
	private Session session;
	
	/** The Object null exception. */
	RuntimeException ObjectNullException = new RuntimeException("Object is null");
	
    /**
     * Instantiates a new country DAO impl.
     *
     * @param Session session
     */
    public CountryDAOImpl(Session session){
		if(session == null){
			throw ObjectNullException;
		}
    	this.session = session;
    }
    
	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.CountryDAO#insert(com.foodybuddy.model.Country)
	 */
	public Country insert(Country country) {
		if(country == null){
			throw ObjectNullException;
		}
		this.session.persist(country);
		return country;
	}
	
	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.CountryDAO#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Country> getAll() {
		String query = "FROM Country";
		List<Country> countryList = this.session.createQuery(query).list();
		return countryList;
	}
	
	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.CountryDAO#getById(java.lang.Integer)
	 */
	public Country getById(Integer id) {
		if(id == null){
			throw ObjectNullException;
		}
		String query = "FROM Country WHERE id = "+id;
		Country country = (Country)this.session.createQuery(query).uniqueResult();
		return country;
	}
	
	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.CountryDAO#update(com.foodybuddy.model.Country)
	 */
	public Country update(Country country) {
		if(country == null){
			throw ObjectNullException;
		}
		this.session.update(country);
		return country;
	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.dao.CountryDAO#delete(com.foodybuddy.model.Country)
	 */
	public void delete(Country country) {
		if(country == null){
			throw ObjectNullException;
		}
		this.session.delete(country);
	}

}