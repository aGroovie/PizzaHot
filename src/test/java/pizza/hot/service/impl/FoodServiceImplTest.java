package pizza.hot.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pizza.hot.dao.DrinkDao;
import pizza.hot.dao.PizzaDao;
import pizza.hot.dao.ProductDao;
import pizza.hot.dao.impl.DrinkDaoImpl;
import pizza.hot.dao.impl.PizzaDaoImpl;
import pizza.hot.dao.impl.ProductDaoImpl;
import pizza.hot.enums.Type;
import pizza.hot.model.ModifiedPizza;
import pizza.hot.model.Pizza;
import pizza.hot.model.Product;
import pizza.hot.service.DrinkService;
import pizza.hot.service.FoodService;
import pizza.hot.service.PizzaService;
import pizza.hot.service.ProductService;

import javax.validation.constraints.AssertTrue;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FoodServiceImplTest {


    @Mock
    private PizzaService pizzaService;

    @Mock
    private DrinkService drinkService;

    @Mock
    private ProductService productService;


    @InjectMocks
    FoodServiceImpl foodServiceImpl = new FoodServiceImpl();


    Pizza pizza;

    Product product;

    Product secondProduct;

    ModifiedPizza modifiedPizza;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        pizza = new Pizza();
        pizza.setSize(30);
        pizza.setDescription("Cheese Pizza");
        pizza.setPrice(15);
        pizza.setId(15L);
        pizza.setName("Cheese Pizza");

        modifiedPizza = new ModifiedPizza();
        modifiedPizza.setSize(15);
        modifiedPizza.setDescription("Cheese Pizza");
        modifiedPizza.setPrice(15f);
        modifiedPizza.setId(25L);
        modifiedPizza.setName("Cheese Pizza");


        product = new Product();
        product.setId(15L);
        product.setType(Type.CHEESE);
        product.setName("Mocarella");
        product.setPrice(2.00f);

        secondProduct = new Product();
        secondProduct.setId(22L);
        secondProduct.setName("Spicy Meat");
        secondProduct.setPrice(4.00f);
        secondProduct.setType(Type.PROTEIN);

    }



    @Test
    void pizzaPriceSetter_SetsPrize_Correctly() {
        pizza.setSize(15);
        pizza.setPrice(15f);

        foodServiceImpl.pizzaPriceSetter(30, pizza);

        assertEquals(20, pizza.getPrice());
    }

    @Test
    void addProductsToPizza_addsProducts() {
        Mockito.when(productService.getProductById(15L)).thenReturn(product);
        foodServiceImpl.addProductsToPizza(Arrays.asList(15L), modifiedPizza);
        assertEquals(1, modifiedPizza.getProducts().size());

    }

}