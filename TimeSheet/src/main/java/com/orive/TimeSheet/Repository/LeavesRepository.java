package com.orive.TimeSheet.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.orive.TimeSheet.Entity.LeavesEntity;


public interface LeavesRepository extends JpaRepository<LeavesEntity, String>{
	
	//find by employeeId
		@Query("SELECT l FROM LeavesEntity l WHERE l.username = :username")
		List<LeavesEntity> findByEmployeeId(@Param("username") String username);

}
