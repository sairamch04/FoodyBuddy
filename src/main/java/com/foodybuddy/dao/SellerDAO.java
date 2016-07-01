package com.foodybuddy.dao;
import java.util.List;
import com.foodybuddy.model.Seller;

// TODO: Auto-generated Javadoc
/**
 * The Interface SellerDAO.
 */
public interface SellerDAO {

	/**
	 * Insert.
	 *
	 * @param s the s
	 * @return the seller
	 */
	public Seller insert(Seller s);
	
	/**
	 * Update.
	 *
	 * @param s the s
	 * @return the seller
	 */
	public Seller update(Seller s);
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(Integer id);
	
	/**
	 * Gets the list seller.
	 *
	 * @return the list seller
	 */
	public List<Seller> getSellerList();
	
	/**
	 * Gets the list by seller id.
	 *
	 * @param sellerId the seller id
	 * @return the list by seller id
	 */
	public Seller getBySellerId(Integer sellerId);
	
	/**
	 * Gets the list by apartment id.
	 *
	 * @param apartmentId the apartment id
	 * @return the list by apartment id
	 */
	public List<Seller> getListByApartmentId(Integer apartmentId);
}
