package com.tinlm.snef.model;

import java.sql.Date;

public class Order {
    private int orderId;
    private Date dateOrder;
    private float totalPrice;
    private String confirmationCode;
    private int orderQuantity;
    private int accountId;
    private boolean status;
    private float ratingPoint;
    private int storeId;
    private String storeName;
    private String comment;

    public Order() {
    }

    public Order(int orderId, Date dateOrder, String confirmationCode, boolean status, float ratingPoint,
                 float totalPrice, int orderQuantity, int storeId, String storeName, int accountId, String comment) {
        this.orderId = orderId;
        this.dateOrder = dateOrder;
        this.confirmationCode = confirmationCode;
        this.status = status;
        this.ratingPoint = ratingPoint;
        this.accountId = accountId;
        this.totalPrice = totalPrice;
        this.orderQuantity = orderQuantity;
        this.storeId = storeId;
        this.storeName = storeName;
        this.comment = comment;

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getRatingPoint() {
        return ratingPoint;
    }

    public void setRatingPoint(float ratingPoint) {
        this.ratingPoint = ratingPoint;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
