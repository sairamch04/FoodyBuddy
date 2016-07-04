package com.foodybuddy.controller;

import com.foodybuddy.model.Buyer;
import com.foodybuddy.service.BuyerService;
import com.foodybuddy.service.impl.BuyerServiceImpl;
import com.foodybuddy.utils.SessionFactoryUtils;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class BuyerController.
 */
@RestController
public class BuyerController {
	
	/** The session factory. */
	private SessionFactory sessionFactory = SessionFactoryUtils.getSessionFactory();
	
	/** The buyer service. */
	private BuyerService buyerService = new BuyerServiceImpl(sessionFactory);
	
	/**
	 * Gets the Buyer by id.
	 *
	 * @param Integer id
	 * @return Buyer buyer
	 * @throws Exception 
	 */
	@RequestMapping(value = "/buyer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Buyer> getById(@PathVariable("id") Integer id) throws Exception{
		Buyer buyer = buyerService.getById(id);
		
		if(buyer == null){
			return new ResponseEntity<Buyer>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Buyer>(buyer, HttpStatus.OK);
	}
	
	/**
	 * Gets all the countries.
	 *
	 * @return all the countries 
	 * @throws Exception
	 */
	@RequestMapping(value = "/buyer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Buyer>> getAll() throws Exception{
		List<Buyer> buyerList = buyerService.getAll();
		
		if(buyerList.isEmpty()){
			return new ResponseEntity<List<Buyer>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Buyer>>(buyerList, HttpStatus.OK);
	}
	
	/**
	 * Insert.
	 *
	 * @param String name 
	 * @return Buyer buyer
	 */
	@RequestMapping(value = "/buyer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Buyer> insert(@RequestParam("name") String name, @RequestParam("apartmentId") Integer apartmentId, @RequestParam("mobileNumber") String mobileNumber, @RequestParam("email") String email, @RequestParam("flatNumber") String flatNumber ){
		Buyer buyer = buyerService.insert(name, apartmentId, new Integer(0), mobileNumber, email, flatNumber, true);
		return new ResponseEntity<Buyer>(buyer, HttpStatus.OK);
	}
	
	/**
	 * Update.
	 *
	 * @param Integer id 
	 * @param Buyer buyer
	 * @return Buyer buyer
	 * @throws Exception
	 */
	@RequestMapping(value = "/buyer/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Buyer> update(@PathVariable("id") Integer id, @RequestBody Buyer buyer) throws Exception{
		Buyer currentBuyer = buyerService.getById(id);
		if(currentBuyer == null){
			return new ResponseEntity<Buyer>(HttpStatus.NOT_FOUND);
		}
		currentBuyer.setName(buyer.getName());
		currentBuyer.setEmail(buyer.getEmail());
		currentBuyer.setMobileNumber(buyer.getMobileNumber());
		buyer = buyerService.update(currentBuyer);
		return new ResponseEntity<Buyer>(currentBuyer, HttpStatus.OK);
	}
	
	/**
	 * Delete.
	 *
	 * @param Integer id
	 * @throws Exception
	 */
	@RequestMapping(value = "/buyer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
		Buyer currentBuyer = buyerService.getById(id);
		if(currentBuyer == null){
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		buyerService.delete(currentBuyer);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}