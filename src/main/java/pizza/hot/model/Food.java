package pizza.hot.model;

public class Food {

    private Long price;

    private String name;

    private Long size;



    public String getName() {
        return name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Food (){

    }
}
