package com.example.sdn.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Expense2 implements Parcelable {

    private String Price;
    private String Type;
    private String Time;

    public Expense2() {
    }

    public Expense2(String price, String type, String time) {
        Price = price;
        Type = type;
        Time = time;
    }

    protected Expense2(Parcel in) {
        Price = in.readString();
        Type = in.readString();
        Time = in.readString();
    }

    public static final Creator<Expense2> CREATOR = new Creator<Expense2>() {
        @Override
        public Expense2 createFromParcel(Parcel in) {
            return new Expense2(in);
        }

        @Override
        public Expense2[] newArray(int size) {
            return new Expense2[size];
        }
    };

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    @Override
    public String toString() {
        return "com.example.sdn.data.Expense2{" +
                "Price='" + Price + '\'' +
                ", Type='" + Type + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Price);
        dest.writeString(Type);
        dest.writeString(Time);
    }
}
