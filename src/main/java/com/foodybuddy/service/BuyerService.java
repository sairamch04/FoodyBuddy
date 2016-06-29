package com.foodybuddy.service;

import java.util.List;

import org.hibernate.TransactionException;

import com.foodybuddy.model.Buyer;

/***
 * Interface for Apartment Service
 */
public interface BuyerService {
    public Buyer insert(Buyer buyer) throws TransactionException;
    public Buyer update(Buyer buyer) throws TransactionException;
    //public Buyer delete(Buyer buyer) throws TransactionException;
    public List<Buyer> getAll();
    public Buyer getById(Integer id);
}