package com.orive.orive_website.Controller;

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

import com.orive.orive_website.DTO.JournalsDetailsDto;
import com.orive.orive_website.Service.JournalsDetailsService;
//import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping(value = "journalsdetails")
@CrossOrigin(origins = "*")
public class JournalsDetailsController {
	
private static final Logger logger = LoggerFactory.getLogger(JournalsDetailsController.class);
	
	@Autowired
	private JournalsDetailsService journalsDetailsService  ;
	
//	// Create a new JournalsDetails
//		  @PostMapping("/create/journalsdetails")
//		  // @PreAuthorize("hasRole('client_HR')")
//		  public ResponseEntity<JournalsDetailsDto> createJournalsDetails(@RequestBody JournalsDetailsDto journalsDetailsDto) {
//			  JournalsDetailsDto createdJournalsDetails = journalsDetailsService.createJournalsDetails(journalsDetailsDto);
//		      logger.info("Created JournalsDetails with id: {}", createdJournalsDetails.getJournalsDetailsId());
//		      return new ResponseEntity<>(createdJournalsDetails, HttpStatus.CREATED);
//		  }

		  
	
		  // Get all JournalsDetails  
		  @GetMapping("/get/journalsdetails")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<List<JournalsDetailsDto>> getAllJournalsDetails() {
		      List<JournalsDetailsDto> journalsDetails = journalsDetailsService.getAllJournalsDetails();
		      logger.info("Retrieved {} JournalsDetails from the database", journalsDetails.size());
		      return new ResponseEntity<>(journalsDetails, HttpStatus.OK);
		  }

		  
		  
//		  // Get JournalsDetails by ID
//		  @GetMapping("/get/{journalsDetailsId}")
//		  // @PreAuthorize("hasRole('client_HR')")
//		  public ResponseEntity<JournalsDetailsDto> getJournalsDetailsId(@PathVariable Long journalsDetailsId) {
//		      Optional<JournalsDetailsDto> journalsDetails = journalsDetailsService.getJournalsDetailsId(journalsDetailsId);
//		      if (journalsDetails.isPresent()) {
//		          logger.info("Retrieved JournalsDetails with ID: {}", journalsDetailsId);
//		          return new ResponseEntity<>(journalsDetails.get(), HttpStatus.OK);
//		      } else {
//		          logger.warn("JournalsDetails with ID {} not found", journalsDetailsId);
//		          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		      }
//		  }
		  
		  
		  
		  
		  
//		  // Update JournalsDetails by ID
//		  @PutMapping("/update/{journalsDetailsId}")
//		  // @PreAuthorize("hasRole('client_HR')")
//		  public ResponseEntity<JournalsDetailsDto> updateJournalsDetails(@PathVariable Long journalsDetailsId, @RequestBody JournalsDetailsDto updatedJournalsDetailsDto) {
//			  JournalsDetailsDto updatedJournalsDetails = journalsDetailsService.updateJournalsDetails(journalsDetailsId, updatedJournalsDetailsDto);
//		      if (updatedJournalsDetails != null) {
//		          logger.info("Updated JournalsDetails with ID: {}", journalsDetailsId);
//		          return new ResponseEntity<>(updatedJournalsDetails, HttpStatus.OK);
//		      } else {
//		          logger.warn("JournalsDetails with ID {} not found for update", updatedJournalsDetails);
//		          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		      }
//		  }
		  
		  
		  
		  // Delete JournalsDetails by ID
		  @DeleteMapping("/delete/{journalsDetailsId}")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<Void> deleteJournalsDetails(@PathVariable Long journalsDetailsId) {
			  journalsDetailsService.deleteJournalsDetails(journalsDetailsId);
		      logger.info("Deleted JournalsDetails with ID: {}", journalsDetailsId);
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  }
		  
		  
		  
//		  // Count total JournalsDetails 
//			    @GetMapping("/count/journalsdetails")
//			   // @PreAuthorize("hasRole('client_HR')")
//			    public long countJournalsDetails()
//			    {
//			    	return journalsDetailsService.countJournalsDetails();
//			    }

}
