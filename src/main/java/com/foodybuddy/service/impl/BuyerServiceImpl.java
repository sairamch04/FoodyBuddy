package com.foodybuddy.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

import com.foodybuddy.dao.BuyerDAO;
import com.foodybuddy.dao.impl.BuyerDAOImpl;
import com.foodybuddy.model.Buyer;
import com.foodybuddy.service.BuyerService;


@Service("buyerService")
public class BuyerServiceImpl implements BuyerService{

    private SessionFactory sessionFactory;
   
    public BuyerServiceImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    
    TransactionException TransactionFailureException = new TransactionException("Transaction could not be completed and rollback inititated!");
    TransactionException RollbackFailureException = new TransactionException("Transaction and Rollback Failed!");
    RuntimeException ObjectNullException = new RuntimeException("Object is null");
    
    public Buyer getById(Integer id) {
        Session session = null;

        session = this.sessionFactory.openSession();
        BuyerDAO buyerdao = new BuyerDAOImpl(session);
        return buyerdao.getById(id);
    }

    public List<Buyer> getAll(){
        Session session = null;

        session = this.sessionFactory.openSession();

        BuyerDAO buyerdao = new BuyerDAOImpl(session);
        return buyerdao.getAll();
    }

    public Buyer insert(Buyer buyer) throws TransactionException {
        Session session = null;
        Transaction transaction = null;

        try {
                if(buyer == null) {
                    throw ObjectNullException;
                }

                session = this.sessionFactory.openSession();
                transaction = session.beginTransaction();
                transaction.setTimeout(10);

                BuyerDAO buyerdao = new BuyerDAOImpl(session);
                buyerdao.insert(buyer);
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
        return buyer;
    }
    public Buyer update(Buyer buyer) throws TransactionException {

        Session session = null;
        Transaction transaction = null;

        try {
                if(buyer == null) {
                    throw ObjectNullException;
                }

                session = this.sessionFactory.openSession();
                transaction = session.beginTransaction();
                transaction.setTimeout(10);

                BuyerDAO buyerdao = new BuyerDAOImpl(session);
                buyerdao.update(buyer);
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
        return buyer;
    }
    /*
    public Buyer delete(Buyer buyer) throws TransactionException {
        Session session = null;
        Transaction transaction = null;

        try {
                if(buyer == null) {
                    throw ObjectNullException;
                }

                session = this.sessionFactory.openSession();
                transaction = session.beginTransaction();
                transaction.setTimeout(10);

                BuyerDAO buyerdao = new BuyerDAOImpl(session);
                buyerdao.delete(buyer);
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
        return buyer;
    }
    */
}