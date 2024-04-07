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

import com.orive.Transactions.Dto.TransferDto;
import com.orive.Transactions.Service.TransferService;

@RestController
@RequestMapping(value = "transfer")
@CrossOrigin(origins = "*")
public class TransferController {
	
	private static final Logger logger=LoggerFactory.getLogger(TransferController.class);
	
	@Autowired
	private TransferService transferService;
	
	// Create a new Transfer
    @PostMapping("/create/transfer")
    public ResponseEntity<TransferDto> createTransfer(@RequestBody TransferDto transferDto) {
    	TransferDto createdTransfer = transferService.createTransfer(transferDto);
        logger.info("Created Transfer with name: {}", createdTransfer.getEmployeeFullName());
        return new ResponseEntity<>(createdTransfer, HttpStatus.CREATED);
    }

    // Get all Transfer   
    @GetMapping("/get/transfer")
    public ResponseEntity<List<TransferDto>> getAllTransfer() {
        List<TransferDto> transfer = transferService.getAllTransfer();
        logger.info("Retrieved {} Transfer from the database", transfer.size());
        return new ResponseEntity<>(transfer, HttpStatus.OK);
    }

    // Get Transfer by ID
    @GetMapping("/get/{transferId}")
    public ResponseEntity<TransferDto> getTransferById(@PathVariable Long transferId) {
        Optional<TransferDto> transfer = transferService.getTransferById(transferId);
        if (transfer.isPresent()) {
            logger.info("Retrieved Transfer with ID: {}", transferId);
            return new ResponseEntity<>(transfer.get(), HttpStatus.OK);
        } else {
            logger.warn("Transfer with ID {} not found", transferId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Transfer by ID
    @PutMapping("/update/{transferId}")
    public ResponseEntity<TransferDto> updateTransfer(@PathVariable Long transferId, @RequestBody TransferDto updatedTransferDto) {
    	TransferDto updatedTransfer = transferService.updateTransfer(transferId, updatedTransferDto);
        if (updatedTransfer != null) {
            logger.info("Updated Transfer with ID: {}", transferId);
            return new ResponseEntity<>(updatedTransfer, HttpStatus.OK);
        } else {
            logger.warn("Transfer with ID {} not found for update", transferId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete Transfer by ID
    @DeleteMapping("/delete/{transferId}")
    public ResponseEntity<Void> deleteTransfer(@PathVariable Long transferId) {
    	transferService.deleteTransfer(transferId);
        logger.info("Deleted Transfer with ID: {}", transferId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
	    @GetMapping("/count/transfer")
	    public long countTransfer()
	    {
	    	return transferService.countTransfer();
	    }
}
