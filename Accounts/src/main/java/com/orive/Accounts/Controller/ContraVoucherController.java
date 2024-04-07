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

import com.orive.Accounts.Dto.ContraVoucherDto;
import com.orive.Accounts.Service.ContraVoucherService;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "contravoucher")
@CrossOrigin(origins = "*")
public class ContraVoucherController {

     private static final Logger logger=LoggerFactory.getLogger(ContraVoucherController.class);
	
	@Autowired
	private ContraVoucherService contraVoucherService;
	
	
	// Create a new ContraVoucher
    @PostMapping("/create/contraVoucher")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<ContraVoucherDto> createContraVoucher(@RequestBody ContraVoucherDto contraVoucherDto) {
    	ContraVoucherDto createdContraVoucher = contraVoucherService.createContraVoucher(contraVoucherDto);
        logger.info("Created ContraVoucher with name: {}", createdContraVoucher.getReversedAccountHead());
        return new ResponseEntity<>(createdContraVoucher, HttpStatus.CREATED);
    }

    // Get all ContraVoucher   
    @GetMapping("/get/contraVoucher")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<ContraVoucherDto>> getAllContraVoucher() {
        List<ContraVoucherDto> contraVoucher = contraVoucherService.getAllContraVoucher();
        logger.info("Retrieved {} ContraVoucher from the database", contraVoucher.size());
        return new ResponseEntity<>(contraVoucher, HttpStatus.OK);
    }

    // Get ContraVoucher by ID
    @GetMapping("/get/{contraVoucherId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<ContraVoucherDto> getContraVoucherById(@PathVariable Long contraVoucherId) {
        Optional<ContraVoucherDto> contraVoucher = contraVoucherService.getContraVoucherById(contraVoucherId);
        if (contraVoucher.isPresent()) {
            logger.info("Retrieved ContraVoucher with ID: {}", contraVoucherId);
            return new ResponseEntity<>(contraVoucher.get(), HttpStatus.OK);
        } else {
            logger.warn("ContraVoucher with ID {} not found", contraVoucherId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update ContraVoucher by ID
    @PutMapping("/update/{contraVoucherId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<ContraVoucherDto> updateContraVoucher(@PathVariable Long contraVoucherId, @RequestBody ContraVoucherDto updatedContraVoucherDto) {
    	ContraVoucherDto updatedContraVoucher = contraVoucherService.updateContraVoucher(contraVoucherId, updatedContraVoucherDto);
        if (updatedContraVoucher != null) {
            logger.info("Updated ContraVoucher with ID: {}", contraVoucherId);
            return new ResponseEntity<>(updatedContraVoucher, HttpStatus.OK);
        } else {
            logger.warn("ContraVoucher with ID {} not found for update", contraVoucherId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete ContraVoucher by ID
    @DeleteMapping("/delete/{contraVoucherId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteContraVoucher(@PathVariable Long contraVoucherId) {
    	contraVoucherService.deleteContraVoucher(contraVoucherId);
        logger.info("Deleted ContraVoucher with ID: {}", contraVoucherId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	   
    // count the total ContraVoucher 
	    @GetMapping("/count/contraVoucher")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countContraVoucher()
	    {
	    	return contraVoucherService.countContraVoucher();
	    }
}
