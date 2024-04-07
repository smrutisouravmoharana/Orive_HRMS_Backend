package com.orive.Procurement.Controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.orive.Procurement.Dto.GoodReceivedDto;
import com.orive.Procurement.Entity.GoodReceivedEntity;
import com.orive.Procurement.Entity.GoodReceivedListEntity;
import com.orive.Procurement.Entity.QuotationEntity;
import com.orive.Procurement.Enum.Status;
import com.orive.Procurement.Service.GoodReceivedService;
//import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping(value = "goodreceived")
@CrossOrigin(origins = "*")
public class GoodReceivedController {
	
	private static final Logger logger = LoggerFactory.getLogger(GoodReceivedController.class);

    @Autowired
    private GoodReceivedService goodReceivedService;
    

    
    
    
    //  Create a new GoodReceived
    @PostMapping(value = "/create/goodreceived", consumes = "multipart/form-data")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<String> saveGoodReceivedEntity(
            @RequestParam("purchaseOrder") String purchaseOrder,
            @RequestParam("paymentSource") String paymentSource,
            @RequestParam("vendorName") String vendorName,
            @RequestParam("date") LocalDate date,
            @RequestParam("receivedByName") String receivedByName,
            @RequestParam("title")  String title,
            @RequestParam(value = "signatureAndStamp", required = false) MultipartFile file,
            @RequestParam("status") String status){
    	
    	String result = goodReceivedService.saveGoodReceivedEntity( 
    			purchaseOrder, paymentSource, vendorName, date, receivedByName, title, file, status);
    
    	if(result != null) {
    		 return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to save GoodReceived entity", HttpStatus.INTERNAL_SERVER_ERROR);
       
    	}
    }
       
    
////Get GoodReceived signatureAndStamp by VendorName
//    @GetMapping("/download/{vendorName}")
 // @PreAuthorize("hasRole('client_HR')")
//	public ResponseEntity<?> downloadImage(@PathVariable String vendorName){
//		byte[] imageData=goodReceivedService.downloadImage(vendorName);
//		return ResponseEntity.status(HttpStatus.OK)
//				.contentType(MediaType.valueOf("image/png"))
//				.body(imageData);
//	}   
    
    
//Get GoodReceived signatureAndStamp by VendorName  
    @GetMapping("/download/{vendorName}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<byte[]> downloadsPdf(@PathVariable String vendorName) {
        byte[] pdf = goodReceivedService.downloadPdf(vendorName);

        if (pdf != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("signatureAndStamp").filename(vendorName + "vendorName.pdf").build());
            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    // Get all GoodReceived   
    @GetMapping("/get/goodreceived")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<GoodReceivedDto>> getAllGoodReceived() {
        List<GoodReceivedDto> goodReceived = goodReceivedService.getAllGoodReceived();
        logger.info("Retrieved {} GoodReceived from the database", goodReceived.size());
        return new ResponseEntity<>(goodReceived, HttpStatus.OK);
    }
    

    // Get GoodReceivedbyId
//    @GetMapping("/get/{goodReceivedId}")
 // @PreAuthorize("hasRole('client_HR')")
//    public ResponseEntity<GoodReceivedDto> getGoodReceivedbyId(@PathVariable Long goodReceivedId) {
//        Optional<GoodReceivedDto> goodReceived = goodReceivedService.getGoodReceivedById(goodReceivedId);
//        if (goodReceived.isPresent()) {
//            logger.info("Retrieved GoodReceived with ID: {}", goodReceivedId);
//            return new ResponseEntity<>(goodReceived.get(), HttpStatus.OK);
//        } else {
//            logger.warn("GoodReceived with ID {} not found", goodReceivedId);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
    
    
    // Get GoodReceivedbyId
    @GetMapping("/get/{goodReceivedId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<GoodReceivedEntity> getGoodReceivedByGoodReceivedId(@PathVariable Long goodReceivedId) {
  	  logger.info("Received GoodReceived to get GoodReceived by ID: {}", goodReceivedId);
  	GoodReceivedEntity goodReceived = goodReceivedService.getByGoodReceivedId(goodReceivedId);
        logger.info("Fetched goodReceived details: {}", goodReceived);
        return ResponseEntity.ok(goodReceived);
    }

    // Update GoodReceived by ID
    @PutMapping("/update/{goodReceivedId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<GoodReceivedDto> updateGoodReceived(@PathVariable Long goodReceivedId, @RequestBody GoodReceivedDto updatedGoodReceivedDto) {
    	GoodReceivedDto updatedGoodReceived = goodReceivedService.updateGoodReceived(goodReceivedId, updatedGoodReceivedDto);
        if (updatedGoodReceived != null) {
            logger.info("Updated GoodReceived with ID: {}", goodReceivedId);
            return new ResponseEntity<>(updatedGoodReceived, HttpStatus.OK);
        } else {
            logger.warn("GoodReceived with ID {} not found for update", goodReceivedId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete GoodReceived by ID
    @DeleteMapping("/delete/{goodReceivedId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteGoodReceived(@PathVariable Long goodReceivedId) {
  	  goodReceivedService.deleteGoodReceived(goodReceivedId);
        logger.info("Deleted GoodReceived with ID: {}", goodReceivedId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
    //Count the total GoodReceived
	    @GetMapping("/count/goodreceived")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countGoodReceived()
	    {
	    	return goodReceivedService.countGoodReceived();
	    }


}
