package com.sagar.metadatagooglesheet.service;

import com.sagar.metadatagooglesheet.dto.AddDeveloperRequest;
import com.sagar.metadatagooglesheet.dto.AddNoteRequest;
import com.sagar.metadatagooglesheet.dto.SetStatusRequest;
import com.sagar.metadatagooglesheet.model.*;
import com.sagar.metadatagooglesheet.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ProjectRepository projectRepository;
    private final DeveloperRepository developerRepository;
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public Ticket addTicket(String name, String desc, long projectId, String createdBy, Date createdOn) {
        Ticket ticket = new Ticket(name, desc, createdBy, createdOn);
        ticket.setProject(projectRepository.findById(projectId).get());
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public Ticket addDeveloperToTicket(AddDeveloperRequest addDeveloperRequest) {
        Developer developer = developerRepository.findById(addDeveloperRequest.getDeveloperId()).get();
        Ticket ticket = ticketRepository.findById(addDeveloperRequest.getTicketId()).get();
        ticket.getDevelopers().add(developer);
        ticketRepository.save(ticket);
        return ticket;
    }

    public Ticket addNotesToTicket(AddNoteRequest addNoteRequest) {
        Note note = new Note(addNoteRequest.getText(), addNoteRequest.getCreatedAt(), addNoteRequest.getCreatedBy());
        noteRepository.save(note);
        Ticket ticket = ticketRepository.findById(addNoteRequest.getTicketId()).get();
        ticket.getNotes().add(note);
        ticketRepository.save(ticket);
        return ticket;
    }

    public Ticket removeDeveloperFromTicket(long devId, long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        ticket.getDevelopers().remove(developerRepository.findById(devId).get());
        ticketRepository.save(ticket);
        return ticket;

    }

    public Optional<Ticket> getTicket(long id) {
        return ticketRepository.findById(id);
    }

    public Ticket setStatus(SetStatusRequest setStatusRequest) {
        Ticket ticket = ticketRepository.findById(setStatusRequest.getTicketId()).get();
        ticket.setStatus(setStatusRequest.getStatus());
        ticketRepository.save(ticket);
        return ticket;
    }

    public boolean findUser(String username) {
        boolean flag;
        String user = "";
        if (userRepository.findByUsername(username).isPresent()) {
            user = userRepository.findByUsername(username).get().getUsername();
        }
        return username.equals(user);
    }

    public List<Notification> getNotification() {
        List<Notification> notifications = new ArrayList<>();
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByUsername(principal.getUsername());
        if(user.isPresent()) {
            notifications = user.get().getNotifications();
        }
        return notifications;
    }
}
