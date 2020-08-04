package pizza.hot.service;

import pizza.hot.model.Pizza;

import java.util.List;

public interface PizzaService {
    void addPizza(Pizza pizza);

    List<Pizza> getAllPizzas();

    void deletePizzaById(Long id);

}
