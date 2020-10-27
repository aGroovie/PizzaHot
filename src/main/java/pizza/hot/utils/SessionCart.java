package pizza.hot.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import pizza.hot.model.Food;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionCart {

    private Map<Food, Integer> userCart = new HashMap<>();

    private float totalPrice;

    public SessionCart() {

    }


    public Map<Food, Integer> getUserCart() {
        return userCart;
    }

    public void setUserCart(Map<Food, Integer> userCart) {
        this.userCart = userCart;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void countTotalPrice() {
        this.totalPrice = getTotalPrice(userCart);
        setTotalPrice(totalPrice);

    }

    public void removeFromCart(String name, String description) {
        Food food = null;
        for (Food foodToCheck : userCart.keySet()) {
            if (foodToCheck.getName().equals(name) && foodToCheck.getDescription().equals(description)) {
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
        Food foodToPut = null;
        for (Food foodToCheck : userCart.keySet()) {
            if (foodToCheck.getName().equals(food.getName()) && foodToCheck.getDescription().equals(food.getDescription())) {
                foodToPut = foodToCheck;
                isFoodAlreadyInCart = true;

            }
        }
        if (isFoodAlreadyInCart) {
            int curAmountOfProduct = userCart.get(foodToPut);
            userCart.put(foodToPut, curAmountOfProduct + 1);
        }
        if (!isFoodAlreadyInCart) {
            userCart.put(food, 1);
        }
        this.countTotalPrice();

    }


    public float getTotalPrice(Map<Food, Integer> food) {
        float price = 0f;
        for (Food foodToCheck : food.keySet()) {
            price += foodToCheck.getPrice() * food.get(foodToCheck);
        }
        return price;
    }


    public void clearCart() {
        userCart.clear();
    }


}
