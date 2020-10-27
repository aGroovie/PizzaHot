package pizza.hot.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "modified_pizzas")
@EntityListeners(AuditingEntityListener.class)
public class ModifiedPizza extends Food {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizza_id")
    private Long id;

    @Column(name = "pizza_name")
    private String name;

    @Column(name = "pizza_price")
    private float price;

    @Column(name = "pizza_size")
    private int size;

    @Lob
    @Column(name = "pizza_picture")
    private String pictureLink;


    @Column(name = "pizza_desc")
    private String description;

    @OneToMany(mappedBy = "ModifiedPizza", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "pizza_base")
    Pizza pizza;


    public Pizza getPizza() {
        return pizza;
    }

    public ModifiedPizza setPizza(Pizza pizza) {
        this.pizza = pizza;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;

    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModifiedPizza)) return false;
        if (!super.equals(o)) return false;
        ModifiedPizza that = (ModifiedPizza) o;
        return Float.compare(that.price, price) == 0 &&
                size == that.size &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(pictureLink, that.pictureLink) &&
                Objects.equals(description, that.description) &&
                Objects.equals(products, that.products) &&
                Objects.equals(pizza, that.pizza);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, price, size, pictureLink, description, products, pizza);
    }
}
