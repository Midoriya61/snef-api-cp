package com.tinlm.snef.model;

public class Customer extends Account{

    private int customerId;

    public Customer() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

}
