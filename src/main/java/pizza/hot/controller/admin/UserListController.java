package pizza.hot.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pizza.hot.service.UserService;

@Controller
@RequestMapping("/admin")
public class UserListController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/user-list")
    public String userList(Model model){
        model.addAttribute("users", userService.getAllUsers());

        return "user-list";
    }


   @GetMapping(value = "deleteUserById/{id}")
    public String deletePizza(@PathVariable Long id){
        userService.deleteUserById(id);
        return "redirect:/admin/user-list";
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
}
