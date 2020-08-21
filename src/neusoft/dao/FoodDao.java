package neusoft.dao;

import neusoft.domain.Food;

import java.util.List;

public interface FoodDao {
    //crud方法必备
    public List<Food> findAll(Integer businessId);
    public int saveFood(String foodName);
    public int updateFood(Food food);
    public int removeFood(Integer foodId);
    public Food getFoodById(Integer foodId);
}
