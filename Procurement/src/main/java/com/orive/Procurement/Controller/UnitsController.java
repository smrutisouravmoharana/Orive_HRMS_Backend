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

import com.orive.Procurement.Dto.UnitsDto;
import com.orive.Procurement.Dto.VendorDto;
import com.orive.Procurement.Service.UnitsService;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "units")
@CrossOrigin(origins = "*")
public class UnitsController {
	
	private static final Logger logger = LoggerFactory.getLogger(UnitsController.class);
	
	@Autowired
	private UnitsService unitsService;

	// Create a new Units
    @PostMapping("/create/units")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<UnitsDto> createUnits(@RequestBody UnitsDto unitsDto) {
    	UnitsDto createdUnits = unitsService.createUnits(unitsDto);
        logger.info("Created Units with name: {}", createdUnits.getUnitName());
        return new ResponseEntity<>(createdUnits, HttpStatus.CREATED);
    }

    // Get all Units   
    @GetMapping("/get/units")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<UnitsDto>> getAllUnits() {
        List<UnitsDto> units = unitsService.getAllUnits();
        logger.info("Retrieved {} Units from the database", units.size());
        return new ResponseEntity<>(units, HttpStatus.OK);
    }

    // Get UnitsbyId
    @GetMapping("/get/{unitsId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<UnitsDto> getUnitsbyId(@PathVariable Long unitsId) {
        Optional<UnitsDto> units = unitsService.getUnitsById(unitsId);
        if (units.isPresent()) {
            logger.info("Retrieved Units with ID: {}", unitsId);
            return new ResponseEntity<>(units.get(), HttpStatus.OK);
        } else {
            logger.warn("Units with ID {} not found", unitsId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Units by ID
    @PutMapping("/update/{unitsId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<UnitsDto> updateUnits(@PathVariable Long unitsId, @RequestBody UnitsDto updatedUnitsDto) {
    	UnitsDto updatedUnits = unitsService.updateUnits(unitsId, updatedUnitsDto);
        if (updatedUnits != null) {
            logger.info("Updated Units with ID: {}", unitsId);
            return new ResponseEntity<>(updatedUnits, HttpStatus.OK);
        } else {
            logger.warn("Units with ID {} not found for update", unitsId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete Units by ID
    @DeleteMapping("/delete/{unitsId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteUnits(@PathVariable Long unitsId) {
  	  unitsService.deleteUnits(unitsId);
        logger.info("Deleted Units with ID: {}", unitsId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
	    @GetMapping("/count/units")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countUnits()
	    {
	    	return unitsService.countUnits();
	    }

}
