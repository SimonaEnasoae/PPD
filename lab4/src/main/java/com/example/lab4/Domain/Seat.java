package com.example.lab4.Domain;

import java.util.Objects;

public class Seat {
    private int place;
    private float price;

    public Seat() {
    }

    public Seat(int place, float price) {
        this.place = place;
        this.price = price;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat1 = (Seat) o;
        return place == seat1.place;
    }

    @Override
    public int hashCode() {
        return Objects.hash(place);
    }

    @Override
    public String toString() {
        return "Seat{" +
                "place=" + place +
                ", price=" + price +
                '}';
    }
}
