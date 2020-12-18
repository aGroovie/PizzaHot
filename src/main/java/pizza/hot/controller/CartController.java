package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pizza.hot.model.Drink;
import pizza.hot.model.Food;
import pizza.hot.model.ModifiedPizza;
import pizza.hot.model.Pizza;
import pizza.hot.service.DrinkService;
import pizza.hot.service.FoodService;
import pizza.hot.service.PizzaService;
import pizza.hot.utils.SessionCart;

@Controller
@SessionAttributes({"sessionCart", "userCart", "pizza"})
public class CartController {

    SessionCart sessionCart;


    FoodService foodService;

    PizzaService pizzaService;

    DrinkService drinkService;

    @Autowired
    public CartController setDrinkService(DrinkService drinkService) {
        this.drinkService = drinkService;
        return this;
    }


    @Autowired
    public void setSessionCart(SessionCart sessionCart) {
        this.sessionCart = sessionCart;
    }


    @Autowired
    public void setPizzaService(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @Autowired
    public void setFoodService(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping(value = "buyPizza")
    public String listProductHandler(@RequestParam(value = "id") Long id,
                                     @RequestParam(value = "size", required = false) String size,
                                     ModelMap model
    ) {
        if (size == null) {
            return "redirect:/main";
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            Pizza pizza = pizzaService.getPizzaById(id);
            pizza.setSize(Integer.parseInt(size));
            foodService.pizzaPriceSetter(Integer.parseInt(size), pizza);
            model.addAttribute("pizza", pizza);
            return "redirect:/extra-products";
        } else {
            return "login";
        }

    }

    @PostMapping(value = "buyDrink")
    public String listProductHandler(@RequestParam(value = "id") Long id,
                                     Model model
    ) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {

            Drink drink;
            drink = drinkService.getDrinkById(id);

            model.addAttribute("drink", drink);

            sessionCart.addToCart(drink);
            return "redirect:shoppingCart";

        } else {
            return "login";
        }

    }

    @PostMapping(value = "addProduct")
    public String increaseAmountHandler(@RequestParam(value = "id") Long id, @RequestParam(value = "description")
            String description, @RequestParam(value = "name") String name) {
        Food food = foodService.getFoodById(id);
        food.setName(name);
        food.setDescription(description);
        sessionCart.addToCart(food);

        return "redirect:shoppingCart";
    }


    @PostMapping(value = "removeProduct{id}")
    public String removeProductHandler(@PathVariable(value = "id") Long id
            , Model model, @RequestParam(value = "description") String description,
                                       @RequestParam(value = "name") String name) {

        model.getAttribute("sessionCart");
        sessionCart.removeFromCart(name, description);
        return "redirect:/shoppingCart";
    }


    @GetMapping("shoppingCart")
    public String ShoppingCartHandler(Model model) {

        model.addAttribute("sessionCart", sessionCart);
        model.addAttribute("userCart", sessionCart.getUserCart());

        return "shoppingCart";

    }

    @PostMapping("clearCart")
    public String CleanCartHandler() {

        sessionCart.getUserCart().clear();
        return "redirect:shoppingCart";
    }

    @PostMapping("continueShopping")
    public String continueShopping() {

        return "redirect:main";
    }
}