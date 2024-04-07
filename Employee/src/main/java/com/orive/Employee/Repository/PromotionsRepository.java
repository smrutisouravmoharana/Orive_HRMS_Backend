package com.orive.Employee.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Employee.Entity.PromotionsEntity;


public interface PromotionsRepository extends JpaRepository<PromotionsEntity, Long> {

	List<PromotionsEntity> findByUsername(String username);
}
