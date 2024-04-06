package com.example.sdn.data;

import java.sql.Time;
import java.util.Objects;

public class Expense {
    private Double price;
    private String type;
    private String time;

    public Expense() {
    }

    public Expense(Double price, String type, String time) {
        this.price = price;
        this.type = type;
        this.time = time;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "price=" + price +
                ", type='" + type + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
