package model;

public class Product {
    private int id;
    private String productName;
    private String brand;
    private String color;
    private float price;

    public Product(int id, String productName, String brand, String color, float price) {
        this.id = id;
        this.productName = productName;
        this.brand = brand;
        this.color = color;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return productName + " - " + brand + " - " + color + " - " + price;

    }

}
