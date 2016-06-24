package com.FoodyBuddy.Service;

import java.util.Date;
import java.util.List;

public interface OrderService{
	
	public boolean placeOrder(int buyerId, List<Integer> dishIds, List<Integer> quantitys) throws Exception;
}