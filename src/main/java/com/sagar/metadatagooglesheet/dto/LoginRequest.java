package com.sagar.metadatagooglesheet.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
