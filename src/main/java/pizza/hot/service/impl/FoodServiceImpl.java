package pizza.hot.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizza.hot.model.Drink;
import pizza.hot.model.Food;
import pizza.hot.model.Pizza;
import pizza.hot.model.Product;
import pizza.hot.service.DrinkService;
import pizza.hot.service.FoodService;
import pizza.hot.service.PizzaService;
import pizza.hot.service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service

@Transactional
public class FoodServiceImpl implements FoodService {

    private PizzaService pizzaService;

    private DrinkService drinkService;

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

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

    @Override
    public void pizzaPriceSetter(int size, Food food) {
        Long currentPrice = food.getPrice();
        if (size > 15) {
            food.setPrice(currentPrice + currentPrice / 3);
        }
    }

    @Override
    public void addProductsToPizza(List<String> ids, Pizza pizza) {
        for(String id : ids){
        Product product  = productService.getProductById(Long.parseLong(id));
        pizza.getProducts().add(product);
       }
    }



}
