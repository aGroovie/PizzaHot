package pizza.hot.enums;

public enum Type {


    PROTEIN("PROTEIN"),
    VEGGIES("VEGGIES"),
    SAUCE("SAUCE"),
    CHEESE("CHEESE");

    private final String type;


    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}