package com.orive.Employee.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Employee.Entity.ComplaintsEntity;


public interface ComplaintsRepository extends JpaRepository<ComplaintsEntity, Long> {

	List<ComplaintsEntity> findByUsername(String username);
}
