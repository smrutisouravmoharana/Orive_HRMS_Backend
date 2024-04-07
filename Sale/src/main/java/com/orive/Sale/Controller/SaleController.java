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

import com.orive.Sale.Dto.SaleDto;
import com.orive.Sale.Service.SaleService;



@RestController
@RequestMapping(value = "sale")
@CrossOrigin(origins = "*")
public class SaleController {

 private static final Logger logger = LoggerFactory.getLogger(SaleController.class);
	
	@Autowired
	private SaleService saleService;
	
	// Create a new WorkSheet
		  @PostMapping("/create/Sale")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<SaleDto> createSale(@RequestBody SaleDto saleDto) {
			  SaleDto createdWorkSheet = saleService.createSale(saleDto);
		      logger.info("Created Sale with id: {}", createdWorkSheet.getSaleId());
		      return new ResponseEntity<>(createdWorkSheet, HttpStatus.CREATED);
		  }

		  
		  // Get all WorkSheet  
		  @GetMapping("/get/Sale")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<List<SaleDto>> getAllSale() {
		      List<SaleDto> workSheet = saleService.getAllSale();
		      logger.info("Retrieved {} Sale from the database", workSheet.size());
		      return new ResponseEntity<>(workSheet, HttpStatus.OK);
		  }

		  // Get WorkSheet by ID
		  @GetMapping("/get/{saleId}")
		  //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
		  public ResponseEntity<SaleDto> getSaleId(@PathVariable String saleId) {
		      Optional<SaleDto> workSheet = saleService.getSaleId(saleId);
		      if (workSheet.isPresent()) {
		          logger.info("Retrieved Sale with ID: {}", saleId);
		          return new ResponseEntity<>(workSheet.get(), HttpStatus.OK);
		      } else {
		          logger.warn("Sale with ID {} not found", saleId);
		          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		      }
		  }
		  
		  
		  
		  
		  // Update WorkSheet by ID
		  @PutMapping("/update/{saleId}")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<SaleDto> updateSale(@PathVariable String saleId, @RequestBody SaleDto saleDto) {
			  SaleDto updatedWorkSheet = saleService.updateSale(saleId, saleDto);
		      if (updatedWorkSheet != null) {
		          logger.info("Updated Sale with ID: {}", saleId);
		          return new ResponseEntity<>(updatedWorkSheet, HttpStatus.OK);
		      } else {
		          logger.warn("Sale with ID {} not found for update", updatedWorkSheet);
		          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		      }
		  }
		  
		  // Delete WorkSheet by ID
		  @DeleteMapping("/delete/{saleId}")
		// @PreAuthorize("hasRole('client_HR')")
		  public ResponseEntity<Void> deleteSale(@PathVariable String saleId) {
			  saleService.deleteSale(saleId);
		      logger.info("Deleted Sale with ID: {}", saleId);
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  }
			    
			    @GetMapping("/count/Sale")
			 // @PreAuthorize("hasRole('client_HR')")
			    public long countSale()
			    {
			    	return saleService.countSale();
			    }
            }

