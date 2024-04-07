package com.orive.Payroll.Controller;

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


import com.orive.Payroll.Dto.SalaryTemplateDto;
import com.orive.Payroll.Service.SalaryTemplateService;
//import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping(value = "salaryTemplate")
@CrossOrigin(origins = "*")
public class SalaryTemplateController {

	private static final Logger logger = LoggerFactory.getLogger(SalaryTemplateController.class);

    @Autowired
    private SalaryTemplateService salaryTemplateService;

  
  	// Create a new SalaryTemplate
      @PostMapping("/create/salaryTemplate")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<SalaryTemplateDto> createSalaryTemplate(@RequestBody SalaryTemplateDto salaryTemplateDto) {
    	  SalaryTemplateDto createdSalaryTemplate = salaryTemplateService.createSalaryTemplate(salaryTemplateDto);
          logger.info("Created SalaryTemplate with name: {}", createdSalaryTemplate.getBasicSalary());
          return new ResponseEntity<>(createdSalaryTemplate, HttpStatus.CREATED);
      }

      // Get all SalaryTemplate      
      @GetMapping("/get/salaryTemplate")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<List<SalaryTemplateDto>> getAllSalaryTemplate() {
          List<SalaryTemplateDto> salaryTemplate = salaryTemplateService.getAllSalaryTemplate();
          logger.info("Retrieved {} SalaryTemplate from the database", salaryTemplate.size());
          return new ResponseEntity<>(salaryTemplate, HttpStatus.OK);
      }

      // Get SalaryTemplate by ID
      @GetMapping("/get/{salaryTemplateId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<SalaryTemplateDto> getSalaryTemplateById(@PathVariable String salaryTemplateId) {
          Optional<SalaryTemplateDto> salaryTemplate = salaryTemplateService.getSalaryTemplateById(salaryTemplateId);
          if (salaryTemplate.isPresent()) {
              logger.info("Retrieved SalaryTemplate with ID: {}", salaryTemplateId);
              return new ResponseEntity<>(salaryTemplate.get(), HttpStatus.OK);
          } else {
              logger.warn("SalaryTemplate with ID {} not found", salaryTemplateId);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }

      // Update SalaryTemplate by ID
      @PutMapping("/update/{salaryTemplateId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<SalaryTemplateDto> updateSalaryTemplate(@PathVariable String salaryTemplateId, @RequestBody SalaryTemplateDto updatedSalaryTemplateDto) {
    	  SalaryTemplateDto updatedSalaryTemplate = salaryTemplateService.updateSalaryTemplate(salaryTemplateId, updatedSalaryTemplateDto);
          if (updatedSalaryTemplate != null) {
              logger.info("Updated SalaryTemplate with ID: {}", salaryTemplateId);
              return new ResponseEntity<>(updatedSalaryTemplate, HttpStatus.OK);
          } else {
              logger.warn("SalaryTemplate with ID {} not found for update", salaryTemplateId);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }
      


      // Delete SalaryTemplate by ID
      @DeleteMapping("/delete/{salaryTemplateId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<Void> deleteSalaryTemplate(@PathVariable String salaryTemplateId) {
    	  salaryTemplateService.deleteSalaryTemplate(salaryTemplateId);
          logger.info("Deleted SalaryTemplate with ID: {}", salaryTemplateId);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  	   
      //Count the total SalaryTemplate
  	    @GetMapping("/count/payrolltemplate")
  	// @PreAuthorize("hasRole('client_HR')")
  	    public long countSalaryTemplate()
  	    {
  	    	return salaryTemplateService.countSalaryTemplate();
  	    }
}
