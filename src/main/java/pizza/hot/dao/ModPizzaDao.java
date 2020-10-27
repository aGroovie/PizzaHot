package pizza.hot.dao;

import pizza.hot.model.ModifiedPizza;
import pizza.hot.model.Pizza;

import java.util.List;

public interface ModPizzaDao {



    void savePizza(ModifiedPizza pizza);

    List<ModifiedPizza> getAllPizzas();

    void deletePizzaById(Long id);

    ModifiedPizza getPizzaById(Long id);


}
