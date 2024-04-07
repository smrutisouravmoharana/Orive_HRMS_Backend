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

import com.orive.Accounts.Dto.CreditVoucherDto;
import com.orive.Accounts.Service.CreditVoucherService;
//import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping(value = "creditvoucher")
@CrossOrigin(origins = "*")
public class CreditVoucherController {

	 private static final Logger logger=LoggerFactory.getLogger(CreditVoucherController.class);
		
		@Autowired
		private CreditVoucherService creditVoucherService;
		
		
		// Create a new CreditVoucher
	    @PostMapping("/create/creditVoucher")
	 // @PreAuthorize("hasRole('client_HR')")
	    public ResponseEntity<CreditVoucherDto> createCreditVoucher(@RequestBody CreditVoucherDto creditVoucherDto) {
	    	CreditVoucherDto createdCreditVoucher= creditVoucherService.createCreditVoucher(creditVoucherDto);
	        logger.info("Created CreditVoucher with year: {}", createdCreditVoucher.getCreditVoucherId());
	        return new ResponseEntity<>(createdCreditVoucher, HttpStatus.CREATED);
	    }

	    // Get all CreditVoucher   
	    @GetMapping("/get/creditVoucher")
	 // @PreAuthorize("hasRole('client_HR')")
	    public ResponseEntity<List<CreditVoucherDto>> getAllCreditVoucher() {
	        List<CreditVoucherDto> creditVoucher = creditVoucherService.getAllCreditVoucher();
	        logger.info("Retrieved {} CreditVoucher from the database", creditVoucher.size());
	        return new ResponseEntity<>(creditVoucher, HttpStatus.OK);
	    }

	    // Get CreditVoucher by ID
	    @GetMapping("/get/{creditVoucherId}")
	 // @PreAuthorize("hasRole('client_HR')")
	    public ResponseEntity<CreditVoucherDto> getCreditVoucherById(@PathVariable Long creditVoucherId) {
	        Optional<CreditVoucherDto> creditVoucher = creditVoucherService.getCreditVoucherById(creditVoucherId);
	        if (creditVoucher.isPresent()) {
	            logger.info("Retrieved CreditVoucher with ID: {}", creditVoucherId);
	            return new ResponseEntity<>(creditVoucher.get(), HttpStatus.OK);
	        } else {
	            logger.warn("CreditVoucher with ID {} not found", creditVoucherId);
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    // Update CreditVoucher by ID
	    @PutMapping("/update/{creditVoucherId}")
	 // @PreAuthorize("hasRole('client_HR')")
	    public ResponseEntity<CreditVoucherDto> updateCreditVoucher(@PathVariable Long creditVoucherId, @RequestBody CreditVoucherDto updatedCreditVoucherDto) {
	    	CreditVoucherDto updatedCreditVoucher = creditVoucherService.updateCreditVoucher(creditVoucherId, updatedCreditVoucherDto);
	        if (updatedCreditVoucher != null) {
	            logger.info("Updated CreditVoucher with ID: {}", creditVoucherId);
	            return new ResponseEntity<>(updatedCreditVoucher, HttpStatus.OK);
	        } else {
	            logger.warn("CreditVoucher with ID {} not found for update", creditVoucherId);
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	    


	    // Delete CreditVoucher by ID
	    @DeleteMapping("/delete/{creditVoucherId}")
	 // @PreAuthorize("hasRole('client_HR')")
	    public ResponseEntity<Void> deleteCreditVoucher(@PathVariable Long creditVoucherId) {
	    	creditVoucherService.deleteCreditVoucher(creditVoucherId);
	        logger.info("Deleted CreditVoucher with ID: {}", creditVoucherId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    
	 // Count the total  CreditVoucher 
		    @GetMapping("/count/creditVoucher")
		 // @PreAuthorize("hasRole('client_HR')")
		    public long countCreditVoucherList()
		    {
		    	return creditVoucherService.countCreditVoucher();
		    }
}
