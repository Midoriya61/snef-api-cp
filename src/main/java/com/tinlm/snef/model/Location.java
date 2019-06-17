package com.tinlm.snef.model;

public class Location {
    private int locationId;
    private String address;
    private String district;
    private String ward;
    private String city;
    private String country;

    public Location() {
    }

    public Location(int locationId, String address, String district, String ward, String city, String country) {
        this.locationId = locationId;
        this.address = address;
        this.district = district;
        this.ward = ward;
        this.city = city;
        this.country = country;
    }

    public Location(String address, String district, String ward, String city, String country) {
        this.address = address;
        this.district = district;
        this.ward = ward;
        this.city = city;
        this.country = country;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
