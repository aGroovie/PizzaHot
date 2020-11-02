package pizza.hot.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pizza.hot.dao.OrderDao;
import pizza.hot.model.Order;

class OrderServiceImplTest {


    @Mock
    OrderDao orderDao;

    @InjectMocks
    OrderServiceImpl orderService = new OrderServiceImpl();


    Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        order = new Order();
        order.setId(11L);
        order.setTotal(228);

    }

    @Test
    void saveOrder_SavesCorrect() {
        orderService.saveOrder(order);

        Assert.assertEquals("02-11-2020", order.getDate());
    }



}