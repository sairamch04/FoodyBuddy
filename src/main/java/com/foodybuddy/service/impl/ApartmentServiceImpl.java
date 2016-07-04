package com.foodybuddy.service.impl;

import com.foodybuddy.dao.ApartmentDAO;
import com.foodybuddy.dao.LocalityDAO;
import com.foodybuddy.dao.impl.ApartmentDAOImpl;
import com.foodybuddy.dao.impl.LocalityDAOImpl;
import com.foodybuddy.model.Apartment;
import com.foodybuddy.model.Locality;
import com.foodybuddy.service.ApartmentService;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

/**
 * The Class ApartmentServiceImpl.
 */
@Service("apartmentService")
public class ApartmentServiceImpl implements ApartmentService {

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
	 * Instantiates a new apartment service impl.
	 *
	 * @param sessionFactory the session factory
	 */
	public ApartmentServiceImpl(SessionFactory sessionFactory) {
		if (sessionFactory == null) {
			throw ObjectNullException;
		}
		this.sessionFactory = sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.ApartmentService#getById(java.lang.Integer)
	 */
	public Apartment getById(Integer id) throws Exception {
		
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
			ApartmentDAO apartmentDAO = new ApartmentDAOImpl(session);
			return apartmentDAO.getById(id);
			
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
	 * @see com.foodybuddy.service.ApartmentService#getAll()
	 */
	public List<Apartment> getAll() throws Exception {
		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			ApartmentDAO apartmentDAO = new ApartmentDAOImpl(session);
			List<Apartment> apartmentList = apartmentDAO.getAll();
			return apartmentList;
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
	 * @see com.foodybuddy.service.ApartmentService#insert(java.lang.String,
	 * java.lang.Integer)
	 */
	public Apartment insert(String name, Integer localityId, Integer blockNumber) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {
			
			if (name == null || localityId == null || localityId <= 0 || name.trim().length() == 0 ) {

				if (name == null) {
					throw new RuntimeException("Name is null");
				}

				else if (localityId == null) {
					throw new RuntimeException("LocalityId is null");
				}

				else if (localityId <= 0) {
					throw new RuntimeException("LocalityId is invalid");
				}

				else {
					throw new RuntimeException("Name is invalid");
				}
			}
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			LocalityDAO localityDao = new LocalityDAOImpl(session);
			Locality locality = localityDao.getById(localityId);

			if (locality == null) {
				throw ObjectNullException;
			}

			Apartment apartment = new Apartment();
			apartment.setName(name);
			apartment.setLocality(locality);
			apartment.setBlockNumber(blockNumber);
			apartment.setIsActive(true);
			
			ApartmentDAO apartmentDAO = new ApartmentDAOImpl(session);
			java.util.Date date = new java.util.Date();
			apartment.setCreatedAt(new Timestamp(date.getTime()));
			apartmentDAO.insert(apartment);
			transaction.commit();
			return apartment;
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
	 * @see com.foodybuddy.service.ApartmentService#update(com.foodybuddy.model.Apartment)
	 */
	public Apartment update(Apartment apartment) throws TransactionException {

		Session session = null;
		Transaction transaction = null;

		try {
			if (apartment == null || apartment.getName().trim().length() == 0 
					|| apartment.getLocality() == null || apartment.getLocality().getCity() == null) {
				
				if (apartment == null) {
					throw new RuntimeException("Apartment Object is null");
				}

				else if (apartment.getName().trim().length() == 0) {
					throw new RuntimeException("Apartment Name is Invalid");
				}

				else if (apartment.getLocality() == null) {
					throw new RuntimeException("Locality Object is null");
				}
				
				else if (apartment.getLocality().getCity() == null) {
					throw new RuntimeException("City Object is null");
				}
				
			}

			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			ApartmentDAO apartmentDAO = new ApartmentDAOImpl(session);
			java.util.Date date = new java.util.Date();
			apartment.setModifiedAt(new Timestamp(date.getTime()));
			apartmentDAO.update(apartment);
			transaction.commit();
			return apartment;
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
	 * @see com.foodybuddy.service.ApartmentService#delete(com.foodybuddy.model.Apartment)
	 */
	public void delete(Apartment apartment) throws TransactionException {
		

		Session session = null;
		Transaction transaction = null;

		try {
			if (apartment == null) {
				throw new RuntimeException("Country Object is null");
			}
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);

			ApartmentDAO apartmentDao = new ApartmentDAOImpl(session);
			apartment.setIsActive(false);
			java.util.Date date = new java.util.Date();
			apartment.setDeletedAt(new Timestamp(date.getTime()));
			apartmentDao.update(apartment);
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