package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pizza.hot.service.PizzaService;
import pizza.hot.service.UserService;

@Controller
public class MainController {



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

    @GetMapping("main")
    public String pizzaList(Model model) {
        model.addAttribute("pizzas", pizzaService.getAllPizzas());
        return "main";
    }

    @GetMapping("403")
    public String accessDenied(){
        return "/403";
    }





}
