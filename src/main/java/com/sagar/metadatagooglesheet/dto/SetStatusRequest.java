package com.sagar.metadatagooglesheet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SetStatusRequest {
    private long ticketId;
    private String status;
}
