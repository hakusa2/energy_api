package com.energy.welfare.dto;

public class Approval {

    private String building;
    private String shop;
    private String apartment;
    private String detached;

    public Approval() {
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getDetached() {
        return detached;
    }

    public void setDetached(String detached) {
        this.detached = detached;
    }
}
