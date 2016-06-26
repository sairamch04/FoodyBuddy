package com.FoodyBuddy.Dao;

import java.util.List;
import com.FoodyBuddy.Model.State;

public interface StateDao {
	public List<State> getAllStates();
	public State getState(int id);
	public void saveState(State state);
	public void updateState(State state);
	public void deleteState(int id);
}