package com.orive.Payroll.Controller;

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

import com.orive.Payroll.Dto.AdvanceSaleryDto;
import com.orive.Payroll.Service.AdvanceSaleryService;
//import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping(value = "advancesalery")
@CrossOrigin(origins = "*")
public class AdvanceSaleryController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdvanceSaleryController.class);

    @Autowired
    private AdvanceSaleryService advanceSaleryService;
    
    
 // Create a new AdvanceSalery
    @PostMapping("/create/advancesalery")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<AdvanceSaleryDto> createAdvanceSalery(@RequestBody AdvanceSaleryDto advanceSaleryDto ) {
    	AdvanceSaleryDto createdadvanceSalery = advanceSaleryService.createAdvanceSalery(advanceSaleryDto);
        logger.info("Created AdvanceSalery with name: {}", createdadvanceSalery.getEmployeeName());
        return new ResponseEntity<>(createdadvanceSalery, HttpStatus.CREATED);
    }

    // Get all AdvanceSalery    
    @GetMapping("/get/advancesalery")
    // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<AdvanceSaleryDto>> getAllAdvanceSalery() {
        List<AdvanceSaleryDto> advanceSalery = advanceSaleryService.getAllAdvanceSalerys();
        logger.info("Retrieved {} AdvanceSalery from the database", advanceSalery.size());
        return new ResponseEntity<>(advanceSalery, HttpStatus.OK);
    }

    // Get AdvanceSalery by ID
    @GetMapping("/get/{advanceSaleryId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<AdvanceSaleryDto> getAdvanceSaleryById(@PathVariable String advanceSaleryId) {
        Optional<AdvanceSaleryDto> advanceSalery = advanceSaleryService.getAdvanceSaleryById(advanceSaleryId);
        if (advanceSalery.isPresent()) {
            logger.info("Retrieved AdvanceSalery with ID: {}", advanceSaleryId);
            return new ResponseEntity<>(advanceSalery.get(), HttpStatus.OK);
        } else {
            logger.warn("AdvanceSalery with ID {} not found", advanceSaleryId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update AdvanceSalery by ID
    @PutMapping("/update/{advanceSaleryId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<AdvanceSaleryDto> updateAdvanceSalery(@PathVariable String advanceSaleryId, @RequestBody AdvanceSaleryDto updatedAdvanceSaleryDto) {
    	AdvanceSaleryDto updatedAdvanceSalery = advanceSaleryService.updateAdvanceSalery(advanceSaleryId, updatedAdvanceSaleryDto);
        if (updatedAdvanceSalery != null) {
            logger.info("Updated AdvanceSalery with ID: {}", advanceSaleryId);
            return new ResponseEntity<>(updatedAdvanceSalery, HttpStatus.OK);
        } else {
            logger.warn("AdvanceSalery with ID {} not found for update", advanceSaleryId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete AdvanceSalery by ID
    @DeleteMapping("/delete/{advanceSaleryId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteAdvanceSalery(@PathVariable String advanceSaleryId) {
  	  advanceSaleryService.deleteAdvanceSalery(advanceSaleryId);
        logger.info("Deleted AdvanceSalery with ID: {}", advanceSaleryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
	    @GetMapping("/count/advancesalery")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countAdvanceSalery()
	    {
	    	return advanceSaleryService.countAdvanceSalery();
	    }
}
