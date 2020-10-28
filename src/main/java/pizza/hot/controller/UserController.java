package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pizza.hot.model.Address;
import pizza.hot.model.Payment;
import pizza.hot.model.User;
import pizza.hot.service.AddressService;
import pizza.hot.service.OrderService;
import pizza.hot.service.PaymentService;
import pizza.hot.service.UserService;

@Controller
public class UserController {

    UserService userService;


    OrderService orderService;

    AddressService addressService;

    PaymentService paymentService;

    @Autowired
    public UserController setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
        return this;
    }

    @Autowired
    public UserController setAddressService(AddressService addressService) {
        this.addressService = addressService;
        return this;
    }

    @Autowired
    public UserController setOrderService(OrderService orderService) {
        this.orderService = orderService;
        return this;
    }

    @Autowired
    public UserController setUserService(UserService userService) {
        this.userService = userService;
        return this;
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

    @GetMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }


    @GetMapping(value = "/admin")
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping(value = "/error403")
    public String errorPage() {
        return "403";
    }


    @GetMapping(value = "/myProfile")
    public String showProfile(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userService.getUsernameFromSession(principal);

        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "/myProfile";

    }

    @PostMapping(value = "/deleteAddressById")
    public String deleteAddressById(@RequestParam(value = "addressId", required = false) String addressId) {
        Address address = addressService.getAddressById(Long.parseLong(addressId));
        address.setUser(null);
        addressService.saveAddress(address);

        return "redirect:/myProfile";
    }

    @PostMapping(value = "/deletePaymentById")
    public String deletePaymentById(@RequestParam(value = "paymentId", required = false) String paymentId) {
        Payment payment = paymentService.getPaymentById(Long.parseLong(paymentId));
        payment.setUser(null);
        paymentService.savePayment(payment);

        return "redirect:/myProfile";
    }

}
