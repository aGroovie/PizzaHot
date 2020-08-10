package pizza.hot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import pizza.hot.enums.Type;
import pizza.hot.model.Food;
import pizza.hot.model.Pizza;
import pizza.hot.model.Product;
import pizza.hot.utils.SessionCart;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication

public class HotApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotApplication.class, args);

       /* Product cheese = new Product();
        cheese.setType(Type.CHEESE);
        cheese.setDescription("russian cheese");
        cheese.setName("Russian Cheese");
        cheese.setPrice(1L);


         Pizza pizza = new Pizza();
         pizza.setDescription(" 4 cheeses");
         pizza.setName("Pizza 4 cheeses ");
         pizza.setPrice(20L);
         pizza.setSize(15L);
         pizza.setProducts(Collections.singletonList(cheese));
        System.out.println(pizza);
        System.out.println(pizza.getProducts());
*/



    }

}
