package pizza.hot.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pizza.hot.dao.PizzaDao;
import pizza.hot.model.Pizza;
import pizza.hot.model.User;
import pizza.hot.service.PizzaService;
import pizza.hot.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class PizzaListController {

    PizzaService pizzaService;

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPizzaService(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }


    @PostMapping(value = "/savePizza")
    public String save(@ModelAttribute("pizza") Pizza pizza) {
        pizzaService.addPizza(pizza);
        return "redirect:/admin/pizza-list";
    }



    @GetMapping("/pizza-list")
    public String userList(Model model){
        model.addAttribute("pizzas", pizzaService.getAllPizzas());

        return "pizza-list";
    }

    @GetMapping(value = "deletePizzaById/{id}")
    public String deletePizza(@PathVariable Long id){
        pizzaService.deletePizzaById(id);
        return "redirect:/admin/pizza-list";
    }

    @PostMapping(value = "updatePizzaById/{id, pizza}")
    public String updatePizza(@ModelAttribute("pizza") Pizza pizza, @PathVariable Long id){
        pizzaService.updatePizzaById(id, pizza);
        return "redirect:/admin/pizza-list";
    }


}
