package com.example.lab4;

import com.example.lab4.Domain.Seat;
import com.example.lab4.Domain.Show;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class Utils {
    public static void loadShows(String fileName, HashMap<String,Show> map){
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String st;
            while ((st = br.readLine()) != null) {
                String rez[] = st.split(",");
                Date date=new SimpleDateFormat("dd/MM/yyyy").parse(rez[1]);
                Show m = new Show(rez[0], date,rez[2],rez[3]);
                map.put(m.getId(),m);
            }
           // System.out.println(map);

        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void loadPlaces(String placesFilePath, HashMap<String, Show> shows) {
        try (BufferedReader br = new BufferedReader(new FileReader(placesFilePath))) {
            String st;
            while ((st = br.readLine()) != null) {
                String rez[] = st.split(",");
                Seat seat = new Seat(Integer.parseInt(rez[1]), (float) Double.parseDouble(rez[2]));
                shows.get(rez[0]).getSeats().add(seat);
            }
             //System.out.println(shows);

        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

    }
    public static void generatePlaces(String placesFilePath, Collection<Show> shows, int nbPlaces){

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(placesFilePath), StandardCharsets.UTF_8))) {
            for (Show show:shows) {
                float price;
                for(int i=1;i<=nbPlaces;i++) {
                    if (i<=nbPlaces/3){
                        price=30;
                    }else if(i<nbPlaces/3*2){
                        price=20;
                    }else {
                        price=30;
                    }
                    writer.write(show.getId()+","+i+","+price+"\n");
                }
            }

        } catch (IOException ex) {
            System.out.println("error");
        }
    }

    public static Float sum(Set<Seat> seats) {
        Float s= Float.valueOf(0);
        for(Seat seat:seats){
            s+=seat.getPrice();
        }
        return s;
    }
}
