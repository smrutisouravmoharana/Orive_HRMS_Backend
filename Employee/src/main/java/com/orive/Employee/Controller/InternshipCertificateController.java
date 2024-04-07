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

import com.orive.Employee.Dto.InternshipCertificateDto;
import com.orive.Employee.Service.InternshipCertificateService;

@RestController
@RequestMapping(value = "internshipcertificate")
@CrossOrigin(origins = "*")
public class InternshipCertificateController {
	
	private static final Logger logger = LoggerFactory.getLogger(InternshipCertificateController.class);
	
	@Autowired
	private InternshipCertificateService internshipCertificateService;
	
	
	// Create a new InternshipCertificate
    @PostMapping("/create/internshipcertificate")
    // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<InternshipCertificateDto> createInternshipCertificate(@RequestBody InternshipCertificateDto internshipCertificateDto) {
    	InternshipCertificateDto createdInternshipCertificate = internshipCertificateService.createInternshipCertificate(internshipCertificateDto);
        logger.info("Created InternshipCertificate with id: {}", createdInternshipCertificate.getInternsName());
        return new ResponseEntity<>(createdInternshipCertificate, HttpStatus.CREATED);
    }

    
    // Get all InternshipCertificate    
    @GetMapping("/get/internshipcertificate")
    // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<InternshipCertificateDto>> getAllInternshipCertificate() {
        List<InternshipCertificateDto> internshipCertificate = internshipCertificateService.getAllInternshipCertificate();
        logger.info("Retrieved {} InternshipCertificate from the database", internshipCertificate.size());
        return new ResponseEntity<>(internshipCertificate, HttpStatus.OK);
    }

    
    // Get InternshipCertificate by ID
    @GetMapping("/get/{internshipCertificateId}")
    // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<InternshipCertificateDto> getInternshipCertificateId(@PathVariable Long internshipCertificateId) {
        Optional<InternshipCertificateDto> internshipCertificate = internshipCertificateService.getInternshipCertificateById(internshipCertificateId);
        if (internshipCertificate.isPresent()) {
            logger.info("Retrieved InternshipCertificate with ID: {}", internshipCertificateId);
            return new ResponseEntity<>(internshipCertificate.get(), HttpStatus.OK);
        } else {
            logger.warn("InternshipCertificate with ID {} not found", internshipCertificateId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    // Update InternshipCertificate by ID
    @PutMapping("/update/{internshipCertificateId}")
    // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<InternshipCertificateDto> updateInternshipCertificate(@PathVariable Long internshipCertificateId, @RequestBody InternshipCertificateDto updatedInternshipCertificateDto) {
    	InternshipCertificateDto updatedInternshipCertificate = internshipCertificateService.updateInternshipCertificate(internshipCertificateId, updatedInternshipCertificateDto);
        if (updatedInternshipCertificate != null) {
            logger.info("Updated InternshipCertificate with ID: {}", internshipCertificateId);
            return new ResponseEntity<>(updatedInternshipCertificate, HttpStatus.OK);
        } else {
            logger.warn("InternshipCertificate with ID {} not found for update", internshipCertificateId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
  
    
    // Delete InternshipCertificate by ID
    @DeleteMapping("/delete/{internshipCertificateId}")
    // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteInternshipCertificate(@PathVariable Long internshipCertificateId) {
   	 internshipCertificateService.deleteInternshipCertificate(internshipCertificateId);
        logger.info("Deleted InternshipCertificate with ID: {}", internshipCertificateId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
 // Count the total  InternshipCertificate
	    @GetMapping("/count/internshipcertificate")
	    // @PreAuthorize("hasRole('client_HR')")
	    public long countInternshipCertificate()
	    {
	    	return internshipCertificateService.countInternshipCertificate();
	    }

}
