package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pizza.hot.model.Pizza;
import pizza.hot.service.PizzaService;

import java.util.List;

@Controller
public class MainController {

    PizzaService pizzaService;



    @Autowired
    public void setPizzaService(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

  @GetMapping("/main")
    public List<Pizza> getallPizzas(){
        return pizzaService.getAllPizzas();
  }




}
