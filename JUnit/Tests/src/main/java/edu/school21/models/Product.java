package edu.school21.models;

import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private Double price;
    public Product(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Product info = (Product) obj;
        return (info.getPrice().equals(price) && info.getName().equals(info.name)
                && info.getId().equals(info.id));
    }

    @Override
    public String toString() {
        return "{id = " + id + "; name = " + name + "; price = " + price + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
