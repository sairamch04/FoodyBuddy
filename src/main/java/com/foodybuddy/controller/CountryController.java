package com.foodybuddy.controller;

import com.foodybuddy.model.Country;
import com.foodybuddy.service.CountryService;
import com.foodybuddy.service.impl.CountryServiceImpl;
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
 * The Class CountryController.
 */
@RestController
public class CountryController {
	
	/** The session factory. */
	private SessionFactory sessionFactory = SessionFactoryUtils.getSessionFactory();
	
	/** The country service. */
	private CountryService countryService = new CountryServiceImpl(sessionFactory);
	
	/**
	 * Gets the Country by id.
	 *
	 * @param Integer id
	 * @return Country country
	 * @throws Exception 
	 */
	@RequestMapping(value = "/country/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Country> getById(@PathVariable("id") Integer id) throws Exception{
		Country country = countryService.getById(id);
		
		if(country == null){
			return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Country>(country, HttpStatus.OK);
	}
	
	/**
	 * Gets all the countries.
	 *
	 * @return all the countries 
	 * @throws Exception
	 */
	@RequestMapping(value = "/country", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Country>> getAll() throws Exception{
		List<Country> countryList = countryService.getAll();
		
		if(countryList.isEmpty()){
			return new ResponseEntity<List<Country>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Country>>(countryList, HttpStatus.OK);
	}
	
	/**
	 * Insert.
	 *
	 * @param String name 
	 * @return Country country
	 */
	@RequestMapping(value = "/country", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Country> insert(@RequestParam("name") String name){
		Country country = countryService.insert(name);
		return new ResponseEntity<Country>(country, HttpStatus.OK);
	}
	
	/**
	 * Update.
	 *
	 * @param Integer id 
	 * @param Country country
	 * @return Country country
	 * @throws Exception
	 */
	@RequestMapping(value = "/country/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Country> update(@PathVariable("id") Integer id, @RequestBody Country country) throws Exception{
		Country currentCountry = countryService.getById(id);
		if(currentCountry == null){
			return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
		}
		currentCountry = countryService.update(country);
		return new ResponseEntity<Country>(currentCountry, HttpStatus.OK);
	}
	
	/**
	 * Delete.
	 *
	 * @param Integer id
	 * @throws Exception
	 */
	@RequestMapping(value = "/country/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
		Country currentCountry = countryService.getById(id);
		if(currentCountry == null){
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		countryService.delete(currentCountry);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}