package com.orive.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.orive.bank.entities.WorkSheetEntity;




public interface WorkSheetRepository extends JpaRepository<WorkSheetEntity, String>{
	
	//Query for find by employeeId
	@Query("SELECT w FROM WorkSheetEntity w WHERE w.username = :username")
	List<WorkSheetEntity> findByEmployeeId(@Param("username") String username);
	
	
	//Query for find duplicate using workSheetTitle And project
	@Query("SELECT w FROM WorkSheetEntity w WHERE w.workSheetTitle = :workSheetTitle AND w.projectName = :projectName")
	Optional<WorkSheetEntity> findByWorkSheetTitleAndProjectName(@Param("workSheetTitle") String workSheetTitle, @Param("projectName") String projectName);
}
