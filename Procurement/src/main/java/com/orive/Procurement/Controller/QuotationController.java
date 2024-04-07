package com.orive.Procurement.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.orive.Procurement.Dto.QuotationDto;
import com.orive.Procurement.Entity.QuotationEntity;
import com.orive.Procurement.Enum.Status;
import com.orive.Procurement.Service.QuotationService;
//import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping(value = "quotation")
@CrossOrigin(origins = "*")
public class QuotationController {

	private static final Logger logger = LoggerFactory.getLogger(QuotationController.class);

    @Autowired
    private QuotationService quotationService;

 
    // Create a new Quotation
    @PostMapping("/create/quotation")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<String> saveQuotationEntity(
            @RequestParam("nameOfCompany") String nameOfCompany,
            @RequestParam("address") String address,
            @RequestParam("pinOrEquivalent") Long pinOrEquivalent,
            @RequestParam("expectedDateOfDelivery") LocalDate expectedDateOfDelivery,
            @RequestParam("placeOfDelivery") String placeOfDelivery,
            @RequestParam(value = "signatureAndStamp", required = false) MultipartFile fileDocument,
            @RequestParam("date") LocalDate date,
            @RequestParam ("status")String status){
    	
    	String result = quotationService.saveQuotationEntity( 
    			nameOfCompany, address, pinOrEquivalent, expectedDateOfDelivery, placeOfDelivery, fileDocument, date, status);
    
    	if(result != null) {
    		 return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to save Quotation entity", HttpStatus.INTERNAL_SERVER_ERROR);
       
    	}
    }
    
    
 // Get Quotation pdf by id  
    @GetMapping("/download/{quotationId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<byte[]> downloadsPdf(@PathVariable Long quotationId) {
        byte[] pdf = quotationService.downloadPdf(quotationId);

        if (pdf != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("signatureAndStamp").filename(quotationId + "QuotationId.pdf").build());
            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    		
      // Get all Quotation
      @GetMapping("/get/quotation")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<List<QuotationDto>> getAllQuotation() {
          List<QuotationDto> quotation = quotationService.getAllQuotation();
          logger.info("Retrieved {} Quotation from the database", quotation.size());
          return new ResponseEntity<>(quotation, HttpStatus.OK);
      }

      // Get quotationId
      @GetMapping("/get/{quotationId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<QuotationEntity> getQuotationByQuotationId(@PathVariable Long quotationId) {
    	  logger.info("Received Quotation to get Quotation by ID: {}", quotationId);
    	  QuotationEntity quotation = quotationService.getByQuotationId(quotationId);
          logger.info("Fetched expense details: {}", quotation);
          return ResponseEntity.ok(quotation);
      }

   
   

      // Delete Quotation by ID
      @DeleteMapping("/delete/{quotationId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<Void> deleteQuotation(@PathVariable Long quotationId) {
    	  quotationService.deleteQuotation(quotationId);
          logger.info("Deleted Quotation with ID: {}", quotationId);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  	    
      //Count the total Quotation
  	    @GetMapping("/count/quotation")
  	// @PreAuthorize("hasRole('client_HR')")
  	    public long countQuotation()
  	    {
  	    	return quotationService.countQuotation();
  	    }
}
