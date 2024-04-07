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

import com.orive.Procurement.Dto.RequestDto;
import com.orive.Procurement.Service.RequestService;
//import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping(value = "request")
@CrossOrigin(origins = "*")
public class RequestController {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    private RequestService requestService;
    
    
 // Create a new Request
    @PostMapping("/create/request")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<RequestDto> createRequest(@RequestBody RequestDto requestDto) {
    	RequestDto createdRequest = requestService.createRequest(requestDto);
        logger.info("Created Request with name: {}", createdRequest.getRequestingPerson());
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    // Get all Request
    
    @GetMapping("/get/request")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<RequestDto>> getAllRequest() {
        List<RequestDto> request = requestService.getAllRequest();
        logger.info("Retrieved {} Request from the database", request.size());
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    // Get RequestbyId
    @GetMapping("/get/{requestId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<RequestDto> getRequestbyId(@PathVariable Long requestId) {
        Optional<RequestDto> request = requestService.getRequestById(requestId);
        if (request.isPresent()) {
            logger.info("Retrieved Request with ID: {}", requestId);
            return new ResponseEntity<>(request.get(), HttpStatus.OK);
        } else {
            logger.warn("Request with ID {} not found", requestId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Request by ID
    @PutMapping("/update/{requestId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<RequestDto> updateRequest(@PathVariable Long requestId, @RequestBody RequestDto updatedRequestDto) {
    	RequestDto updatedRequest = requestService.updateRequest(requestId, updatedRequestDto);
        if (updatedRequest != null) {
            logger.info("Updated Request with ID: {}", requestId);
            return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
        } else {
            logger.warn("Request with ID {} not found for update", requestId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete Request by ID
    @DeleteMapping("/delete/{requestId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long requestId) {
  	  requestService.deleteRequest(requestId);
        logger.info("Deleted Request with ID: {}", requestId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
	    @GetMapping("/count/request")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countRequest()
	    {
	    	return requestService.countRequest();
	    }

}
