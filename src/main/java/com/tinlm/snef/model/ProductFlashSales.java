package com.tinlm.snef.model;

import java.io.Serializable;

public class ProductFlashSales implements Serializable {
    private int productFlashSalesId;

    private int discount;

    private String startDate;

    private String endDate;

    private int quantity;

    private int storeProductId;

    private int storeId;

    private float price;

    private String storeProductName;

    private String image;

    public ProductFlashSales() {
    }

    public ProductFlashSales(int productFlashSalesId, int discount, String startDate, String endDate, int quantity, int storeProductId, int storeId, float price, String storeProductName, String image) {
        this.productFlashSalesId = productFlashSalesId;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
        this.storeProductId = storeProductId;
        this.storeId = storeId;
        this.price = price;
        this.storeProductName = storeProductName;
        this.image = image;
    }

    public ProductFlashSales(int productFlashSalesId, int discount, String startDate, String endDate, int quantity, int storeProductId, int storeId) {
        this.productFlashSalesId = productFlashSalesId;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
        this.storeProductId = storeProductId;
        this.storeId = storeId;
    }

    public int getProductFlashSalesId() {
        return productFlashSalesId;
    }

    public void setProductFlashSalesId(int productFlashSalesId) {
        this.productFlashSalesId = productFlashSalesId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStoreProductId() {
        return storeProductId;
    }

    public void setStoreProductId(int storeProductId) {
        this.storeProductId = storeProductId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStoreProductName() {
        return storeProductName;
    }

    public void setStoreProductName(String storeProductName) {
        this.storeProductName = storeProductName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
