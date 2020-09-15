package pizza.hot.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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
    private Long price;

    @Column(name = "pizza_size")
    private Long size;


    @Lob
    @Column(name = "pizza_picture")
    private byte[] image;


    @Column(name = "pizza_desc")
    private String description;

    @Transient
    private CommonsMultipartFile fileData;

    @OneToMany(mappedBy = "pizza", fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();


    public Pizza() {

    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
