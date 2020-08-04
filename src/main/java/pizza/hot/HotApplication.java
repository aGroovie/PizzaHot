package pizza.hot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import pizza.hot.enums.Type;
import pizza.hot.model.Product;

@SpringBootApplication

public class HotApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotApplication.class, args);


    }

}
