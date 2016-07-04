package com.foodybuddy.controller;

import com.foodybuddy.model.State;
import com.foodybuddy.service.StateService;
import com.foodybuddy.service.impl.StateServiceImpl;
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
 * The Class StateController.
 */
@RestController
public class StateController {
	
	/** The session factory. */
	private SessionFactory sessionFactory = SessionFactoryUtils.getSessionFactory();
	
	/** The state service. */
	private StateService stateService = new StateServiceImpl(sessionFactory);
	
	
	/**
	 * Gets the State by id.
	 *
	 * @param Integer id
	 * @return the State by id
	 * @throws Exception
	 */
	@RequestMapping(value = "/state/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<State> getById(@PathVariable("id") Integer id) throws Exception{
		State state = stateService.getById(id);
		
		if(state == null){
			return new ResponseEntity<State>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<State>(state, HttpStatus.OK);
	}
	
	/**
	 * Gets all the states.
	 *
	 * @return all the states
	 * @throws Exception
	 */
	@RequestMapping(value = "/state", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<State>> getAll() throws Exception{
		List<State> stateList = stateService.getAll();
		
		if(stateList.isEmpty()){
			return new ResponseEntity<List<State>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<State>>(stateList, HttpStatus.OK);
	}
	
	/**
	 * Insert.
	 *
	 * @param String name
	 * @param Integer countryId the country id
	 * @return State state
	 */
	@RequestMapping(value = "/state", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<State> insert(@RequestParam("name") String name, @RequestParam("countryId") Integer countryId){
		State state = stateService.insert(name, countryId);
		return new ResponseEntity<State>(state, HttpStatus.OK);
	}
	
	/**
	 * Update.
	 *
	 * @param Integer id
	 * @param State state
	 * @return State state
	 * @throws Exception
	 */
	@RequestMapping(value = "/state/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<State> update(@PathVariable("id") Integer id, @RequestBody State state) throws Exception{
		State currentState = stateService.getById(id);
		if(currentState == null){
			return new ResponseEntity<State>(HttpStatus.NOT_FOUND);
		}
		currentState = stateService.update(state);
		return new ResponseEntity<State>(currentState, HttpStatus.OK);
	}
	
	/**
	 * Delete.
	 *
	 * @param Integer id
	 * @throws Exception
	 */
	@RequestMapping(value = "/state/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
		State currentState = stateService.getById(id);
		if(currentState == null){
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		stateService.delete(currentState);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}