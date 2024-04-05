package com.example.sdn;

import java.sql.Time;
import java.util.Objects;

public class expenses {
    private Double price;
    private String type;
    private Time time;

    public expenses(Double price, String type, Time time) {
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public expenses() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        expenses expenses = (expenses) o;
        return Objects.equals(price, expenses.price) && Objects.equals(type, expenses.type) && Objects.equals(time, expenses.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, type, time);
    }
}
