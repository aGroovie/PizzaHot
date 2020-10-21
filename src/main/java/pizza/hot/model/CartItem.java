package pizza.hot.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Table(name = "items_in_cart")
@EntityListeners(AuditingEntityListener.class)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "pizza_in_order")
    private ModifiedPizza modifiedPizza;

    @Column(name = "pizza_quantity")
    private int pizzaQuantity;


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "drink_in_order")
    private Drink drink;

    @Column(name = "drink_quantity")
    private int drinkQuantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    public Order getOrder() {
        return order;
    }

    public CartItem setOrder(Order order) {
        this.order = order;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CartItem setId(Long id) {
        this.id = id;
        return this;
    }

    public ModifiedPizza getModifiedPizza() {
        return modifiedPizza;
    }

    public CartItem setModifiedPizza(ModifiedPizza modifiedPizza) {
        this.modifiedPizza = modifiedPizza;
        return this;
    }

    public int getPizzaQuantity() {
        return pizzaQuantity;
    }

    public CartItem setPizzaQuantity(int pizzaQuantity) {
        this.pizzaQuantity = pizzaQuantity;
        return this;
    }

    public Drink getDrink() {
        return drink;
    }

    public CartItem setDrink(Drink drink) {
        this.drink = drink;
        return this;
    }

    public int getDrinkQuantity() {
        return drinkQuantity;
    }

    public CartItem setDrinkQuantity(int drinkQuantity) {
        this.drinkQuantity = drinkQuantity;
        return this;
    }
}
