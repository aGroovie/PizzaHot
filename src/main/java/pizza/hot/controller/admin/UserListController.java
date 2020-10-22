package pizza.hot.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pizza.hot.model.User;
import pizza.hot.service.AddressService;
import pizza.hot.service.PaymentService;
import pizza.hot.service.UserService;

@Controller
@RequestMapping("/admin")
public class UserListController {

    UserService userService;

    PaymentService paymentService;

    AddressService addressService;

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user-list")
    public String userList(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "user-list";
    }


    @GetMapping(value = "deleteUserById/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/user-list";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "User name or password incorrect");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully");
        }
        return "login";
    }

    @GetMapping(value = "showUserWithId/{id}")
    public String showUser(Model model, @PathVariable Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "show-user-info";
    }


    @PostMapping(value = "/removePaymentById")
    public String removePayment(@RequestParam("paymentId") String paymentId) {

        paymentService.deletePaymentById(Long.parseLong(paymentId));

        return "redirect:/admin/user-list";
    }

    @PostMapping(value = "/removeAddressById")
    public String removeAddress(@RequestParam("addressId") String addressId) {

        addressService.deleteAddressById(Long.parseLong(addressId));
        return "redirect:/admin/user-list";
    }
}
