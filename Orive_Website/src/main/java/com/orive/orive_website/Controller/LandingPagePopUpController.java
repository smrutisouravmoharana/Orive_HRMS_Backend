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

import com.orive.orive_website.DTO.LandingPagePopUpDto;
import com.orive.orive_website.Service.LandingPagePopUpService;
//import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping(value = "landingpagepopup")
@CrossOrigin(origins = "*")
public class LandingPagePopUpController {

private static final Logger logger = LoggerFactory.getLogger(LandingPagePopUpController.class);
	
	@Autowired
	private LandingPagePopUpService landingPagePopUpService ;
	
//	// Create a new LandingPagePopUp
//		  @PostMapping("/create/landingpagepopup")
//		  // @PreAuthorize("hasRole('client_HR')")
//		  public ResponseEntity<LandingPagePopUpDto> createLandingPagePopUp(@RequestBody LandingPagePopUpDto landingPagePopUpDto) {
//			  LandingPagePopUpDto createdLandingPagePopUp = landingPagePopUpService.createLandingPagePopUp(landingPagePopUpDto);
//		      logger.info("Created LandingPagePopUp with id: {}", createdLandingPagePopUp.getLandingPagePopUpId());
//		      return new ResponseEntity<>(createdLandingPagePopUp, HttpStatus.CREATED);
//		  }

		  
	
		  // Get all LandingPagePopUp  
		  @GetMapping("/get/landingpagepopup")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<List<LandingPagePopUpDto>> getAllLandingPagePopUp() {
		      List<LandingPagePopUpDto> landingPagePopUp = landingPagePopUpService.getAllLandingPagePopUp();
		      logger.info("Retrieved {} LandingPagePopUp from the database", landingPagePopUp.size());
		      return new ResponseEntity<>(landingPagePopUp, HttpStatus.OK);
		  }

		  
		  
//		  // Get LandingPagePopUp by ID
//		  @GetMapping("/get/{landingPagePopUpId}")
//		  // @PreAuthorize("hasRole('client_HR')")
//		  public ResponseEntity<LandingPagePopUpDto> getLandingPagePopUpId(@PathVariable Long landingPagePopUpId) {
//		      Optional<LandingPagePopUpDto> landingPagePopUp = landingPagePopUpService.getLandingPagePopUpId(landingPagePopUpId);
//		      if (landingPagePopUp.isPresent()) {
//		          logger.info("Retrieved LandingPagePopUp with ID: {}", landingPagePopUpId);
//		          return new ResponseEntity<>(landingPagePopUp.get(), HttpStatus.OK);
//		      } else {
//		          logger.warn("LandingPagePopUp with ID {} not found", landingPagePopUpId);
//		          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		      }
//		  }
		  
		  
		  
		  
		  
//		  // Update LandingPagePopUp by ID
//		  @PutMapping("/update/{landingPagePopUpId}")
//		  // @PreAuthorize("hasRole('client_HR')")
//		  public ResponseEntity<LandingPagePopUpDto> updateLandingPagePopUp(@PathVariable Long landingPagePopUpId, @RequestBody LandingPagePopUpDto updatedLandingPagePopUpDto) {
//			  LandingPagePopUpDto updatedLandingPagePopUp = landingPagePopUpService.updateLandingPagePopUp(landingPagePopUpId, updatedLandingPagePopUpDto);
//		      if (updatedLandingPagePopUp != null) {
//		          logger.info("Updated LandingPagePopUp with ID: {}", landingPagePopUpId);
//		          return new ResponseEntity<>(updatedLandingPagePopUp, HttpStatus.OK);
//		      } else {
//		          logger.warn("LandingPagePopUp with ID {} not found for update", updatedLandingPagePopUp);
//		          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		      }
//		  }
		  
		  
		  
		  // Delete LandingPagePopUp by ID
		  @DeleteMapping("/delete/{landingPagePopUpId}")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<Void> deleteLandingPagePopUp(@PathVariable Long landingPagePopUpId) {
			  landingPagePopUpService.deleteLandingPagePopUp(landingPagePopUpId);
		      logger.info("Deleted LandingPagePopUp with ID: {}", landingPagePopUpId);
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  }
			
		  
//		  
//		// count total LandingPagePopUp
//			    @GetMapping("/count/landingpagepopup")
//			    // @PreAuthorize("hasRole('client_HR')")
//			    public long countLandingPagePopUp()
//			    {
//			    	return landingPagePopUpService.countLandingPagePopUp();
//			    }

}
