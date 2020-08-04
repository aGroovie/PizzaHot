package pizza.hot.dao;

import org.springframework.stereotype.Repository;
import pizza.hot.model.Pizza;

import java.util.List;


public interface PizzaDao {

    void addPizza(Pizza pizza);

    List<Pizza> getAllPizzas();

    void deletePizzaById(Long id);


}
