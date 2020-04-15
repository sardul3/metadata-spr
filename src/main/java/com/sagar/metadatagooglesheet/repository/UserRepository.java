package com.sagar.metadatagooglesheet.repository;

import com.sagar.metadatagooglesheet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String s);
}
