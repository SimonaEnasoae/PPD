package com.example.lab4.Service;

import com.example.lab4.Domain.Sale;
import com.example.lab4.Domain.Seat;
import com.example.lab4.Persistence.RepositoryShows;
import com.example.lab4.controller.RequestSale;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

public class Worker implements Callable<Sale> {
    private RepositoryShows repositoryShows;
    private RequestSale requestSale;
    public Worker(RepositoryShows repositoryShows, RequestSale requestSale) {
        this.repositoryShows=repositoryShows;
        this.requestSale =requestSale;
    }

    @Override
    public Sale call(){
        Set<Seat> seats =requestSale.getSeats();
        synchronized (repositoryShows) {
            if (repositoryShows.exists(requestSale.getIdShow(), seats)) {
                Set<Seat> removeSeats = repositoryShows.getSeats(requestSale.getIdShow(), seats);
                repositoryShows.remove(requestSale.getIdShow(), seats);
                Date date = new Date();
                return new Sale(date, requestSale.getIdShow(), seats.size(), removeSeats);
            } else {
                return null;
            }
        }

    }
}
