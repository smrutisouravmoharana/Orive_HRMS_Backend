package com.orive.Organisation.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orive.Organisation.Dto.DepartmentDto;
import com.orive.Organisation.Entity.DepartmentEntity;
import com.orive.Organisation.Service.DepartmentService;
//import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;



@RestController
@RequestMapping(value = "department")
@CrossOrigin(origins = "*")
public class DepartmentController {

	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

  
// 	// Create a new Department
//      @PostMapping("/create/department")
//   // @PreAuthorize("hasRole('client_admin')")
//      public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
//    	  DepartmentDto createdDepartment = departmentService.createDepartment(departmentDto);
//          logger.info("Created Department with name: {}", createdDepartment.getCompanyName());
//          return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
//          
//      }
    
    
 // Create a new Department
    @PostMapping("/create/department")
 // @PreAuthorize("hasRole('client_HR')")
  public ResponseEntity<?> createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
      try {
          // Check if the department name already exists
          Optional<DepartmentDto> existingDepartment = departmentService.getDepartmentByName(departmentDto.getDepartmentName());
          if (existingDepartment.isPresent()) {
              // Department name already exists, return a bad request response with the error message
              return ResponseEntity.badRequest().body("Department with name '" + departmentDto.getDepartmentName() + "' already exists");
          }

          // Department name is unique, proceed with creating the department
          DepartmentDto createdDepartment = departmentService.createDepartment(departmentDto);
          logger.info("Created Department with name: {}", createdDepartment.getCompanyName());
          
          // Return the created department with a success status code
          return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
      } catch (Exception e) {
          // Handle any unexpected errors
          logger.error("Error creating department: {}", e.getMessage());
          
          // Return an internal server error response with a generic error message
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Department");
      }
  }
    
    
      // Get all Department     
      @GetMapping("/get/department")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<List<DepartmentDto>> getAllDepartment() {
          List<DepartmentDto> department = departmentService.getAllDepartment();
          logger.info("Retrieved {} Department from the database", department.size());
          return new ResponseEntity<>(department, HttpStatus.OK);
      }

      // Get Department by ID
      @GetMapping("/get/{departmentId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long departmentId) {
          Optional<DepartmentDto> department = departmentService.getDepartmentById(departmentId);
          if (department.isPresent()) {
              logger.info("Retrieved Department with ID: {}", departmentId);
              return new ResponseEntity<>(department.get(), HttpStatus.OK);
          } else {
              logger.warn("Department with ID {} not found", departmentId);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }
      
      
      // Get Department by Name
      @GetMapping("/get/name/{departmentName}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<DepartmentDto> getDepartmentByName(@PathVariable String departmentName) {
          Optional<DepartmentDto> department = departmentService.getDepartmentByName(departmentName);
          if (department.isPresent()) {
              logger.info("Retrieved Department with Name: {}", departmentName);
              return new ResponseEntity<>(department.get(), HttpStatus.OK);
          } else {
              logger.warn("Department with Name {} not found", departmentName);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }

      // Update Department by ID
      @PutMapping("/update/{departmentId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long departmentId, @RequestBody DepartmentDto updatedDepartmentDto) {
    	  DepartmentDto updatedDepartment = departmentService.updateDepartment(departmentId, updatedDepartmentDto);
          if (updatedDepartment != null) {
              logger.info("Updated Department with ID: {}", departmentId);
              return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
          } else {
              logger.warn("Department with ID {} not found for update", departmentId);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }
      


      // Delete Department by ID
      @DeleteMapping("/delete/{departmentId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<Void> deleteDepartment(@PathVariable Long departmentId) {
    	  departmentService.deleteDepartment(departmentId);
          logger.info("Deleted Department with ID: {}", departmentId);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      
      
//      // REST endpoint to restore a department by ID
//      @PostMapping("/restore/{departmentId}")
//      public ResponseEntity<DepartmentEntity> restoreDepartment(@PathVariable Long departmentId) {
//          DepartmentEntity restoredDepartment = departmentService.restoreDepartment(departmentId);
//
//          if (restoredDepartment != null) {
//              return new ResponseEntity<>(restoredDepartment, HttpStatus.OK);
//          } else {
//              logger.warn("Unable to restore Department with ID: {}", departmentId);
//              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//          }
//      }
      
      
      
   // count the total Department    
  	    @GetMapping("/count/department")
  	// @PreAuthorize("hasRole('client_HR')")
  	    public long countDepartment()
  	    {
  	    	return departmentService.countDepartment();
  	    }
}
