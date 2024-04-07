package com.orive.Accounts.Controller;

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

import com.orive.Accounts.Dto.OpeningBalanceDTo;
import com.orive.Accounts.Service.OpeningBalanceService;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "openingbalance")
@CrossOrigin(origins = "*")
public class OpeningBalanceController {

private static final Logger logger=LoggerFactory.getLogger(OpeningBalanceController.class);
	
	@Autowired
	private OpeningBalanceService openingBalanceService;
	
	
	// Create a new OpeningBalance
    @PostMapping("/create/openingBalance")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<OpeningBalanceDTo> createOpeningBalance(@RequestBody OpeningBalanceDTo openingBalanceDTo) {
    	OpeningBalanceDTo createdOpeningBalance = openingBalanceService.createOpeningBalance(openingBalanceDTo);
        logger.info("Created OpeningBalance with year: {}", createdOpeningBalance.getOpeningBalanceId());
        return new ResponseEntity<>(createdOpeningBalance, HttpStatus.CREATED);
    }

    // Get all OpeningBalance   
    @GetMapping("/get/openingBalance")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<OpeningBalanceDTo>> getAllOpeningBalance() {
        List<OpeningBalanceDTo> openingBalance = openingBalanceService.getAllOpeningBalance();
        logger.info("Retrieved {} OpeningBalance from the database", openingBalance.size());
        return new ResponseEntity<>(openingBalance, HttpStatus.OK);
    }

    // Get OpeningBalance by ID
    @GetMapping("/get/{openingBalanceId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<OpeningBalanceDTo> getOpeningBalanceById(@PathVariable Long openingBalanceId) {
        Optional<OpeningBalanceDTo> openingBalance = openingBalanceService.getOpeningBalanceById(openingBalanceId);
        if (openingBalance.isPresent()) {
            logger.info("Retrieved OpeningBalance with ID: {}", openingBalanceId);
            return new ResponseEntity<>(openingBalance.get(), HttpStatus.OK);
        } else {
            logger.warn("OpeningBalance with ID {} not found", openingBalanceId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update OpeningBalance by ID
    @PutMapping("/update/{openingBalanceId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<OpeningBalanceDTo> updateOpeningBalance(@PathVariable Long openingBalanceId, @RequestBody OpeningBalanceDTo updatedOpeningBalanceDTo) {
    	OpeningBalanceDTo updatedOpeningBalance = openingBalanceService.updateOpeningBalance(openingBalanceId, updatedOpeningBalanceDTo);
        if (updatedOpeningBalance != null) {
            logger.info("Updated OpeningBalance with ID: {}", openingBalanceId);
            return new ResponseEntity<>(updatedOpeningBalance, HttpStatus.OK);
        } else {
            logger.warn("OpeningBalance with ID {} not found for update", openingBalanceId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete OpeningBalance by ID
    @DeleteMapping("/delete/{openingBalanceId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteOpeningBalance(@PathVariable Long openingBalanceId) {
    	openingBalanceService.deleteOpeningBalance(openingBalanceId);
        logger.info("Deleted OpeningBalance with ID: {}", openingBalanceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
    // Count the total OpeningBalance
	    @GetMapping("/count/openingBalance")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countOpeningBalance()
	    {
	    	return openingBalanceService.countOpeningBalance();
	    }
}
