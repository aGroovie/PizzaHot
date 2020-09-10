package pizza.hot.service;

import pizza.hot.model.Drink;

import java.util.List;

public interface DrinkService {
    void saveDrink(Drink drink);
    List<Drink> findAll();
    void deleteDrinkById(Long id);
    Drink getDrinkById(Long id);
}
