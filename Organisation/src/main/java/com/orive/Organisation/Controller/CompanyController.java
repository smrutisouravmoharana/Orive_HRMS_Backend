package com.orive.Organisation.Controller;

import com.orive.Organisation.Dto.CompanyDto;
import com.orive.Organisation.Entity.CompanyEntity;
import com.orive.Organisation.Enum.Status;
import com.orive.Organisation.Exceptions.ResourceNotFoundException;
import com.orive.Organisation.Service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping(value = "company")
@CrossOrigin(origins = "*")
public class CompanyController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

  
 	// Create a new Company
      @PostMapping(value = "/create/company" , consumes = "multipart/form-data")  
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<?> saveCompanyEntity(
    		  @RequestParam("address") String address,
    		  @RequestParam("cin") String cin,
    		  @RequestParam("city") String city,
              @RequestParam("companyName") String companyName,
              @RequestParam("companyType") String companyType,
              @RequestParam("contactNumber") Long contactNumber,
              @RequestParam("country") String country,
              @RequestParam("createdDate") LocalDate createdDate,
              @RequestParam("email") String email,
              @RequestParam(value = "file", required = false) MultipartFile fileDocument,
              @RequestParam("gst") String gst,
              @RequestParam("legalOrTradingName") String legalOrTradingName,           
              @RequestParam("registrationNumber") String registrationNumber,
              @RequestParam("state") String state,
              @RequestParam("uan") String uan,
              @RequestParam("website") String website,  
              @RequestParam("zipCode") int zipCode,                                                           
              @RequestParam("status")  String status

              ) {
    	  String result = companyService.saveCompanyEntity( 
    			  address, cin, city,  companyName, companyType, contactNumber, country, createdDate,  email,  fileDocument,  gst, legalOrTradingName, registrationNumber, state, uan, website, zipCode, status);
      
    	  if (result != null && result.startsWith("Error")) {
              return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
          } else if (result != null) {
              return new ResponseEntity<>(result, HttpStatus.OK);
          } else {
              return new ResponseEntity<>("Failed to save Company entity", HttpStatus.INTERNAL_SERVER_ERROR);
          }
      }
    
    
    
 // Get companies logo by name
    @GetMapping("/{companyName}")
 // @PreAuthorize("hasRole('client_HR')")
	public ResponseEntity<?> downloadImage(@PathVariable String companyName){
		byte[] imageData=companyService.downloadImage(companyName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);
	}
     
    
 // Get all companies  
      @GetMapping("/get/company")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<List<CompanyDto>> getAllCompany() {
          List<CompanyDto> companies = companyService.getAllCompany();
          logger.info("Retrieved {} companies from the database", companies.size());
          return new ResponseEntity<>(companies, HttpStatus.OK);
      }

      // Get company by ID
      @GetMapping("/get/{companyId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<CompanyEntity> getCompanyById(@PathVariable Long companyId) {
          try {
              CompanyEntity company = companyService.getCompanyById(companyId);
              return ResponseEntity.ok(company);
          } catch (ResourceNotFoundException e) {
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
          } catch (Exception e) {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
          }
      }
      

   // Update company by ID
      @PutMapping("/update/{companyId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<Void> partialUpdateCompany(
              @PathVariable Long companyId,
              @RequestBody CompanyEntity companyEntity) {

          companyService.partialUpdateCompany(companyId, companyEntity);
          return new ResponseEntity<>(HttpStatus.OK);
      }


      // Delete Company by ID
      @DeleteMapping("/delete/{companyId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId) {
    	  companyService.deleteCompany(companyId);
          logger.info("Deleted company with ID: {}", companyId);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  	    
      //Count the total Company
  	    @GetMapping("/count/company")
  	// @PreAuthorize("hasRole('client_HR')")
  	    public long countCompany()
  	    {
  	    	return companyService.countCompany();
  	    }

}




	

