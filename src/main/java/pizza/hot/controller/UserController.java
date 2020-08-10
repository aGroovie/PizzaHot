package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pizza.hot.service.SecurityService;
import pizza.hot.service.UserService;

@Controller
public class UserController {

    private UserService userService;

  //  private SecurityService securityService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


}
