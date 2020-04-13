package com.sagar.metadatagooglesheet.service;

import com.sagar.metadatagooglesheet.model.Project;
import com.sagar.metadatagooglesheet.model.Ticket;
import com.sagar.metadatagooglesheet.repository.ProjectRepository;
import com.sagar.metadatagooglesheet.repository.TicketRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ProjectRepository projectRepository;

    public Ticket addTicket(String name, String desc, long projectId, String createdBy, LocalDate createdOn) {
        log.info("method called");
        log.info(name);
        log.info(desc);
        log.info(String.valueOf(projectId));

        Ticket ticket = new Ticket(name, desc, createdBy, createdOn);
        log.info(String.valueOf(ticket));


        ticket.setProject(projectRepository.findById(projectId).get());
        log.info(String.valueOf(ticket));

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }
}
