package com.sagar.metadatagooglesheet.repository;

import com.sagar.metadatagooglesheet.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
