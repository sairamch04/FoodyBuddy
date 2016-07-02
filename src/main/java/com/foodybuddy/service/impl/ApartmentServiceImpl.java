package com.foodybuddy.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

import com.foodybuddy.dao.ApartmentDAO;
import com.foodybuddy.dao.impl.ApartmentDAOImpl;
import com.foodybuddy.model.Apartment;
import com.foodybuddy.service.ApartmentService;

/***
 * service implementation for Apartment table
 * @author sainathbatthala
 *
 */
@Service("apartmentService")
public class ApartmentServiceImpl implements ApartmentService{

    private SessionFactory sessionFactory = null;
    
    public ApartmentServiceImpl(SessionFactory sessionFactory){
        if(sessionFactory == null){
            throw new NullPointerException("session factory is null");
        }
        this.sessionFactory = sessionFactory;
    }
    
    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
    
    TransactionException TransactionFailureException = new TransactionException("Transaction could not be completed and rollback inititated!");
    TransactionException RollbackFailureException = new TransactionException("Transaction and Rollback Failed!");
    RuntimeException ObjectNullException = new RuntimeException("Object is null");
    
    public Apartment getById(Integer id) {
        Session session = null;
        session = this.sessionFactory.openSession();
 
        ApartmentDAO apartmentdao = new ApartmentDAOImpl(session);
        return apartmentdao.getById(id);
    }

    public List<Apartment> getAll(){
        Session session = null;
        session = this.sessionFactory.openSession();

        ApartmentDAO apartmentDao = new ApartmentDAOImpl(session);
        return apartmentDao.getAll();
    }

    public Apartment insert(Apartment apartment) throws TransactionException {
        Session session = null;
        Transaction transaction = null;

        try {
                session = this.sessionFactory.openSession();
                transaction = session.beginTransaction();
                transaction.setTimeout(10);

                ApartmentDAO apartmentdao = new ApartmentDAOImpl(session);
                apartmentdao.insert(apartment);
                transaction.commit();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            try {
                transaction.rollback();
                throw TransactionFailureException;
            }   
            catch (RuntimeException runtimeException) {
                throw RollbackFailureException;
            }
        }

        finally{
            if(session != null){
                session.close();
            }
        }
        return apartment;
    }

    public Apartment update(Apartment apartment) throws TransactionException {

        Session session = null;
        Transaction transaction = null;

        try {
                if(apartment == null) {
                    throw ObjectNullException;
                }

                session = this.sessionFactory.openSession();
                transaction = session.beginTransaction();
                transaction.setTimeout(10);

                ApartmentDAO apartmentDao = new ApartmentDAOImpl(session);
                apartmentDao.update(apartment);
                transaction.commit();
        }
        
        catch (Exception exception) {
            exception.printStackTrace();
            try {
                transaction.rollback();
                throw TransactionFailureException;
            }
            
            catch (RuntimeException runtimeException) {
                throw RollbackFailureException;
            }
        }

        finally{
            if(session != null){
                session.close();
            }
        }
        return apartment;
    }
    /*
    public Apartment delete(Apartment apartment) throws TransactionException {
        Session session = null;
        Transaction transaction = null;

        try {
                session = this.sessionFactory.openSession();
                transaction = session.beginTransaction();
                transaction.setTimeout(10);

                ApartmentDAO apartmentdao = new ApartmentDAOImpl(session);
                apartmentdao.delete(apartment);
                transaction.commit();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            try {
                transaction.rollback();
                throw TransactionFailureException;
            }
            
            catch (RuntimeException runtimeException) {
                throw RollbackFailureException;
            }
        }
        finally{
            if(session != null){
                session.close();
            }
        }
        return apartment;
    }
    */
}