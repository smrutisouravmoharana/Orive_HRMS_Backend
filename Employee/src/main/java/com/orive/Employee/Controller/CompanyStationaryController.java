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

import com.orive.Employee.Dto.CompanyStationaryDto;
import com.orive.Employee.Service.AwardsService;
import com.orive.Employee.Service.CompanyStationaryService;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "companystationary")
@CrossOrigin(origins = "*")
public class CompanyStationaryController {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyStationaryController.class);

    @Autowired
    private CompanyStationaryService companyStationaryService;

  
  	// Create a new CompanyStationary
      @PostMapping("/create/companystationary")
      // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<CompanyStationaryDto> createCompanyStationary(@RequestBody CompanyStationaryDto companyStationaryDto) {
    	  CompanyStationaryDto createdCompanyStationary = companyStationaryService.createCompanyStationary(companyStationaryDto);
          logger.info("Created CompanyStationary with name: {}", createdCompanyStationary.getEmployeeName());
          return new ResponseEntity<>(createdCompanyStationary, HttpStatus.CREATED);
      }

      // Get all CompanyStationary      
      @GetMapping("/get/companystationary")
      // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<List<CompanyStationaryDto>> getAllCompanyStationary() {
          List<CompanyStationaryDto> companyStationary = companyStationaryService.getAllCompanyStationary();
          logger.info("Retrieved {} CompanyStationary from the database", companyStationary.size());
          return new ResponseEntity<>(companyStationary, HttpStatus.OK);
      }

      // Get CompanyStationary by ID
      @GetMapping("/get/{companyStationaryId}")
      // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<CompanyStationaryDto> getCompanyStationaryById(@PathVariable Long companyStationaryId) {
          Optional<CompanyStationaryDto> companyStationary = companyStationaryService.getCompanyStationaryById(companyStationaryId);
          if (companyStationary.isPresent()) {
              logger.info("Retrieved CompanyStationary with ID: {}", companyStationaryId);
              return new ResponseEntity<>(companyStationary.get(), HttpStatus.OK);
          } else {
              logger.warn("CompanyStationary with ID {} not found", companyStationaryId);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }

      // Update CompanyStationary by ID
      @PutMapping("/update/{companyStationaryId}")
      // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<CompanyStationaryDto> updateCompanyStationary(@PathVariable Long companyStationaryId, @RequestBody CompanyStationaryDto updatedCompanyStationaryDto) {
    	  CompanyStationaryDto updatedCompanyStationary = companyStationaryService.updateCompanyStationary(companyStationaryId, updatedCompanyStationaryDto);
          if (updatedCompanyStationary != null) {
              logger.info("Updated CompanyStationary with ID: {}", companyStationaryId);
              return new ResponseEntity<>(updatedCompanyStationary, HttpStatus.OK);
          } else {
              logger.warn("CompanyStationary with ID {} not found for update", companyStationaryId);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }
      


      // Delete CompanyStationary by ID
      @DeleteMapping("/delete/{companyStationaryId}")
      // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<Void> deleteCompanyStationary(@PathVariable Long companyStationaryId) {
    	  companyStationaryService.deleteCompanyStationary(companyStationaryId);
          logger.info("Deleted CompanyStationary with ID: {}", companyStationaryId);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  	    
      //Count CompanyStationary
  	    @GetMapping("/count/companystationary")
  	  // @PreAuthorize("hasRole('client_HR')")
  	    public long countCompanyStationary()
  	    {
  	    	return companyStationaryService.countCompanyStationary();
  	    }
}
