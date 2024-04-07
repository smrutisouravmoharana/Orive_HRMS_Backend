package com.orive.Sale.Controller;

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

import com.orive.Sale.Dto.ClientDto;
import com.orive.Sale.Service.ClientService;



@RestController
@RequestMapping(value = "client")
@CrossOrigin(origins = "*")
public class ClientController {

private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	private ClientService clientService;
	
	// Create a new WorkSheet
		  @PostMapping("/create/Client")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto) {
			  ClientDto createdWorkSheet = clientService.createClient(clientDto);
		      logger.info("Created Sale with id: {}", createdWorkSheet.getClientId());
		      return new ResponseEntity<>(createdWorkSheet, HttpStatus.CREATED);
		  }

		  
		  // Get all WorkSheet  
		  @GetMapping("/get/Client")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<List<ClientDto>> getAllClient() {
		      List<ClientDto> workSheet = clientService.getAllClient();
		      logger.info("Retrieved {} Sale from the database", workSheet.size());
		      return new ResponseEntity<>(workSheet, HttpStatus.OK);
		  }

		  // Get WorkSheet by ID
		  @GetMapping("/get/{clientId}")
		  //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
		  public ResponseEntity<ClientDto> getClientId(@PathVariable String clientId) {
		      Optional<ClientDto> workSheet = clientService.getClientId(clientId);
		      if (workSheet.isPresent()) {
		          logger.info("Retrieved Sale with ID: {}", clientId);
		          return new ResponseEntity<>(workSheet.get(), HttpStatus.OK);
		      } else {
		          logger.warn("Sale with ID {} not found", clientId);
		          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		      }
		  }
		  
		  
		  
		  
		  // Update WorkSheet by ID
		  @PutMapping("/update/{clientId}")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<ClientDto> updateClient(@PathVariable String clientId, @RequestBody ClientDto clientDto) {
			  ClientDto updatedWorkSheet = clientService.updateClient(clientId, clientDto);
		      if (updatedWorkSheet != null) {
		          logger.info("Updated Sale with ID: {}", clientId);
		          return new ResponseEntity<>(updatedWorkSheet, HttpStatus.OK);
		      } else {
		          logger.warn("Sale with ID {} not found for update", updatedWorkSheet);
		          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		      }
		  }
		  
		  // Delete WorkSheet by ID
		  @DeleteMapping("/delete/{clientId}")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<Void> deleteClient(@PathVariable String clientId) {
			  clientService.deleteClient(clientId);
		      logger.info("Deleted Sale with ID: {}", clientId);
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  }
			    
			    @GetMapping("/count/Client")
			 // @PreAuthorize("hasRole('client_HR')")
			    public long countClient()
			    {
			    	return clientService.countClient();
			    }
}
