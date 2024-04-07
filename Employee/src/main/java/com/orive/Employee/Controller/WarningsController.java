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

import com.orive.Employee.Dto.ComplaintsDto;
import com.orive.Employee.Dto.TransfersDto;
import com.orive.Employee.Dto.WarningsDto;
import com.orive.Employee.Service.WarningsService;
//import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping(value = "warnings")
@CrossOrigin(origins = "*")
public class WarningsController {
	
	private static final Logger logger = LoggerFactory.getLogger(WarningsController.class);
	
	 @Autowired
	    private WarningsService warningsService;
	 
	// Create a new Warnings
	  @PostMapping("/create/warnings")
	// @PreAuthorize("hasRole('client_HR')")
	  public ResponseEntity<WarningsDto> createWarnings(@RequestBody WarningsDto warningsDto) {
		  WarningsDto createdWarnings = warningsService.createWarnings(warningsDto);
	      logger.info("Created Warnings with id: {}", createdWarnings.getEmployeeName());
	      return new ResponseEntity<>(createdWarnings, HttpStatus.CREATED);
	  }

	  // Get all Complaints  
	  @GetMapping("/get/warnings")
	// @PreAuthorize("hasRole('client_HR')")
	  public ResponseEntity<List<WarningsDto>> getAllWarnings() {
	      List<WarningsDto> warnings = warningsService.getAllWarnings();
	      logger.info("Retrieved {} Warnings from the database", warnings.size());
	      return new ResponseEntity<>(warnings, HttpStatus.OK);
	  }

	  // Get Complaints by ID
	  @GetMapping("/get/{warningsId}")
	// @PreAuthorize("hasRole('client_HR')")
	  public ResponseEntity<WarningsDto> getWarningsDtoId(@PathVariable Long warningsId) {
	      Optional<WarningsDto> warnings = warningsService.getWarningsById(warningsId);
	      if (warnings.isPresent()) {
	          logger.info("Retrieved Warnings with ID: {}", warningsId);
	          return new ResponseEntity<>(warnings.get(), HttpStatus.OK);
	      } else {
	          logger.warn("Warnings with ID {} not found", warningsId);
	          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	  }
	  
	  @GetMapping("/findwarnings/{username}")
      public ResponseEntity<List<WarningsDto>> getWarningsByUsername(@PathVariable String username) {
          logger.info("Getting Warnings for username: {}", username);
          List<WarningsDto> transfers = warningsService.getTransfersByUsername(username);
          logger.info("Found {} Warnings for username: {}", transfers.size(), username);
          return ResponseEntity.ok(transfers);
      }

	  // Update Complaints by ID
	  @PutMapping("/update/{warningsId}")
	// @PreAuthorize("hasRole('client_HR')")
	  public ResponseEntity<WarningsDto> updateWarnings(@PathVariable Long warningsId, @RequestBody WarningsDto updatedWarningsDto) {
		  WarningsDto updatedWarnings = warningsService.updateWarnings(warningsId, updatedWarningsDto);
	      if (updatedWarnings != null) {
	          logger.info("Updated Warnings with ID: {}", warningsId);
	          return new ResponseEntity<>(updatedWarnings, HttpStatus.OK);
	      } else {
	          logger.warn("Warnings with ID {} not found for update", updatedWarnings);
	          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	  }
	  
	  // Delete Complaints by ID
	  @DeleteMapping("/delete/{warningsId}")
	// @PreAuthorize("hasRole('client_HR')")
	  public ResponseEntity<Void> deleteWarnings(@PathVariable Long warningsId) {
		   warningsService.deleteWarnings(warningsId);
	      logger.info("Deleted Warnings with ID: {}", warningsId);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
		   
	  // Count the total Complaints
		    @GetMapping("/count/warnings")
		 // @PreAuthorize("hasRole('client_HR')")
		    public long countWarnings()
		    {
		    	return warningsService.countWarnings();
		    }
}