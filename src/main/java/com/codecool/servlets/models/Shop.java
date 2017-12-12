package com.codecool.servlets.models;

public class Shop {

    private int ID;
    private String location;

    public Shop(int ID, String location) {
        this.ID = ID;
        this.location = location;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
