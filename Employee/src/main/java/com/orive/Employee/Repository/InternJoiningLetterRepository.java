package com.orive.Employee.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Employee.Entity.InternJoiningLetterEntity;


public interface InternJoiningLetterRepository extends JpaRepository<InternJoiningLetterEntity, Long> {

	List<InternJoiningLetterEntity> findByUsername(String username);
}
