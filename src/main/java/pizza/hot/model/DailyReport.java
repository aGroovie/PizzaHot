package pizza.hot.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "report_date", nullable = false)
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyReport that = (DailyReport) o;
        return id.equals(that.id) &&
                earnings.equals(that.earnings) &&
                date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, earnings, date);
    }
}
