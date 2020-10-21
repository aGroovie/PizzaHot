package pizza.hot.service;

import pizza.hot.model.Order;

import java.util.List;

public interface OrderService {
    void saveOrder(Order order);

    List<Order> findAll();

    void deleteOrderById(Long id);

    String getTotalByDate(String date);

    Order getOrderById(Long id);

    public List<Order> getOrdersByDate(String date);
}
