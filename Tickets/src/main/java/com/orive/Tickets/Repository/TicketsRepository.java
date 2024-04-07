package com.orive.Tickets.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.orive.Tickets.Entity.TicketsEntity;



public interface TicketsRepository extends JpaRepository<TicketsEntity, String> {
	
//	@Query("SELECT t FROM TicketsEntity t WHERE t.employeeId = :employeeId")
//	Optional<TicketsEntity> findByEmployeeId(@Param("employeeId") Long employeeId);


	
	@Query("SELECT t FROM TicketsEntity t WHERE t.username = :username")
	List<TicketsEntity> findByEmployeeId(@Param("username") String username);
	
	//Query for find duplicate using ticketsCode And projectTitle
	@Query("SELECT t FROM TicketsEntity t WHERE t.ticketsCode = :ticketsCode AND t.projectTitle = :projectTitle")
	Optional<TicketsEntity> findByTicketsCodeAndProjectTitle(@Param("ticketsCode") String ticketsCode, @Param("projectTitle") String projectTitle);
}
