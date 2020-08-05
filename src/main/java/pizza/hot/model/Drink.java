package pizza.hot.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@Component
@Entity
@Table(name = "drinks")
@EntityListeners(AuditingEntityListener.class)
public class Drink extends Food implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "drink_price")
    private Long price;

    @Column(name = "drink_name")
    private String name;

    @Column(name = "drink_size")
    private Long size;


    public Drink() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return id.equals(drink.id) &&
                price.equals(drink.price) &&
                name.equals(drink.name) &&
                size.equals(drink.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, name, size);
    }

    @Override
    public String toString() {
        return "Drink{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}
