package com.foodybuddy.dao;

import java.util.List;

import com.foodybuddy.model.Dish;

public interface DishDAO {

	public void insert(Dish d);
	public void update(Dish d);
	public void delete(Integer id);
	public List<Dish> getListdish();
	public Dish getListByDishId(Integer DishId);
	public List<Dish> getListBySellerId(Integer SellerId);
}