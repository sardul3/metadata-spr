package com.sagar.metadatagooglesheet.repository;

import com.sagar.metadatagooglesheet.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin
public interface NoteRepository extends JpaRepository<Note, Long> {
}
