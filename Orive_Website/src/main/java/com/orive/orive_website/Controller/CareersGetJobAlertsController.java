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

import com.orive.orive_website.DTO.CareersGetJobAlertsDto;
import com.orive.orive_website.Service.CareersGetJobAlertsService;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "careersgetjobAlerts")
@CrossOrigin(origins = "*")
public class CareersGetJobAlertsController {
	
private static final Logger logger = LoggerFactory.getLogger(CareersGetJobAlertsController.class);
	
	@Autowired
	private CareersGetJobAlertsService careersGetJobAlertsService ;
	
//	// Create a new CareersGetJobAlerts
//		  @PostMapping("/create/careersgetjobAlerts")
//		  // @PreAuthorize("hasRole('client_HR')")
//		  public ResponseEntity<CareersGetJobAlertsDto> createCareersGetJobAlerts(@RequestBody CareersGetJobAlertsDto careersGetJobAlertsDto) {
//			  CareersGetJobAlertsDto createdCareersGetJobAlerts = careersGetJobAlertsService.createCareersGetJobAlerts(careersGetJobAlertsDto);
//		      logger.info("Created CareersGetJobAlerts with id: {}", createdCareersGetJobAlerts.getName());
//		      return new ResponseEntity<>(createdCareersGetJobAlerts, HttpStatus.CREATED);
//		  }

		  
	
		  // Get all CareersGetJobAlerts  
		  @GetMapping("/get/careersgetjobAlerts")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<List<CareersGetJobAlertsDto>> getAllCareersGetJobAlerts() {
		      List<CareersGetJobAlertsDto> careersGetJobAlerts = careersGetJobAlertsService.getAllCareersGetJobAlerts();
		      logger.info("Retrieved {} CareersGetJobAlerts from the database", careersGetJobAlerts.size());
		      return new ResponseEntity<>(careersGetJobAlerts, HttpStatus.OK);
		  }

		  
//		  // Get CareersGetJobAlerts by ID
//		  @GetMapping("/get/{careersGetJobAlertsId}")
//		  // @PreAuthorize("hasRole('client_HR')")
//		  public ResponseEntity<CareersGetJobAlertsDto> getCareersGetJobAlertsId(@PathVariable Long careersGetJobAlertsId) {
//		      Optional<CareersGetJobAlertsDto> careersGetJobAlerts = careersGetJobAlertsService.getCareersGetJobAlertsId(careersGetJobAlertsId);
//		      if (careersGetJobAlerts.isPresent()) {
//		          logger.info("Retrieved CareersGetJobAlerts with ID: {}", careersGetJobAlertsId);
//		          return new ResponseEntity<>(careersGetJobAlerts.get(), HttpStatus.OK);
//		      } else {
//		          logger.warn("CareersGetJobAlerts with ID {} not found", careersGetJobAlertsId);
//		          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		      }
//		  }
		  
		  
		  
		  
//		  // Update CareersGetJobAlerts by ID
//		  @PutMapping("/update/{careersGetJobAlertsId}")
//		 // @PreAuthorize("hasRole('client_HR')")
//		  public ResponseEntity<CareersGetJobAlertsDto> updateCareersGetJobAlerts(@PathVariable Long careersGetJobAlertsId, @RequestBody CareersGetJobAlertsDto updatedCareersGetJobAlertsDto) {
//			  CareersGetJobAlertsDto updatedCareersGetJobAlerts = careersGetJobAlertsService.updateCareersGetJobAlerts(careersGetJobAlertsId, updatedCareersGetJobAlertsDto);
//		      if (updatedCareersGetJobAlerts != null) {
//		          logger.info("Updated CareersGetJobAlerts with ID: {}", careersGetJobAlertsId);
//		          return new ResponseEntity<>(updatedCareersGetJobAlerts, HttpStatus.OK);
//		      } else {
//		          logger.warn("CareersGetJobAlerts with ID {} not found for update", updatedCareersGetJobAlerts);
//		          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		      }
//		  }
		  
		  
		  
		  // Delete CareersGetJobAlerts by ID
		  @DeleteMapping("/delete/{careersGetJobAlertsId}")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<Void> deleteCareersGetJobAlerts(@PathVariable Long careersGetJobAlertsId) {
			  careersGetJobAlertsService.deleteCareersGetJobAlerts(careersGetJobAlertsId);
		      logger.info("Deleted CareersGetJobAlerts with ID: {}", careersGetJobAlertsId);
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  }
			   
		  
//		  //count all CareersGetJobAlerts
//			    @GetMapping("/count/careersgetjobAlerts")
//			    // @PreAuthorize("hasRole('client_HR')")
//			    public long countCareersGetJobAlerts()
//			    {
//			    	return careersGetJobAlertsService.countCareersGetJobAlerts();
//			    }
//
        }
