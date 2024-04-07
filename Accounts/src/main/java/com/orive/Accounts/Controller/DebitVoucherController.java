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

import com.orive.Accounts.Dto.DebitVoucherDto;
import com.orive.Accounts.Service.DebitVoucherService;
//import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping(value = "debitvoucher")
@CrossOrigin(origins = "*")
public class DebitVoucherController {

    private static final Logger logger=LoggerFactory.getLogger(DebitVoucherController.class);
	
	@Autowired
	private DebitVoucherService debitVoucherService;
	
	
	// Create a new DebitVoucher
    @PostMapping("/create/debitVoucher")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<DebitVoucherDto> createDebitVoucher(@RequestBody DebitVoucherDto debitVoucherDto) {
    	DebitVoucherDto createdDebitVoucher = debitVoucherService.createDebitVoucher(debitVoucherDto);
        logger.info("Created DebitVoucher with year: {}", createdDebitVoucher.getDebitVoucherId());
        return new ResponseEntity<>(createdDebitVoucher, HttpStatus.CREATED);
    }

    // Get all DebitVoucher   
    @GetMapping("/get/debitVoucher")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<DebitVoucherDto>> getAllDebitVoucher() {
        List<DebitVoucherDto> debitVoucher = debitVoucherService.getAllDebitVoucher();
        logger.info("Retrieved {} DebitVoucher from the database", debitVoucher.size());
        return new ResponseEntity<>(debitVoucher, HttpStatus.OK);
    }

    // Get DebitVoucher by ID
    @GetMapping("/get/{debitVoucherId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<DebitVoucherDto> getDebitVoucherById(@PathVariable Long debitVoucherId) {
        Optional<DebitVoucherDto> debitVoucher = debitVoucherService.getDebitVoucherById(debitVoucherId);
        if (debitVoucher.isPresent()) {
            logger.info("Retrieved DebitVoucher with ID: {}", debitVoucherId);
            return new ResponseEntity<>(debitVoucher.get(), HttpStatus.OK);
        } else {
            logger.warn("DebitVoucher with ID {} not found", debitVoucherId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update DebitVoucher by ID
    @PutMapping("/update/{debitVoucherId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<DebitVoucherDto> updateDebitVoucher(@PathVariable Long debitVoucherId, @RequestBody DebitVoucherDto updatedDebitVoucherDto) {
    	DebitVoucherDto updatedDebitVoucher = debitVoucherService.updateDebitVoucher(debitVoucherId, updatedDebitVoucherDto);
        if (updatedDebitVoucher != null) {
            logger.info("Updated DebitVoucher with ID: {}", debitVoucherId);
            return new ResponseEntity<>(updatedDebitVoucher, HttpStatus.OK);
        } else {
            logger.warn("DebitVoucher with ID {} not found for update", debitVoucherId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete DebitVoucher by ID
    @DeleteMapping("/delete/{debitVoucherId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteDebitVoucher(@PathVariable Long debitVoucherId) {
    	debitVoucherService.deleteDebitVoucher(debitVoucherId);
        logger.info("Deleted DebitVoucher with ID: {}", debitVoucherId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
    // Count the total DebitVoucher 
	    @GetMapping("/count/debitVoucher")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countDebitVoucherList()
	    {
	    	return debitVoucherService.countDebitVoucher();
	    }
}
