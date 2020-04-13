package com.sagar.metadatagooglesheet.repository;

import com.sagar.metadatagooglesheet.model.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface MetadataRepository extends JpaRepository<Metadata, Long> {
}
