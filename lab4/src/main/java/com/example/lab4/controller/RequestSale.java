package com.example.lab4.controller;

import com.example.lab4.Domain.Seat;

import java.util.Set;

public class RequestSale {
    private String  idShow;

    private Set<Seat> seats;

    public RequestSale() {
    }

    public RequestSale(String idShow, Set<Seat> seats) {
        this.idShow = idShow;
        this.seats = seats;
    }

    public String getIdShow() {
        return idShow;
    }

    public void setIdShow(String idShow) {
        this.idShow = idShow;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "RequestSale{" +
                "idShow='" + idShow + '\'' +
                ", seats=" + seats +
                '}';
    }
}
