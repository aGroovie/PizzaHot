package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pizza.hot.model.Drink;
import pizza.hot.model.Pizza;
import pizza.hot.service.DrinkService;
import pizza.hot.service.PizzaService;
import pizza.hot.utils.SessionCart;

@Controller
@RequestMapping("/cart")
public class CartController {

    private SessionCart sessionCart;
    private PizzaService pizzaService;
    private DrinkService drinkService;

    @Autowired
    public void setPizzaService(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @Autowired
    public void setDrinkService(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @Autowired
    public void setSessionCart(SessionCart sessionCart) {
        this.sessionCart = sessionCart;
    }


    @ModelAttribute
    public void addCart(Model model) {
        model.addAttribute("sessionCart", sessionCart);
    }
    @PostMapping
    public String addPizzaToCart(@RequestParam("PizzaId")Long pizzaId, @RequestParam("amount") String amount){
        Pizza pizza = pizzaService.getPizzaById(pizzaId);
        sessionCart.addToCart(pizza,Integer.parseInt(amount));
        return "redirect:/cart";
    }

    @DeleteMapping(value = "remove/{pizzaId}/")
    public String removePizzaFromCart(@PathVariable Long pizzaId){
        Pizza pizza = pizzaService.getPizzaById(pizzaId);

        sessionCart.removeFromCart(pizza, sessionCart.getUsercart().get(pizza));
        return "redirect:/cart";
    }


    @PostMapping
    public String addDrinkToCart(@RequestParam("DrinkId")Long drinkId, @RequestParam("amount") String amount){
         Drink drink = drinkService.getDrinkById(drinkId);
        sessionCart.addToCart(drink,Integer.parseInt(amount));
        return "redirect:/cart";
    }

    @DeleteMapping(value ="remove/{drinkId}/")
    public String removeDrinkFromCart(@PathVariable Long drinkId){
        Drink drink = drinkService.getDrinkById(drinkId);

        sessionCart.removeFromCart(drink, sessionCart.getUsercart().get(drink));
        return "redirect:/cart";
    }












}
