package com.foodybuddy.service;

import com.foodybuddy.model.Buyer;

import java.util.List;

import org.hibernate.TransactionException;

/**
 * The Interface BuyerService.
 */
public interface BuyerService {

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 * @throws Exception the exception
	 */
	public Buyer getById(Integer id) throws Exception;
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 * @throws Exception the exception
	 */
	public List<Buyer> getAll() throws Exception;
	
	/**
	 * Insert.
	 *
	 * @param name the name
	 * @param apartmentId the apartment id
	 * @param lastModifiedById the last modified by id
	 * @param mobileNumber the mobile number
	 * @param email the email
	 * @param flatNumber the flat number
	 * @param isActive the is active
	 * @return the buyer
	 * @throws TransactionException the transaction exception
	 */
	public Buyer insert(String name, Integer apartmentId, Integer lastModifiedById, String mobileNumber, String email, String flatNumber, Boolean isActive) throws TransactionException;
	
	/**
	 * Update.
	 *
	 * @param buyer the buyer
	 * @return the buyer
	 * @throws TransactionException the transaction exception
	 */
	public Buyer update(Buyer buyer) throws TransactionException;
	
	/**
	 * Delete.
	 *
	 * @param buyer the buyer
	 * @throws TransactionException the transaction exception
	 */
	public void delete(Buyer buyer) throws TransactionException;
}