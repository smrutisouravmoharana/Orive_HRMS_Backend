package com.orive.Employee.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Employee.Entity.ExperienceLetterEntity;


public interface ExperienceLetterRepository extends JpaRepository<ExperienceLetterEntity, Long>{

	List<ExperienceLetterEntity> findByUsername(String username);
}
