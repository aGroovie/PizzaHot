package pizza.hot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import pizza.hot.model.Food;
import pizza.hot.service.FoodService;
import pizza.hot.service.impl.FoodServiceImpl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionCart {

    Map<Food, Integer> userCart = new LinkedHashMap<>();
    private long totalPrice;

    FoodService foodService;


    public void setUserCart(Map<Food, Integer> userCart) {
        this.userCart = userCart;
    }

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

    public double getTotalPrice() {
        long price = foodService.getTotalPrice(userCart);
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void countTotalPrice() {
        long price = foodService.getTotalPrice(userCart);
        this.totalPrice = price;


    }

    public void removeFromCart(Food food, int removeNum) {
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


}
