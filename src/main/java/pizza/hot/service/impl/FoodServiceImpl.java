package pizza.hot.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import pizza.hot.model.Drink;
import pizza.hot.model.Food;
import pizza.hot.model.Pizza;
import pizza.hot.model.Product;
import pizza.hot.service.DrinkService;
import pizza.hot.service.FoodService;
import pizza.hot.service.PizzaService;

import java.util.Map;
import java.util.Set;

@Service


public class FoodServiceImpl implements FoodService {

    private PizzaService pizzaService;

    private DrinkService drinkService;


    @Autowired
    public void setPizzaService(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @Autowired
    public void setDrinkService(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @Override

    public Long getPizzaTotalPrice(Pizza pizza) {

        Long totalPrice = pizza.getPrice();

        Set<Product> products = pizza.getProducts();
        for (Product product : products) {

            if (pizza.getSize() == 30) {
                totalPrice += product.getPrice() + product.getPrice() / 3;
            } else {
                totalPrice += product.getPrice();
            }
        }

        return totalPrice;
    }

    @Override
    public Long getTotalPrice(Map<Food, Integer> food) {
        long price = 0L;

        for (Food fud : food.keySet()) {
            if (fud instanceof Pizza) {
                price += getPizzaTotalPrice((Pizza) fud) * food.get(fud);

            } else {
                price += fud.getPrice() * food.get(fud);

            }
            //     System.out.println(price);

        }
        return price;
    }

    @Override
    public Food getFoodById(Long id) {
        Drink drink;
        Pizza pizza;

        if (pizzaService.getPizzaById(id) == null) {

            drink = drinkService.getDrinkById(id);
            return drink;
        } else {
            pizza = pizzaService.getPizzaById(id);
            return pizza;
        }

    }
}
