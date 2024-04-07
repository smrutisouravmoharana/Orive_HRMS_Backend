package com.orive.PayeesAndPayers.Controller;

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

import com.orive.PayeesAndPayers.Dto.PayersDto;
import com.orive.PayeesAndPayers.Service.PayersService;

@RestController
@RequestMapping(value = "payers")
@CrossOrigin(origins = "*")
public class PayersController {
	
	private static final Logger logger = LoggerFactory.getLogger(PayersController.class);
	
	@Autowired
	private PayersService payersService;
	
	// Create a new Payers
    @PostMapping("/create/payers")
    public ResponseEntity<PayersDto> createPayers(@RequestBody PayersDto payersDto) {
    	PayersDto createdPayers = payersService.createPayers(payersDto);
        logger.info("Created Payers with name: {}", createdPayers.getEmployeeFullName());
        return new ResponseEntity<>(createdPayers, HttpStatus.CREATED);
    }

    // Get all Payers   
    @GetMapping("/get/payers")
    public ResponseEntity<List<PayersDto>> getAllPayers() {
        List<PayersDto> payers = payersService.getAllPayers();
        logger.info("Retrieved {} Payers from the database", payers.size());
        return new ResponseEntity<>(payers, HttpStatus.OK);
    }

    // Get Payers by ID
    @GetMapping("/get/{payersId}")
    public ResponseEntity<PayersDto> getPayersById(@PathVariable Long payersId) {
        Optional<PayersDto> payers = payersService.getPayersById(payersId);
        if (payers.isPresent()) {
            logger.info("Retrieved Payers with ID: {}", payersId);
            return new ResponseEntity<>(payers.get(), HttpStatus.OK);
        } else {
            logger.warn("Payers with ID {} not found", payersId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Payers by ID
    @PutMapping("/update/{payersId}")
    public ResponseEntity<PayersDto> updatePayers(@PathVariable Long payersId, @RequestBody PayersDto updatedPayersDto) {
    	PayersDto updatedPayers = payersService.updatePayers(payersId, updatedPayersDto);
        if (updatedPayers != null) {
            logger.info("Updated Payers with ID: {}", payersId);
            return new ResponseEntity<>(updatedPayers, HttpStatus.OK);
        } else {
            logger.warn("Payers with ID {} not found for update", payersId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete Payers by ID
    @DeleteMapping("/delete/{payersId}")
    public ResponseEntity<Void> deletePayers(@PathVariable Long payersId) {
    	payersService.deletePayers(payersId);
        logger.info("Deleted Payers with ID: {}", payersId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	  //Count the total Payers
	    @GetMapping("/count/payers")
	    public long countPayers()
	    {
	    	return payersService.countPayers();
	    }
}
