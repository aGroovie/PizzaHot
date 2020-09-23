package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pizza.hot.model.Pizza;
import pizza.hot.service.FoodService;
import pizza.hot.service.PizzaService;
import pizza.hot.service.ProductService;
import pizza.hot.utils.SessionCart;

import java.util.*;

@Controller
@SessionAttributes("pizza")
public class ProductController {

    ProductService productService;

    PizzaService pizzaService;

    SessionCart sessionCart;

    FoodService foodService;


    @Autowired
    public void setSessionCart(SessionCart sessionCart) {
        this.sessionCart = sessionCart;
    }

    @Autowired
    public void setPizzaService(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setFoodService(FoodService foodService) {
        this.foodService = foodService;
    }


    @GetMapping("/extra-products")
    public String showProducts(Model model) {
         model.getAttribute("pizza");
        model.getAttribute("sessionCart");
        model.addAttribute("products", productService.findAllProducts());
        return "extra-selection";
    }


    @PostMapping(value = "/addProductById")
    String addProductToPizzaById(@RequestParam(value = "id") List<String> ids,Model model ) {
        Pizza pizza = (Pizza) model.getAttribute("pizza");


        foodService.addProductsToPizza(ids,pizza);


        foodService.pizzaPriceSetter(pizza.getSize(), pizza);
        sessionCart.addToCart(pizza, 1); // problem here

        return "redirect:/drink-selection";
    }


}



