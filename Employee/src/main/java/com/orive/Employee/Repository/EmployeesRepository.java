package com.orive.Employee.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.orive.Employee.Entity.EmployeesEntity;


public interface EmployeesRepository extends JpaRepository<EmployeesEntity, Long>{
	
//Query for find by EmployeeName
 List<EmployeesEntity> findEmployeeByEmployeeName(String employeeName);
 
 
//Query for find by EmployeeName
List<EmployeesEntity> findEmployeeByUsername(String username);
 
//Query for find by employeeId
 List<EmployeesEntity> findEmployeeByEmployeeId(Long employeeId);
//  @Query("SELECT l FROM EmployeesEntity l WHERE l.employeeId = :employeeId")
//  List<EmployeesEntity> findEmployeeByEmployeeId(@Param("employeeId") Long employeeId);
 
 //Query for count male employee
 @Query("SELECT COUNT(e) FROM EmployeesEntity e WHERE e.gender = 'MALE'")
 Long countEmployeeByMale();
 
 //Query for count female employee
 @Query("SELECT COUNT(f) FROM EmployeesEntity f WHERE f.gender = 'FEMALE'")
 Long countEmployeeByFemale();
 
 
//Query for find EmployeeCredentials using UserEmailOrName And Password
	@Query("SELECT e FROM EmployeesEntity e WHERE e.userEmailOrName = :userEmailOrName AND e.password = :password")
	Optional<EmployeesEntity> findByUserEmailOrNameAndPassword(@Param("userEmailOrName") String userEmailOrName, @Param("password") String password);
 
	
	@Query("SELECT DISTINCT e.employeeRole FROM EmployeesEntity e")
    List<String> findDistinctEmployeeRoles();
}

