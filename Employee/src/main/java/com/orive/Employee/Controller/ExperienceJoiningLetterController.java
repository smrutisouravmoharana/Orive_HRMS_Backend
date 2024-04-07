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

import com.orive.Employee.Dto.ExperienceJoiningLetterDto;
import com.orive.Employee.Dto.TransfersDto;
import com.orive.Employee.Service.ExperienceJoiningLetterService;

@RestController
@RequestMapping(value = "experiencejoiningletter")
@CrossOrigin(origins = "*")
public class ExperienceJoiningLetterController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExperienceJoiningLetterController.class);
	
	@Autowired
	private ExperienceJoiningLetterService experienceJoiningLetterService;
	
	
	// Create a new ExperienceJoiningLetter
    @PostMapping("/create/experiencejoiningletter")
    // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<ExperienceJoiningLetterDto> createExperienceJoiningLetter(@RequestBody ExperienceJoiningLetterDto experienceJoiningLetterDto) {
    	ExperienceJoiningLetterDto createdExperienceJoiningLetter = experienceJoiningLetterService.createExperienceJoiningLetter(experienceJoiningLetterDto);
        logger.info("Created ExperienceJoiningLetter with id: {}", createdExperienceJoiningLetter.getEmployeeName());
        return new ResponseEntity<>(createdExperienceJoiningLetter, HttpStatus.CREATED);
    }

    
    // Get all ExperienceJoiningLetter   
    @GetMapping("/get/experiencejoiningletter")
    // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<ExperienceJoiningLetterDto>> getAllExperienceJoiningLetter() {
        List<ExperienceJoiningLetterDto> experienceJoiningLetter = experienceJoiningLetterService.getAllExperienceJoiningLetter();
        logger.info("Retrieved {} ExperienceJoiningLetter from the database", experienceJoiningLetter.size());
        return new ResponseEntity<>(experienceJoiningLetter, HttpStatus.OK);
    }

    // Get ExperienceJoiningLetter by ID
    @GetMapping("/get/{experienceJoiningLetterId}")
    // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<ExperienceJoiningLetterDto> getExperienceJoiningLetterId(@PathVariable Long experienceJoiningLetterId) {
        Optional<ExperienceJoiningLetterDto> experienceJoiningLetter = experienceJoiningLetterService.getExperienceJoiningLetterById(experienceJoiningLetterId);
        if (experienceJoiningLetter.isPresent()) {
            logger.info("Retrieved ExperienceJoiningLetter with ID: {}", experienceJoiningLetterId);
            return new ResponseEntity<>(experienceJoiningLetter.get(), HttpStatus.OK);
        } else {
            logger.warn("ExperienceJoiningLetter with ID {} not found", experienceJoiningLetterId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    //Get Complaints by Employee ID
    @GetMapping("/findexperiencejoiningletter/{username}")
    //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
    public ResponseEntity<List<ExperienceJoiningLetterDto>> getTransfersByUsername(@PathVariable String username) {
        logger.info("Getting ExperienceJoiningLetter for username: {}", username);
        List<ExperienceJoiningLetterDto> transfers = experienceJoiningLetterService.getExperienceJoiningLetterByUsername(username);
        logger.info("Found {} ExperienceJoiningLetter for username: {}", transfers.size(), username);
        return ResponseEntity.ok(transfers);
    }

    // Update ExperienceJoiningLetter by ID
    @PutMapping("/update/{experienceJoiningLetterId}")
    // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<ExperienceJoiningLetterDto> updateExperienceJoiningLetter(@PathVariable Long experienceJoiningLetterId, @RequestBody ExperienceJoiningLetterDto updatedExperienceJoiningLetterDto) {
    	ExperienceJoiningLetterDto updatedExperienceJoiningLetter = experienceJoiningLetterService.updateExperienceJoiningLetter(experienceJoiningLetterId, updatedExperienceJoiningLetterDto);
        if (updatedExperienceJoiningLetter != null) {
            logger.info("Updated ExperienceJoiningLetter with ID: {}", experienceJoiningLetterId);
            return new ResponseEntity<>(updatedExperienceJoiningLetter, HttpStatus.OK);
        } else {
            logger.warn("ExperienceJoiningLetter with ID {} not found for update", experienceJoiningLetterId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete ExperienceJoiningLetter by ID
    @DeleteMapping("/delete/{experienceJoiningLetterId}")
    // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteExperienceJoiningLetter(@PathVariable Long experienceJoiningLetterId) {
   	 experienceJoiningLetterService.deleteExperienceJoiningLetter(experienceJoiningLetterId);
        logger.info("Deleted ExperienceJoiningLetter with ID: {}", experienceJoiningLetterId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
 // Count the total  ExperienceJoiningLetter
	    @GetMapping("/count/experiencejoiningletter")
	    // @PreAuthorize("hasRole('client_HR')")
	    public long countExperienceJoiningLetter()
	    {
	    	return experienceJoiningLetterService.countExperienceJoiningLetter();
	    }

}
