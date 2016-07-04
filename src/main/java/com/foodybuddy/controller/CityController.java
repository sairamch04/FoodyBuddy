package com.foodybuddy.controller;

import com.foodybuddy.model.City;
import com.foodybuddy.service.CityService;
import com.foodybuddy.service.impl.CityServiceImpl;
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
 * The Class CityController.
 */
@RestController
public class CityController {
	
	/** The session factory. */
	private SessionFactory sessionFactory = SessionFactoryUtils.getSessionFactory();
	
	/** The city service. */
	private CityService cityService = new CityServiceImpl(sessionFactory);
	
	
	/**
	 * Gets the City by id.
	 *
	 * @param Integer id 
	 * @return the City by id
	 * @throws Exception
	 */
	@RequestMapping(value = "/city/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<City> getById(@PathVariable("id") Integer id) throws Exception{
		City city = cityService.getById(id);
		
		if(city == null){
			return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<City>(city, HttpStatus.OK);
	}
	
	/**
	 * Gets all the city.
	 *
	 * @return all the cities
	 * @throws Exception
	 */
	@RequestMapping(value = "/city", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<City>> getAll() throws Exception{
		List<City> cityList = cityService.getAll();
		
		if(cityList.isEmpty()){
			return new ResponseEntity<List<City>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<City>>(cityList, HttpStatus.OK);
	}
	
	/**
	 * Insert.
	 *
	 * @param String name 
	 * @param Integer stateId the state id
	 * @return City city
	 */
	@RequestMapping(value = "/city", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<City> insert(@RequestParam("name") String name, @RequestParam("stateId") Integer stateId){
		City city = cityService.insert(name, stateId);
		return new ResponseEntity<City>(city, HttpStatus.OK);
	}
	
	/**
	 * Update.
	 *
	 * @param Integer id
	 * @param City city
	 * @return City city
	 * @throws Exception
	 */
	@RequestMapping(value = "/city/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<City> update(@PathVariable("id") Integer id, @RequestBody City city) throws Exception{
		City currentCity = cityService.getById(id);
		if(currentCity == null){
			return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
		}
		currentCity = cityService.update(city);
		return new ResponseEntity<City>(currentCity, HttpStatus.OK);
	}
	
	/**
	 * Delete.
	 *
	 * @param Integer Id
	 * @throws Exception
	 */
	@RequestMapping(value = "/city/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
		City currentCity = cityService.getById(id);
		if(currentCity == null){
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		cityService.delete(currentCity);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}