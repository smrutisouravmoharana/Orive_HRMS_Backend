package com.orive.Employee.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Employee.Entity.TransfersEntity;

public interface TransfersRepository extends JpaRepository<TransfersEntity, Long>{

	List<TransfersEntity> findByUsername(String username);
}
