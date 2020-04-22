package com.sagar.metadatagooglesheet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String title;
    private String avatar;

    @OneToOne
    private Project project;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="developer_tickets",
            joinColumns = @JoinColumn(name="developer_id"),
            inverseJoinColumns = @JoinColumn(name="ticket_id")
    )
    private List<Ticket> tickets;

    @OneToMany
    private List<Notification> notifications;

    public Developer(String name, String email, String title, String avatar) {
        this.name = name;
        this.email = email;
        this.title = title;
        this.avatar = avatar;
    }

}
