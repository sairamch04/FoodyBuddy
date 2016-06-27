package com.FoodyBuddy.Service;

import java.util.Date;

public interface DishService {
	public boolean AddDish(String name, String description, Integer sellerId, String price, Date orderBy, Date dishAvailableStart,
			Date dishAvailableEnd, Boolean isVeg, int quantityAvailable) throws Exception;
	public boolean DeleteDish(Integer id) throws Exception;
}

