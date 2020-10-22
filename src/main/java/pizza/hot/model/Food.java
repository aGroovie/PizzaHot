package pizza.hot.model;

import java.util.Objects;

public abstract class Food {
    private Long id;

    private float price;

    private String name;

    private String description;

    private String pictureLink;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public Food() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food)) return false;
        Food food = (Food) o;
        return Objects.equals(id, food.id) &&
                Objects.equals(description, food.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}
