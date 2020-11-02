package pizza.hot.service;

import pizza.hot.model.Food;
import pizza.hot.model.ModifiedPizza;

import java.util.List;

public interface FoodService {


    public Food getFoodById(Long id);

    public void pizzaPriceSetter(int size, Food food);

    public void addProductsToPizza(List<Long> ids, ModifiedPizza modifiedPizza);
}
