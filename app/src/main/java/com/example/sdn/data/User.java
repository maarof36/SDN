package com.example.sdn.data;

import java.util.ArrayList;

public class User {
    private String username;
    private String email;
    private String address;
    private String photo;
    private Expense2[] expenses;

    public User(String username, String email, String addres, String photo, ArrayList<Expense2> expenses) {
    }

    public User(String username, String phone, String address, String photo, Expense2[] expenses) {

        this.username = username;
        this.email = email;
        this.address = address;
        this.photo = photo;
        this.expenses = expenses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ArrayList<Expense2> getExpenses() {
        return expenses;
    }

    public void setExpenses(String[] expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", phone='" + email + '\'' +
                ", address='" + address + '\'' +
                ", Photo='" + photo + '\'' +
                '}';
    }
}
