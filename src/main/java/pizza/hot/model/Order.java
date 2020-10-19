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

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "report_id")
    private DailyReport dailyReport;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")

    private Set<CartItem> cartItems = new HashSet<>();

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public Order setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DailyReport getDailyReport() {
        return dailyReport;
    }

    public void setDailyReport(DailyReport dailyReport) {
        this.dailyReport = dailyReport;
    }

    public Order() {
    }

    public void setAll(User user, Payment payment, float total) {
        this.user = user;
        this.payment = payment;
        this.total = total;


    }


    public User getUser() {
        return user;
    }
}
