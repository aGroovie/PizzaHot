package pizza.hot.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Table(name = "items_in_cart")
@EntityListeners(AuditingEntityListener.class)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Pizza pizza;

    @Column(name = "pizza_quantity")
    private  int pizzaQuantity;


    @ManyToOne
    private Drink drink;

    @Column(name = "drink_quantity")
    private  int drinkQuantity;


    public Long getId() {
        return id;
    }

    public CartItem setId(Long id) {
        this.id = id;
        return this;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public CartItem setPizza(Pizza pizza) {
        this.pizza = pizza;
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
