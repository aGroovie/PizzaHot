package pizza.hot.dao;

import pizza.hot.model.Address;
import pizza.hot.model.Order;

import java.util.List;

public interface OrderDao {
    void saveOrder(Order order);
    List<Order> findAll();
    void deleteOrderById(Long id);
}
