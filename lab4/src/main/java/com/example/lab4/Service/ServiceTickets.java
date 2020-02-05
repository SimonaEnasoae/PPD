package com.example.lab4.Service;

import com.example.lab4.Domain.Sale;
import com.example.lab4.Domain.Seat;
import com.example.lab4.Domain.Show;
import com.example.lab4.Persistence.RepositoryShows;
import com.example.lab4.Utils;
import com.example.lab4.controller.RequestSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ServiceTickets {
    @Autowired
    private RepositoryShows repositoryShows;
    private List<Future<Sale>> sales;
    private ExecutorService executor;
    private final static int nrTHreads =4;
    private static int nrIteratii=2;
    private String salesShowsFilePath ="C:\\Users\\Simona\\Desktop\\An 3\\PPD\\lab4\\src\\main\\resources\\salesShows.csv";
    private String salesFilePath ="C:\\Users\\Simona\\Desktop\\An 3\\PPD\\lab4\\src\\main\\resources\\sales.csv";
    private String soldFilePath ="C:\\Users\\Simona\\Desktop\\An 3\\PPD\\lab4\\src\\main\\resources\\sold.csv";

    private AtomicInteger crtIteration = new AtomicInteger(0);
    public ServiceTickets() {
        sales =new ArrayList<>();
        executor = Executors.newFixedThreadPool(nrTHreads);
    }

    public String sell(RequestSale requestSale) {
        Callable<Sale> worker=new Worker(repositoryShows,requestSale);
        Future<Sale> sale =executor.submit(worker);
        try {
            if(sale.get()!=null){
                sales.add(sale);
                crtIteration.getAndIncrement();
                synchronized (sales) {
                    if (crtIteration.get() == nrIteratii) {
                        makeRaport();
                        crtIteration.set(0);

                    }
                }
                return "OK";
            }
           else{
               return "Not Ok";
            }
        } catch (InterruptedException | ExecutionException e) {
            return "Not Ok";
        }

    }

    private void makeRaport() {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(salesFilePath), StandardCharsets.UTF_8))) {
            for(Future<Sale> sale:sales){
                try {
                    writer.write(sale.get()+"\n");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException ex) {
            System.out.println("error");
        }
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(salesShowsFilePath), StandardCharsets.UTF_8))) {
         try (Writer writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(soldFilePath), StandardCharsets.UTF_8))) {
             for (Show s : repositoryShows.getShows()) {
                String seats = "";
                 Float sold= Float.valueOf(0);
                 for (Future<Sale> sale : sales) {
                     try {
                         if (sale.get().getIdShow().equals(s.getId())) {
                             for(Seat seat:sale.get().getSeats()){
                                 seats=seats+", "+seat.getPlace();
                             }
                             sold+= Utils.sum(sale.get().getSeats());
                         }
                     } catch (InterruptedException | ExecutionException e) {
                         e.printStackTrace();
                     }
                 }
                 writer.write(s.getDate() + " , " + s.getId()  + seats + "\n");
                 writer2.write( s.getId() + " , " + sold + "\n");
             }
         }
        } catch (IOException ex) {
            System.out.println("error");
        }

    }

    public Collection<Show> getShows() {
        return repositoryShows.getShows();
    }
}
