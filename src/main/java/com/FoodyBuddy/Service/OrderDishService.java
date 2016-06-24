package com.FoodyBuddy.Service;

import com.FoodyBuddy.Model.OrderDish;

public interface OrderDishService {
	public OrderDish OrderDishQuantity(int orderDishId, int updatedQuantity) throws Exception ;
}
