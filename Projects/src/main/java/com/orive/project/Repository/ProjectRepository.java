package com.orive.project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.orive.project.Entity.EmployeeProjectManagementEntity;
import com.orive.project.Entity.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

	//find by managerEmployeeid
	@Query("SELECT p FROM ProjectEntity p WHERE p.managerEmployeeId = :managerEmployeeId")
	List<ProjectEntity> findByManagerEmployeeId(@Param("managerEmployeeId") Long managerEmployeeId);
	
	//find by employeeId
	@Query("SELECT p.employeeProjectManagementEntities FROM ProjectEntity p JOIN p.employeeProjectManagementEntities e WHERE e.username = :username")
    Optional<List<EmployeeProjectManagementEntity>> findEmployeeDetailsByEmployeeId(@Param("username") String username);
	
}
