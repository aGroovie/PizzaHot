package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pizza.hot.model.Drink;
import pizza.hot.service.DrinkService;

import java.util.List;

@Controller
public class DrinkController {

    DrinkService drinkService;

    @Autowired

    public void setDrinkService(DrinkService drinkService) {
        this.drinkService = drinkService;
    }



    @GetMapping("/drink-selection")
    public String getAllDrinks(Model model){
        model.addAttribute("drinks" ,drinkService.findAll());
        return "/drink-selection";
    }


}
