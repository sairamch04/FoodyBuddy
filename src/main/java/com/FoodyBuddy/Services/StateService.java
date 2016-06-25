package com.FoodyBuddy.Service;

import com.FoodyBuddy.Model.State;
import java.util.List;
import org.hibernate.TransactionException;

public interface StateService {

	public State getState(int id);
	public List<State> getAllStates();
	public void insertState(String name, int countryId) throws TransactionException;
	public void updateState(State state) throws TransactionException;
	public void deleteState(int id) throws TransactionException;
}