package com.sagar.metadatagooglesheet.repository;

import com.sagar.metadatagooglesheet.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
