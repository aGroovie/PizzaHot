package pizza.hot.service;

import pizza.hot.model.ModifiedPizza;
import pizza.hot.model.Pizza;

import java.util.List;

public interface ModPizzaService {

    void savePizza(ModifiedPizza pizza);

    List<ModifiedPizza> getAllPizzas();

    void deletePizzaById(Long id);

    void updatePizzaById(Long id, ModifiedPizza pizza);

    ModifiedPizza getPizzaById(Long id);

    ModifiedPizza clonePizza(Pizza pizza);
}
