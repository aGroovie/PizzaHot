package pizza.hot.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "base_pizzas")
@EntityListeners(AuditingEntityListener.class)
public class Pizza extends Food implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizza_id")
    private Long id;

    @Column(name = "pizza_name")
    private String name;

    @Column(name = "pizza_price")
    private float price;

    @Column(name = "pizza_size")
    private int size;

    @Lob
    @Column(name = "pizza_picture")
    private String pictureLink;


    @Column(name = "pizza_desc")
    private String description;



    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public void setPrice(float price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String getPictureLink() {
        return pictureLink;
    }

    @Override
    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }




}
