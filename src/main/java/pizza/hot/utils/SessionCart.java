package pizza.hot.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import pizza.hot.model.Food;
import pizza.hot.model.Pizza;
import pizza.hot.model.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionCart {

    private Map<Food,Integer> usercart = new HashMap<>();
    private double totalPrice;

    public SessionCart(){

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

    public void countTotalPrice(){
        long price =0;

        for(Food food : usercart.keySet()){
            totalPrice+= food.getPrice()*usercart.get(food);
        }
        this.totalPrice = price;

    }
    public void removeFromCart(Food food, int removeNum){
         for(Food fuud : usercart.keySet()){
             if(fuud.getName().equals(food.getName())){
                 usercart.put(food,usercart.get(fuud) - removeNum);
             }

         }
    }
    public void addToCart(Food food, int addNum){

        usercart.put(food,addNum);


    }





}
