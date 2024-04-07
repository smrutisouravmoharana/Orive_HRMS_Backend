package com.orive.Employee.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.orive.Employee.Entity.TerminationsEntity;


public interface TerminationsRepository extends JpaRepository<TerminationsEntity, Long> {

	@Query("SELECT d FROM TerminationsEntity d WHERE d.employeeName = :employeeName")
	Optional<TerminationsEntity> findByEmployeeName(String employeeName);
	
	List<TerminationsEntity> findByUsername(String username);
}
