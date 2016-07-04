package com.foodybuddy.service.impl;

import com.foodybuddy.dao.CityDAO;
import com.foodybuddy.dao.LocalityDAO;
import com.foodybuddy.dao.impl.CityDAOImpl;
import com.foodybuddy.dao.impl.LocalityDAOImpl;
import com.foodybuddy.model.City;
import com.foodybuddy.model.Locality;
import com.foodybuddy.service.LocalityService;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

/**
 * The Class LocalityServiceImpl.
 */
@Service("localityService")
public class LocalityServiceImpl implements LocalityService {

	/** The Transaction failure exception. */
	TransactionException TransactionFailureException = new TransactionException(
			"Transaction could not be completed and rollback inititated!");

	/** The Rollback failure exception. */
	TransactionException RollbackFailureException = new TransactionException("Transaction and Rollback Failed!");

	/** The Object null exception. */
	RuntimeException ObjectNullException = new RuntimeException("Object is null");

	/** The session factory. */
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a new locality service impl.
	 *
	 * @param sessionFactory the session factory
	 */
	public LocalityServiceImpl(SessionFactory sessionFactory) {
		if (sessionFactory == null) {
			throw ObjectNullException;
		}
		this.sessionFactory = sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.LocalityService#getById(java.lang.Integer)
	 */
	public Locality getById(Integer id) throws Exception {
		
		Session session = null;
		try {
			if (id == null || id <= 0) {
				if (id == null) {
					throw new RuntimeException("ID is null");
				} else {
					throw new RuntimeException("ID is invalid");
				}
			}
			
			session = this.sessionFactory.openSession();
			LocalityDAO localityDAO = new LocalityDAOImpl(session);
			return localityDAO.getById(id);
			
		} catch (Exception exception) {
			throw exception;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.LocalityService#getAll()
	 */
	public List<Locality> getAll() throws Exception {
		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			LocalityDAO localityDAO = new LocalityDAOImpl(session);
			List<Locality> localityList = localityDAO.getAll();
			return localityList;
		} catch (Exception exception) {
			throw exception;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.LocalityService#insert(java.lang.String,
	 * java.lang.Integer)
	 */
	public Locality insert(String name, Integer cityId, String pincode) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {
			
			if (name == null || cityId == null || cityId <= 0 || name.trim().length() == 0 || pincode == null) {

				if (name == null) {
					throw new RuntimeException("Name is null");
				}

				else if (cityId == null) {
					throw new RuntimeException("CityId is null");
				}

				else if (cityId <= 0) {
					throw new RuntimeException("CityId is invalid");
				}

				else if(name.trim().length() == 0) {
					throw new RuntimeException("Name is invalid");
				}
				
				else {
				    throw new RuntimeException("Pincode is invalid");
				}
			}
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			CityDAO cityDao = new CityDAOImpl(session);
			City city = cityDao.getById(cityId);

			if (city == null) {
				throw ObjectNullException;
			}

			Locality locality = new Locality();
			
			locality.setName(name);
			locality.setCity(city);
			locality.setPincode(pincode);
			locality.setIsActive(true);
			java.util.Date date = new java.util.Date();
			locality.setCreatedAt(new Timestamp(date.getTime()));
			

			LocalityDAO localityDAO = new LocalityDAOImpl(session);
			localityDAO.insert(locality);
			transaction.commit();
			return locality;
		}

		catch (Exception exception) {

			if (transaction != null) {
				transaction.rollback();
				throw TransactionFailureException;
			} else {
				throw RollbackFailureException;
			}

		}

		finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.LocalityService#update(com.foodybuddy.model.Locality)
	 */
	public Locality update(Locality locality) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {
			if (locality == null || locality.getName().trim().length() == 0 
					|| locality.getCity() == null || locality.getCity().getState() == null) {
				
				if (locality == null) {
					throw new RuntimeException("Locality Object is null");
				}

				else if (locality.getName().trim().length() == 0) {
					throw new RuntimeException("Locality Name is Invalid");
				}

				else if (locality.getCity() == null) {
					throw new RuntimeException("City Object is null");
				}
				
				else if (locality.getCity().getState() == null) {
					throw new RuntimeException("State Object is null");
				}
				
				
			}

			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			LocalityDAO localityDao = new LocalityDAOImpl(session);
			java.util.Date date = new java.util.Date();
			locality.setModifiedAt(new Timestamp(date.getTime()));
			localityDao.update(locality);
			transaction.commit();
			return locality;
		}

		catch (Exception exception) {

			if (transaction != null) {
				transaction.rollback();
				throw TransactionFailureException;
			} else {
				throw RollbackFailureException;
			}

		}

		finally {
			if (session != null) {
				session.close();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.LocalityService#delete(com.foodybuddy.model.Locality)
	 */
	public void delete(Locality locality) throws TransactionException {
		

		Session session = null;
		Transaction transaction = null;

		try {
			if (locality == null) {
				throw new RuntimeException("Country Object is null");
			}
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			LocalityDAO localityDao = new LocalityDAOImpl(session);
			locality.setIsActive(false);
			java.util.Date date = new java.util.Date();
			locality.setDeletedAt(new Timestamp(date.getTime()));
			localityDao.update(locality);
			transaction.commit();
		}

		catch (Exception exception) {

			if (transaction != null) {
				transaction.rollback();
				throw TransactionFailureException;
			} else {
				throw RollbackFailureException;
			}

		}

		finally {
			if (session != null) {
				session.close();
			}
		}
	}

}