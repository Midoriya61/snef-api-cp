package com.tinlm.snef.model;

import java.util.Date;

public class FlashSaleProduct {

    private int flashSaleProductId;
    private int quantity;
    private int storeProductId;
    private int flashSalesId;

    private String productName;
    private int spQuantity;
    private float price;
    private int storeId;
    private int discount;
    private Date endDate;

    private int totalQuantity;
    private String imageSrc;

    private String description;

    public FlashSaleProduct() {
    }

    public FlashSaleProduct(int flashSaleProductId, int quantity, int storeProductId) {
        this.flashSaleProductId = flashSaleProductId;
        this.quantity = quantity;
        this.storeProductId = storeProductId;
    }

    public FlashSaleProduct(int flashSaleProductId, int quantity, int storeProductId, String productName, int spQuantity, float price, int storeId, int discount, Date endDate) {
        this.flashSaleProductId = flashSaleProductId;
        this.quantity = quantity;
        this.storeProductId = storeProductId;
        this.productName = productName;
        this.spQuantity = spQuantity;
        this.price = price;
        this.storeId = storeId;
        this.discount = discount;
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public int getFlashSaleProductId() {
        return flashSaleProductId;
    }

    public void setFlashSaleProductId(int flashSaleProductId) {
        this.flashSaleProductId = flashSaleProductId;
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

    public int getFlashSalesId() {
        return flashSalesId;
    }

    public void setFlashSalesId(int flashSalesId) {
        this.flashSalesId = flashSalesId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSpQuantity() {
        return spQuantity;
    }

    public void setSpQuantity(int spQuantity) {
        this.spQuantity = spQuantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
