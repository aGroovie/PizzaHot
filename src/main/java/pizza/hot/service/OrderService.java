package pizza.hot.service;

import pizza.hot.utils.Order;

import java.util.List;

public interface OrderService {
    void saveOrder(Order order);
    List<Order> findAll();
    void deleteOrderById(Long id);
}
