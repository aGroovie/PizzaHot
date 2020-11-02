package pizza.hot;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;
import java.net.URISyntaxException;

@SpringBootApplication
 @EnableAutoConfiguration(exclude= HibernateJpaAutoConfiguration.class)
public class HotApplication {

    @Bean
    public Validator validatorFactory () {
        return new LocalValidatorFactoryBean();
    }

    public static void main(String[] args) {
        SpringApplication.run(HotApplication.class, args);}



}

