package com.tinlm.snef.model;

import java.io.Serializable;

public class Product implements Serializable {

    private int productid;
    private String productname;
    private String description;
    private String picture;
    private int categoriesid;

    public Product() {
    }

    public Product(int productid, String productname, String description, String picture, int categoriesid) {
        this.productid = productid;
        this.productname = productname;
        this.description = description;
        this.picture = picture;
        this.categoriesid = categoriesid;
    }

    public Product(String productname, String description, String picture, int categoriesid) {
        this.productname = productname;
        this.description = description;
        this.picture = picture;
        this.categoriesid = categoriesid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
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

    public int getCategoriesid() {
        return categoriesid;
    }

    public void setCategoriesid(int categoriesid) {
        this.categoriesid = categoriesid;
    }
}
