package com.tinlm.snef.model;

import java.io.Serializable;

public class StoreFollower implements Serializable {

    private int storeFollower;
    private int storeId;
    private int customerId;

    public StoreFollower() {
    }

    public StoreFollower(int storeFollower, int storeId, int customerId) {
        this.storeFollower = storeFollower;
        this.storeId = storeId;
        this.customerId = customerId;
    }

    public int getStoreFollower() {
        return storeFollower;
    }

    public void setStoreFollower(int storeFollower) {
        this.storeFollower = storeFollower;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
