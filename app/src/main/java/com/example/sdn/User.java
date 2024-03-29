package com.example.sdn;

import android.os.Parcel;

import java.util.ArrayList;

public class User {
    private String username;
    private String email;
    private String address;
    private String photo;
    private ArrayList<String> favorites;

    public User() {
    }

    public User(String firstName, String lastName, String username, String phone, String address, String photo) {

        this.username = username;
        this.email = phone;
        this.address = address;
        this.photo = photo;
        this.favorites = new ArrayList<>();
    }

    public User(Parcel in) {
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return email;
    }

    public void setPhone(String phone) {
        this.email = phone;
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

    public ArrayList<String> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<String> favourits) {
        this.favorites = favourits;
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
