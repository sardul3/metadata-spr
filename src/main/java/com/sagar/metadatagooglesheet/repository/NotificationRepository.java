package com.sagar.metadatagooglesheet.repository;

import com.sagar.metadatagooglesheet.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
