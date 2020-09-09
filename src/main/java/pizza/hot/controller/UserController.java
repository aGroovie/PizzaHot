package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;
import pizza.hot.model.User;
import pizza.hot.service.SecurityService;
import pizza.hot.service.UserService;
import pizza.hot.validator.UserNameConstraint;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    private UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/registration")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute  User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/registration";
        }
        userService.saveUser(user);
        return "redirect:/main";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if(error!=null){
            model.addAttribute("error", "User name or password incorrect");
        }
        if(logout!=null){
            model.addAttribute("message","Logged out successfully");
        }
        return "login";
    }

    @GetMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model){
        model.addAttribute("title","Logout");
        return "logoutSuccessfulPage";
    }


    @GetMapping(value = "/admin")
    public String admin(Model model){
        return "admin";
    }

}
