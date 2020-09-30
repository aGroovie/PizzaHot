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
import pizza.hot.model.Pizza;
import pizza.hot.model.Product;
import pizza.hot.service.*;
import pizza.hot.utils.SessionCart;

import javax.transaction.Transactional;

@Controller
@SessionAttributes({"sessionCart", "userCart", "pizza"})
public class CartController {

    SessionCart sessionCart;


    FoodService foodService;

    PizzaService pizzaService;

    DrinkService drinkService;


    ProductService productService;
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

    @PostMapping(value = "/buyPizza")           //razdelit mb pizza i drink controlleri hz gabe? ?
    public String listProductHandler(@RequestParam(value = "id") Long id,
                                     @RequestParam(value = "size") String size,
                                     ModelMap model
    ) {
        if (size == null) {
            return "redirect:/main";
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            Pizza pizza = pizzaService.getPizzaById(id);
            foodService.pizzaPriceSetter(Integer.parseInt(size), pizza);
            sessionCart.addToCart(pizza, 1); // problem here
            model.addAttribute("pizza", pizza);
            return "redirect:/extra-products";
        } else {
            return "/login";
        }

    }
    @PostMapping(value = "/buyDrink")           //razdelit mb pizza i drink controlleri hz gabe? ?
    public String listProductHandler(@RequestParam(value = "id") Long id, @RequestParam(value = "quantity") String quantity,
                                     Model model
    ) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {

            Drink drink;
            drink = drinkService.getDrinkById(id);

            model.addAttribute("drink", drink);

            sessionCart.addToCart(drink, Integer.parseInt(quantity)); // problem here
            return "redirect:/shoppingCart";

        } else {
            return "/login";
        }

    }


    @PostMapping(value = "/removeProduct{id}")
    public String removeProductHandler(@PathVariable(value = "id") Long id
            , Model model, @RequestParam(value = "quantity", required = false) String quantity) {

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

/*
    @PostMapping
    public String CleanCartHandler() {

        sessionCart.getUserCart().clear();
        return "redirect:/main";
    }
*/

    @PostMapping("/continueShopping")
    public String continueShopping() {

        return "redirect:/main";
    }
}