package pizza.hot.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import pizza.hot.model.Pizza;
import pizza.hot.service.FoodService;

import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.*;

class FoodServiceImplTest {

    @Mock
    FoodServiceImpl foodServiceImpl;


    @InjectMocks
    FoodService foodService;

    @BeforeEach
    void setUp() {
        Pizza pizza = new Pizza();
        pizza.setSize(30);
        pizza.setDescription("Cheese Pizza");
        pizza.setPrice(15);
        pizza.setId(15L);
        pizza.setName("Cheese Pizza");

    }


}