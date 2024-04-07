package com.orive.Accounts.Controller;

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

import com.orive.Accounts.Dto.SubTypeDto;
import com.orive.Accounts.Service.SubTypeSevice;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "subtype")
@CrossOrigin(origins = "*")
public class SubTypeController {

	 private static final Logger logger=LoggerFactory.getLogger(SubTypeController.class);
		
		@Autowired
		private SubTypeSevice subTypeSevice;
		
		
		// Create a new SubType
	    @PostMapping("/create/subType")
	 // @PreAuthorize("hasRole('client_HR')")
	    public ResponseEntity<SubTypeDto> createSubTypeList(@RequestBody SubTypeDto subTypeDto) {
	    	SubTypeDto createdSubType = subTypeSevice.createSubTypeList(subTypeDto);
	        logger.info("Created SubType with year: {}", createdSubType.getSubType());
	        return new ResponseEntity<>(createdSubType, HttpStatus.CREATED);
	    }

	    // Get all SubType   
	    @GetMapping("/get/subType")
	 // @PreAuthorize("hasRole('client_HR')")
	    public ResponseEntity<List<SubTypeDto>> getAllSubTypeList() {
	        List<SubTypeDto> subType = subTypeSevice.getAllSubTypeList();
	        logger.info("Retrieved {} SubType from the database", subType.size());
	        return new ResponseEntity<>(subType, HttpStatus.OK);
	    }

	    // Get SubType by ID
	    @GetMapping("/get/{subTypeId}")
	 // @PreAuthorize("hasRole('client_HR')")
	    public ResponseEntity<SubTypeDto> getSubTypeById(@PathVariable Long subTypeId) {
	        Optional<SubTypeDto> subType = subTypeSevice.getSubTypeById(subTypeId);
	        if (subType.isPresent()) {
	            logger.info("Retrieved SubType with ID: {}", subTypeId);
	            return new ResponseEntity<>(subType.get(), HttpStatus.OK);
	        } else {
	            logger.warn("SubType with ID {} not found", subTypeId);
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    // Update SubType by ID
	    @PutMapping("/update/{subTypeId}")
	 // @PreAuthorize("hasRole('client_HR')")
	    public ResponseEntity<SubTypeDto> updateSubTypeList(@PathVariable Long subTypeId, @RequestBody SubTypeDto updatedSubTypeDto) {
	    	SubTypeDto updatedSubType = subTypeSevice.updateSubTypeList(subTypeId, updatedSubTypeDto);
	        if (updatedSubType != null) {
	            logger.info("Updated SubType with ID: {}", subTypeId);
	            return new ResponseEntity<>(updatedSubType, HttpStatus.OK);
	        } else {
	            logger.warn("SubType with ID {} not found for update", subTypeId);
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	    


	    // Delete SubType by ID
	    @DeleteMapping("/delete/{subTypeId}")
	 // @PreAuthorize("hasRole('client_HR')")
	    public ResponseEntity<Void> deleteSubTypeList(@PathVariable Long subTypeId) {
	    	subTypeSevice.deleteSubTypeList(subTypeId);
	        logger.info("Deleted SubType with ID: {}", subTypeId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
		   
	    // Count the total SubType
		    @GetMapping("/count/subType")
		 // @PreAuthorize("hasRole('client_HR')")
		    public long countSubTypeList()
		    {
		    	return subTypeSevice.countSubTypeList();
		    }
}
