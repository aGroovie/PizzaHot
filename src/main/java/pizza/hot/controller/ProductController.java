package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pizza.hot.model.Pizza;
import pizza.hot.model.Product;
import pizza.hot.service.PizzaService;
import pizza.hot.service.ProductService;
import pizza.hot.utils.SessionCart;

import java.util.HashSet;

@Controller
public class ProductController {

    ProductService productService;

    PizzaService pizzaService;

    SessionCart sessionCart;

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



    @GetMapping("/extra-products")
    public String showProducts(Model model) {
        Pizza pizza = (Pizza) model.getAttribute("pizza");
        model.addAttribute("products", productService.findAllProducts());
        return "extra-products";
    }


    @PostMapping(value = "addProductById/{id}")
    String addProductToPizzaById(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        Pizza pizza = (Pizza) model.getAttribute("pizza");
        pizza.getProducts().add(product);

        return "redirect:/extra-products";
    }

}



