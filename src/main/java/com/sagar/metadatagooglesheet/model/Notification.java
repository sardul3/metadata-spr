package com.sagar.metadatagooglesheet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String message;
    private boolean seen;
    private Instant createdAt;

    public Notification(String message, boolean seen) {
        this.message = message;
        this.seen = seen;
        this.createdAt = Instant.now();

    }
}
