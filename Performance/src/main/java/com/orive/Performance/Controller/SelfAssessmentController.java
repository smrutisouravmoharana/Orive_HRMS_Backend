package com.orive.Performance.Controller;

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

import com.orive.Performance.Dto.SelfAssessmentDto;
import com.orive.Performance.Service.SelfAssessmentService;
//import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping(value = "selfAssessment")
@CrossOrigin(origins = "*")
public class SelfAssessmentController {
	
	private static final Logger logger = LoggerFactory.getLogger(SelfAssessmentController.class);
	
	 @Autowired
	    private SelfAssessmentService selfAssessmentService;
	 
	// Create a new SelfAssessment
   @PostMapping("/create/selfAssessment")
// @PreAuthorize("hasRole('client_HR')")
   public ResponseEntity<SelfAssessmentDto> createSelfAssessment(@RequestBody SelfAssessmentDto selfAssessmentDto) {
	   SelfAssessmentDto createdSelfAssessment = selfAssessmentService.createSelfAssessment(selfAssessmentDto);
       logger.info("Created SelfAssessment with id: {}", createdSelfAssessment.getSelfAssessmentId());
       return new ResponseEntity<>(createdSelfAssessment, HttpStatus.CREATED);
   }

   
   // Get all SelfAssessment  
   @GetMapping("/get/selfAssessment")
// @PreAuthorize("hasRole('client_HR')")
   public ResponseEntity<List<SelfAssessmentDto>> getAllSelfAssessment() {
       List<SelfAssessmentDto> selfAssessment = selfAssessmentService.getAllSelfAssessment();
       logger.info("Retrieved {} SelfAssessment from the database", selfAssessment.size());
       return new ResponseEntity<>(selfAssessment, HttpStatus.OK);
   }

   // Get SelfAssessment by ID
   @GetMapping("/get/{selfAssessmentId}")
	 //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
   public ResponseEntity<SelfAssessmentDto> getSelfAssessmentId(@PathVariable String selfAssessmentId) {
       Optional<SelfAssessmentDto> selfAssessment = selfAssessmentService.getSelfAssessmentById(selfAssessmentId);
       if (selfAssessment.isPresent()) {
           logger.info("Retrieved SelfAssessment with ID: {}", selfAssessmentId);
           return new ResponseEntity<>(selfAssessment.get(), HttpStatus.OK);
       } else {
           logger.warn("SelfAssessment with ID {} not found", selfAssessmentId);
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
   }

   // Update SelfAssessment by ID
   @PutMapping("/update/{selfAssessmentId}")
// @PreAuthorize("hasRole('client_HR')")
   public ResponseEntity<SelfAssessmentDto> updateSelfAssessment(@PathVariable String selfAssessmentId, @RequestBody SelfAssessmentDto updatedSelfAssessmentDto) {
	   SelfAssessmentDto updatedTravels = selfAssessmentService.updateSelfAssessment(selfAssessmentId, updatedSelfAssessmentDto);
       if (updatedTravels != null) {
           logger.info("Updated SelfAssessment with ID: {}", selfAssessmentId);
           return new ResponseEntity<>(updatedTravels, HttpStatus.OK);
       } else {
           logger.warn("SelfAssessment with ID {} not found for update", selfAssessmentId);
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
   }
   
   // Delete SelfAssessment by ID
   @DeleteMapping("/delete/{selfAssessmentId}")
// @PreAuthorize("hasRole('client_HR')")
   public ResponseEntity<Void> deleteSelfAssessment(@PathVariable String selfAssessmentId) {
	   selfAssessmentService.deleteSelfAssessment(selfAssessmentId);
       logger.info("Deleted SelfAssessment with ID: {}", selfAssessmentId);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
	    
       // Count the total  SelfAssessment
	    @GetMapping("/count/selfAssessment")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countSelfAssessment()
	    {
	    	return selfAssessmentService.CountSelfAssessment();
	    }

}
