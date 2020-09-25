package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pizza.hot.model.Address;
import pizza.hot.model.Order;
import pizza.hot.model.Payment;
import pizza.hot.model.User;
import pizza.hot.service.AddressService;
import pizza.hot.service.OrderService;
import pizza.hot.service.PaymentService;
import pizza.hot.service.UserService;
import pizza.hot.utils.SessionCart;

@Controller
@SessionAttributes({"address", "payment", "user"})
public class UserController {

    private UserService userService;

    private AddressService addressService;

    private PaymentService paymentService;

    private SessionCart sessionCart;

    private OrderService orderService;

    @Autowired
    public UserController setOrderService(OrderService orderService) {
        this.orderService = orderService;
        return this;
    }

    @Autowired
    public void setSessionCart(SessionCart sessionCart) {
        this.sessionCart = sessionCart;
    }

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }


    @GetMapping("/registration")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/registration";
        }
        userService.saveUser(user);
        return "redirect:/main";
    }


    @GetMapping("/address-input")
    public String getAddress(Model model) {
        model.addAttribute("address", new Address());
        return "/address-input";
    }

    @PostMapping("/address-input")
    public String addAddress(@ModelAttribute Address address, Model model) {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        address.setUser(user);
        user.getAddresses().add(address);
        addressService.saveAddress(address);

        return "redirect:/payment-input";
    }


    @GetMapping("/payment-input")
    public String getPayment(Model model) {
        model.addAttribute("payment", new Payment());
        return "/payment-input";
    }

    @PostMapping("/payment-input")
    public String addPayment(@ModelAttribute Payment payment, Model model) {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userService.findByUsername(username);
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        payment.setDate(date);
        payment.setUser(user);
        user.getPayments().add(payment);
        paymentService.savePayment(payment);
        model.addAttribute("payment");

        return "redirect:/successPage";
    }


    @GetMapping("/successPage")
    public String successPage(Model model) {
        Payment payment = (Payment) model.getAttribute("payment");

        User user = (User) model.getAttribute("user");
        user = userService.findByUsername(user.getUsername());
        long total = sessionCart.getTotalPrice();
        Order order = new Order();
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        order.setAll(user, payment, total, date);

        orderService.saveOrder(order);

        return "successPage";
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


}
