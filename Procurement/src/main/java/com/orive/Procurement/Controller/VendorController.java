package com.orive.Procurement.Controller;

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

import com.orive.Procurement.Dto.VendorDto;
import com.orive.Procurement.Service.VendorService;
//import org.springframework.security.access.prepost.PreAuthorize;




@RestController
@RequestMapping(value = "vendor")
@CrossOrigin(origins = "*")
public class VendorController {
	
	private static final Logger logger = LoggerFactory.getLogger(VendorController.class);

    @Autowired
    private VendorService vendorService;
    
    
 // Create a new Request
    @PostMapping("/create/vendor")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<VendorDto> createVendor(@RequestBody VendorDto vendorDto) {
    	VendorDto createdVendor = vendorService.createVendor(vendorDto);
        logger.info("Created vendor with name: {}", createdVendor.getVendorName());
        return new ResponseEntity<>(createdVendor, HttpStatus.CREATED);
    }

    // Get all vendor
    
    @GetMapping("/get/vendor")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<VendorDto>> getAllVendor() {
        List<VendorDto> vendor = vendorService.getAllVendor();
        logger.info("Retrieved {} vendor from the database", vendor.size());
        return new ResponseEntity<>(vendor, HttpStatus.OK);
    }

    // Get vendorbyId
    @GetMapping("/get/{vendorId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<VendorDto> getVendorbyId(@PathVariable Long vendorId) {
        Optional<VendorDto> vendor = vendorService.getVendorById(vendorId);
        if (vendor.isPresent()) {
            logger.info("Retrieved vendor with ID: {}", vendorId);
            return new ResponseEntity<>(vendor.get(), HttpStatus.OK);
        } else {
            logger.warn("vendor with ID {} not found", vendorId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Vendor by ID
    @PutMapping("/update/{vendorId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<VendorDto> updateVendor(@PathVariable Long vendorId, @RequestBody VendorDto updatedVendorDto) {
    	VendorDto updatedvendor = vendorService.updateVendor(vendorId, updatedVendorDto);
        if (updatedvendor != null) {
            logger.info("Updated vendor with ID: {}", vendorId);
            return new ResponseEntity<>(updatedvendor, HttpStatus.OK);
        } else {
            logger.warn("vendor with ID {} not found for update", vendorId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    // Delete Vendor by ID
    @DeleteMapping("/delete/{vendorId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteVendor(@PathVariable Long vendorId) {
  	  vendorService.deleteVendor(vendorId);
        logger.info("Deleted vendor with ID: {}", vendorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
	    @GetMapping("/count/vendor")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countVendor()
	    {
	    	return vendorService.countVendor();
	    }

}
