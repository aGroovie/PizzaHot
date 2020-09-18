package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pizza.hot.model.Food;
import pizza.hot.service.FoodService;
import pizza.hot.service.PizzaService;
import pizza.hot.service.UserService;
import pizza.hot.utils.SessionCart;

import javax.servlet.http.HttpServletRequest;

@Controller

public class CartController {

    SessionCart sessionCart;


    FoodService foodService;

    PizzaService pizzaService;

    UserService userService;

    @Autowired
    public void setSessionCart(SessionCart sessionCart) {
        this.sessionCart = sessionCart;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPizzaService(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @Autowired
    public void setFoodService(FoodService foodService) {
        this.foodService = foodService;
    }


    @GetMapping(value = {"/buyProduct{id}", "/buyProduct"})
    public String listProductHandler(@PathVariable(value = "id") Long id, Model model
    ) {   //  @RequestParam(value = "quantity") String quantity  tyt request param je? Posmotri pls ya vse pravilno visral ili net   kak eto sdelat prosto pole clya cifri vo view sdelat?

        Food food;
        food = foodService.getFoodById(id);
        model.addAttribute("food", food);

        sessionCart.addToCart(food, 2); // problem here

        return "redirect:/shoppingCart";

    }


    @GetMapping(value = "/removeProduct{id}")
    public String removeProductHandler(@PathVariable(value = "id") Long id
            , Model model) {  // @RequestParam(value = "quantity") String quantity

        Food food = foodService.getFoodById(id);

        model.getAttribute("sessionCart");
        sessionCart.removeFromCart(food, 1);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/shoppingCart")
    public String ShoppingCartHandler(Model model) {

        model.addAttribute("sessionCart", sessionCart);
        model.addAttribute("userCart", sessionCart.getUserCart()); //ono rabotaet no mne kajetsya eto kostil

        return "/shoppingCart";

    }

    @PostMapping
    public String CleanCartHandler() {

        sessionCart.getUserCart().clear();
        return "redirect:/main";
    }

    @PostMapping("/continueShopping")
    public String continueShopping() {

        return "redirect:/main";
    }


}
