package com.foodybuddy.controller;

import com.foodybuddy.model.Apartment;
import com.foodybuddy.service.ApartmentService;
import com.foodybuddy.service.impl.ApartmentServiceImpl;
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
 * The Class ApartmentController.
 */
@RestController
public class ApartmentController {
	
	/** The session factory. */
	private SessionFactory sessionFactory = SessionFactoryUtils.getSessionFactory();
	
	/** The apartment service. */
	private ApartmentService apartmentService = new ApartmentServiceImpl(sessionFactory);
	
	/**
	 * Gets the Apartment by id.
	 *
	 * @param Integer id
	 * @return Apartment apartment
	 * @throws Exception 
	 */
	@RequestMapping(value = "/apartment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Apartment> getById(@PathVariable("id") Integer id) throws Exception{
		Apartment apartment = apartmentService.getById(id);
		
		if(apartment == null){
			return new ResponseEntity<Apartment>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Apartment>(apartment, HttpStatus.OK);
	}
	
	/**
	 * Gets all the countries.
	 *
	 * @return all the countries 
	 * @throws Exception
	 */
	@RequestMapping(value = "/apartment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Apartment>> getAll() throws Exception{
		List<Apartment> apartmentList = apartmentService.getAll();
		
		if(apartmentList.isEmpty()){
			return new ResponseEntity<List<Apartment>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Apartment>>(apartmentList, HttpStatus.OK);
	}
	
	/**
	 * Insert.
	 *
	 * @param String name 
	 * @return Apartment apartment
	 */
	@RequestMapping(value = "/apartment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Apartment> insert(@RequestParam("name") String name, @RequestParam("localityId") Integer localityId, @RequestParam("blockNumber") Integer blockNumber){
		Apartment apartment = apartmentService.insert(name, localityId, blockNumber);
		return new ResponseEntity<Apartment>(apartment, HttpStatus.OK);
	}
	
	/**
	 * Update.
	 *
	 * @param Integer id 
	 * @param Apartment apartment
	 * @return Apartment apartment
	 * @throws Exception
	 */
	@RequestMapping(value = "/apartment/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Apartment> update(@PathVariable("id") Integer id, @RequestBody Apartment apartment) throws Exception{
		Apartment currentApartment = apartmentService.getById(id);
		if(currentApartment == null){
			return new ResponseEntity<Apartment>(HttpStatus.NOT_FOUND);
		}
		currentApartment = apartmentService.update(apartment);
		return new ResponseEntity<Apartment>(currentApartment, HttpStatus.OK);
	}
	
	/**
	 * Delete.
	 *
	 * @param Integer id
	 * @throws Exception
	 */
	@RequestMapping(value = "/apartment/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
		Apartment currentApartment = apartmentService.getById(id);
		if(currentApartment == null){
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		apartmentService.delete(currentApartment);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}