package com.foodybuddy.dao;

import java.util.List;
import com.foodybuddy.model.State;

public interface StateDAO {
	public List<State> getAll();
	public State getById(int id);
	public void insert(State state);
	public void update(State state);
	public void delete(State state);
}