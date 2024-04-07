package com.orive.loan.controller;

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


import com.orive.loan.dto.GrantLoanDto;
import com.orive.loan.service.GrantLoanService;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "grantloan")
@CrossOrigin(origins = "*")
public class GrantLoanController {
	
	private static final Logger logger = LoggerFactory.getLogger(GrantLoanController.class);
	
	@Autowired
	private GrantLoanService grantLoanService;
	
	// Create a new GrantLoan
    @PostMapping("/create/grantloan")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<GrantLoanDto> createGrantLoan(@RequestBody GrantLoanDto grantLoanDto) {
    	GrantLoanDto createdGrantLoan = grantLoanService.createGrantLoan(grantLoanDto);
        logger.info("Created GrantLoan with name: {}", createdGrantLoan.getEmployeeName());
        return new ResponseEntity<>(createdGrantLoan, HttpStatus.CREATED);
    }

    // Get all GrantLoan   
    @GetMapping("/get/grantloan")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<GrantLoanDto>> getAllGrantLoan() {
        List<GrantLoanDto> grantLoan = grantLoanService.getAllGrantLoan();
        logger.info("Retrieved {} GrantLoan from the database", grantLoan.size());
        return new ResponseEntity<>(grantLoan, HttpStatus.OK);
    }

    // Get GrantLoan by ID
    @GetMapping("/get/{grantLoanId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<GrantLoanDto> getGrantLoanById(@PathVariable String grantLoanId) {
        Optional<GrantLoanDto> grantLoan = grantLoanService.getGrantLoanById(grantLoanId);
        if (grantLoan.isPresent()) {
            logger.info("Retrieved GrantLoan with ID: {}", grantLoanId);
            return new ResponseEntity<>(grantLoan.get(), HttpStatus.OK);
        } else {
            logger.warn("GrantLoan with ID {} not found", grantLoanId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Get GrantLoan by Employee ID
    @GetMapping("/findGrantLoan/{username}")
  //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
    public ResponseEntity<List<GrantLoanDto>> getGrantLoanByUsername(@PathVariable String username) {
        logger.info("Getting GrantLoan for username: {}", username);
        List<GrantLoanDto> transfers = grantLoanService.getGrantLoanByUsername(username);
        logger.info("Found {} GrantLoan for username: {}", transfers.size(), username);
        return ResponseEntity.ok(transfers);
    }

    // Update GrantLoan by ID
    @PutMapping("/update/{grantLoanId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<GrantLoanDto> updateGrantLoan(@PathVariable String grantLoanId, @RequestBody GrantLoanDto updatedGrantLoanDto) {
    	GrantLoanDto updatedGrantLoan = grantLoanService.updateGrantLoan(grantLoanId, updatedGrantLoanDto);
        if (updatedGrantLoan != null) {
            logger.info("Updated GrantLoan with ID: {}", grantLoanId);
            return new ResponseEntity<>(updatedGrantLoan, HttpStatus.OK);
        } else {
            logger.warn("GrantLoan with ID {} not found for update", grantLoanId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete GrantLoan by ID
    @DeleteMapping("/delete/{grantLoanId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteGrantLoan(@PathVariable String grantLoanId) {
    	grantLoanService.deleteGrantLoan(grantLoanId);
        logger.info("Deleted GrantLoan with ID: {}", grantLoanId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
    // Count the total GrantLoan
	    @GetMapping("/count/grantloan")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countGrantLoan()
	    {
	    	return grantLoanService.countGrantLoan();
	    }
}
