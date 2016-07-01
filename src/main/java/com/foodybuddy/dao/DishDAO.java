package com.foodybuddy.dao;
import java.util.List;
import com.foodybuddy.model.Dish;
// TODO: Auto-generated Javadoc
/**
 * The Interface DishDAO.
 */
public interface DishDAO {
    /**
     * Insert.
     *
     * @param d the d
     * @return the dish
     */
    public Dish insert(Dish d);
    
    /**
     * Update.
     *
     * @param d the d
     * @return the dish
     */
    public Dish update(Dish d);
    
    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(Integer id);
    
    /**
     * Gets the listdish.
     *
     * @return the listdish
     */
    public List<Dish> getDishList();
    
    /**
     * Gets the list by dish id.
     *
     * @param DishId the dish id
     * @return the list by dish id
     */
    public Dish getByDishId(Integer DishId);
    
    /**
     * Gets the list by seller id.
     *
     * @param SellerId the seller id
     * @return the list by seller id
     */
    public List<Dish> getListBySellerId(Integer SellerId);
}