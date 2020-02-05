package com.example.lab4.Persistence;

import com.example.lab4.Domain.Seat;
import com.example.lab4.Domain.Show;
import com.example.lab4.Utils;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RepositoryShows {
    private HashMap<String, Show> shows;
    private String showsFilePath ="C:\\Users\\Simona\\Desktop\\An 3\\PPD\\lab4\\src\\main\\resources\\shows.csv";
    private String placesFilePath ="C:\\Users\\Simona\\Desktop\\An 3\\PPD\\lab4\\src\\main\\resources\\places.csv";
    public RepositoryShows() {
        this.shows = new HashMap<>();
        Utils.loadShows(showsFilePath,shows);
        Utils.loadPlaces(placesFilePath,shows);
       // Utils.generatePlaces(placesFilePath, shows.values(),100);
    }
    public Boolean exists(Show show){
        return shows.containsKey(show.getId());
    }
    public Boolean delete(Show show){
        return shows.remove(show.getId(),show);
    }
    public Boolean add(Show show){
        if(shows.containsKey(show.getId())){
            return false;
        }
        else {
            shows.put(show.getId(),show);
            return true;
        }
    }

    public synchronized void remove(String idShow, Set<Seat> seats) {
        shows.get(idShow).getSeats().removeAll(seats);
    }

    public synchronized boolean exists(String idShow, Set<Seat> seats) {
        return shows.get(idShow).getSeats().containsAll(seats);
    }
    public Show getShow(String id){
        return shows.get(id);
    }
    public Set<Seat> getSeats(String id ,Set<Seat> seats) {
        Set<Seat> newSeats =new HashSet<>();
        Set<Seat> crtSeats= shows.get(id).getSeats();
        for(Seat s:crtSeats){
            if(seats.contains(s)) {
                newSeats.add(s);
            }
        }
        return newSeats;
    }

    public Collection<Show> getShows() {
        return  shows.values();
    }
}
