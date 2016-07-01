package com.foodybuddy.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

import com.foodybuddy.dao.LocalityDAO;
import com.foodybuddy.dao.impl.LocalityDAOImpl;
import com.foodybuddy.model.Locality;
import com.foodybuddy.service.LocalityService;


@Service("localityService")
public class LocalityServiceImpl implements LocalityService{

    private SessionFactory sessionFactory;
    
    public LocalityServiceImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
        System.out.println(this.sessionFactory);
    }
    
    TransactionException TransactionFailureException = new TransactionException("Transaction could not be completed and rollback inititated!");
    TransactionException RollbackFailureException = new TransactionException("Transaction and Rollback Failed!");
    RuntimeException ObjectNullException = new RuntimeException("Object is null");
    
    public Locality getById(Integer id) {
        Session session = null;
        session = this.sessionFactory.openSession();
        
        LocalityDAO localityDao = new LocalityDAOImpl(session);
        return localityDao.getById(id);
    }

    public List<Locality> getAll(){
        Session session = null;
        session = this.sessionFactory.openSession();

        LocalityDAO localityDao = new LocalityDAOImpl(session);
        return localityDao.getAll();
    }

    public Locality insert(Locality locality) throws TransactionException {
        
        Session session = null;
        Transaction transaction = null;

        try {
                if(locality == null) {
                    throw ObjectNullException;
                }

                session = this.sessionFactory.openSession();
                transaction = session.beginTransaction();
                transaction.setTimeout(10);

                LocalityDAO localityDao = new LocalityDAOImpl(session);
                localityDao.insert(locality);
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
        return locality;
    }

    public Locality update(Locality locality) throws TransactionException {

        Session session = null;
        Transaction transaction = null;

        try {
                if(locality == null) {
                    throw ObjectNullException;
                }

                session = this.sessionFactory.openSession();
                transaction = session.beginTransaction();
                transaction.setTimeout(10);

                LocalityDAO localityDAO = new LocalityDAOImpl(session);
                localityDAO.update(locality);
                transaction.commit();
        }
        
        catch (Exception exception) {
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
        return locality;
    }
    /*
    public Locality delete(Locality locality) throws TransactionException {
        Session session = null;
        Transaction transaction = null;
        try {
                session = this.sessionFactory.openSession();
                transaction = session.beginTransaction();
                transaction.setTimeout(10);
                LocalityDAO localityDao = new LocalityDAOImpl(session);
                localityDao.delete(locality);
                transaction.commit();
        }
        
        catch (Exception exception) {
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
        return locality;
    }
*/

}