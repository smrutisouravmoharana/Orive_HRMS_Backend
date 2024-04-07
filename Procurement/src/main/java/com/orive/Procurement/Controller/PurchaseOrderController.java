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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.orive.Procurement.Dto.PurchaseOrderDto;
import com.orive.Procurement.Entity.PurchaseOrderEntity;
import com.orive.Procurement.Enum.Status;
import com.orive.Procurement.Service.PurchaseOrderService;
//import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping(value = "purchaseOrder")
@CrossOrigin(origins = "*")
public class PurchaseOrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);

    @Autowired
    private  PurchaseOrderService purchaseOrderService;
    
    
    
    // Create a new PurchaseOrder
    @PostMapping(value = "/create/purchaseOrder",consumes = "multipart/form-data")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<String> savePurchaseOrderEntity(
            @RequestParam("quotation")String quotation,
            @RequestParam("location") String location,
            @RequestParam("vendorName") String vendorName,
            @RequestParam("address") String address,
            @RequestParam("notes") String notes,
            @RequestParam("authorizedByName") String authorizedByName,
            @RequestParam("title") String title,
            @RequestParam(value = "signatureAndStamp", required = false) MultipartFile fileDocument,
            @RequestParam("date") LocalDate date,
            @RequestParam("status") String status){
    	
    	String result = purchaseOrderService.savePurchaseOrderEntity( 
    			quotation, location, vendorName, address, notes, authorizedByName, title, fileDocument, date, status);
    
    	if(result != null) {
    		 return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to save PurchaseOrder entity", HttpStatus.INTERNAL_SERVER_ERROR);
       
    	}
    }
    
    
 // Get Quotation pdf by id  
    @GetMapping("/download/{purchaseOrderId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<byte[]> downloadsPdf(@PathVariable Long purchaseOrderId) {
        byte[] pdf = purchaseOrderService.downloadPdf(purchaseOrderId);

        if (pdf != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("signatureAndStamp").filename(purchaseOrderId + "purchaseOrderId.pdf").build());
            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    // Get all PurchaseOrder    
    @GetMapping("/get/purchaseOrder")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<PurchaseOrderDto>> getAllPurchaseOrder() {
        List<PurchaseOrderDto> purchaseOrder = purchaseOrderService.getAllPurchaseOrder();
        logger.info("Retrieved {} PurchaseOrder from the database", purchaseOrder.size());
        return new ResponseEntity<>(purchaseOrder, HttpStatus.OK);
    }

//    // Get PurchaseOrderbyId
//    @GetMapping("/get/{bidAnalysisId}")
 // @PreAuthorize("hasRole('client_HR')")
//    public ResponseEntity<PurchaseOrderDto> getPurchaseOrderbyId(@PathVariable Long purchaseOrderId) {
//        Optional<PurchaseOrderDto> purchaseOrder = purchaseOrderService.getPurchaseOrderById(purchaseOrderId);
//        if (purchaseOrder.isPresent()) {
//            logger.info("Retrieved PurchaseOrder with ID: {}", purchaseOrderId);
//            return new ResponseEntity<>(purchaseOrder.get(), HttpStatus.OK);
//        } else {
//            logger.warn("PurchaseOrder with ID {} not found", purchaseOrderId);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    
    // Get PurchaseOrderId
    @GetMapping("/get/{purchaseOrderId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<PurchaseOrderEntity> getPurchaseOrderByPurchaseOrderId(@PathVariable Long purchaseOrderId) {
  	  logger.info("Received PurchaseOrder to get PurchaseOrder by ID: {}", purchaseOrderId);
  	PurchaseOrderEntity purchaseOrder = purchaseOrderService.getByPurchaseOrderId(purchaseOrderId);
        logger.info("Fetched PurchaseOrder details: {}", purchaseOrder);
        return ResponseEntity.ok(purchaseOrder);
    }

    
    // Update PurchaseOrder by ID
    @PutMapping("/update/{purchaseOrderId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<PurchaseOrderDto> updatePurchaseOrder(@PathVariable Long purchaseOrderId, @RequestBody PurchaseOrderDto updatedPurchaseOrderDto) {
    	PurchaseOrderDto updatedPurchaseOrder= purchaseOrderService.updatePurchaseOrder(purchaseOrderId, updatedPurchaseOrderDto);
        if (updatedPurchaseOrder != null) {
            logger.info("Updated PurchaseOrder with ID: {}", purchaseOrderId);
            return new ResponseEntity<>(updatedPurchaseOrder, HttpStatus.OK);
        } else {
            logger.warn("PurchaseOrder with ID {} not found for update", purchaseOrderId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete PurchaseOrder by ID
    @DeleteMapping("/delete/{purchaseOrderId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deletePurchaseOrder(@PathVariable Long purchaseOrderId) {
  	  purchaseOrderService.deletePurchaseOrder(purchaseOrderId);
        logger.info("Deleted PurchaseOrder with ID: {}", purchaseOrderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
    //count the total PurchaseOrder
	    @GetMapping("/count/purchaseOrder")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countPurchaseOrder()
	    {
	    	return purchaseOrderService.countPurchaseOrder();
	    }


}
