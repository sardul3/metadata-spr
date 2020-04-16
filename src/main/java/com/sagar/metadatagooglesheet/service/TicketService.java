package com.sagar.metadatagooglesheet.service;

import com.sagar.metadatagooglesheet.dto.AddDeveloperRequest;
import com.sagar.metadatagooglesheet.dto.AddNoteRequest;
import com.sagar.metadatagooglesheet.model.Developer;
import com.sagar.metadatagooglesheet.model.Note;
import com.sagar.metadatagooglesheet.model.Ticket;
import com.sagar.metadatagooglesheet.repository.DeveloperRepository;
import com.sagar.metadatagooglesheet.repository.NoteRepository;
import com.sagar.metadatagooglesheet.repository.ProjectRepository;
import com.sagar.metadatagooglesheet.repository.TicketRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public Ticket addTicket(String name, String desc, long projectId, String createdBy, Date createdOn) {
        log.info(String.valueOf(createdOn));
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
        log.info(String.valueOf(developer));
        log.info(String.valueOf(ticket));
        ticket.getDevelopers().add(developer);
        log.info(String.valueOf(ticket));
        ticketRepository.save(ticket);
        return ticket;
    }

    public Ticket addNotesToTicket(AddNoteRequest addNoteRequest) {
        Note note = new Note(addNoteRequest.getText(), addNoteRequest.getCreatedAt(), addNoteRequest.getCreatedBy());
        noteRepository.save(note);
        Ticket ticket = ticketRepository.findById(addNoteRequest.getTicketId()).get();
        log.info(String.valueOf(note));
        log.info(String.valueOf(ticket));
        ticket.getNotes().add(note);
        log.info(String.valueOf(ticket));
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
}
