package com.sagar.metadatagooglesheet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique=true)
    private String username;
    private String password;
    @Email

    private String email;
    private Instant created;
    private boolean enabled;

    @OneToMany
    @JsonIgnore
    private List<Notification> notifications;

    private String avatar;


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", created=" + created +
                ", enabled=" + enabled +
                ", avatar='" + avatar + '\'' +
                '}';
    }



}
