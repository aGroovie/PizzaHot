package pizza.hot.utils;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pizza.hot.model.Food;
import pizza.hot.model.Product;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.Map;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "cart")
public class Cart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long Id;

    @Column(name = "cart_foodname") // or use food object?
    private String foodname;

    @Column(name = "cart_quanity")
    private Long quanity;



   // private Map<Product, Integer> usercart = new HashMap<>();



    @Column(name = "cart_foodsize")
    private Long foodsize;

    @Column(name = "cart_totalprice")
    private Long totalprice;




}
