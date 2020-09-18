package pizza.hot.dao;

import pizza.hot.model.Drink;

import java.util.List;

public interface DrinkDao {
     void saveDrink(Drink drink);
     List<Drink> findAll();
     void deleteDrinkById(Long id);
     Drink getDrinkById(Long id);
     void updateDrinkById(Long id, Drink newDrink);


}
