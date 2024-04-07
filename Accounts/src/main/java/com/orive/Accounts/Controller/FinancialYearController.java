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

import com.orive.Accounts.Dto.FinancialYearDto;
import com.orive.Accounts.Service.FinancialYearService;
//import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping(value = "financialyear")
@CrossOrigin(origins = "*")
public class FinancialYearController {

  private static final Logger logger=LoggerFactory.getLogger(FinancialYearController.class);
	
	@Autowired
	private FinancialYearService financialYearService;
	
	
	// Create a new FinancialYear
    @PostMapping("/create/financialYear")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<FinancialYearDto> createFinancialYearList(@RequestBody FinancialYearDto financialYearDto) {
    	FinancialYearDto createdFinancialYear = financialYearService.createFinancialYearList(financialYearDto);
        logger.info("Created FinancialYear with year: {}", createdFinancialYear.getFinancialYear());
        return new ResponseEntity<>(createdFinancialYear, HttpStatus.CREATED);
    }

    // Get all FinancialYear   
    @GetMapping("/get/financialYear")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<FinancialYearDto>> getAllFinancialYearList() {
        List<FinancialYearDto> financialYear = financialYearService.getAllFinancialYearList();
        logger.info("Retrieved {} FinancialYear from the database", financialYear.size());
        return new ResponseEntity<>(financialYear, HttpStatus.OK);
    }

    // Get FinancialYear by ID
    @GetMapping("/get/{financialYearId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<FinancialYearDto> getFinancialYearListById(@PathVariable Long financialYearId) {
        Optional<FinancialYearDto> financialYear = financialYearService.getFinancialYearById(financialYearId);
        if (financialYear.isPresent()) {
            logger.info("Retrieved FinancialYear with ID: {}", financialYearId);
            return new ResponseEntity<>(financialYear.get(), HttpStatus.OK);
        } else {
            logger.warn("FinancialYear with ID {} not found", financialYearId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update FinancialYear by ID
    @PutMapping("/update/{financialYearId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<FinancialYearDto> updateFinancialYearList(@PathVariable Long financialYearId, @RequestBody FinancialYearDto updatedFinancialYearDto) {
    	FinancialYearDto updatedFinancialYear = financialYearService.updateFinancialYearList(financialYearId, updatedFinancialYearDto);
        if (updatedFinancialYear != null) {
            logger.info("Updated FinancialYear with ID: {}", financialYearId);
            return new ResponseEntity<>(updatedFinancialYear, HttpStatus.OK);
        } else {
            logger.warn("AccountList with ID {} not found for update", financialYearId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete FinancialYear by ID
    @DeleteMapping("/delete/{financialYearId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteFinancialYearList(@PathVariable Long financialYearId) {
    	financialYearService.deleteFinancialYearList(financialYearId);
        logger.info("Deleted AccountList with ID: {}", financialYearId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
    // Count the total FinancialYear 
	    @GetMapping("/count/financialYear")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countFinancialYearList()
	    {
	    	return financialYearService.countFinancialYearList();
	    }
}
