package com.orive.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.bank.entities.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, String>{

}
