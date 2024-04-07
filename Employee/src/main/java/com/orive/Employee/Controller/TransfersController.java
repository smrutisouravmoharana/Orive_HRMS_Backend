package com.orive.Employee.Controller;

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

import com.orive.Employee.Dto.TransfersDto;
import com.orive.Employee.Service.TransfersService;
//import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping(value = "transfers")
@CrossOrigin(origins = "*")
public class TransfersController {

	private static final Logger logger = LoggerFactory.getLogger(TransfersController.class);

    @Autowired
    private TransfersService transfersService;

  
  	// Create a new Transfers
      @PostMapping("/create/transfers")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<TransfersDto> createTransfers(@RequestBody TransfersDto transfersDto) {
    	  TransfersDto createdTransfer = transfersService.createTransfers(transfersDto);
          logger.info("Created Transfers with name: {}", createdTransfer.getEmployeeName());
          return new ResponseEntity<>(createdTransfer, HttpStatus.CREATED);
      }

      // Get all Transfers
      @GetMapping("/get/transfers")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<List<TransfersDto>> getAllTransfers() {
          List<TransfersDto> transfer = transfersService.getAllTransfers();
          logger.info("Retrieved {} Transfers from the database", transfer.size());
          return new ResponseEntity<>(transfer, HttpStatus.OK);
      }

      // Get Transfers by ID
      @GetMapping("/get/{transferId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<TransfersDto> getTransfersById(@PathVariable Long transferId) {
          Optional<TransfersDto> transfer = transfersService.getTransfersById(transferId);
          if (transfer.isPresent()) {
              logger.info("Retrieved Transfers with ID: {}", transferId);
              return new ResponseEntity<>(transfer.get(), HttpStatus.OK);
          } else {
              logger.warn("Transfers with ID {} not found", transferId);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }
      
      // Get Transfers by Employee ID
      @GetMapping("/findtransfer/{username}")
    //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
      public ResponseEntity<List<TransfersDto>> getTransfersByUsername(@PathVariable String username) {
          logger.info("Getting transfers for username: {}", username);
          List<TransfersDto> transfers = transfersService.getTransfersByUsername(username);
          logger.info("Found {} transfers for username: {}", transfers.size(), username);
          return ResponseEntity.ok(transfers);
      }

      // Update Transfers by ID
      @PutMapping("/update/{transferId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<TransfersDto> updateTransfers(@PathVariable Long transferId, @RequestBody TransfersDto updatedTransfersDto) {
    	  TransfersDto updatedTransfer = transfersService.updateTransfers(transferId, updatedTransfersDto);
          if (updatedTransfer != null) {
              logger.info("Updated Transfers with ID: {}", transferId);
              return new ResponseEntity<>(updatedTransfer, HttpStatus.OK);
          } else {
              logger.warn("Transfers with ID {} not found for update", transferId);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }
      


      // Delete Transfers by ID
      @DeleteMapping("/delete/{transferId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<Void> deleteTransfers(@PathVariable Long transferId) {
    	  transfersService.deleteTransfers(transferId);
          logger.info("Deleted Transfers with ID: {}", transferId);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  	    
   // Count the total Transfers
  	    @GetMapping("/count/transfers")
  	// @PreAuthorize("hasRole('client_HR')")
  	    public long countTransfers()
  	    {
  	    	return transfersService.countTransfers();
  	    }
}
