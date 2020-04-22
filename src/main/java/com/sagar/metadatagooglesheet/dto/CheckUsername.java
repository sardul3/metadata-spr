package com.sagar.metadatagooglesheet.dto;

import com.sagar.metadatagooglesheet.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Consumer;

@Data
@AllArgsConstructor
public class CheckUsername {
    private String username;
    private String message;
}
