package com.orive.Employee.Controller;

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


import com.orive.Employee.Dto.TerminationsDto;
import com.orive.Employee.Dto.TransfersDto;
import com.orive.Employee.Service.TerminationsService;
//import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;



@RestController
@RequestMapping(value = "terminations")
@CrossOrigin(origins = "*")
public class TerminationsController {
	
	private static final Logger logger = LoggerFactory.getLogger(TerminationsController.class);
	
	 @Autowired
	    private TerminationsService terminationsService;
	 
//		// Create a new Terminations
//     @PostMapping("/create/terminations")
//  // @PreAuthorize("hasRole('client_admin')")
//     public ResponseEntity<TerminationsDto> createTerminations(@RequestBody TerminationsDto terminationsDto) {
//    	 TerminationsDto createdTerminations = terminationsService.createTerminations(terminationsDto);
//         logger.info("Created Terminations with name: {}", createdTerminations.getEmployeeName());
//         return new ResponseEntity<>(createdTerminations, HttpStatus.CREATED);
//     }
	 
	 
	 // Create a new Department
	    @PostMapping("/create/terminations")
	 // @PreAuthorize("hasRole('client_HR')")
	    public ResponseEntity<TerminationsDto> createTermination(@RequestBody TerminationsDto terminationsDto) {
	        logger.info("Received request to create termination for Employee: {}", terminationsDto.getEmployeeName());

	        TerminationsDto createdTermination = terminationsService.createTermination(terminationsDto);
	        if (createdTermination != null) {
	            logger.info("Termination created successfully with ID: {}", createdTermination.getTerminationId());
	            return new ResponseEntity<>(createdTermination, HttpStatus.CREATED);
	        } else {
	            logger.warn("Failed to create termination for Employee: {}", terminationsDto.getEmployeeName());
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	    }

     // Get all Terminations  
     @GetMapping("/get/terminationsId")
  // @PreAuthorize("hasRole('client_HR')")
     public ResponseEntity<List<TerminationsDto>> getAllTerminations  () {
         List<TerminationsDto> terminations = terminationsService.getAllTerminations();
         logger.info("Retrieved {} Terminations from the database", terminations.size());
         return new ResponseEntity<>(terminations, HttpStatus.OK);
     }

     // Get Terminations by ID
     @GetMapping("/get/{terminationsId}")
  // @PreAuthorize("hasRole('client_HR')")
     public ResponseEntity<TerminationsDto> getTerminationsById(@PathVariable Long terminationsId) {
         Optional<TerminationsDto> terminations = terminationsService.getTerminationsById(terminationsId);
         if (terminations.isPresent()) {
             logger.info("Retrieved Terminations with ID: {}", terminationsId);
             return new ResponseEntity<>(terminations.get(), HttpStatus.OK);
         } else {
             logger.warn("Terminations with ID {} not found", terminationsId);
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
     }
     
   //Get Complaints by Employee ID
     @GetMapping("/findterminations/{username}")
   //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
     public ResponseEntity<List<TerminationsDto>> getTerminationsByUsername(@PathVariable String username) {
         logger.info("Getting Terminations for username: {}", username);
         List<TerminationsDto> transfers = terminationsService.getTransfersByUsername(username);
         logger.info("Found {} Terminations for username: {}", transfers.size(), username);
         return ResponseEntity.ok(transfers);
     }

     // Update Terminations by ID
     @PutMapping("/update/{terminationsId}")
  // @PreAuthorize("hasRole('client_HR')")
     public ResponseEntity<TerminationsDto> updateTerminations(@PathVariable Long terminationsId, @RequestBody TerminationsDto updatedTerminationsDto) {
    	 TerminationsDto updatedTerminations = terminationsService.updateTerminations(terminationsId, updatedTerminationsDto);
         if (updatedTerminations != null) {
             logger.info("Updated Terminations with ID: {}", terminationsId);
             return new ResponseEntity<>(updatedTerminations, HttpStatus.OK);
         } else {
             logger.warn("Terminations with ID {} not found for update", terminationsId);
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
     }
     


     // Delete Terminations by ID
     @DeleteMapping("/delete/{terminationsId}")
  // @PreAuthorize("hasRole('client_HR')")
     public ResponseEntity<Void> deleteTerminations(@PathVariable Long terminationsId) {
   	  terminationsService.deleteTerminations(terminationsId);
         logger.info("Deleted Terminations with ID: {}", terminationsId);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }
 	    
     // Count the total Terminations
 	    @GetMapping("/count/awards")
 	// @PreAuthorize("hasRole('client_HR')")
 	    public long countTerminations()
 	    {
 	    	return terminationsService.countTerminations();
 	    }

}
