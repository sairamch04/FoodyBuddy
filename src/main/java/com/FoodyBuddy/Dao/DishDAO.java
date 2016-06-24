package com.FoodyBuddy.Dao;

import java.util.List;

import com.FoodyBuddy.Model.Dish;

public interface DishDAO {

	public void save(Dish d);
	public void update(Dish d);
	public void delete(Integer id);
	public List<Dish> listdish();
	public List<Dish> listdishByDishId(Integer DishId);
	public List<Dish> listdishBySellerId(Integer SellerId);
}
