package com.example.lab4.Domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Sale {
    private Date date;
    private String idShow;
    private Integer nbTickets;
    private Set<Seat> seats;

    public Sale() {
    }

    public Sale(Date date, String idShow, Integer nbTickets) {
        this.date = date;
        this.idShow = idShow;
        this.nbTickets = nbTickets;
        seats =new HashSet<>();
    }

    public Sale(Date date, String idShow, Integer nbTickets, Set<Seat> seats) {
        this.date = date;
        this.idShow = idShow;
        this.nbTickets = nbTickets;
        this.seats = seats;
    }

    public Date getDate() {
        return date;
    }

    public String getIdShow() {
        return idShow;
    }

    public Integer getNbTickets() {
        return nbTickets;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setIdShow(String idShow) {
        this.idShow = idShow;
    }

    public void setNbTickets(Integer nbTickets) {
        this.nbTickets = nbTickets;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "date=" + date +
                ", idShow='" + idShow + '\'' +
                ", nbTickets=" + nbTickets +
                ", seats=" + seats +
                '}';
    }
}
