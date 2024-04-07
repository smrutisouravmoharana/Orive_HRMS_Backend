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

import com.orive.Employee.Dto.ComplaintsDto;
import com.orive.Employee.Dto.PromotionsDto;
import com.orive.Employee.Dto.TransfersDto;
import com.orive.Employee.Service.ComplaintsService;
import com.orive.Employee.Service.PromotionsService;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "complaints")
@CrossOrigin(origins = "*")
public class ComplaintsController {
	
	private static final Logger logger = LoggerFactory.getLogger(ComplaintsController.class);
	
	 @Autowired
	    private ComplaintsService complaintsService;
	 
	// Create a new Complaints
  @PostMapping("/create/complaints")
  // @PreAuthorize("hasRole('client_HR')")
  public ResponseEntity<ComplaintsDto> createComplaints(@RequestBody ComplaintsDto complaintsDto) {
	  ComplaintsDto createdComplaints = complaintsService.createComplaints(complaintsDto);
      logger.info("Created Complaints with name: {}", createdComplaints.getEmployeeName());
      return new ResponseEntity<>(createdComplaints, HttpStatus.CREATED);
  }

  // Get all Complaints
  @GetMapping("/get/complaints")
  // @PreAuthorize("hasRole('client_HR')")
  public ResponseEntity<List<ComplaintsDto>> getAllComplaints() {
      List<ComplaintsDto> complaints = complaintsService.getAllComplaints();
      logger.info("Retrieved {} Complaints from the database", complaints.size());
      return new ResponseEntity<>(complaints, HttpStatus.OK);
  }

  // Get Complaints by ID
  @GetMapping("/get/{complaintsId}")
  // @PreAuthorize("hasRole('client_HR')")
  public ResponseEntity<ComplaintsDto> getComplaintsDtoId(@PathVariable Long complaintsId) {
      Optional<ComplaintsDto> complaints = complaintsService.getComplaintsById(complaintsId);
      if (complaints.isPresent()) {
          logger.info("Retrieved Complaints with ID: {}", complaintsId);
          return new ResponseEntity<>(complaints.get(), HttpStatus.OK);
      } else {
          logger.warn("Complaints with ID {} not found", complaintsId);
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  }
  
  @GetMapping("/findcomplaints/{username}")
  public ResponseEntity<List<ComplaintsDto>> getComplaintsByUsername(@PathVariable String username) {
      logger.info("Getting Complaints for username: {}", username);
      List<ComplaintsDto> transfers = complaintsService.getTransfersByUsername(username);
      logger.info("Found {} Complaints for username: {}", transfers.size(), username);
      return ResponseEntity.ok(transfers);
  }

  // Update Complaints by ID
  @PutMapping("/update/{complaintsId}")
  // @PreAuthorize("hasRole('client_HR')")
  public ResponseEntity<ComplaintsDto> updateComplaints(@PathVariable Long complaintsId, @RequestBody ComplaintsDto updatedComplaintsDTO) {
	  ComplaintsDto updatedComplaints = complaintsService.updateComplaints(complaintsId, updatedComplaintsDTO);
      if (updatedComplaints != null) {
          logger.info("Updated Complaints with ID: {}", complaintsId);
          return new ResponseEntity<>(updatedComplaints, HttpStatus.OK);
      } else {
          logger.warn("Complaints with ID {} not found for update", complaintsId);
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  }
  
  // Delete Complaints by ID
  @DeleteMapping("/delete/{complaintsId}")
  // @PreAuthorize("hasRole('client_HR')")
  public ResponseEntity<Void> deleteComplaints(@PathVariable Long complaintsId) {
	   complaintsService.deleteComplaints(complaintsId);
      logger.info("Deleted Complaints with ID: {}", complaintsId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
	   
//Count the total Complaints
	    @GetMapping("/count/complaints")
	    // @PreAuthorize("hasRole('client_HR')")
	    public long countComplaints()
	    {
	    	return complaintsService.countComplaints();
	    }
}