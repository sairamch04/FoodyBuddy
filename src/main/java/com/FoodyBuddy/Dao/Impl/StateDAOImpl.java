package com.FoodyBuddy.DAO.Impl;

import com.FoodyBuddy.DAO.State;
import com.FoodyBuddy.Model.State;

import java.util.List;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class StateDAOImpl implements StateDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(StateDAOImpl.class);

	private Session session = null;
	
	public StateDAOImpl(Session session){
		this.session = session;
	}

	@Override
	public void insertState(State state) {
		session.persist(state);
		logger.info("State saved successfully, State Details = " + state);
	}

	@Override
	public void updateState(State state) {

		session.update(state);
		logger.info("State updated successfully, State Details = " + state );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<State> getAllStates() {
		List<State> StatesList = session.createQuery("from State").list();
		for(State state : StatesList){
			logger.info("State List :: " + state);
		}
		return StatesList;
	}

	@Override
	public State getState(int id) {		
		State state = (State) session.load(State.class, new Integer(id));
		logger.info("State loaded successfully, State details = " + state);
		return state;
	}

	@Override
	public void deleteState(int id) {
		State state = (State) session.load(State.class, new Integer(id));
		if(null != state){
			session.delete(state);
		}
		logger.info("State deleted successfully, State details = " + state);
	}

}