package com.example.lab4;

import com.example.lab4.Domain.Seat;
import com.example.lab4.Domain.Show;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;

public class ClientConsole {
    public static void main(String[] args) {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        String idShow = null;
        String uriShows = "http://localhost:8080/tickets";
        RestTemplate restTemplateShows = new RestTemplate();
        ResponseEntity<Collection> result = restTemplateShows.getForEntity(uriShows, Collection.class);
        Collection<Show> shows =result.getBody();
        for(Object s:shows){
            System.out.println(s);
        }
        try {
            System.out.println(" Give Id: ");
            idShow = reader.readLine();
            System.out.println(idShow);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
