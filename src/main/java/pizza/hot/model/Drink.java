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
    @Column(name = "drink_id")
    private Long id;

    @Column(name = "drink_price")
    private float price;

    @Column(name = "drink_name")
    private String name;

    @Column(name = "drink_size")
    private int size;

    @Column(name = "drink_description")
    private String description;


    @Column(name = "drink_picture")
    private String pictureLink;


    public Drink() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Drink)) return false;
        if (!super.equals(o)) return false;
        Drink drink = (Drink) o;
        return Objects.equals(id, drink.id) &&
                Objects.equals(description, drink.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, description);
    }
}
