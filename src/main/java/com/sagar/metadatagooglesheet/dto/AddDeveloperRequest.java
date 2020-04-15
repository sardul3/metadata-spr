package com.sagar.metadatagooglesheet.dto;

import lombok.Data;

@Data
public class AddDeveloperRequest {
    private long ticketId;
    private long developerId;
}
