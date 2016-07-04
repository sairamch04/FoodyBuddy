package com.foodybuddy.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.foodybuddy.dao.ApartmentDAO;
import com.foodybuddy.dao.SellerDAO;
import com.foodybuddy.dao.impl.ApartmentDAOImpl;
import com.foodybuddy.dao.impl.SellerDAOImpl;
import com.foodybuddy.model.Apartment;
import com.foodybuddy.model.Seller;
import com.foodybuddy.service.SellerService;


/**
 * The Class SellerServiceImpl.
 */
@Service("SellerService")
public class SellerServiceImpl implements SellerService {

	/** The session factory. */
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a new seller service impl.
	 *
	 * @param sessionFactory
	 *            the session factory
	 */
	public SellerServiceImpl(SessionFactory sessionFactory) throws NullPointerException {
		if (sessionFactory == null) {
			throw new NullPointerException("Session is null");
		}
		this.sessionFactory = sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.SellerService#AddSeller(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, int, boolean)
	 */
	public Seller addSeller(String name, String email, String mobile, String flatNumber, int apartmentId,
			boolean isActive) throws Exception {
		Session session = null;
		Transaction transaction = null;
		Seller newSeller = null;
		try {
			if (name == null)
				throw new Exception("Invalid name");
			if (email == null)
				throw new Exception("Invalid email");
			if (mobile == null)
				throw new Exception("Invalid mobile");
			if (flatNumber == null)
				throw new Exception("Invalid flatNumber");
			if (apartmentId <1)
				throw new Exception("Invalid apartmentId");
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);
			SellerDAO sellerDAO = new SellerDAOImpl(session);
			// Getting apartment Sellerect
			ApartmentDAO apartmentDAO = new ApartmentDAOImpl(session);
			Apartment apartment = apartmentDAO.getById(apartmentId);
			// Inserting seller to database
			Seller seller = new Seller(name, email, mobile, flatNumber, apartment, isActive);
			newSeller = sellerDAO.insert(seller);
			transaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				if (transaction == null) {
					throw new Exception("Transaction could not be completed: " + ex.getMessage(), ex);
				}
				transaction.rollback();
				throw new Exception("Transaction could not be completed will be rollbacked: " + ex.getMessage(), ex);
			} catch (RuntimeException rbe) {
				throw new Exception("Transaction could not be completed and rollback failed: " + ex.getMessage(), ex);
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return newSeller;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.foodybuddy.service.SellerService#UpdateSeller(com.foodybuddy.model.
	 * Seller)
	 */
	public Seller updateSeller(Integer id, String newName) throws Exception {
		Session session = null;
		Transaction transaction = null;
		Seller updatedSeller;
		try {
			if (id < 1 || newName == null)
				throw new Exception("Invalid Input");
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);
			SellerDAO sellerDAO = new SellerDAOImpl(session);
			Seller seller = sellerDAO.getBySellerId(id);
			seller.setName(newName);
			updatedSeller = sellerDAO.update(seller);
			transaction.commit();
		} catch (Exception ex) {
			try {
				if (transaction == null) {
					throw new Exception("Transaction could not be completed: " + ex.getMessage(), ex);
				}
				transaction.rollback();
				throw new Exception("Transaction could not be completed will be rollbacked: " + ex.getMessage(), ex);
			} catch (RuntimeException rbe) {
				throw new Exception("Transaction could not be completed and rollback failed: " + ex.getMessage(), ex);
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return updatedSeller;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.SellerService#getById(java.lang.Integer)
	 */
	public Seller getById(Integer id) throws Exception {
		Session session = null;
		Seller getSeller = null;
		try {
			if (id < 1)
				throw new Exception("Invalid Input");
			session = this.sessionFactory.openSession();
			SellerDAO sellerDAO = new SellerDAOImpl(session);
			getSeller = sellerDAO.getBySellerId(id);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage(), ex);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return getSeller;

	}

	/* (non-Javadoc)
	 * @see com.foodybuddy.service.SellerService#getList()
	 */
	public List<Seller> getList() throws Exception {
		
		Session session = null;
		List<Seller> SellerList = null;
		try {
			session = this.sessionFactory.openSession();
			SellerDAO sellerDAO = new SellerDAOImpl(session);
			SellerList = sellerDAO.getSellerList();
		} catch (Exception ex) {
			throw new Exception(ex.getMessage(), ex);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return SellerList;

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.foodybuddy.service.SellerService#deactivateUser(java.lang.Integer)
	 */
	public Seller deactivateSeller(Integer id) throws Exception {
		Session session = null;
		Transaction transaction = null;
		Seller deactivaedSeller = null;
		try {
			if (id < 1)
				throw new Exception("Invalid Input");
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);
			SellerDAO sellerDAO = new SellerDAOImpl(session);
			deactivaedSeller = sellerDAO.getBySellerId(id);
			deactivaedSeller.setIsActive(false);
			sellerDAO.update(deactivaedSeller);
			transaction.commit();
		} catch (Exception ex) {
			try {
				if (transaction == null) {
					throw new Exception("Transaction could not be completed: " + ex.getMessage(), ex);
				}
				transaction.rollback();
				throw new Exception("Transaction could not be completed will be rollbacked: " + ex.getMessage(), ex);
			} catch (RuntimeException rbe) {
				throw new Exception("Transaction could not be completed and rollback failed: " + ex.getMessage(), ex);
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return deactivaedSeller;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foodybuddy.service.SellerService#activateUser(java.lang.Integer)
	 */
	public Seller activateSeller(Integer id) throws Exception {
		Session session = null;
		Transaction transaction = null;
		Seller activaedSeller = null;
		Seller getSeller = null;
		try {
			if (id < 1)
				throw new Exception("Invalid Input");
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			transaction.setTimeout(10);
			SellerDAO sellerDAO = new SellerDAOImpl(session);
			getSeller = sellerDAO.getBySellerId(id);
			getSeller.setIsActive(true);
			activaedSeller = sellerDAO.update(getSeller);
			transaction.commit();
		} catch (Exception ex) {
			try {
				if (transaction == null) {
					throw new Exception("Transaction could not be completed: " + ex.getMessage(), ex);
				}
				transaction.rollback();
				throw new Exception("Transaction could not be completed will be rollbacked: " + ex.getMessage(), ex);
			} catch (RuntimeException rbe) {
				throw new Exception("Transaction could not be completed and rollback failed: " + ex.getMessage(), ex);
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return activaedSeller;
	}

}
