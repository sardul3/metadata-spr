package com.sagar.metadatagooglesheet.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class TicketRequest {
    private String title;
    private String description;
    private String createdBy;
    private Date createdOn;
    private long projectId;

}
