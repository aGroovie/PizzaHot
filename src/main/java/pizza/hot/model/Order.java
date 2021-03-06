package pizza.hot.model;
// many tables to this one

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_date")
    private String date;


    @Column(name = "order_total")
    private float total;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    private Set<CartItem> cartItems = new HashSet<>();

    public Set<CartItem> getCartItems() {
        return cartItems;
    }


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_payment")
    private Payment payment;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_address")
    private Address address;


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Order setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
        return this;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order() {
    }

    public void setAll(User user, float total, Payment payment, Address address) {
        this.user = user;
        this.total = total;
        this.payment = payment;
        this.address = address;
    }


    public User getUser() {
        return user;
    }
}
