package pizza.hot.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "daily_report")
public class DailyReport implements Serializable {


    @Id
    @Column(name ="report_id")
    private Long id;

    @Column(name = "report_earnings")
    private  Long earnings;

    @Column(name = "report_date", nullable = false)
    private String date;

   @OneToMany(mappedBy = "dailyReport")
    private Set<Order> orders = new HashSet<>();


    public DailyReport(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEarnings() {
        return earnings;
    }

    public void setEarnings(Long earnings) {
        this.earnings = earnings;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, earnings, date);
    }
}
