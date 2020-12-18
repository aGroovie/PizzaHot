package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pizza.hot.model.Drink;
import pizza.hot.model.Pizza;
import pizza.hot.service.DrinkService;
import pizza.hot.service.FoodService;
import pizza.hot.utils.SessionCart;

import java.util.List;

@Controller
public class DrinkController {

    DrinkService drinkService;

    FoodService foodService;

    SessionCart sessionCart;

    @Autowired
    public void setSessionCart(SessionCart sessionCart) {
        this.sessionCart = sessionCart;
    }

    @Autowired

    public void setDrinkService(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @Autowired
    public void setFoodService(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("drink-selection")
    public String getAllDrinks(Model model) {
        model.addAttribute("drinks", drinkService.findAll());
        return "drink-selection";
    }





}
