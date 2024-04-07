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

import com.orive.Employee.Dto.PromotionsDto;
import com.orive.Employee.Dto.TransfersDto;
import com.orive.Employee.Service.PromotionsService;
//import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping(value = "promotions")
@CrossOrigin(origins = "*")
public class PromotionsController {
	
	private static final Logger logger = LoggerFactory.getLogger(PromotionsController.class);
	
	 @Autowired
	    private PromotionsService promotionsService;
	 
	// Create a new promotions
   @PostMapping("/create/promotions")
// @PreAuthorize("hasRole('client_HR')")
   public ResponseEntity<PromotionsDto> createPromotions(@RequestBody PromotionsDto promotionsDto) {
	   PromotionsDto createdPromotions = promotionsService.createPromotions(promotionsDto);
       logger.info("Created Promotions with id: {}", createdPromotions.getEmployeeName());
       return new ResponseEntity<>(createdPromotions, HttpStatus.CREATED);
   }

   // Get all promotions
   
   @GetMapping("/get/promotions")
// @PreAuthorize("hasRole('client_HR')")
   public ResponseEntity<List<PromotionsDto>> getAllPromotions() {
       List<PromotionsDto> promotions = promotionsService.getAllPromotions();
       logger.info("Retrieved {} Promotions from the database", promotions.size());
       return new ResponseEntity<>(promotions, HttpStatus.OK);
   }

   // Get promotions by ID
   @GetMapping("/get/{promotionsId}")
// @PreAuthorize("hasRole('client_HR')")
   public ResponseEntity<PromotionsDto> getPromotionsId(@PathVariable Long promotionsId) {
       Optional<PromotionsDto> promotions = promotionsService.getPromotionsById(promotionsId);
       if (promotions.isPresent()) {
           logger.info("Retrieved promotions with ID: {}", promotionsId);
           return new ResponseEntity<>(promotions.get(), HttpStatus.OK);
       } else {
           logger.warn("Promotions with ID {} not found", promotionsId);
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
   }
   
// Get Travels by Employee ID
   @GetMapping("/findpromotions/{username}")
   //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
   public ResponseEntity<List<PromotionsDto>> getTransfersByUsername(@PathVariable String username) {
       logger.info("Getting Promotions for username: {}", username);
       List<PromotionsDto> transfers = promotionsService.getPromotionsByUsername(username);
       logger.info("Found {} Promotions for username: {}", transfers.size(), username);
       return ResponseEntity.ok(transfers);
   }

   // Update promotions by ID
   @PutMapping("/update/{promotionsId}")
// @PreAuthorize("hasRole('client_HR')")
   public ResponseEntity<PromotionsDto> updatePromotions(@PathVariable Long promotionsId, @RequestBody PromotionsDto updatedPromotionsDTO) {
	   PromotionsDto updatedPromotions = promotionsService.updatePromotions(promotionsId, updatedPromotionsDTO);
       if (updatedPromotions != null) {
           logger.info("Updated promotions with ID: {}", promotionsId);
           return new ResponseEntity<>(updatedPromotions, HttpStatus.OK);
       } else {
           logger.warn("Promotions with ID {} not found for update", promotionsId);
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
   }
   
   // Delete promotions by ID
   @DeleteMapping("/delete/{promotionsId}")
// @PreAuthorize("hasRole('client_HR')")
   public ResponseEntity<Void> deletePromotions(@PathVariable Long promotionsId) {
	   promotionsService.deletePromotions(promotionsId);
       logger.info("Deleted Promotions with ID: {}", promotionsId);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
	    
   // Count the total promotions 
	    @GetMapping("/count/promotions")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countPromotions()
	    {
	    	return promotionsService.countPromotions();
	    }
}
