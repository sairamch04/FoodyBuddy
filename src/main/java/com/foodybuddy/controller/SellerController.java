package com.foodybuddy.controller;

import com.foodybuddy.model.Seller;
import com.foodybuddy.service.SellerService;
import com.foodybuddy.service.impl.SellerServiceImpl;
import com.foodybuddy.utils.SessionFactoryUtils;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class SellerController.
 */
@RestController
public class SellerController {
	
	/** The session factory. */
	private SessionFactory sessionFactory = SessionFactoryUtils.getSessionFactory();
	
	/** The seller service. */
	private SellerService sellerService = new SellerServiceImpl(sessionFactory);
	
	/**
	 * Gets the Seller by id.
	 *
	 * @param Integer id
	 * @return Seller seller
	 * @throws Exception 
	 */
	@RequestMapping(value = "/seller/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Seller> getById(@PathVariable("id") Integer id) throws Exception{
		Seller seller = sellerService.getById(id);
		
		if(seller == null){
			return new ResponseEntity<Seller>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Seller>(seller, HttpStatus.OK);
	}
	
	
	
	/**
	 * Insert.
	 *
	 * @param String name 
	 * @return Seller seller
	 * @throws Exception 
	 */
	@RequestMapping(value = "/seller", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Seller> insert(@RequestParam("name") String name, @RequestParam("apartmentId") Integer apartmentId, @RequestParam("mobileNumber") String mobileNumber, @RequestParam("email") String email, @RequestParam("flatNumber") String flatNumber ) throws Exception{
		Seller seller = sellerService.addSeller(name, email, mobileNumber, flatNumber, apartmentId, true);
		return new ResponseEntity<Seller>(seller, HttpStatus.OK);
	}
	
	/**
	 * Update.
	 *
	 * @param Integer id 
	 * @param Seller seller
	 * @return Seller seller
	 * @throws Exception
	 */
	@RequestMapping(value = "/seller/{id}", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Seller> update(@PathVariable("id") Integer id, @RequestParam("newName") String newName) throws Exception{
		Seller currentSeller = sellerService.getById(id);
		if(currentSeller == null){
			return new ResponseEntity<Seller>(HttpStatus.NOT_FOUND);
		}
		
		
		currentSeller = sellerService.updateSeller(id, newName);
		return new ResponseEntity<Seller>(currentSeller, HttpStatus.OK);
	}
	
	/**
	 * Delete.
	 *
	 * @param Integer id
	 * @throws Exception
	 */
	@RequestMapping(value = "/seller/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
		Seller currentSeller = sellerService.getById(id);
		if(currentSeller == null){
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		sellerService.deactivateSeller(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}