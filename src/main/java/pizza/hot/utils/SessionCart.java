package pizza.hot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import pizza.hot.model.Food;
import pizza.hot.model.Pizza;
import pizza.hot.model.Product;
import pizza.hot.service.FoodService;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionCart {

    Map<Food, Integer> userCart = new LinkedHashMap<>();

    private float totalPrice;

    FoodService foodService;


    @Autowired
    public void setFoodService(FoodService foodService) {
        this.foodService = foodService;
    }

    public SessionCart() {

    }

    public Map<Food, Integer> getUserCart() {
        return userCart;
    }

    public void setUsercart(Map<Food, Integer> userCart) {
        this.userCart = userCart;
    }

    public float getTotalPrice() {
        float price = foodService.getTotalPrice(userCart);
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void countTotalPrice() {
        float price = foodService.getTotalPrice(userCart);
        this.totalPrice = price;


    }

    public void removeFromCart(Food food, String description) {
        food.setDescription(description);
        for (Food foodToCheck : userCart.keySet()) {
            if (foodToCheck.equals(food)) {
                food = foodToCheck;
            }
        }
        int currentProductsCount = userCart.get(food);
        if (currentProductsCount <= 1) {
            userCart.remove(food);
        } else {
            userCart.put(food, currentProductsCount - 1);
        }
        this.countTotalPrice();
    }


    public void addToCart(Food food) {
        boolean isFoodAlreadyInCart = false;
        Food foodToIncrease = null;
        for (Food foodToCheck : userCart.keySet()) {
            if (foodToCheck.equals(food)) {
                isFoodAlreadyInCart = true;
                foodToIncrease = foodToCheck;
            }
        }
        if (isFoodAlreadyInCart) {
            int curAmountOfProduct = userCart.get(foodToIncrease);
            userCart.put(foodToIncrease, curAmountOfProduct + 1);

        }
        if (!isFoodAlreadyInCart) {
            userCart.put(food, 1);
        }
        this.countTotalPrice();
    }

    public void clearCart() {
        userCart.clear();
    }


}
