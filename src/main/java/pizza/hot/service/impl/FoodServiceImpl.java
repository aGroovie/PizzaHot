package pizza.hot.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizza.hot.model.*;
import pizza.hot.service.DrinkService;
import pizza.hot.service.FoodService;
import pizza.hot.service.PizzaService;
import pizza.hot.service.ProductService;

import java.util.List;

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
        float currentPrice = food.getPrice();
        if (size > 15) {
            food.setPrice(currentPrice + currentPrice / 3);
        }
    }

    @Override
    public void addProductsToPizza(List<String> ids, ModifiedPizza modifiedPizza) {
        float productPrice = 0;
        StringBuffer sb = new StringBuffer(modifiedPizza.getDescription());
        sb.append(" With extras : ");
        for (String id : ids) {
            Product product = productService.getProductById(Long.parseLong(id));
            if (modifiedPizza.getSize() == 30) {
                productPrice = product.getPrice() + (product.getPrice() / 2);

            }
            sb.append(product.getName() + " " + productPrice + "$ ");
            modifiedPizza.setPrice(modifiedPizza.getPrice() + productPrice);
            modifiedPizza.getProducts().add(product);
        }
        modifiedPizza.setDescription(sb.toString());
    }


}
