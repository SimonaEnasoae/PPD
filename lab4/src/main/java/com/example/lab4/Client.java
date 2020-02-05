package com.example.lab4;

import com.example.lab4.Domain.Seat;
import com.example.lab4.Domain.Show;
import com.example.lab4.controller.RequestSale;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Client {

    public static void main(String[] args) {
        Thread t1=new SaleThread();
        Thread t2=new SaleThread();
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
class SaleThread extends Thread {
    private static final int intervalPlace =10;

    public void run(){
        String uriShows = "http://localhost:8080/tickets";
        RestTemplate restTemplateShows = new RestTemplate();
        ResponseEntity<Collection> result = restTemplateShows.getForEntity(uriShows, Collection.class);
        Collection<Show> shows =result.getBody();

        int nrShows = shows.size();
        final String uri = "http://localhost:8080/tickets/sale";

        int requests=10;

        long startTime = System.nanoTime();
        for(int i = 0; i<requests; i++) {
            RestTemplate restTemplate = new RestTemplate();
            Set<Seat> seats = new HashSet<>();
            int s1= (int)(Math.random()*intervalPlace)+1;
            int s2= (int)(Math.random()*intervalPlace)+1;
            if(s1==s2)s1+=1;
            seats.add(new Seat(s1, 20));
            seats.add(new Seat(s2, 20));
            int idSh =(int)(Math.random()*nrShows)+1;
            String idShow =String.valueOf(idSh);
            RequestSale requestSale = new RequestSale(idShow, seats);
            String resultReq = restTemplate.postForObject(uri, requestSale, String.class);
            System.out.println(idShow+" "+s1+" "+s2);
            System.out.println(resultReq);
        }
        long endTime = System.nanoTime();
        System.out.println((endTime-startTime)/1000000.0);
    }
}
