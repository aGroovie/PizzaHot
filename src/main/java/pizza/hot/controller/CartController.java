package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pizza.hot.model.Food;
import pizza.hot.model.Pizza;
import pizza.hot.model.Product;
import pizza.hot.service.FoodService;
import pizza.hot.service.PizzaService;
import pizza.hot.service.ProductService;
import pizza.hot.service.UserService;
import pizza.hot.utils.SessionCart;

@Controller
@SessionAttributes("pizza")
public class CartController {

    SessionCart sessionCart;


    FoodService foodService;

    PizzaService pizzaService;


    ProductService productService;


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

        Pizza pizza = pizzaService.getPizzaById(id);
        foodService.pizzaPriceSetter(Integer.parseInt(size), pizza);
        model.addAttribute("pizza", pizza);

        sessionCart.addToCart(pizza, 1); // problem here

        return "redirect:/extra-products";

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