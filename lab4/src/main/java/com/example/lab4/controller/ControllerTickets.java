package com.example.lab4.controller;

import com.example.lab4.Domain.Show;
import com.example.lab4.Service.ServiceTickets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ControllerTickets {

    @Autowired
    private ServiceTickets serviceTickets;

    @RequestMapping("/tickets/sale")
    public String sale(@RequestBody RequestSale requestSale) {
        String msg = serviceTickets.sell(requestSale);
        return msg;
    }

    @GetMapping(path="/tickets", produces = "application/json")
    public Collection<Show> shows() {
        return serviceTickets.getShows();
    }
}
