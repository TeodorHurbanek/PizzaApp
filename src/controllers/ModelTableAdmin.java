package controllers;

public class ModelTableAdmin {

    String id, name, price, firstName, street, numberStreet, phoneNumber;

    public ModelTableAdmin(String id, String name, String price, String firstName, String street, String numberStreet, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.firstName = firstName;
        this.street = street;
        this.numberStreet = numberStreet;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String surname) {
        this.firstName = surname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumberStreet() {
        return numberStreet;
    }

    public void setNumberStreet(String numberStreet) {
        this.numberStreet = numberStreet;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
