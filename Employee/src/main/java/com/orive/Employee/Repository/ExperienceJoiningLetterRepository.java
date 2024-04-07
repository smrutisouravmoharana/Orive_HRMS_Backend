package com.orive.Employee.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Employee.Entity.ExperienceJoiningLetterEntity;


public interface ExperienceJoiningLetterRepository extends JpaRepository<ExperienceJoiningLetterEntity, Long> {

	List<ExperienceJoiningLetterEntity> findByUsername(String username);
}
