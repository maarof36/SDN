package com.example.sdn.fragmnts.data;

import java.util.ArrayList;

public class User {
    private String username;
    private String email;
    private String address;
    private String photo;
    private ArrayList<Expense> expenses;

    public User(String username, String email, String addres) {
    }

    public User(String firstName, String lastName, String username, String phone, String address, String photo) {

        this.username = username;
        this.email = email;
        this.address = address;
        this.photo = photo;
        this.expenses = new ArrayList<>();
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

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(ArrayList<Expense> expenses) {
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
