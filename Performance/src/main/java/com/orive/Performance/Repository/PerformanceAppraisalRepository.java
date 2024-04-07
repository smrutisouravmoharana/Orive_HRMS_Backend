package com.orive.Performance.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.orive.Performance.Entity.PerformanceAppraisalEntity;

public interface PerformanceAppraisalRepository extends JpaRepository<PerformanceAppraisalEntity, String> {
	
	@Query("SELECT p FROM PerformanceAppraisalEntity p WHERE p.username = :username")
    List<PerformanceAppraisalEntity> findPerformanceAppraisalByUsername(@Param("username") String username);
	//  @Query("SELECT p FROM PerformanceAppraisalEntity p WHERE p.employeeId = :employeeId")
	//  List<PerformanceAppraisalEntity> findPerformanceAppraisalByEmployeeId(@Param("employeeId") Long employeeId);

}
