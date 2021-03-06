package pizza.hot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizza.hot.dao.OrderDao;
import pizza.hot.model.Order;
import pizza.hot.service.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String curDate = formatter.format(date);
        order.setDate(curDate);
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

    @Override
    public Float getTotalByDate(String date) {
        return orderDao.getTotalByDate(date);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderDao.getOrderById(id);
    }

    @Override
    public List<Order> getOrdersByDate(String date) {
        return orderDao.getOrdersByDate(date);
    }

    @Override
    public boolean isThereAnyOrders(String date) {
        if (orderDao.getOrdersByDate(date) != null && orderDao.getTotalByDate(date) !=null) {
            return true;
        } else {
            return false;
        }
    }

}
