package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/drink-selection")
    public String getAllDrinks(Model model) {
        model.addAttribute("drinks", drinkService.findAll());
        return "/drink-selection";
    }


    @PostMapping(value = "/buyDrink")           //razdelit mb pizza i drink controlleri hz gabe? ?
    public String listProductHandler(@RequestParam(value = "id") Long id, @RequestParam(value = "quantity") String quantity,
                                     Model model
    ) {

        Drink drink;
        drink = drinkService.getDrinkById(id);

        model.addAttribute("drink", drink);

        sessionCart.addToCart(drink, Integer.parseInt(quantity)); // problem here

        return "redirect:/shoppingCart";

    }


}
