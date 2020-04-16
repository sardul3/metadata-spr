package com.sagar.metadatagooglesheet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoveDeveloperFromTicketRequest {
    private long devId;
    private long ticketId;
}
