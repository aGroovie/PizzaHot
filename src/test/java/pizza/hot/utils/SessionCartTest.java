package pizza.hot.utils;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import pizza.hot.model.Drink;
import pizza.hot.model.Food;
import pizza.hot.model.Pizza;
import pizza.hot.service.FoodService;
import pizza.hot.service.impl.FoodServiceImpl;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
        // idk
class SessionCartTest {


    Pizza pizza;

    Pizza secondPizza;

    Drink drink;


    Map<Food, Integer> userCart = new LinkedHashMap<>();

    @Mock
    FoodServiceImpl foodService;


    @InjectMocks
    SessionCart sessionCart;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        pizza = new Pizza();
        pizza.setId(15L);
        pizza.setName("BBQ Pizza");
        pizza.setPrice(20.00f);
        pizza.setDescription("BBQ PIZZA");
        pizza.setSize(30);
        pizza.setPictureLink("dfjhjfsdjsdf.com");

        secondPizza =  new Pizza();
        secondPizza.setId(13L);
        secondPizza.setPictureLink("gdfjdfgjdfgjs");
        secondPizza.setSize(30);
        secondPizza.setDescription("Classic Tomato Pizza");
        secondPizza.setPrice(15.00f);
        secondPizza.setName("Margarita");


        drink = new Drink();
        drink.setSize(2);
        drink.setId(3L);
        drink.setPrice(3.00f);
        drink.setName("Dr.Pepper");
        drink.setDescription("Dr.Pepper, your favorite");
        drink.setPictureLink("pfsadpdfspfsdpfsd.com");


    }

    @Test
    void addToCart_worksCorrectly() {
        sessionCart.addToCart(drink);
        sessionCart.addToCart(pizza);

        assertEquals(2, sessionCart.getUserCart().size());

    }

    @Test
    void addToCart_worksCorrect_with_sameFood(){
        sessionCart.addToCart(pizza);
        sessionCart.addToCart(secondPizza);
        sessionCart.addToCart(pizza);
        assertEquals(2,sessionCart.getUserCart().size());

    }





    @Test
    void getTotalPrice_worksCorrectly() {
        sessionCart.addToCart(drink);
        sessionCart.addToCart(pizza);
        assertEquals(23f, sessionCart.getTotalPrice());
    }

    @Test
    void setTotalPrice() {
        sessionCart.setTotalPrice(25f);
        assertEquals(25f, sessionCart.getTotalPrice());
    }

    @Test
    void countTotalPrice() {
    }

    @Test
    void removeFromCart_worksCorrectly() {
        sessionCart.addToCart(pizza);

        sessionCart.removeFromCart("BBQ Pizza", "BBQ PIZZA");

        Assert.assertEquals(0, sessionCart.getUserCart().size());
    }

    @Test
    void testAddToCart_worksCorrectly() {
        sessionCart.addToCart(pizza);

        Assert.assertEquals(1, sessionCart.getUserCart().size());
    }

    @Test
    void clearCart() {
        sessionCart.addToCart(pizza);

        sessionCart.clearCart();

        Assert.assertEquals(0, sessionCart.getUserCart().size());
    }
}