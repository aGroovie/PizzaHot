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
    private Long total;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "report_id")
   private DailyReport dailyReport;


    @OneToMany(mappedBy = "order")
    Set<Drink> drinks = new HashSet<>();

    @OneToMany(mappedBy = "order")
    Set<Pizza> pizzas = new HashSet<>();


    public Set<Pizza> getPizzas() {
        return pizzas;
    }

    public Order setPizzas(Set<Pizza> pizzas) {
        this.pizzas = pizzas;
        return this;
    }

    public Set<Drink> getDrinks() {
        return drinks;
    }



    public Order setDrinks(Set<Drink> drinks) {
        this.drinks = drinks;
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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
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



    public void setAll(User user, Payment payment, Long total){
        this.user = user;
        this.payment = payment;
        this.total = total;


    }


    public User getUser() {
        return user;
    }
}
