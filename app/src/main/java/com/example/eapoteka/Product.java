package com.example.eapoteka;

public class Product {
    private Double price;
    private String title;
    private String description;
    private int imageResId;

    public Product(Double price, String title, String description, int imageResId) {
        this.price = price;
        this.title = title != null ? title : "";
        this.description = description != null ? description : "";
        this.imageResId = imageResId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
