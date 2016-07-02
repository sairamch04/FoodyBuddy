package com.foodybuddy.service;

import java.util.Date;

import com.foodybuddy.model.Dish;
import com.foodybuddy.model.Seller;

// TODO: Auto-generated Javadoc
/**
 * The Interface DishService.
 */
public interface DishService {
	
	/**
	 * Adds the Dish.
	 *
	 * @param name the name
	 * @param email the email
	 * @param mobile the mobile
	 * @param flat_number the flat number
	 * @param apartmentId the apartment id
	 * @param is_active the is active
	 * @return the Dish
	 * @throws Exception the exception
	 */
	public Dish addDish(String name, String description, Integer sellerId, Integer price, Date orderBy, Date dishAvailableStart,
			Date dishAvailableEnd, Boolean isVeg, Boolean isActive, Integer quantityAvailable)  throws Exception;
	
	/**
	 * Update Dish.
	 *
	 * @param Dish the Dish
	 * @return the Dish
	 * @throws Exception the exception
	 */
	public Dish updateDish(Integer id, String newName) throws Exception;
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 * @throws Exception the exception
	 */
	public Dish getById(Integer id) throws Exception;
	
	/**
	 * Deactivate user.
	 *
	 * @param id the id
	 * @return the Dish
	 * @throws Exception the exception
	 */
	public Dish deactivateDish(Integer id) throws Exception;
	
	/**
	 * Activate user.
	 *
	 * @param id the id
	 * @return the Dish
	 * @throws Exception the exception
	 */
	public Dish activateDish(Integer id) throws Exception;
}
