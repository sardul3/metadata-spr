package com.sagar.metadatagooglesheet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String createdBy;
    private LocalDate createdOn;
    @OneToOne
    @JoinColumn(name="project_id")
    private Project project;

    @ManyToMany
    private List<Developer> developers;

    public Ticket(String title, String description, String createdBy, LocalDate createdOn) {
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
    }

}
