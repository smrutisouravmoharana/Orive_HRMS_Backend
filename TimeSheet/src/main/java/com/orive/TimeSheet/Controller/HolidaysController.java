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


import com.orive.TimeSheet.Dto.HolidaysDto;
import com.orive.TimeSheet.Service.HolidaysService;
//import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "holidays")
@CrossOrigin(origins = "*")
public class HolidaysController {

	private static final Logger logger = LoggerFactory.getLogger(HolidaysController.class);

    @Autowired
    private HolidaysService holidaysService;

  
//  	// Create a new Holidays
//      @PostMapping("/create/holidays")
//   // @PreAuthorize("hasRole('client_HR')")
//      public ResponseEntity<HolidaysDto> createHolidays(@RequestBody HolidaysDto holidaysDto) {
//    	  HolidaysDto createdHoliday = holidaysService.createHolidays(holidaysDto);
//          logger.info("Created Holidays with name: {}", createdHoliday.getEventName());
//          return new ResponseEntity<>(createdHoliday, HttpStatus.CREATED);
//      }
    
    
 // Create a new Holidays
    @PostMapping("/create/holidays")
 // @PreAuthorize("hasRole('client_HR')")
  public ResponseEntity<?> createDesignation(@Valid @RequestBody HolidaysDto holidaysDto) {
      try {
          // Check if the EventName name already exists
          Optional<HolidaysDto> existingEventName = holidaysService.getHolidaysByEventName(holidaysDto.getEventName());
          if (existingEventName.isPresent()) {
              // EventName name already exists, return a bad request response with the error message
              return ResponseEntity.badRequest().body("Holidays with EventName '" + holidaysDto.getEventName() + "' already exists");
          }

          // EventName name is unique, proceed with creating the Holidays
          HolidaysDto createdHoliday = holidaysService.createHolidays(holidaysDto);
          logger.info("Created Holidays with EventName: {}", createdHoliday.getEventName());
          
          // Return the created Holidays with a success status code
          return new ResponseEntity<>(createdHoliday, HttpStatus.CREATED);
      } catch (Exception e) {
          // Handle any unexpected errors
          logger.error("Error creating Holidays: {}", e.getMessage());
          
          // Return an internal server error response with a generic error message
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Holidays");
      }
  }
    
    

      // Get all Holidays   
      @GetMapping("/get/holidays")
    //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
      public ResponseEntity<List<HolidaysDto>> getAllHolidays() {
          List<HolidaysDto> holidays = holidaysService.getAllHolidays();
          logger.info("Retrieved {} Holidays from the database", holidays.size());
          return new ResponseEntity<>(holidays, HttpStatus.OK);
      }

      // Get Holidays by ID
      @GetMapping("/get/{holidaysId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<HolidaysDto> getHolidaysById(@PathVariable String holidaysId) {
          Optional<HolidaysDto> holidays = holidaysService.getHolidaysById(holidaysId);
          if (holidays.isPresent()) {
              logger.info("Retrieved Holidays with ID: {}", holidaysId);
              return new ResponseEntity<>(holidays.get(), HttpStatus.OK);
          } else {
              logger.warn("Holidays with ID {} not found", holidaysId);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }

      
      // Get Holidays by EventName
      @GetMapping("/get/name/{eventName}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<HolidaysDto> getHolidaysByEventName(@PathVariable String eventName) {
          Optional<HolidaysDto> holidays = holidaysService.getHolidaysByEventName(eventName);
          if (holidays.isPresent()) {
              logger.info("Retrieved Holidays with EventName: {}", eventName);
              return new ResponseEntity<>(holidays.get(), HttpStatus.OK);
          } else {
              logger.warn("Holidays with EventName {} not found", eventName);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }
      
      
      // Update Holidays by ID
      @PutMapping("/update/{holidaysId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<HolidaysDto> updateHolidays(@PathVariable String holidaysId, @RequestBody HolidaysDto updatedHolidaysDto) {
    	  HolidaysDto updatedHolidays = holidaysService.updateHolidays(holidaysId, updatedHolidaysDto);
          if (updatedHolidays != null) {
              logger.info("Updated Holidays with ID: {}", holidaysId);
              return new ResponseEntity<>(updatedHolidays, HttpStatus.OK);
          } else {
              logger.warn("Holidays with ID {} not found for update", holidaysId);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }
      


      // Delete Holidays by ID
      @DeleteMapping("/delete/{holidaysId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<Void> deleteHolidays(@PathVariable String holidaysId) {
    	  holidaysService.deleteHolidays(holidaysId);
          logger.info("Deleted Holidays with ID: {}", holidaysId);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  	    
  	    @GetMapping("/count/holidays")
  	// @PreAuthorize("hasRole('client_HR')")
  	    public long countHolidays()
  	    {
  	    	return holidaysService.countHolidays();
  	    }
}
