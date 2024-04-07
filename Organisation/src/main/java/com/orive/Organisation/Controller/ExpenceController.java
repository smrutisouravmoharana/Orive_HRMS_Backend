package com.orive.Organisation.Controller;

import com.orive.Organisation.Dto.ExpenceDto;
import com.orive.Organisation.Entity.ExpenceEntity;
import com.orive.Organisation.Service.ExpenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
//import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping(value = "expence")
@CrossOrigin(origins = "*")
public class ExpenceController {

	private static final Logger logger = LoggerFactory.getLogger(ExpenceController.class);

    @Autowired
    private ExpenceService expenceService;

 
    // Create a new Expence
    @PostMapping(value = "/create/expence" , consumes = "multipart/form-data")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<String> saveExpenceEntity(
            @RequestParam("expenceType") String expenceType,
            @RequestParam("createdDate") LocalDate createdDate,
            @RequestParam("total") Long total,
            @RequestParam(value = "uploadDocument", required = false) MultipartFile fileDocument
    ){
    	
    	String result = expenceService.saveExpenceEntity( 
    			expenceType, createdDate, total,  fileDocument );
    
    	if(result != null) {
    		 return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to save Expence entity", HttpStatus.INTERNAL_SERVER_ERROR);
       
    	}
    }
    
    
 // Get Expence pdf by id  
    @GetMapping("/download/{expenceId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<byte[]> downloadsPdf(@PathVariable Long expenceId) {
        byte[] pdf = expenceService.downloadPdf(expenceId);

        if (pdf != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(expenceId + "Expence.pdf").build());
            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    		
      // Get all Expence
      @GetMapping("/get/expence")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<List<ExpenceDto>> getAllExpence() {
          List<ExpenceDto> expence = expenceService.getAllExpence();
          logger.info("Retrieved {} Expence from the database", expence.size());
          return new ResponseEntity<>(expence, HttpStatus.OK);
      }

      @GetMapping("/get/{expenceId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<ExpenceEntity> getExpenceByExpenceId(@PathVariable Long expenceId) {
    	  logger.info("Received request to get expense by ID: {}", expenceId);
          ExpenceEntity expence = expenceService.getByCareerSiteId(expenceId);
          logger.info("Fetched expense details: {}", expence);
          return ResponseEntity.ok(expence);
      }

     
      

      // Delete Expence by ID
      @DeleteMapping("/delete/{expenceId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<Void> deleteExpence(@PathVariable Long expenceId) {
    	  expenceService.deleteExpence(expenceId);
          logger.info("Deleted Expence with ID: {}", expenceId);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  	    
      //Count the total Expence
  	    @GetMapping("/count/expence")
  	// @PreAuthorize("hasRole('client_HR')")
  	    public long countExpence()
  	    {
  	    	return expenceService.countExpence();
  	    }
}
