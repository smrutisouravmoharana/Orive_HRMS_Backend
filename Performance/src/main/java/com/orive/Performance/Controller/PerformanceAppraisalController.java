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
import com.orive.Performance.Dto.PerformanceAppraisalDto;
import com.orive.Performance.Entity.PerformanceAppraisalEntity;
import com.orive.Performance.Exceptions.ResourceNotFoundException;
import com.orive.Performance.Service.PerformanceAppraisalService;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "performanceappraisal")
@CrossOrigin(origins = "*")
public class PerformanceAppraisalController {

	
	private static final Logger logger = LoggerFactory.getLogger(PerformanceAppraisalController.class);

    @Autowired
    private PerformanceAppraisalService performanceAppraisalService;
    
    
 // Create a new PerformanceAppraisal
    @PostMapping("/create/performanceappraisal")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<PerformanceAppraisalDto> createPerformanceAppraisal(@RequestBody PerformanceAppraisalDto performanceAppraisalDto) {
    	PerformanceAppraisalDto createdPerformanceAppraisal = performanceAppraisalService.createPerformanceAppraisal(performanceAppraisalDto);
        logger.info("Created PerformanceAppraisal with name: {}", createdPerformanceAppraisal.getEmployeeName());
        return new ResponseEntity<>(createdPerformanceAppraisal, HttpStatus.CREATED);
    }

    // Get all PerformanceAppraisal
    @GetMapping("/get/performanceappraisal")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<PerformanceAppraisalDto>> getAllPerformanceAppraisal() {
        List<PerformanceAppraisalDto> performanceAppraisal = performanceAppraisalService.getAllPerformanceAppraisal();
        logger.info("Retrieved {} PerformanceAppraisal from the database", performanceAppraisal.size());
        return new ResponseEntity<>(performanceAppraisal, HttpStatus.OK);
    }

    // Get PerformanceAppraisalbyId
    @GetMapping("/get/{performanceAppraisalId}")
  //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
    public ResponseEntity<PerformanceAppraisalDto> getPerformanceAppraisalbyId(@PathVariable String performanceAppraisalId) {
        Optional<PerformanceAppraisalDto> performanceAppraisal = performanceAppraisalService.getPerformanceAppraisalById(performanceAppraisalId);
        if (performanceAppraisal.isPresent()) {
            logger.info("Retrieved PerformanceAppraisal with ID: {}", performanceAppraisalId);
            return new ResponseEntity<>(performanceAppraisal.get(), HttpStatus.OK);
        } else {
            logger.warn("PerformanceAppraisal with ID {} not found", performanceAppraisalId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    
 // Get PerformanceAppraisal by EmployeeId
    @GetMapping("/byId/{username}")
  //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
    public ResponseEntity<List<PerformanceAppraisalEntity>> getPerformanceAppraisalByEmployeeId(@PathVariable String username) {
        try {
            List<PerformanceAppraisalEntity> performanceAppraisal = performanceAppraisalService.getPerformanceAppraisalByEmployeeId(username);
            logger.info("Retrieved PerformanceAppraisal with EmployeeId: {}", username);
            return new ResponseEntity<>(performanceAppraisal, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
      	  logger.warn("PerformanceAppraisal with EmployeeID {} not found", username);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    // Update PerformanceAppraisal by PerformanceAppraisalbyId
    @PutMapping("/update/{performanceAppraisalId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<PerformanceAppraisalDto> updatePerformanceAppraisal(@PathVariable String performanceAppraisalId, @RequestBody PerformanceAppraisalDto updatedPerformanceAppraisalDto) {
    	PerformanceAppraisalDto updatedPerformanceAppraisal = performanceAppraisalService.updatePerformanceAppraisal(performanceAppraisalId, updatedPerformanceAppraisalDto);
        if (updatedPerformanceAppraisal != null) {
            logger.info("Updated PerformanceAppraisal with ID: {}", performanceAppraisalId);
            return new ResponseEntity<>(updatedPerformanceAppraisal, HttpStatus.OK);
        } else {
            logger.warn("PerformanceAppraisal with ID {} not found for update", performanceAppraisalId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete PerformanceAppraisal by ID
    @DeleteMapping("/delete/{performanceAppraisalId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deletePerformanceAppraisal(@PathVariable String performanceAppraisalId) {
  	  performanceAppraisalService.deletePerformanceAppraisal(performanceAppraisalId);
        logger.info("Deleted PerformanceAppraisal with ID: {}", performanceAppraisalId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
	    @GetMapping("/count/performanceappraisal")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countPerformanceAppraisal()
	    {
	    	return performanceAppraisalService.countPerformanceAppraisal();
	    }
}
