package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pizza.hot.model.User;
import pizza.hot.service.SecurityService;
import pizza.hot.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

  //  private SecurityService securityService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/registration")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute @Valid User user, BindingResult result){
        if (result.hasErrors()){
            return "/registration";
        }
        userService.saveUser(user);
        return "redirect:/main";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

}
