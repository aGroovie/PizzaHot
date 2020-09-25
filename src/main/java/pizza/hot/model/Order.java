package pizza.hot.model;
// many tables to this one

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_date")
    private Date date;


    @Column(name = "order_total")
    private Long total;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "report_id")
   private DailyReport dailyReport;




    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
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



    public void setAll(User user, Payment payment, Long total, Date date ){
        this.user = user;
        this.payment = payment;
        this.total = total;
        this.date = date;

    }


    public User getUser() {
        return user;
    }
}
