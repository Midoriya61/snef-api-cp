package com.tinlm.snef.model;

import java.io.Serializable;

public class Product implements Serializable {

    private int productId;
    private String productName;
    private int categoriesId;
    private String imageSrc;

    public Product() {
    }

    public Product(int productId, String productName, String imageSrc, int categoriesId) {
        this.productId = productId;
        this.productName = productName;
        this.imageSrc = imageSrc;
        this.categoriesId = categoriesId;
    }

    public Product(String productName, int categoriesId, String imageSrc){
        this.productName = productName;
        this.categoriesId = categoriesId;
        this.imageSrc = imageSrc;
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

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }
}
