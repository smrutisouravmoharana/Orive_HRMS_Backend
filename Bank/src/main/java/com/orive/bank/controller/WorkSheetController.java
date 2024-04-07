package com.orive.bank.controller;

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

import com.orive.bank.dto.WorkSheetDto;
import com.orive.bank.service.WorkSheetService;

//import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "worksheet")
@CrossOrigin(origins = "*")
public class WorkSheetController {
	
	private static final Logger logger = LoggerFactory.getLogger(WorkSheetController.class);
	
	@Autowired
	private WorkSheetService workSheetService;
	
//	// Create a new WorkSheet
//		  @PostMapping("/create/worksheet")
//		 // @PreAuthorize("hasRole('client_HR')")
//		  public ResponseEntity<WorkSheetDto> createWorkSheet(@RequestBody WorkSheetDto workSheetDto) {
//			  WorkSheetDto createdWorkSheet = workSheetService.createWorkSheet(workSheetDto);
//		      logger.info("Created WorkSheet with id: {}", createdWorkSheet.getWorkSheetTitle());
//		      return new ResponseEntity<>(createdWorkSheet, HttpStatus.CREATED);
//		  }

	
	// Create a new  WorkSheet
    @PostMapping("/create/worksheet")
 // @PreAuthorize("hasRole('client_HR')")
  public ResponseEntity<?> createDepartment(@Valid @RequestBody WorkSheetDto workSheetDto) {
      try {
          // Check if the WorkSheetTitle And Project already exists
          Optional<WorkSheetDto> existingWorkSheetTitleAndProject = workSheetService.getWorkSheetByWorkSheetTitleAndProject(workSheetDto.getWorkSheetTitle(), workSheetDto.getProjectName());
          if (existingWorkSheetTitleAndProject.isPresent()) {
              // WorkSheetTitle And Project already exists, return a bad request response with the error message
              return ResponseEntity.badRequest().body(" WorkSheet with WorkSheetTitle '" + workSheetDto.getWorkSheetTitle() + " And Project Name " + workSheetDto.getProjectName() + "' already exists");
          }

          // WorkSheetTitle And Project name is unique, proceed with creating the WorkSheet
          WorkSheetDto createdWorkSheet = workSheetService.createWorkSheet(workSheetDto);
          logger.info("Created WorkSheet with WorkSheetTitle And Project name: {}",  createdWorkSheet.getWorkSheetTitle() , createdWorkSheet.getProjectName());
          
          // Return the created WorkSheet with a success status code
          return new ResponseEntity<>(createdWorkSheet, HttpStatus.CREATED);
      } catch (Exception e) {
          // Handle any unexpected errors
          logger.error("Error creating WorkSheet: {}", e.getMessage());
          
          // Return an internal server error response with a generic error message
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create WorkSheet");
        }
      }
	
    
		  
	// Get all WorkSheet  
		  @GetMapping("/get/worksheet")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<List<WorkSheetDto>> getAllWorkSheets() {
		      List<WorkSheetDto> workSheet = workSheetService.getAllWorkSheets();
		      logger.info("Retrieved {} WorkSheet from the database", workSheet.size());
		      return new ResponseEntity<>(workSheet, HttpStatus.OK);
		  }
		  
		  

	// Get WorkSheet by ID
		  @GetMapping("/get/{workSheetId}")
		//@PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<WorkSheetDto> getWorkSheetDtoId(@PathVariable String workSheetId) {
		      Optional<WorkSheetDto> workSheet = workSheetService.getWorkSheetId(workSheetId);
		      if (workSheet.isPresent()) {
		          logger.info("Retrieved WorkSheet with ID: {}", workSheetId);
		          return new ResponseEntity<>(workSheet.get(), HttpStatus.OK);
		      } else {
		          logger.warn("WorkSheet with ID {} not found", workSheetId);
		          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		      }
		  }
		  
		  
		  
	// Get WorkSheet by WorkSheetTitle And Project
	      @GetMapping("/get/name/{workSheetTitle}/{projectName}")
	   // @PreAuthorize("hasRole('client_HR')")
	      public ResponseEntity<WorkSheetDto> getWorkSheetByWorkSheetTitleAndProject(@PathVariable String workSheetTitle, @PathVariable String projectName) {
	          Optional<WorkSheetDto> workSheet = workSheetService.getWorkSheetByWorkSheetTitleAndProject(workSheetTitle,projectName);
	          if (workSheet.isPresent()) {
	              logger.info("Retrieved WorkSheet with WorkSheetTitle And Project: {}", workSheetTitle,projectName);
	              return new ResponseEntity<>(workSheet.get(), HttpStatus.OK);
	          } else {
	              logger.warn("WorkSheet with WorkSheetTitle And Project {} not found", workSheetTitle,projectName);
	              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	          }
	      }
		  
		  
		  
	// Get Worksheet by EmployeeID
		  @GetMapping("/getdetails/{username}")
		//@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
		    public ResponseEntity<List<WorkSheetDto>> getWorkSheetsByEmployeeId(@PathVariable String username) {
		        List<WorkSheetDto> workSheet = workSheetService.getEmployeeId(username);

		        if (workSheet.isEmpty()) {
		            return ResponseEntity.notFound().build();
		        } else {
		            return ResponseEntity.ok(workSheet);
		        }
		    }
		  
		  
		  
	 // Update WorkSheet by ID
		  @PutMapping("/update/{workSheetId}")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<WorkSheetDto> updateWorkSheet(@PathVariable String workSheetId, @RequestBody WorkSheetDto updatedWorkSheetDto) {
			  WorkSheetDto updatedWorkSheet = workSheetService.updateWorkSheet(workSheetId, updatedWorkSheetDto);
		      if (updatedWorkSheet != null) {
		          logger.info("Updated WorkSheet with ID: {}", workSheetId);
		          return new ResponseEntity<>(updatedWorkSheet, HttpStatus.OK);
		      } else {
		          logger.warn("WorkSheet with ID {} not found for update", updatedWorkSheet);
		          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		      }
		  }
		  
		  
		  
	 // Delete WorkSheet by ID
		  @DeleteMapping("/delete/{workSheetId}")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<Void> deleteWorkSheet(@PathVariable String workSheetId) {
			   workSheetService.deleteWorkSheet(workSheetId);
		      logger.info("Deleted WorkSheet with ID: {}", workSheetId);
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  }
		  
			  
     // Count WorkSheet
			    @GetMapping("/count/worksheet")
			 // @PreAuthorize("hasRole('client_HR')")
			    public long countWorkSheet()
			    {
			    	return workSheetService.countWorkSheet();
			    }
            }
