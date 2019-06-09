package com.tinlm.snef.model;

import java.io.Serializable;

public class Product implements Serializable {

    private int productId;
    private String productName;
    private String description;
    private String picture;
    private int categoriesId;

    public Product() {
    }

    public Product(String productName, String description, String picture, int categoriesId) {
        this.productName = productName;
        this.description = description;
        this.picture = picture;
        this.categoriesId = categoriesId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public Product(int productId, String productName, String description, String picture, int categoriesId) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.picture = picture;
        this.categoriesId = categoriesId;
    }
}
