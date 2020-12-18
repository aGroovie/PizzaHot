package pizza.hot.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pizza.hot.model.Pizza;
import pizza.hot.service.PizzaService;
import pizza.hot.service.UserService;

@Controller
@RequestMapping("admin")
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


    @PostMapping(value = "savePizza")
    public String save(@ModelAttribute("pizza") Pizza pizza) {

        pizzaService.addPizza(pizza);
        return "redirect:admin/pizza-list";
    }


    @GetMapping("pizza-list")
    public String pizzaList(Model model) {
        model.addAttribute("pizzas", pizzaService.getAllPizzas());

        return "pizza-list";
    }

    @GetMapping(value = "deletePizzaById/{id}")
    public String deletePizza(@PathVariable("id") Long id) {
        pizzaService.deletePizzaById(id);
        return "redirect:admin/pizza-list";
    }


    @GetMapping(value = "add-pizza")
    public String getAddPizza(@ModelAttribute("pizza") Pizza pizza){
        return "/add-pizza";
    }

    @PostMapping(value = "add-pizza")
        public String addPizza(@ModelAttribute("pizza") Pizza pizza){
            pizzaService.addPizza(pizza);
            return "redirect:admin/pizza-list";
        }

    @GetMapping(value = {"updatePizzaById/{id}", "/deletePizzaById/", "updatePizza"})
    public String getUpdatePizza(@PathVariable("id") Long id, Model model, @ModelAttribute("pizza") Pizza pizza) {

        model.addAttribute("pizza", pizzaService.getPizzaById(id));


        return "edit-pizza";


    }

    @PostMapping(value = "updatePizzaById/{id}")
    public String updatePizza(@ModelAttribute("pizza") Pizza pizza, @PathVariable Long id) {
        pizzaService.updatePizzaById(id, pizza);

        return "redirect:admin/pizza-list";
    }


    @GetMapping(value = "new-pizza")
    public String newPizza(@ModelAttribute("pizza") Pizza pizza) {

        return "new-pizza";

    }


}
