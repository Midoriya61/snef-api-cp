package com.tinlm.snef.model;

public class StoreProductImage {
    private int spiId;
    private String imageSrc;
    private int storeProductId;

    public int getSpiId() {
        return spiId;
    }

    public void setSpiId(int spiId) {
        this.spiId = spiId;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public int getStoreProductId() {
        return storeProductId;
    }

    public void setStoreProductId(int storeProductId) {
        this.storeProductId = storeProductId;
    }

    public StoreProductImage(int fspId, String imageSrc, int spiId) {
        this.spiId = spiId;
        this.imageSrc = imageSrc;
        this.storeProductId = storeProductId;
    }

    public StoreProductImage() {
    }

}
