package com.foodybuddy.service;

import com.foodybuddy.model.Seller;

// TODO: Auto-generated Javadoc
/**
 * The Interface SellerService.
 */
public interface SellerService {
	
	/**
	 * Adds the seller.
	 *
	 * @param name the name
	 * @param email the email
	 * @param mobile the mobile
	 * @param flat_number the flat number
	 * @param apartmentId the apartment id
	 * @param is_active the is active
	 * @return the seller
	 * @throws Exception the exception
	 */
	public Seller addSeller(String name, String email,String mobile, String flatNumber , int apartmentId, boolean isActive) throws Exception;
	
	/**
	 * Update seller.
	 *
	 * @param seller the seller
	 * @return the seller
	 * @throws Exception the exception
	 */
	public Seller updateSeller(Integer id, String newName) throws Exception;
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 * @throws Exception the exception
	 */
	public Seller getById(Integer id) throws Exception;
	
	/**
	 * Deactivate user.
	 *
	 * @param id the id
	 * @return the seller
	 * @throws Exception the exception
	 */
	public Seller deactivateSeller(Integer id) throws Exception;
	
	/**
	 * Activate user.
	 *
	 * @param id the id
	 * @return the seller
	 * @throws Exception the exception
	 */
	public Seller activateSeller(Integer id) throws Exception;

	/**
	 * Gets the list.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<Seller> getList() throws Exception ;
}

