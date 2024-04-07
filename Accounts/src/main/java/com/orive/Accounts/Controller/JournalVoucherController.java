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

import com.orive.Accounts.Dto.JournalVoucherDto;
import com.orive.Accounts.Service.JournalVoucherService;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "journalvoucher")
@CrossOrigin(origins = "*")
public class JournalVoucherController {

private static final Logger logger=LoggerFactory.getLogger(JournalVoucherController.class);
	
	@Autowired
	private JournalVoucherService journalVoucherService;
	
	
	// Create a new JournalVoucher
    @PostMapping("/create/journalVoucher")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<JournalVoucherDto> createJournalVoucher(@RequestBody JournalVoucherDto journalVoucherDto) {
    	JournalVoucherDto createdJournalVoucher = journalVoucherService.createJournalVoucher(journalVoucherDto);
        logger.info("Created JournalVoucher with year: {}", createdJournalVoucher.getJournalVoucherId());
        return new ResponseEntity<>(createdJournalVoucher, HttpStatus.CREATED);
    }

    // Get all JournalVoucher   
    @GetMapping("/get/journalVoucher")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<JournalVoucherDto>> getAllJournalVoucher() {
        List<JournalVoucherDto> journalVoucher = journalVoucherService.getAllJournalVoucher();
        logger.info("Retrieved {} JournalVoucher from the database", journalVoucher.size());
        return new ResponseEntity<>(journalVoucher, HttpStatus.OK);
    }

    // Get JournalVoucher by ID
    @GetMapping("/get/{journalVoucherId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<JournalVoucherDto> getJournalVoucherById(@PathVariable Long journalVoucherId) {
        Optional<JournalVoucherDto> journalVoucher = journalVoucherService.getJournalVoucherById(journalVoucherId);
        if (journalVoucher.isPresent()) {
            logger.info("Retrieved JournalVoucher with ID: {}", journalVoucherId);
            return new ResponseEntity<>(journalVoucher.get(), HttpStatus.OK);
        } else {
            logger.warn("JournalVoucher with ID {} not found", journalVoucherId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update JournalVoucher by ID
    @PutMapping("/update/{journalVoucherId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<JournalVoucherDto> updateJournalVoucher(@PathVariable Long journalVoucherId, @RequestBody JournalVoucherDto updatedJournalVoucherDto) {
    	JournalVoucherDto updatedJournalVoucher = journalVoucherService.updateJournalVoucher(journalVoucherId, updatedJournalVoucherDto);
        if (updatedJournalVoucher != null) {
            logger.info("Updated JournalVoucher with ID: {}", journalVoucherId);
            return new ResponseEntity<>(updatedJournalVoucher, HttpStatus.OK);
        } else {
            logger.warn("JournalVoucher with ID {} not found for update", journalVoucherId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete JournalVoucher by ID
    @DeleteMapping("/delete/{journalVoucherId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteJournalVoucher(@PathVariable Long journalVoucherId) {
    	journalVoucherService.deleteJournalVoucher(journalVoucherId);
        logger.info("Deleted JournalVoucher with ID: {}", journalVoucherId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
   // Count the total JournalVoucher
	    @GetMapping("/count/journalVoucher")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countJournalVoucher()
	    {
	    	return journalVoucherService.countJournalVoucherList();
	    }
}
