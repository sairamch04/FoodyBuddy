package com.foodybuddy.service.impl;

import com.foodybuddy.dao.ApartmentDAO;
import com.foodybuddy.dao.BuyerDAO;
import com.foodybuddy.dao.impl.ApartmentDAOImpl;
import com.foodybuddy.dao.impl.BuyerDAOImpl;
import com.foodybuddy.model.Apartment;
import com.foodybuddy.model.Buyer;
import com.foodybuddy.service.BuyerService;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

/**
 * The Class BuyerServiceImpl.
 */
@Service("buyerService")
public class BuyerServiceImpl implements BuyerService {

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
     * Instantiates a new buyer service impl.
     *
     * @param sessionFactory the session factory
     */
    public BuyerServiceImpl(SessionFactory sessionFactory) {
        if (sessionFactory == null) {
            throw ObjectNullException;
        }
        this.sessionFactory = sessionFactory;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.foodybuddy.service.BuyerService#getById(java.lang.Integer)
     */
    public Buyer getById(Integer id) throws Exception {
        
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
            BuyerDAO buyerDAO = new BuyerDAOImpl(session);
            return buyerDAO.getById(id);
            
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
     * @see com.foodybuddy.service.BuyerService#getAll()
     */
    public List<Buyer> getAll() throws Exception {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            BuyerDAO buyerDAO = new BuyerDAOImpl(session);
            List<Buyer> buyerList = buyerDAO.getAll();
            return buyerList;
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
     * @see com.foodybuddy.service.BuyerService#insert(java.lang.String,
     * java.lang.Integer)
     */
    public Buyer insert(String name, Integer apartmentId, String mobileNumber, String email, String flatNumber) throws TransactionException {

        Session session = null;
        Transaction transaction = null;

        try {
            
            if (name == null || apartmentId == null || apartmentId <= 0 || name.trim().length() == 0 ||  mobileNumber == null || email == null || flatNumber == null) {

                if (name == null) {
                    throw new RuntimeException("Name is null");
                }

                else if (apartmentId == null) {
                    throw new RuntimeException("ApartmentId is null");
                }

                else if (apartmentId <= 0) {
                    throw new RuntimeException("ApartmentId is invalid");
                }

                else if(name.trim().length() == 0) {
                    throw new RuntimeException("Name is invalid");
                }
               
                else if(mobileNumber == null) {
                    throw new RuntimeException("mobileNumber is null");
                }
                
                else if(email == null) {
                    throw new RuntimeException("email is null");
                }
                
                else if(flatNumber == null) {
                    throw new RuntimeException("flatNumber is null");
                }
                
                
            }
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            transaction.setTimeout(10);

            ApartmentDAO apartmentDao = new ApartmentDAOImpl(session);
            Apartment apartment = apartmentDao.getById(apartmentId);

            if (apartment == null) {
                throw ObjectNullException;
            }

            Buyer buyer = new Buyer();
            buyer.setName(name);
            buyer.setApartment(apartment);
            buyer.setMobileNumber(mobileNumber);
            buyer.setEmail(email);
            buyer.setFlatNumber(flatNumber);
            buyer.setIsActive(true);
            
            BuyerDAO buyerDao = new BuyerDAOImpl(session);
            java.util.Date date = new java.util.Date();
			buyer.setCreatedAt(new Timestamp(date.getTime()));
            buyerDao.insert(buyer);
            transaction.commit();
            return buyer;
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
     * @see com.foodybuddy.service.BuyerService#update(com.foodybuddy.model.Buyer)
     */
    public Buyer update(Buyer buyer) throws TransactionException {

        Session session = null;
        Transaction transaction = null;

        try {
            if (buyer == null || buyer.getName().trim().length() == 0 
                    || buyer.getApartment() == null
                        || buyer.getApartment().getLocality() == null) {
                
                if (buyer == null) {
                    throw new RuntimeException("Buyer Object is null");
                }

                else if (buyer.getName().trim().length() == 0) {
                    throw new RuntimeException("Buyer Name is Invalid");
                }

                else if (buyer.getApartment() == null) {
                    throw new RuntimeException("Apartment Object is null");
                }
                
                
                else if (buyer.getApartment().getLocality() == null) {
                    throw new RuntimeException("Locality Object is null");
                }
                
            }

            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            transaction.setTimeout(10);

            BuyerDAO buyerDAO = new BuyerDAOImpl(session);
            java.util.Date date = new java.util.Date();
			buyer.setModifiedAt(new Timestamp(date.getTime()));
            buyerDAO.update(buyer);
            transaction.commit();
            return buyer;
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
     * @see com.foodybuddy.service.BuyerService#delete(com.foodybuddy.model.Buyer)
     */
    public void delete(Buyer buyer) throws TransactionException {
        

        Session session = null;
        Transaction transaction = null;

        try {
            if (buyer == null) {
                throw new RuntimeException("Country Object is null");
            }
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            transaction.setTimeout(10);

            BuyerDAO buyerDao = new BuyerDAOImpl(session);
            buyer.setIsActive(false);
			java.util.Date date = new java.util.Date();
			buyer.setDeletedAt(new Timestamp(date.getTime()));
            buyerDao.update(buyer);
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