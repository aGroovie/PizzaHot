package pizza.hot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizza.hot.dao.OrderDao;
import pizza.hot.model.Order;
import pizza.hot.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    OrderDao orderDao;

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void saveOrder(Order order) {
        orderDao.saveOrder(order);
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public void deleteOrderById(Long id) {
        orderDao.deleteOrderById(id);


    }
}
