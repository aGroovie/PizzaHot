package pizza.hot.service;

import pizza.hot.model.Food;
import pizza.hot.model.Pizza;

import java.util.Map;

public interface FoodService {

    public Long getPizzaTotalPrice(Pizza pizza);

    public Long getTotalPrice(Map<Food, Integer> food);

    public Food getFoodById(Long id);

    public void pizzaPriceSetter(int size, Food food);
}
