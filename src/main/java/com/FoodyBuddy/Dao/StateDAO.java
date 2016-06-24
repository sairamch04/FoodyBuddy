package com.FoodyBuddy.DAO;

import java.util.List;
import com.FoodyBuddy.Model.State;

public interface StateDAO {
	public List<State> getAllStates();
	public State getState(int id);
	public void insertState(State state);
	public void updateState(State state);
	public void deleteState(int id);
}