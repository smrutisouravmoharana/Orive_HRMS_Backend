package com.orive.Tickets.Controller;

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

import com.orive.Tickets.Dto.TicketsDto;
import com.orive.Tickets.Entity.TicketsEntity;
import com.orive.Tickets.Service.TicketsService;
//import org.springframework.security.access.prepost.PreAuthorize;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "tickets")
@CrossOrigin(origins = "*")
public class TicketsController {
	
	private static final Logger logger = LoggerFactory.getLogger(TicketsController.class);
	
	@Autowired
	private TicketsService ticketsService;
	
	
//	// Create a new Tickets
//	  @PostMapping("/create/tickets")
//	 // @PreAuthorize("hasRole('client_HR')")
//	  public ResponseEntity<TicketsDto> createTickets(@RequestBody TicketsDto ticketsDto) {
//		  TicketsDto createdTickets = ticketsService.createTickets(ticketsDto);
//	      logger.info("Created Tickets with id: {}", createdTickets.getTicketsCode());
//	      return new ResponseEntity<>(createdTickets, HttpStatus.CREATED);
//	  }
	
	
	
	// Create a new  Tickets
    @PostMapping("/create/tickets")
 // @PreAuthorize("hasRole('client_HR')")
  public ResponseEntity<?> createTickets(@Valid @RequestBody TicketsDto ticketsDto) {
      try {
          // Check if the TicketsCode And ProjectTitle already exists
          Optional<TicketsDto> existingTicketsCodeAndProjectTitle = ticketsService.getTicketsByTicketsCodeAndProjectTitle(ticketsDto.getTicketsCode(), ticketsDto.getProjectTitle());
          if (existingTicketsCodeAndProjectTitle.isPresent()) {
              // TicketsCode And ProjectTitle already exists, return a bad request response with the error message
              return ResponseEntity.badRequest().body(" Tickets with TicketsCode '" + ticketsDto.getTicketsCode() + " And Project Tittle " + ticketsDto.getProjectTitle() + "' already exists");
          }

          // TicketsCode And ProjectTitle name is unique, proceed with creating the WorkSheet
          TicketsDto createdTickets = ticketsService.createTickets(ticketsDto);
          logger.info("Created Tickets with TicketsCode And ProjectTitle: {}",  createdTickets.getTicketsCode() , createdTickets.getProjectTitle());
          
          // Return the created Tickets with a success status code
          return new ResponseEntity<>(createdTickets, HttpStatus.CREATED);
      } catch (Exception e) {
          // Handle any unexpected errors
          logger.error("Error creating Tickets: {}", e.getMessage());
          
          // Return an internal server error response with a generic error message
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Tickets");
        }
      }
	

	  
	  // Get all Tickets  
	  @GetMapping("/get/tickets")
	// @PreAuthorize("hasRole('client_HR')")
	  public ResponseEntity<List<TicketsDto>> getAllTickets() {
	      List<TicketsDto> tickets = ticketsService.getAllTickets();
	      logger.info("Retrieved {} Tickets from the database", tickets.size());
	      return new ResponseEntity<>(tickets, HttpStatus.OK);
	  }

	  // Get Tickets by ID
	  @GetMapping("/get/{ticketsId}")
	  //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
	  public ResponseEntity<TicketsDto> getTicketsId(@PathVariable String ticketsId) {
	      Optional<TicketsDto> tickets = ticketsService.getTicketsId(ticketsId);
	      if (tickets.isPresent()) {
	          logger.info("Retrieved Tickets with ID: {}", ticketsId);
	          return new ResponseEntity<>(tickets.get(), HttpStatus.OK);
	      } else {
	          logger.warn("Tickets with ID {} not found", ticketsId);
	          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	  }
	  
	  
	  
	// Get Tickets by TicketsCode And ProjectTitle
      @GetMapping("/get/name/{ticketsCode}/{projectTitle}")
      // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<TicketsDto> getTicketsByTicketsCodeAndProjectTitle(@PathVariable String ticketsCode, @PathVariable String projectTitle) {
          Optional<TicketsDto> tickets = ticketsService.getTicketsByTicketsCodeAndProjectTitle(ticketsCode,projectTitle);
          if (tickets.isPresent()) {
              logger.info("Retrieved Tickets with TicketsCode And ProjectTitle: {}", ticketsCode,projectTitle);
              return new ResponseEntity<>(tickets.get(), HttpStatus.OK);
          } else {
              logger.warn("Tickets with TicketsCode And ProjectTitle {} not found", ticketsCode,projectTitle);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }
	  

	  
	// Get Employee by ID
	  @GetMapping("/getticketdetails/{username}")
	  // @PreAuthorize("hasRole('client_HR')")
	    public ResponseEntity<List<TicketsDto>> getTicketsByEmployeeId(@PathVariable String username) {
	        List<TicketsDto> tickets = ticketsService.getEmployeeId(username);

	        if (tickets.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        } else {
	            return ResponseEntity.ok(tickets);
	        }
	    }
	  
	  
	  // Update Tickets by ID
	  @PutMapping("/update/{ticketsId}")
	// @PreAuthorize("hasRole('client_HR')")
	  public ResponseEntity<TicketsDto> updateTickets(@PathVariable String ticketsId, @RequestBody TicketsDto updatedTicketsDto) {
		  TicketsDto updatedTickets = ticketsService.updateTickets(ticketsId, updatedTicketsDto);
	      if (updatedTickets != null) {
	          logger.info("Updated Tickets with ID: {}", ticketsId);
	          return new ResponseEntity<>(updatedTickets, HttpStatus.OK);
	      } else {
	          logger.warn("Tickets with ID {} not found for update", updatedTickets);
	          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	  }
	  
	  // Delete Tickets by ID
	  @DeleteMapping("/delete/{ticketsId}")
	// @PreAuthorize("hasRole('client_HR')")
	  public ResponseEntity<Void> deleteTickets(@PathVariable String ticketsId) {
		   ticketsService.deleteTickets(ticketsId);
	      logger.info("Deleted Tickets with ID: {}", ticketsId);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
		    
		    @GetMapping("/count/tickets")
		 // @PreAuthorize("hasRole('client_HR')")
		    public long countTickets()
		    {
		    	return ticketsService.countTickets();
		    }


}
