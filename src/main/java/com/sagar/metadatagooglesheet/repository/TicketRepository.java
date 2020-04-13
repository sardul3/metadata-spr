package com.sagar.metadatagooglesheet.repository;

import com.sagar.metadatagooglesheet.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
