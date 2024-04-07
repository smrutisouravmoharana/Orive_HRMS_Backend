package com.orive.Transactions.Controller;

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

import com.orive.Transactions.Dto.DepositDto;
import com.orive.Transactions.Service.DepositService;

@RestController
@RequestMapping(value = "deposit")
@CrossOrigin(origins = "*")
public class DepositController {
	
	private static final Logger logger = LoggerFactory.getLogger(DepositController.class);
	
	@Autowired
	private DepositService depositService;
	
	
	// Create a new Deposit
    @PostMapping("/create/deposit")
    public ResponseEntity<DepositDto> createDeposit(@RequestBody DepositDto depositDto) {
    	DepositDto createdDeposit = depositService.createDeposit(depositDto);
        logger.info("Created Deposit with name: {}", createdDeposit.getEmployeeFullName());
        return new ResponseEntity<>(createdDeposit, HttpStatus.CREATED);
    }

    // Get all Deposit   
    @GetMapping("/get/deposit")
    public ResponseEntity<List<DepositDto>> getAllDeposit() {
        List<DepositDto> deposit = depositService.getAllDeposit();
        logger.info("Retrieved {} Deposit from the database", deposit.size());
        return new ResponseEntity<>(deposit, HttpStatus.OK);
    }

    // Get Deposit by ID
    @GetMapping("/get/{depositId}")
    public ResponseEntity<DepositDto> getDepositById(@PathVariable Long depositId) {
        Optional<DepositDto> deposit = depositService.getDepositById(depositId);
        if (deposit.isPresent()) {
            logger.info("Retrieved Deposit with ID: {}", depositId);
            return new ResponseEntity<>(deposit.get(), HttpStatus.OK);
        } else {
            logger.warn("Deposit with ID {} not found", depositId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Deposit by ID
    @PutMapping("/update/{depositId}")
    public ResponseEntity<DepositDto> updateDeposit(@PathVariable Long depositId, @RequestBody DepositDto updatedDepositDto) {
    	DepositDto updatedDeposit = depositService.updateDeposit(depositId, updatedDepositDto);
        if (updatedDeposit != null) {
            logger.info("Updated Deposit with ID: {}", depositId);
            return new ResponseEntity<>(updatedDeposit, HttpStatus.OK);
        } else {
            logger.warn("Deposit with ID {} not found for update", depositId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete Deposit by ID
    @DeleteMapping("/delete/{depositId}")
    public ResponseEntity<Void> deleteDeposit(@PathVariable Long depositId) {
    	depositService.deleteDeposit(depositId);
        logger.info("Deleted Deposit with ID: {}", depositId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
	    @GetMapping("/count/deposit")
	    public long countDeposit()
	    {
	    	return depositService.countDeposit();
	    }
}
