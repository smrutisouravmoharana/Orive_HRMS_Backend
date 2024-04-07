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

import com.orive.Employee.Dto.TransfersDto;
import com.orive.Employee.Dto.TravelsDto;
import com.orive.Employee.Service.TravelsService;
//import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping(value = "travels")
@CrossOrigin(origins = "*")
public class TravelsController {
	
	private static final Logger logger = LoggerFactory.getLogger(TravelsController.class);
	
	 @Autowired
	    private TravelsService travelsService;
	 
	// Create a new Travels
    @PostMapping("/create/travels")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<TravelsDto> createTravels(@RequestBody TravelsDto travelsDto) {
    	TravelsDto createdTravel = travelsService.createTravels(travelsDto);
        logger.info("Created Travels with id: {}", createdTravel.getEmployeeName());
        return new ResponseEntity<>(createdTravel, HttpStatus.CREATED);
    }

    // Get all Travels 
    @GetMapping("/get/travels")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<TravelsDto>> getAllTravels() {
        List<TravelsDto> travels = travelsService.getAllTravels();
        logger.info("Retrieved {} Travels from the database", travels.size());
        return new ResponseEntity<>(travels, HttpStatus.OK);
    }

    // Get Travels by ID
    @GetMapping("/get/{travelsId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<TravelsDto> getTravelsId(@PathVariable Long travelsId) {
        Optional<TravelsDto> travels = travelsService.getTravelsById(travelsId);
        if (travels.isPresent()) {
            logger.info("Retrieved travels with ID: {}", travelsId);
            return new ResponseEntity<>(travels.get(), HttpStatus.OK);
        } else {
            logger.warn("travels with ID {} not found", travelsId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
 // Get Travels by Employee ID
    @GetMapping("/findtravels/{username}")
    //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
    public ResponseEntity<List<TravelsDto>> getTravelsByUsername(@PathVariable String username) {
        logger.info("Getting Travels for username: {}", username);
        List<TravelsDto> transfers = travelsService.getTravelsByUsername(username);
        logger.info("Found {} Travels for username: {}", transfers.size(), username);
        return ResponseEntity.ok(transfers);
    }

    // Update Travels by ID
    @PutMapping("/update/{travelsId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<TravelsDto> updateTravels(@PathVariable Long travelsId, @RequestBody TravelsDto updatedTravelsDTO) {
    	TravelsDto updatedTravels = travelsService.updateTravels(travelsId, updatedTravelsDTO);
        if (updatedTravels != null) {
            logger.info("Updated travels with ID: {}", travelsId);
            return new ResponseEntity<>(updatedTravels, HttpStatus.OK);
        } else {
            logger.warn("Travels with ID {} not found for update", travelsId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Delete Travels by ID
    @DeleteMapping("/delete/{travelsId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteTravels(@PathVariable Long travelsId) {
   	 travelsService.deleteTravels(travelsId);
        logger.info("Deleted Travels with ID: {}", travelsId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
 // Count the total  Travels
	    @GetMapping("/count/travels")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countTravels()
	    {
	    	return travelsService.countTravels();
	    }
}
