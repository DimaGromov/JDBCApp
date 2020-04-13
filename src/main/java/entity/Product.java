package entity;

public class Product {
    private String name;
    private Double price;
    private Integer count;

    public Product(String name, Double price, Integer count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getCount() {
        return count;
    }
}
