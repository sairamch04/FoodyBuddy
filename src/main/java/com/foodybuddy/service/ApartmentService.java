package com.foodybuddy.service;

import com.foodybuddy.model.Apartment;
import java.util.List;
import org.hibernate.TransactionException;
/***
 * Interface for Apartment Service
 */
public interface ApartmentService {
    public Apartment insert(Apartment apartment) throws TransactionException;
    public Apartment update(Apartment apartment) throws TransactionException;
    //public Apartment delete(Apartment apartment) throws TransactionException;
    public List<Apartment> getAll();
    public Apartment getById(Integer id);
}