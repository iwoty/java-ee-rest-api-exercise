package com.codecool.models;

public class Employee {

    private int ID;
    private String firstName;
    private String lastName;
    private int shopID;

    public Employee(int ID, String firstName, String lastName, int shopID) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shopID = shopID;
    }

    public Employee(String firstName, String lastName, int shopID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.shopID = shopID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

}
