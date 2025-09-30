package com.dev.notificationService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.notificationService.model.EmailModel;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
}
