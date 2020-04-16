package com.sagar.metadatagooglesheet.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AddNoteRequest {
    private String text;
    private Date createdAt;
    private String createdBy;
    private long ticketId;

    public AddNoteRequest(String text, Date createdAt, String createdBy) {
        this.text = text;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
}
