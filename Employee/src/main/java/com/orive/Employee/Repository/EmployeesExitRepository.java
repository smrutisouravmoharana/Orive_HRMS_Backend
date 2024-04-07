package com.orive.Employee.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Employee.Entity.EmployeesExitEntity;


public interface EmployeesExitRepository extends JpaRepository<EmployeesExitEntity, Long> {

	List<EmployeesExitEntity> findByUsername(String username);
}