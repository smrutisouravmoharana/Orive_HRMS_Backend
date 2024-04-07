package com.orive.bank.controller;

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


import com.orive.bank.dto.AddBankDto;
import com.orive.bank.service.AddBankService;
//import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "addbank")
@CrossOrigin(origins = "*")
public class AddBankController {
	
	private static final Logger logger=LoggerFactory.getLogger(AddBankController.class);
	
	@Autowired
	private AddBankService addBankService;
	
//	// Create a new AddBank
//    @PostMapping("/create/addbank")
// // @PreAuthorize("hasRole('client_admin')")
//    public ResponseEntity<AddBankDto> createAddBank(@RequestBody AddBankDto addBankDto) {
//    	AddBankDto createdAddBank = addBankService.createAddBank(addBankDto);
//        logger.info("Created AddBank with name: {}", createdAddBank.getAccountName());
//        return new ResponseEntity<>(createdAddBank, HttpStatus.CREATED);
//    }
	
	// Create a new Bank
    @PostMapping("/create/bank")
 // @PreAuthorize("hasRole('client_HR')")
  public ResponseEntity<?> createBank(@Valid @RequestBody AddBankDto addBankDto) {
      try {
          // Check if the Bank name already exists
          Optional<AddBankDto> existingDepartment = addBankService.getBankByAccountNumber(addBankDto.getAccountNumber());
          if (existingDepartment.isPresent()) {
              // Bank name already exists, return a bad request response with the error message
              return ResponseEntity.badRequest().body("Bank with name '" + addBankDto.getAccountNumber() + "' already exists");
          }

          // Bank name is unique, proceed with creating the Bank
          AddBankDto createdDepartment = addBankService.createBank(addBankDto);
          logger.info("Bank Department with name: {}", createdDepartment.getBankName());
          
          // Return the created Bank with a success status code
          return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
      } catch (Exception e) {
          // Handle any unexpected errors
          logger.error("Error creating Bank: {}", e.getMessage());
          
          // Return an internal server error response with a generic error message
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Department");
      }
  }
	
	
//	 @PostMapping("/create")
//	    public ResponseEntity<AddBankDto> createPromotions(@Valid @RequestBody AddBankDto promotionsDto) {
//	        logger.info("Received request to create bank: {}", promotionsDto);
//	        AddBankDto createdPromotions = addBankService.createPromotions(promotionsDto);
//	        logger.info("Created promotion with ID: {}", createdPromotions.getAddBankId());
//	        return new ResponseEntity<>(createdPromotions, HttpStatus.CREATED);
//	    }

    // Get all AddBank   
    @GetMapping("/get/addbank")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<AddBankDto>> getAllAddBank() {
        List<AddBankDto> addBank = addBankService.getAllAddBank();
        logger.info("Retrieved {} AddBank from the database", addBank.size());
        return new ResponseEntity<>(addBank, HttpStatus.OK);
    }

    // Get AddBank by ID
    @GetMapping("/get/{addBankId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<AddBankDto> getAddBankById(@PathVariable String addBankId) {
        Optional<AddBankDto> addBank = addBankService.getAddBankById(addBankId);
        if (addBank.isPresent()) {
            logger.info("Retrieved AddBank with ID: {}", addBankId);
            return new ResponseEntity<>(addBank.get(), HttpStatus.OK);
        } else {
            logger.warn("AddBank with ID {} not found", addBankId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update AddBank by ID
    @PutMapping("/update/{addBankId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<AddBankDto> updateAddBank(@PathVariable String addBankId, @RequestBody AddBankDto updatedAddBankDto) {
    	AddBankDto updatedAddBank = addBankService.updateAddBank(addBankId, updatedAddBankDto);
        if (updatedAddBank != null) {
            logger.info("Updated AddBank with ID: {}", addBankId);
            return new ResponseEntity<>(updatedAddBank, HttpStatus.OK);
        } else {
            logger.warn("AddBank with ID {} not found for update", addBankId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete AddBank by ID
    @DeleteMapping("/delete/{addBankId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteAddBank(@PathVariable String addBankId) {
    	addBankService.deleteAddBank(addBankId);
        logger.info("Deleted AddBank with ID: {}", addBankId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
   // count the total AddBank
	    @GetMapping("/count/addbank")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countAddBank()
	    {
	    	return addBankService.countAddBank();
	    }
}
