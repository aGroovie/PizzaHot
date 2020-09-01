package pizza.hot.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pizza.hot.dao.PizzaDao;
import pizza.hot.model.Pizza;
import pizza.hot.service.PizzaService;

import java.util.List;

@Controller
public class PizzaListController {

    PizzaService pizzaService;

    @Autowired
    public void setPizzaService(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }


    @PostMapping(value = "/savePizza")
    public String save(@ModelAttribute("pizza") Pizza pizza) {
        pizzaService.addPizza(pizza);
        return "redirect:/pizza-list";
    }

    @GetMapping("/pizza-list")
    public List<Pizza> getallPizzas() {
        return pizzaService.getAllPizzas();
    }

    @GetMapping(value = "deletePizzaById/{id}")
    public String deletePizza(@PathVariable Long id){
        pizzaService.deletePizzaById(id);
        return "redirect:/pizza-list";
    }

    @PostMapping(value = "updatePizzaById/{id, pizza}")
    public String updatePizza(@ModelAttribute("pizza") Pizza pizza, @PathVariable Long id){
        pizzaService.updatePizzaById(id, pizza);
        return "redirect:/pizza-list";
    }

}
