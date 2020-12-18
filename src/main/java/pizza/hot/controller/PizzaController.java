package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pizza.hot.service.PizzaService;

import java.security.Principal;

@Controller
public class PizzaController {
    PizzaService pizzaService;

    @Autowired
    public void setPizzaService(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

     @GetMapping("pizzas")
    public String userList(Model model, Principal principal){
        model.addAttribute("pizzas", pizzaService.getAllPizzas());
        return "main";


    }
}
