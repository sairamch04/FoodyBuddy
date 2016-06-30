package com.foodybuddy.service;

import com.foodybuddy.model.Locality;
import java.util.List;
import org.hibernate.TransactionException;

public interface LocalityService {
    public Locality insert(Locality locality) throws TransactionException;
    public Locality update(Locality locality) throws TransactionException;
    //public Locality delete(Locality locality) throws TransactionException;
    public List<Locality> getAll();
    public Locality getById(Integer id);
}