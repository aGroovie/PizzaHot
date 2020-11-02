package pizza.hot.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pizza.hot.dao.ModPizzaDao;
import pizza.hot.enums.Type;
import pizza.hot.model.ModifiedPizza;
import pizza.hot.model.Pizza;
import pizza.hot.model.Product;
import pizza.hot.service.ProductService;

import static org.junit.jupiter.api.Assertions.*;

class ModPizzaServiceImplTest {


    @Mock
    ModPizzaDao modPizzaDao;

    @InjectMocks
    ModPizzaServiceImpl modPizzaService = new ModPizzaServiceImpl();

    Pizza pizza;

    ModifiedPizza modifiedPizza;

    Product product;

    Product secondProduct;


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
    void clonePizza_WorksCorrect() {
        ModifiedPizza clonePizza  = new ModifiedPizza();
        clonePizza.setId(15L);
        clonePizza.setSize(30);
        clonePizza.setPrice(15);
        clonePizza.setDescription("Cheese Pizza");
        clonePizza.setName("Cheese Pizza");

        ModifiedPizza comparePizza = modPizzaService.clonePizza(pizza);

        assertEquals(clonePizza.getDescription(),comparePizza.getDescription());




    }


}

