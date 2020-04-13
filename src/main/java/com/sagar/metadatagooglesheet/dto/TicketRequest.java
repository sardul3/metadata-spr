package com.sagar.metadatagooglesheet.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TicketRequest {
    private String title;
    private String description;
    private String createdBy;
    private LocalDate createdOn;
    private long projectId;

}
