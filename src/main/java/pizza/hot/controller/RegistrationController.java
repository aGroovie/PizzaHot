package pizza.hot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pizza.hot.model.*;
import pizza.hot.service.*;
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
    public String addAddress(@Validated @ModelAttribute Address address, BindingResult result, Model model, @RequestParam(value = "addressId",
            required = false) String addressId) {

        User user = (User) model.getAttribute("user");
        if (addressId != null) {
            address = addressService.getAddressById(Long.parseLong(addressId));
            model.addAttribute("address", address);
            return "redirect:/payment-input";
        }
        if (result.hasErrors()) {
            return "/address-input";
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
    public String addPayment(@Validated @ModelAttribute Payment payment, BindingResult result, Model model, @RequestParam(value = "paymentId", required = false) String paymentId) {
        User user = (User) model.getAttribute("user");
        if (paymentId != null) {
            payment = paymentService.getPaymentById(Long.parseLong(paymentId));
            model.addAttribute("payment", payment);
            return "redirect:/successPage";

        }
        if(result.hasErrors()){
            return "/payment-input";
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
        User user = (User) model.getAttribute("user");
        Address address = (Address) model.getAttribute("address");
        Payment payment = (Payment) model.getAttribute("payment");
        user = userService.findByUsername(user.getUsername());
        float total = sessionCart.getTotalPrice();


        Order order = new Order();
        CartItem itemDrink = new CartItem();
        CartItem itemPizza = new CartItem();
        for (Food food : sessionCart.getUserCart().keySet()) {
            if (food instanceof Drink) {

                itemDrink.setOrder(order);
                itemDrink.setDrinkQuantity(sessionCart.getUserCart().get(food));
                itemDrink.setDrink((Drink) food);
                order.getCartItems().add(itemDrink);
            } else {

                itemPizza.setOrder(order);
                itemPizza.setPizzaQuantity(sessionCart.getUserCart().get(food));
                food.setId(null);
                itemPizza.setModifiedPizza((ModifiedPizza) food);
                order.getCartItems().add(itemPizza);
            }
        }

        order.setAll(user, total, payment, address);

        orderService.saveOrder(order);
        sessionCart.clearCart();
        return "successPage";
    }


}
