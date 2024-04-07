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

import com.orive.Employee.Dto.InternJoiningLetterDto;
import com.orive.Employee.Dto.TransfersDto;
import com.orive.Employee.Service.InternJoiningLetterService;

@RestController
@RequestMapping(value = "internjoiningletter")
@CrossOrigin(origins = "*")
public class InternJoiningLetterController {
	
	private static final Logger logger = LoggerFactory.getLogger(InternJoiningLetterController.class);
	
	@Autowired
	private InternJoiningLetterService internJoiningLetterService;
	
	// Create a new InternJoiningLetter
    @PostMapping("/create/internjoiningletter")
    // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<InternJoiningLetterDto> createInternJoiningLetter(@RequestBody InternJoiningLetterDto internJoiningLetterDto) {
    	InternJoiningLetterDto createdInternJoiningLetter = internJoiningLetterService.createInternJoiningLetter(internJoiningLetterDto);
        logger.info("Created InternJoiningLetter with id: {}", createdInternJoiningLetter.getInternJoiningLetterId());
        return new ResponseEntity<>(createdInternJoiningLetter, HttpStatus.CREATED);
    }

    // Get all InternJoiningLetter   
    @GetMapping("/get/internjoiningletter")
    // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<InternJoiningLetterDto>> getAllInternJoiningLetter() {
        List<InternJoiningLetterDto> internJoiningLetter = internJoiningLetterService.getAllInternJoiningLetter();
        logger.info("Retrieved {} InternJoiningLetter from the database", internJoiningLetter.size());
        return new ResponseEntity<>(internJoiningLetter, HttpStatus.OK);
    }

    // Get InternJoiningLetter by ID
    @GetMapping("/get/{internJoiningLetterId}")
    // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<InternJoiningLetterDto> getInternJoiningLetterId(@PathVariable Long internJoiningLetterId) {
        Optional<InternJoiningLetterDto> internJoiningLetter = internJoiningLetterService.getInternJoiningLetterId(internJoiningLetterId);
        if (internJoiningLetter.isPresent()) {
            logger.info("Retrieved InternJoiningLetter with ID: {}", internJoiningLetterId);
            return new ResponseEntity<>(internJoiningLetter.get(), HttpStatus.OK);
        } else {
            logger.warn("InternJoiningLetter with ID {} not found", internJoiningLetterId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    //Get Complaints by Employee ID
    @GetMapping("/findInternJoiningLetter/{username}")
  //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
    public ResponseEntity<List<InternJoiningLetterDto>> getInternJoiningLetterByUsername(@PathVariable String username) {
        logger.info("Getting InternJoiningLetter for username: {}", username);
        List<InternJoiningLetterDto> transfers = internJoiningLetterService.getInternJoiningLetterByUsername(username);
        logger.info("Found {} InternJoiningLetter for username: {}", transfers.size(), username);
        return ResponseEntity.ok(transfers);
    }

    
    // Update InternJoiningLetter by ID
    @PutMapping("/update/{internJoiningLetterId}")
    // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<InternJoiningLetterDto> updateInternJoiningLetter(@PathVariable Long internJoiningLetterId, @RequestBody InternJoiningLetterDto updatedInternJoiningLetterDto) {
    	InternJoiningLetterDto updatedInternJoiningLetter = internJoiningLetterService.updateInternJoiningLetter(internJoiningLetterId, updatedInternJoiningLetterDto);
        if (updatedInternJoiningLetter != null) {
            logger.info("Updated InternJoiningLetter with ID: {}", internJoiningLetterId);
            return new ResponseEntity<>(updatedInternJoiningLetter, HttpStatus.OK);
        } else {
            logger.warn("InternJoiningLetter with ID {} not found for update", internJoiningLetterId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete InternJoiningLetter by ID
    @DeleteMapping("/delete/{internJoiningLetterId}")
    // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteInternJoiningLetter(@PathVariable Long internJoiningLetterId) {
   	 internJoiningLetterService.deleteInternJoiningLetter(internJoiningLetterId);
        logger.info("Deleted InternJoiningLetter with ID: {}", internJoiningLetterId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
 // Count the total  InternJoiningLetter
	    @GetMapping("/count/internjoiningletter")
	    // @PreAuthorize("hasRole('client_HR')")
	    public long countInternJoiningLetter()
	    {
	    	return internJoiningLetterService.countInternJoiningLetter();
	    }

}
