/*
package pizza.hot.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pizza.hot.model.Order;
import pizza.hot.service.OrderService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class OrderController {

    OrderService orderService;


    @Autowired
    public OrderController setOrderService(OrderService orderService) {
        this.orderService = orderService;
        return this;
    }


    @GetMapping
    public String getAllOrders(Model model){
        List<Order>  orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "order-list";
    }


}
*/
