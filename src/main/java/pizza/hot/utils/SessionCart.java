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

    public void removeFromCart(Food food, int removeNum, String description) {

        for (Food fud : userCart.keySet()) {
            if (fud.getId().equals(food.getId()) && fud.getDescription().equals(description)) {
                food = fud;
            }
        }
        int currentProductsCount = userCart.get(food);
        if (currentProductsCount <= removeNum) {
            userCart.remove(food);
        } else {
            userCart.put(food, currentProductsCount - removeNum);
        }
        this.countTotalPrice();
    }


    public void addToCart(Food food, int addNum) {

        userCart.put(food, addNum);
        this.countTotalPrice();

    }

    public void increaseProductQuantity(Long id, String description) {
        for (Food food : userCart.keySet()) {
            if (id.equals(food.getId()) && description.equals(food.getDescription())) {
                int curAmount = userCart.get(food);
                userCart.put(food, curAmount + 1);
            }
        }

        this.countTotalPrice();
    }


    public void clearCart() {
        userCart.clear();
    }







}
