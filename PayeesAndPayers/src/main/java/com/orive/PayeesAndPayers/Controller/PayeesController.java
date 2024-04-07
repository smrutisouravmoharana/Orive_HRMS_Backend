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

import com.orive.PayeesAndPayers.Dto.PayeesDto;
import com.orive.PayeesAndPayers.Service.PayeesService;

@RestController
@RequestMapping(value = "payees")
@CrossOrigin(origins = "*")
public class PayeesController {
	
	private static final Logger logger = LoggerFactory.getLogger(PayeesController.class);
	
	@Autowired
	private PayeesService payeesService;
	
	// Create a new Payees
    @PostMapping("/create/payees")
    public ResponseEntity<PayeesDto> createPayees(@RequestBody PayeesDto payeesDto) {
    	PayeesDto createdPayees = payeesService.createPayees(payeesDto);
        logger.info("Created Payees with name: {}", createdPayees.getPayeesFullName());
        return new ResponseEntity<>(createdPayees, HttpStatus.CREATED);
    }

    // Get all Payees   
    @GetMapping("/get/payees")
    public ResponseEntity<List<PayeesDto>> getAllPayees() {
        List<PayeesDto> payees = payeesService.getAllPayees();
        logger.info("Retrieved {} Payees from the database", payees.size());
        return new ResponseEntity<>(payees, HttpStatus.OK);
    }

    // Get Payees by ID
    @GetMapping("/get/{payeesId}")
    public ResponseEntity<PayeesDto> getPayeesById(@PathVariable Long payeesId) {
        Optional<PayeesDto> payees = payeesService.getPayeesById(payeesId);
        if (payees.isPresent()) {
            logger.info("Retrieved Payees with ID: {}", payeesId);
            return new ResponseEntity<>(payees.get(), HttpStatus.OK);
        } else {
            logger.warn("Payees with ID {} not found", payeesId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Payees by ID
    @PutMapping("/update/{payeesId}")
    public ResponseEntity<PayeesDto> updatePayees(@PathVariable Long payeesId, @RequestBody PayeesDto updatedPayeesDto) {
    	PayeesDto updatedPayees = payeesService.updatePayees(payeesId, updatedPayeesDto);
        if (updatedPayees != null) {
            logger.info("Updated Payees with ID: {}", payeesId);
            return new ResponseEntity<>(updatedPayees, HttpStatus.OK);
        } else {
            logger.warn("Payees with ID {} not found for update", payeesId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete Payees by ID
    @DeleteMapping("/delete/{payeesId}")
    public ResponseEntity<Void> deletePayees(@PathVariable Long payeesId) {
    	payeesService.deletePayees(payeesId);
        logger.info("Deleted Payees with ID: {}", payeesId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	 
    //Count the total Payees
	    @GetMapping("/count/payees")
	    public long countPayees()
	    {
	    	return payeesService.countPayees();
	    }
}
