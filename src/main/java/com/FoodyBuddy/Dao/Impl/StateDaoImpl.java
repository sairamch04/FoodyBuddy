package com.FoodyBuddy.Dao.Impl;

import com.FoodyBuddy.Dao.StateDao;
import com.FoodyBuddy.Model.State;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;


@Repository
public class StateDaoImpl implements StateDao {
	
	private Session session = null;
	
	public StateDaoImpl(Session session){
		this.session = session;
	}

	
	public void saveState(State state) {
		session.persist(state);
	}

	
	public void updateState(State state) {

		session.update(state);
	}

	@SuppressWarnings("unchecked")
	public List<State> getAllStates() {
		List<State> StatesList = session.createQuery("from State").list();
		return StatesList;
	}

	
	public State getState(int id) {		
		State state = (State) session.load(State.class, new Integer(id));
		return state;
	}

	
	public void deleteState(int id) {
		State state = (State) session.load(State.class, new Integer(id));
		if(null != state){
			session.delete(state);
		}
	}

}