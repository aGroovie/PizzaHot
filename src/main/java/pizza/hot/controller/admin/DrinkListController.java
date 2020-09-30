package pizza.hot.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pizza.hot.model.Drink;
import pizza.hot.service.DrinkService;

@Controller
@RequestMapping("/admin")

public class DrinkListController {

    DrinkService drinkService;

    @Autowired
    public void setDrinkService(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @PostMapping(value = "/saveDrink")
    public String save(@Validated @ModelAttribute("drink") Drink drink, BindingResult result, Model model){
        if(result.hasErrors()){
            return "drink-list";
        }
        model.addAttribute("drink", drink);
        drinkService.saveDrink(drink);
        return "redirect:/admin/drink-list";
    }

    @GetMapping("/drink-list")
    public String getAllDrinks(Model model){
        model.addAttribute("drinks", drinkService.findAll());

        return "drink-list";
    }

    @GetMapping(value = "/deleteDrinkById{id}")
    public String deleteDrinkById(@PathVariable Long id){
        drinkService.deleteDrinkById(id);
        return "redirect:/admin/drink-list";
    }


    @PostMapping(value = "updateDrinkById/{id}")
    public String updateDrink(@ModelAttribute("drink") Drink drink, @PathVariable Long id) {
        drinkService.updateDrinkById(id, drink);
        return "redirect:/admin/drink-list";
    }

    @GetMapping(value = {"updateDrinkById/{id}", "/updateDrinkById/", "updateDrink"})
    public String getUpdateDrink(@PathVariable("id") Long id, Model model, @ModelAttribute("drink") Drink drink) {

        model.addAttribute("drink",drinkService.getDrinkById(id) );


        return "/edit-drink";


    }

}
