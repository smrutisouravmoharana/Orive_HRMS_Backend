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
import com.orive.Organisation.Dto.DesignationDto;
import com.orive.Organisation.Service.DesignationService;
//import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;



@RestController
@RequestMapping(value = "designation")
@CrossOrigin(origins = "*")
public class DesignationController {

	private static final Logger logger = LoggerFactory.getLogger(DesignationController.class);

    @Autowired
    private DesignationService designationService;

  
//  	// Create a new Designation
//      @PostMapping("/create/designation")
//   // @PreAuthorize("hasRole('client_admin')")
//      public ResponseEntity<DesignationDto> createDesignation(@RequestBody DesignationDto designationDto) {
//    	  DesignationDto createdDesignation = designationService.createDesignation(designationDto);
//          logger.info("Created Designation with name: {}", createdDesignation.getDesignationName());
//          return new ResponseEntity<>(createdDesignation, HttpStatus.CREATED);
//      }
    
 // Create a new Department
    @PostMapping("/create/designation")
 // @PreAuthorize("hasRole('client_HR')")
  public ResponseEntity<?> createDesignation(@Valid @RequestBody DesignationDto designationDto) {
      try {
          // Check if the department name already exists
          Optional<DesignationDto> existingDepartment = designationService.getDesignationByName(designationDto.getDesignationName());
          if (existingDepartment.isPresent()) {
              // Department name already exists, return a bad request response with the error message
              return ResponseEntity.badRequest().body("Designation with name '" + designationDto.getDesignationName() + "' already exists");
          }

          // Department name is unique, proceed with creating the department
          DesignationDto createdDepartment = designationService.createDesignation(designationDto);
          logger.info("Created Designation with name: {}", createdDepartment.getDesignationName());
          
          // Return the created department with a success status code
          return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
      } catch (Exception e) {
          // Handle any unexpected errors
          logger.error("Error creating department: {}", e.getMessage());
          
          // Return an internal server error response with a generic error message
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Designation");
      }
  }

      // Get all Designation
      @GetMapping("/get/designation")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<List<DesignationDto>> getAllDesignation() {
          List<DesignationDto> designation = designationService.getAllDesignation();
          logger.info("Retrieved {} Designation from the database", designation.size());
          return new ResponseEntity<>(designation, HttpStatus.OK);
      }

      // Get Designation by ID
      @GetMapping("/get/{designationId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<DesignationDto> getDesignationById(@PathVariable Long designationId) {
          Optional<DesignationDto> designation = designationService.getDesignationById(designationId);
          if (designation.isPresent()) {
              logger.info("Retrieved Designation with ID: {}", designationId);
              return new ResponseEntity<>(designation.get(), HttpStatus.OK);
          } else {
              logger.warn("Designation with ID {} not found", designationId);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }

      // Update Designation by ID
      @PutMapping("/update/{designationId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<DesignationDto> updateDesignation(@PathVariable Long designationId, @RequestBody DesignationDto updatedDesignationDto) {
    	  DesignationDto updatedDesignation = designationService.updateDesignation(designationId, updatedDesignationDto);
          if (updatedDesignation != null) {
              logger.info("Updated Designation with ID: {}", designationId);
              return new ResponseEntity<>(updatedDesignation, HttpStatus.OK);
          } else {
              logger.warn("Designation with ID {} not found for update", designationId);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }
      

      // Delete Designation by ID
      @DeleteMapping("/delete/{designationId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<Void> deleteDesignation(@PathVariable Long designationId) {
    	  designationService.deleteDesignation(designationId);
          logger.info("Deleted Designation with ID: {}", designationId);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      
      
  	  //Count the total Designation
  	    @GetMapping("/count/designation")
  	// @PreAuthorize("hasRole('client_HR')")
  	    public long countDesignation()
  	    {
  	    	return designationService.countDesignation();
  	    }
}
