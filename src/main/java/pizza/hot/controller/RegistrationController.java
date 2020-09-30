package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pizza.hot.model.*;
import pizza.hot.service.AddressService;
import pizza.hot.service.OrderService;
import pizza.hot.service.PaymentService;
import pizza.hot.service.UserService;
import pizza.hot.utils.SessionCart;

@Controller
@SessionAttributes({"address", "payment", "user"})
public class RegistrationController {

    private UserService userService;

    private AddressService addressService;

    private PaymentService paymentService;

    private SessionCart sessionCart;

    private OrderService orderService;

    @Autowired
    public RegistrationController setOrderService(OrderService orderService) {
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
    public String register(@Validated @ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/registration";
        }
        userService.saveUser(user);
        return "redirect:/main";
    }


    @GetMapping("/address-input")
    public String getAddress(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userService.getUsernameFromSession(principal);

        User user = userService.findByUsername(username);
        model.addAttribute("address", new Address());
        model.addAttribute("user", user);
        return "/address-input";
    }

    @PostMapping("/address-input")
    public String addAddress(@ModelAttribute Address address, Model model, @RequestParam(value = "addressId",
            required = false) String addressId, BindingResult result) {

        User user = (User) model.getAttribute("user");
        if (addressId != null) {
            address = addressService.getAddressById(Long.parseLong(addressId));

        }
        address.setUser(user);
        user.getAddresses().add(address);
        addressService.saveAddress(address);
        model.addAttribute("user", user);
        return "redirect:/payment-input";
    }


    @GetMapping("/payment-input")
    public String getPayment(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userService.getUsernameFromSession(principal);

        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("payment", new Payment());
        return "/payment-input";
    }

    @PostMapping("/payment-input")
    public String addPayment(@ModelAttribute Payment payment, Model model, @RequestParam(value = "paymentId", required = false) String paymentId) {
        User user = (User) model.getAttribute("user"); //logika naryshena ono sohranyaet daje kogda ne nado po syti no ne poly4alos inache sdelat s if else

        if (paymentId != null) {
            payment = paymentService.getPaymentById(Long.parseLong(paymentId));
            model.addAttribute("payment", payment);
            return "redirect:/successPage";

        }
        user.getPayments().add(payment);
        payment.setUser(user);
        paymentService.savePayment(payment);
        model.addAttribute("user", user);
        model.addAttribute("payment", payment);

        return "redirect:/successPage";
    }


    @GetMapping("/successPage")
    public String successPage(Model model) {
        Payment payment = (Payment) model.getAttribute("payment");

        User user = (User) model.getAttribute("user");
        user = userService.findByUsername(user.getUsername());
        long total = sessionCart.getTotalPrice();


        Order order = new Order();

        for (Food food : sessionCart.getUserCart().keySet()) {
            if (food instanceof Drink) {

                order.getDrinks().add((Drink) food);
            } else {
                order.getPizzas().add((Pizza) food);
            }
        }
        order.setAll(user, payment, total);

        orderService.saveOrder(order);
        sessionCart.clearCart();
        return "successPage";
    }


}