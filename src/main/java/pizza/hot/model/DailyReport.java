package pizza.hot.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@Component
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "daily_report")
public class DailyReport implements Serializable {


    @Id
    @Column(name ="report_id")
    private Long id;

    @Column(name = "report_earnings")
    private  Long earnings;

    @Column(name ="report_date")
    private String date;



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
    public DailyReport(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyReport that = (DailyReport) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(earnings, that.earnings) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, earnings, date);
    }

    @Override
    public String toString() {
        return "DailyReport{" +
                "id=" + id +
                ", earnings=" + earnings +
                ", date='" + date + '\'' +
                '}';
    }
}
