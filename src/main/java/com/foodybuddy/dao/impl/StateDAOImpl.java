package com.foodybuddy.dao.impl;

import com.foodybuddy.dao.StateDAO;
import com.foodybuddy.model.State;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;


@Repository
public class StateDAOImpl implements StateDAO {
	
	private Session session = null;
	
	public StateDAOImpl(Session session){
		this.session = session;
	}

	
	public void insert(State state) {
		session.persist(state);
	}

	
	public void update(State state) {
		session.update(state);
	}

	@SuppressWarnings("unchecked")
	public List<State> getAll() {
		String query = "FROM State";
		List<State> statesList = session.createQuery(query).list();
		return statesList;
	}

	public State getById(int id) {
		String query = "FROM State WHERE id = " + id; 
		State state = (Locality)session.createQuery(query).uniqueResult();
		return state;
	}

	
	public void deleteState(int id) {
		State state = (State) session.load(State.class, new Integer(id));
		if(null != state){
			session.delete(state);
		}
	}

}