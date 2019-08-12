package com.tinlm.snef.model;


public class Store {
    private int storeId;
    private int accountId;
    private String storeName;
    private int locationId;
    private float ratingPoint;
    private String avatar;
    private String openHour;
    private String closeHour;
    private String phone;

    private String address;

    private double latitude;
    private double longitude;
    private double distance;


    public Store() {
    }

    public Store(int storeId, int accountId, String storeName, int locationId, String avatar, String openHour, String closeHour,double latitude, double longitude) {
        this.storeId = storeId;
        this.accountId = accountId;
        this.storeName = storeName;
        this.locationId = locationId;
        this.avatar = avatar;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Store(int storeId, int accountId, String storeName, int locationId, String avatar, String openHour, String closeHour) {
        this.storeId = storeId;
        this.accountId = accountId;
        this.storeName = storeName;
        this.locationId = locationId;
        this.avatar = avatar;
        this.openHour = openHour;
        this.closeHour = closeHour;
    }

    public Store(int storeId, int accountId, String storeName, int locationId, String avatar, String openHour, String closeHour, float ratingPoint) {
        this.storeId = storeId;
        this.accountId = accountId;
        this.storeName = storeName;
        this.locationId = locationId;
        this.avatar = avatar;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.ratingPoint = ratingPoint;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public float getRatingPoint() {
        return ratingPoint;
    }

    public void setRatingPoint(float ratingPoint) {
        this.ratingPoint = ratingPoint;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getOpenHour() {
        return openHour;
    }

    public void setOpenHour(String openHour) {
        this.openHour = openHour;
    }

    public String getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(String closeHour) {
        this.closeHour = closeHour;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
