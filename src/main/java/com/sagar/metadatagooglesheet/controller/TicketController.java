package com.sagar.metadatagooglesheet.controller;

import com.sagar.metadatagooglesheet.dto.AddDeveloperRequest;
import com.sagar.metadatagooglesheet.dto.AddNoteRequest;
import com.sagar.metadatagooglesheet.dto.RemoveDeveloperFromTicketRequest;
import com.sagar.metadatagooglesheet.dto.TicketRequest;
import com.sagar.metadatagooglesheet.model.Ticket;
import com.sagar.metadatagooglesheet.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/get-tickets")
    public ResponseEntity<List<Ticket>> getTickets() {
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.getTickets());
    }

    @GetMapping("/get-ticket/{id}")
    public ResponseEntity<Optional<Ticket>> getTicket(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.getTicket(id));
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

    @PostMapping("/remove-developer")
    public ResponseEntity<Ticket> deleteDeveloper(@RequestBody RemoveDeveloperFromTicketRequest removeDeveloperFromTicketRequest) {
        Ticket ticket = ticketService.removeDeveloperFromTicket(removeDeveloperFromTicketRequest.getDevId(), removeDeveloperFromTicketRequest.getTicketId());
        return ResponseEntity.status(HttpStatus.OK).body(ticket);
    }

    @PostMapping("/add-note")
    public ResponseEntity<Ticket> addNote(@RequestBody AddNoteRequest addNoteRequest) {
        Ticket ticket = ticketService.addNotesToTicket(addNoteRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ticket);
    }
}
