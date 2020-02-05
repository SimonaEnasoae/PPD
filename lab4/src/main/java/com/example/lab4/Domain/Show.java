package com.example.lab4.Domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Show {
    private String id;
    private Date date;
    private String title;
    private String description;

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    private Set<Seat> seats;

    public Show(String id, Date date, String title, String description) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.description = description;
        seats =new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Show{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", seats=" + seats +
                '}';
    }
}
