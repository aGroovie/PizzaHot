package pizza.hot.service;

import pizza.hot.model.Food;
import pizza.hot.model.ModifiedPizza;
import pizza.hot.model.Pizza;

import java.util.List;
import java.util.Map;

public interface FoodService {



    public float getTotalPrice(Map<Food, Integer> food);

    public Food getFoodById(Long id);

    public void pizzaPriceSetter(int size, Food food);

    public void addProductsToPizza(List<String> ids, ModifiedPizza modifiedPizza);
}
