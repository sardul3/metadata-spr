package com.sagar.metadatagooglesheet.controller;

import com.sagar.metadatagooglesheet.dto.AddDeveloperRequest;
import com.sagar.metadatagooglesheet.dto.TicketRequest;
import com.sagar.metadatagooglesheet.model.Developer;
import com.sagar.metadatagooglesheet.model.Project;
import com.sagar.metadatagooglesheet.model.Ticket;
import com.sagar.metadatagooglesheet.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/get-tickets")
    public ResponseEntity<List<Ticket>> getTickets() {
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.getTickets());
    }

    @PostMapping("/create-ticket")
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketRequest ticketRequest) {
        Ticket ticket = ticketService.addTicket(ticketRequest.getTitle(), ticketRequest.getDescription(), ticketRequest.getProjectId(), ticketRequest.getCreatedBy(), ticketRequest.getCreatedOn());
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @PostMapping("/add-developer")
    public ResponseEntity<Ticket> addDeveloper(@RequestBody AddDeveloperRequest addDeveloperRequest) {
        Ticket ticket = ticketService.addDeveloperToTicket(addDeveloperRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ticket);
    }
}
