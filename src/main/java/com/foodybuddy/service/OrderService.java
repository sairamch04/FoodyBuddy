package com.foodybuddy.service;

import java.util.List;

public interface OrderService{
	
	public void placeOrder(int buyerId, List<Integer> dishIds, List<Integer> orderedQuantitys) throws Exception;
}