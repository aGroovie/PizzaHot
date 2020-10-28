package pizza.hot.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pizza.hot.model.Order;
import pizza.hot.service.OrderService;
import pizza.hot.validator.DateSearchConstraint;

import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes({"orders", "total"})
public class OrderListController {

    OrderService orderService;

    @Autowired
    public OrderListController setOrderService(OrderService orderService) {
        this.orderService = orderService;
        return this;
    }


    @GetMapping("/order-list")
    public String getOrderList(Model model) {
        model.getAttribute("orders");
        model.getAttribute("total");
        return "/order-list";
    }

    @GetMapping(value = "/showOrderWithId/{id}")
    public String showUser(Model model, @PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "show-order-info";
    }



    @PostMapping(value = "/filterByDate")
    public String filterOrders(Model model, @Validated @RequestParam("date") String date) {
        List<Order> orders =  orderService.getOrdersByDate(date);
        String total = orderService.getTotalByDate(date);
        if(total.equals("null")){
         total =  "  There are no orders for this day!";
        }
        else {
            total = "Earning for this date : " + total;
        }
        model.addAttribute("orders", orders);
        model.addAttribute("total", total);
        return "redirect:/admin/order-list";
    }
}
