package com.foodybuddy.dao.impl;

import com.foodybuddy.dao.StateDAO;
import com.foodybuddy.model.State;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


/**
 * The Class StateDAOImpl.
 */
@Repository
public class StateDAOImpl implements StateDAO {
	
	/** The session. */
	private Session session;
	
	/** The Object null exception. */
	RuntimeException ObjectNullException = new RuntimeException("Object is null");
	
	/**
	 * Instantiates a new state DAO impl.
	 *
	 * @param Session session
	 */
	public StateDAOImpl(Session session){
		if(session == null){
			throw ObjectNullException;
		}
		this.session = session;
	}

	
	/**
	 * Insert.
	 *
	 * @param State state
	 * @return State state
	 */
	public State insert(State state) {
		if(state == null){
			throw ObjectNullException;
		}
		this.session.persist(state);
		return state;
	}

	
	/**
	 * Update.
	 *
	 * @param State state
	 * @return State state
	 */
	public State update(State state) {
		if(state == null){
			throw ObjectNullException;
		}
		this.session.update(state);
		return state;
	}

	/**
	 * Gets all the States
	 *
	 * @return all states
	 */
	@SuppressWarnings("unchecked")
	public List<State> getAll() {
		String query = "FROM State";
		List<State> statesList = this.session.createQuery(query).list();
		return statesList;
	}

	/**
	 * Gets the State by id.
	 *
	 * @param id the State id
	 * @return the state by id
	 */
	public State getById(Integer id) {
		if(id == null){
			throw ObjectNullException;
		}
		String query = "FROM State WHERE id = " + id; 
		State state = (State)this.session.createQuery(query).uniqueResult();
		return state;
	}

	
	/**
	 * Delete.
	 *
	 * @param State state
	 */
	public void delete(State state) {
		if(state == null){
			throw ObjectNullException;
		}
		this.session.delete(state);
	}

}