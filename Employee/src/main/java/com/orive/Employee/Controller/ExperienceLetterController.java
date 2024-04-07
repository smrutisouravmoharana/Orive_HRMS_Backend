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

import com.orive.Employee.Dto.ExperienceLetterDto;
import com.orive.Employee.Dto.TransfersDto;
import com.orive.Employee.Service.ExperienceLetterService;

@RestController
@RequestMapping(value = "experienceletter")
@CrossOrigin(origins = "*")
public class ExperienceLetterController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExperienceLetterController.class);
	
	@Autowired
	private ExperienceLetterService experienceLetterService;
	
	// Create a new ExperienceLetter
	   @PostMapping("/create/experienceletter")
	   // @PreAuthorize("hasRole('client_HR')")
	   public ResponseEntity<ExperienceLetterDto> createExperienceLetter(@RequestBody ExperienceLetterDto experienceLetterDto) {
		   ExperienceLetterDto createdExperienceLetter = experienceLetterService.createExperienceLetter(experienceLetterDto);
	       logger.info("Created ExperienceLetter with id: {}", createdExperienceLetter.getEmployeeName());
	       return new ResponseEntity<>(createdExperienceLetter, HttpStatus.CREATED);
	   }

	   // Get all ExperienceLetter	   
	   @GetMapping("/get/experienceletter")
	   // @PreAuthorize("hasRole('client_HR')")
	   public ResponseEntity<List<ExperienceLetterDto>> getAllExperienceLetter() {
	       List<ExperienceLetterDto> experienceLetter = experienceLetterService.getAllExperienceLetter();
	       logger.info("Retrieved {} ExperienceLetter from the database", experienceLetter.size());
	       return new ResponseEntity<>(experienceLetter, HttpStatus.OK);
	   }

	   // Get ExperienceLetter by ID
	   @GetMapping("/get/{experienceLetterId}")
	   // @PreAuthorize("hasRole('client_HR')")
	   public ResponseEntity<ExperienceLetterDto> getExperienceLetterId(@PathVariable Long experienceLetterId) {
	       Optional<ExperienceLetterDto> experienceLetter = experienceLetterService.getExperienceLetterById(experienceLetterId);
	       if (experienceLetter.isPresent()) {
	           logger.info("Retrieved ExperienceLetter with ID: {}", experienceLetterId);
	           return new ResponseEntity<>(experienceLetter.get(), HttpStatus.OK);
	       } else {
	           logger.warn("Promotions with ID {} not found", experienceLetterId);
	           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	       }
	   }
	   
	 //Get Complaints by Employee ID
	   @GetMapping("/findexperienceletter/{username}")
	   //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
	      public ResponseEntity<List<ExperienceLetterDto>> getExperienceLetterByUsername(@PathVariable String username) {
	          logger.info("Getting ExperienceLetter for username: {}", username);
	          List<ExperienceLetterDto> transfers = experienceLetterService.getTransfersByUsername(username);
	          logger.info("Found {} ExperienceLetter for username: {}", transfers.size(), username);
	          return ResponseEntity.ok(transfers);
	      }

	   
	   // Update ExperienceLetter by ID
	   @PutMapping("/update/{experienceLetterId}")
	   // @PreAuthorize("hasRole('client_HR')")
	   public ResponseEntity<ExperienceLetterDto> updateExperienceLetter(@PathVariable Long experienceLetterId, @RequestBody ExperienceLetterDto updatedExperienceLetterDto) {
		   ExperienceLetterDto updatedExperienceLetter = experienceLetterService.updateExperienceLetter(experienceLetterId, updatedExperienceLetterDto);
	       if (updatedExperienceLetter != null) {
	           logger.info("Updated ExperienceLetter with ID: {}", experienceLetterId);
	           return new ResponseEntity<>(updatedExperienceLetter, HttpStatus.OK);
	       } else {
	           logger.warn("Promotions with ID {} not found for update", experienceLetterId);
	           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	       }
	   }

	   
	   // Delete ExperienceLetter by ID
	   @DeleteMapping("/delete/{experienceLetterId}")
	   // @PreAuthorize("hasRole('client_HR')")
	   public ResponseEntity<Void> deleteExperienceLetter(@PathVariable Long experienceLetterId) {
		   experienceLetterService.deleteExperienceLetter(experienceLetterId);
	       logger.info("Deleted ExperienceLetter with ID: {}", experienceLetterId);
	       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   }
	   
		    
	   // Count the total ExperienceLetter 
		    @GetMapping("/count/experienceletter")
		    // @PreAuthorize("hasRole('client_HR')")
		    public long countExperienceLetter()
		    {
		    	return experienceLetterService.countExperienceLetter();
		    }
       }
