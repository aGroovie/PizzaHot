package pizza.hot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizza.hot.dao.PizzaDao;
import pizza.hot.model.Pizza;
import pizza.hot.service.PizzaService;

import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {

    PizzaDao pizzaDao;


    @Autowired
    public void setPizzaDao(PizzaDao pizzaDao) {
        this.pizzaDao = pizzaDao;
    }

    @Override
    public void addPizza(Pizza pizza) {
        pizzaDao.addPizza(pizza);
    }

    @Override
    public List<Pizza> getAllPizzas() {
        return pizzaDao.getAllPizzas();
    }

    @Override
    public void deletePizzaById(Long id) {

        pizzaDao.deletePizzaById(id);
    }

    @Override
    public void updatePizzaById(Long id, Pizza newPizza) {
        pizzaDao.updatePizzaById(id, newPizza);
    }

    @Override
    public Pizza getPizzaById(Long id) {
        return pizzaDao.getPizzaById(id);
    }
}
