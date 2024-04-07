package com.orive.TimeSheet.Controller;

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

import com.orive.TimeSheet.Dto.OfficeShiftsDto;
import com.orive.TimeSheet.Service.OfficeShiftsService;
//import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping(value = "officeshifts")
@CrossOrigin(origins = "*")
public class OfficeShiftsController {

	private static final Logger logger = LoggerFactory.getLogger(OfficeShiftsController.class);

    @Autowired
    private OfficeShiftsService officeShiftsService;

  
  	// Create a new OfficeShifts
      @PostMapping("/create/officeShifts")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<OfficeShiftsDto> createOfficeShifts(@RequestBody OfficeShiftsDto officeShiftsDto) {
    	  OfficeShiftsDto createdOfficeShifts = officeShiftsService.createOfficeShifts(officeShiftsDto);
          logger.info("Created OfficeShifts with name: {}", createdOfficeShifts.getOfficeShiftsId());
          return new ResponseEntity<>(createdOfficeShifts, HttpStatus.CREATED);
      }

      // Get all OfficeShifts   
      @GetMapping("/get/officeShifts")
    //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
      public ResponseEntity<List<OfficeShiftsDto>> getAllOfficeShifts() {
          List<OfficeShiftsDto> officeShifts = officeShiftsService.getAllOfficeShifts();
          logger.info("Retrieved {} OfficeShifts from the database", officeShifts.size());
          return new ResponseEntity<>(officeShifts, HttpStatus.OK);
      }

      // Get OfficeShifts by ID
      @GetMapping("/get/{OfficeShiftsId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<OfficeShiftsDto> getOfficeShiftsById(@PathVariable String OfficeShiftsId) {
          Optional<OfficeShiftsDto> officeShifts = officeShiftsService.getOfficeShiftsById(OfficeShiftsId);
          if (officeShifts.isPresent()) {
              logger.info("Retrieved OfficeShifts with ID: {}", OfficeShiftsId);
              return new ResponseEntity<>(officeShifts.get(), HttpStatus.OK);
          } else {
              logger.warn("OfficeShifts with ID {} not found", OfficeShiftsId);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }

      // Update OfficeShifts by ID
      @PutMapping("/update/{OfficeShiftsId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<OfficeShiftsDto> updateOfficeShifts(@PathVariable String OfficeShiftsId, @RequestBody OfficeShiftsDto updatedOfficeShiftsDTO) {
    	  OfficeShiftsDto updatedOfficeShifts = officeShiftsService.updateOfficeShifts(OfficeShiftsId, updatedOfficeShiftsDTO);
          if (updatedOfficeShifts != null) {
              logger.info("Updated OfficeShifts with ID: {}", OfficeShiftsId);
              return new ResponseEntity<>(updatedOfficeShifts, HttpStatus.OK);
          } else {
              logger.warn("OfficeShifts with ID {} not found for update", OfficeShiftsId);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }
      


      // Delete OfficeShifts by ID
      @DeleteMapping("/delete/{OfficeShiftsId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<Void> deleteOfficeShifts(@PathVariable String OfficeShiftsId) {
    	  officeShiftsService.deleteOfficeShifts(OfficeShiftsId);
          logger.info("Deleted company with ID: {}", OfficeShiftsId);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  	    
  	    @GetMapping("/count/officeShifts")
  	// @PreAuthorize("hasRole('client_HR')")
  	    public long countOfficeShifts()
  	    {
  	    	return officeShiftsService.countOfficeShifts();
  	    }
}
