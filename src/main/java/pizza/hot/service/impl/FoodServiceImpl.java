package pizza.hot.service.impl;

import org.springframework.stereotype.Service;
import pizza.hot.model.Food;
import pizza.hot.model.Pizza;
import pizza.hot.model.Product;
import pizza.hot.service.FoodService;

import java.util.Map;
import java.util.Set;


@Service
public class FoodServiceImpl implements FoodService {
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
        Long price = 0L;

        for (Food fud : food.keySet()) {
            if (fud instanceof Pizza) {
                price += getPizzaTotalPrice((Pizza)fud) * food.get(fud);
                System.out.println(price);
            } else {
                price += fud.getPrice() * food.get(fud);

            }
            //     System.out.println(price);

        }
        return price;
    }
}
