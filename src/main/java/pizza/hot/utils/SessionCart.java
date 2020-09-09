package pizza.hot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import pizza.hot.model.Food;
import pizza.hot.service.FoodService;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionCart {

    private Map<Food, Integer> usercart = new HashMap<>();
    private double totalPrice;

    FoodService foodService;

    @Autowired
    public void setFoodService(FoodService foodService) {
        this.foodService = foodService;
    }

    public SessionCart() {

    }

    public Map<Food, Integer> getUsercart() {
        return usercart;
    }

    public void setUsercart(Map<Food, Integer> usercart) {
        this.usercart = usercart;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void countTotalPrice() {
        long price = foodService.getTotalPrice(usercart);
        this.totalPrice = price;


    }

    public void removeFromCart(Food food, int removeNum) {
        for (Food fuud : usercart.keySet()) {
            if (fuud.getName().equals(food.getName())) {
                usercart.put(food, usercart.get(fuud) - removeNum);
            }

        }
    }


    public void addToCart(Food food, int addNum) {

        usercart.put(food, addNum);


    }


}
