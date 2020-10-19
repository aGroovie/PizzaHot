package pizza.hot.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;
import pizza.hot.enums.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Component
@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
public class Product  extends Food implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;


    @Column(name = "product_price")
    private float price;

    @Column(name = "product_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "product_description")
    private String description;

   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "pizza_id")
   private ModifiedPizza ModifiedPizza;


    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public pizza.hot.model.ModifiedPizza getModifiedPizza() {
        return ModifiedPizza;
    }

    public Product setModifiedPizza(pizza.hot.model.ModifiedPizza modifiedPizza) {
        ModifiedPizza = modifiedPizza;
        return this;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, price, name, type, description);
    }


}
