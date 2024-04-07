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

import com.orive.orive_website.DTO.ReachUsDto;
import com.orive.orive_website.Service.ReachUsService;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "reachus")
@CrossOrigin(origins = "*")
public class ReachUsController {
	
private static final Logger logger = LoggerFactory.getLogger(ReachUsController.class);
	
	@Autowired
	private ReachUsService reachUsService ;
	
//	// Create a new ReachUs
//		  @PostMapping("/create/reachus")
//		 // @PreAuthorize("hasRole('client_HR')")
//		  public ResponseEntity<ReachUsDto> createReachUs(@RequestBody ReachUsDto reachUsDto) {
//			  ReachUsDto createdReachUs = reachUsService.createReachUs(reachUsDto);
//		      logger.info("Created ReachUs with id: {}", createdReachUs.getReachUsId());
//		      return new ResponseEntity<>(createdReachUs, HttpStatus.CREATED);
//		  }

		  
		  // Get all ReachUs  
		  @GetMapping("/get/reachus")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<List<ReachUsDto>> getAllReachUs() {
		      List<ReachUsDto> reachUs = reachUsService.getAllReachUs();
		      logger.info("Retrieved {} ReachUs from the database", reachUs.size());
		      return new ResponseEntity<>(reachUs, HttpStatus.OK);
		  }
		  
		  

//		  // Get ReachUs by ID
//		  @GetMapping("/get/{reachUsId}")
//		  // @PreAuthorize("hasRole('client_HR')")
//		  public ResponseEntity<ReachUsDto> getReachUsId(@PathVariable Long reachUsId) {
//		      Optional<ReachUsDto> reachUs = reachUsService.getReachUsId(reachUsId);
//		      if (reachUs.isPresent()) {
//		          logger.info("Retrieved ReachUs with ID: {}", reachUsId);
//		          return new ResponseEntity<>(reachUs.get(), HttpStatus.OK);
//		      } else {
//		          logger.warn("ReachUs with ID {} not found", reachUsId);
//		          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		      }
//		  }
		  
		  
		  
//		  // Update ReachUs by ID
//		  @PutMapping("/update/{reachUsId}")
//		  // @PreAuthorize("hasRole('client_HR')")
//		  public ResponseEntity<ReachUsDto> updateReachUs(@PathVariable Long reachUsId, @RequestBody ReachUsDto updatedReachUsDto) {
//			  ReachUsDto updatedReachUs = reachUsService.updateReachUs(reachUsId, updatedReachUsDto);
//		      if (updatedReachUs != null) {
//		          logger.info("Updated ReachUs with ID: {}", reachUsId);
//		          return new ResponseEntity<>(updatedReachUs, HttpStatus.OK);
//		      } else {
//		          logger.warn("ReachUs with ID {} not found for update", updatedReachUs);
//		          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		      }
//		  }
		  
		  
		  
		  // Delete ReachUs by ID
		  @DeleteMapping("/delete/{reachUsId}")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<Void> deleteReachUs(@PathVariable Long reachUsId) {
			  reachUsService.deleteReachUs(reachUsId);
		      logger.info("Deleted ReachUs with ID: {}", reachUsId);
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  }
		  
		  
//			//count all  ReachUs   
//			    @GetMapping("/count/reachus")
//			   // @PreAuthorize("hasRole('client_HR')")
//			    public long countReachUs()
//			    {
//			    	return reachUsService.countReachUs();
//			    }

}
