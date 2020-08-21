package neusoft.test;

import neusoft.dao.Impl.FoodDaoImpl;
import neusoft.domain.Food;

public class TestFood {
    public static void main(String[] args) {
        FoodDaoImpl dao = new FoodDaoImpl();
        int i = dao.updateFood(new Food());
        System.out.println(i);
    }
}
