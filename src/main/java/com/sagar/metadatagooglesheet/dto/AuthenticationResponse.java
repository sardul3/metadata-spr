package com.sagar.metadatagooglesheet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String jwt;
    private String loggedInUser;
    private String errorMessage;

    public AuthenticationResponse(String jwt, String loggedInUser) {
        this.jwt = jwt;
        this.loggedInUser = loggedInUser;
    }

    public AuthenticationResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}
