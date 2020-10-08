package pizza.hot.model;
// many tables to this one

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;


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


@ElementCollection
@CollectionTable(name = "pizzas_in_order")
@MapKeyJoinColumn(name = "pizza_id") //po order_pizza_id toje joinil
@Column(name = "pizza_quantity")
private Map<Pizza, Integer> pizzas = new HashMap();

    public Map<Pizza, Integer> getPizzas() {
        return pizzas;
    }

    public Order setPizzas(Map<Pizza, Integer> pizzas) {
        this.pizzas = pizzas;
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

    public void setAll(User user, Payment payment, float total){
        this.user = user;
        this.payment = payment;
        this.total = total;


    }


    public User getUser() {
        return user;
    }
}
