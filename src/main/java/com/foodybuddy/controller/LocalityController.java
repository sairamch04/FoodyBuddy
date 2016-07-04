package com.foodybuddy.controller;

import com.foodybuddy.model.Locality;
import com.foodybuddy.service.LocalityService;
import com.foodybuddy.service.impl.LocalityServiceImpl;
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
 * The Class LocalityController.
 */
@RestController
public class LocalityController {
	
	/** The session factory. */
	private SessionFactory sessionFactory = SessionFactoryUtils.getSessionFactory();
	
	/** The locality service. */
	private LocalityService localityService = new LocalityServiceImpl(sessionFactory);
	
	/**
	 * Gets the Locality by id.
	 *
	 * @param Integer id
	 * @return Locality locality
	 * @throws Exception 
	 */
	@RequestMapping(value = "/locality/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Locality> getById(@PathVariable("id") Integer id) throws Exception{
		Locality locality = localityService.getById(id);
		
		if(locality == null){
			return new ResponseEntity<Locality>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Locality>(locality, HttpStatus.OK);
	}
	
	/**
	 * Gets all the countries.
	 *
	 * @return all the countries 
	 * @throws Exception
	 */
	@RequestMapping(value = "/locality", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Locality>> getAll() throws Exception{
		List<Locality> localityList = localityService.getAll();
		
		if(localityList.isEmpty()){
			return new ResponseEntity<List<Locality>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Locality>>(localityList, HttpStatus.OK);
	}
	
	/**
	 * Insert.
	 *
	 * @param String name 
	 * @return Locality locality
	 */
	@RequestMapping(value = "/locality", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Locality> insert(@RequestParam("name") String name, @RequestParam("cityId") Integer cityId, @RequestParam("pincode") String pincode){
		Locality locality = localityService.insert(name, cityId, pincode);
		return new ResponseEntity<Locality>(locality, HttpStatus.OK);
	}
	
	/**
	 * Update.
	 *
	 * @param Integer id 
	 * @param Locality locality
	 * @return Locality locality
	 * @throws Exception
	 */
	@RequestMapping(value = "/locality/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Locality> update(@PathVariable("id") Integer id, @RequestBody Locality locality) throws Exception{
		Locality currentLocality = localityService.getById(id);
		if(currentLocality == null){
			return new ResponseEntity<Locality>(HttpStatus.NOT_FOUND);
		}
		currentLocality = localityService.update(locality);
		return new ResponseEntity<Locality>(currentLocality, HttpStatus.OK);
	}
	
	/**
	 * Delete.
	 *
	 * @param Integer id
	 * @throws Exception
	 */
	@RequestMapping(value = "/locality/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
		Locality currentLocality = localityService.getById(id);
		if(currentLocality == null){
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		localityService.delete(currentLocality);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}