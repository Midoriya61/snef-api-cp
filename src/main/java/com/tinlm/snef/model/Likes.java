package com.tinlm.snef.model;

import java.io.Serializable;

public class Likes implements Serializable {
    private int likeId;
    private int customerId;
    private int storeProductId;

    public Likes() {
    }

    public Likes(int likeId, int customerId, int storeProductId) {
        this.likeId = likeId;
        this.customerId = customerId;
        this.storeProductId = storeProductId;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getStoreProductId() {
        return storeProductId;
    }

    public void setStoreProductId(int storeProductId) {
        this.storeProductId = storeProductId;
    }
}
