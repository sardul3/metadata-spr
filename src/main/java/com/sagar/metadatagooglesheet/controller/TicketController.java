package com.sagar.metadatagooglesheet.controller;

import com.sagar.metadatagooglesheet.dto.*;
import com.sagar.metadatagooglesheet.model.Developer;
import com.sagar.metadatagooglesheet.model.Notification;
import com.sagar.metadatagooglesheet.model.Ticket;
import com.sagar.metadatagooglesheet.repository.NotificationRepository;
import com.sagar.metadatagooglesheet.repository.UserRepository;
import com.sagar.metadatagooglesheet.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin
@Slf4j
public class TicketController {
    private final TicketService ticketService;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

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
//    @MessageMapping("/add.developer")
//    @SendTo("/topic/developers")
    public ResponseEntity<Ticket> addDeveloper(@RequestBody AddDeveloperRequest addDeveloperRequest) {
        Ticket ticket = ticketService.addDeveloperToTicket(addDeveloperRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ticket);
    }

    @PostMapping("/remove-developer")
//    @MessageMapping("/remove.developer")
//    @SendTo("/topic/developers")
    public ResponseEntity<Ticket> deleteDeveloper(@RequestBody RemoveDeveloperFromTicketRequest removeDeveloperFromTicketRequest) {
        Ticket ticket = ticketService.removeDeveloperFromTicket(removeDeveloperFromTicketRequest.getDevId(), removeDeveloperFromTicketRequest.getTicketId());
        return ResponseEntity.status(HttpStatus.OK).body(ticket);
    }

    @PostMapping("/add-note")
//    @MessageMapping("/add.note")
//    @SendTo("/topic/notes")
    public ResponseEntity<Ticket> addNote(@RequestBody AddNoteRequest addNoteRequest) {
        Ticket ticket = ticketService.addNotesToTicket(addNoteRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ticket);
    }

    @PostMapping("/set-status")
    public ResponseEntity<Ticket> setStatus(@RequestBody SetStatusRequest setStatusRequest) {
        Ticket ticket = ticketService.setStatus(setStatusRequest);
        log.info(String.valueOf(ticket));
        return ResponseEntity.status(HttpStatus.OK).body(ticket);
    }

    @PostMapping("/check-username")
    public ResponseEntity<CheckUsername> checkUsername(@RequestBody CheckUsernameRequest checkUsernameRequest) {

        CheckUsername checkUsername;
        log.info(String.valueOf(ticketService.findUser(checkUsernameRequest.getUsername())));

        if (!userRepository.findByUsername(checkUsernameRequest.getUsername()).isPresent()) {
            checkUsername = new CheckUsername(checkUsernameRequest.getUsername(), "valid");
        } else {
            checkUsername = new CheckUsername(checkUsernameRequest.getUsername(), "duplicate");
        }
        return ResponseEntity.status(HttpStatus.OK).body(checkUsername);
    }

    @GetMapping("/get-notifications")
    public ResponseEntity<List<Notification>> getNotifications() {
        List<Notification> notifications = ticketService.getNotification();
        return ResponseEntity.status(HttpStatus.OK).body(notifications);
    }

    @PostMapping("read-notification")
    public ResponseEntity<Notification> readNotification(@RequestBody RemoveNotificationRequest removeNotificationRequest) {
        Notification notification = notificationRepository.findById(removeNotificationRequest.getId()).get();
        notification.setSeen(true);
        notificationRepository.save(notification);
        return ResponseEntity.status(HttpStatus.OK).body(notification);
    }
}
