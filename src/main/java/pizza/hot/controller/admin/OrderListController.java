package pizza.hot.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pizza.hot.model.Order;
import pizza.hot.service.OrderService;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Pattern;
import java.util.List;

@Controller
@RequestMapping("admin")
@SessionAttributes({"orders", "total"})
@Validated
public class OrderListController {

    OrderService orderService;

    @Autowired
    public OrderListController setOrderService(OrderService orderService) {
        this.orderService = orderService;
        return this;
    }


    @GetMapping("order-list")
    public String getOrderList(Model model) {
        model.getAttribute("orders");
        model.getAttribute("total");
        return "order-list";
    }

    @GetMapping(value = "showOrderWithId/{id}")
    public String showUser(Model model, @PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "show-order-info";
    }


    @PostMapping(value = "filterByDate")
    public String filterOrders(Model model, @Pattern(message = "please enter date in correct format",regexp = "(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-((19|20)\\d\\d)")
    @RequestParam("date") String date) throws ConstraintViolationException {


        List<Order> orders = orderService.getOrdersByDate(date);
        Float totalSum = orderService.getTotalByDate(date);
        String total;

        if(totalSum == null){
            total = "there are no orders for such date!";
        }
        else {
            total = "Total earnings for this date " + totalSum;
        }
        model.addAttribute("orders", orders);
        model.addAttribute("total", total);
        return "redirect:admin/order-list";
    }
}
