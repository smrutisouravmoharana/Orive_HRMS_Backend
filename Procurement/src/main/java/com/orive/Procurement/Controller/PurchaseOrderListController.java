package com.orive.Procurement.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orive.Procurement.Entity.PurchaseOrderListEntity;
import com.orive.Procurement.Service.PurchaseOrderListService;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "purchaseorderlist")
@CrossOrigin(origins = "*")
public class PurchaseOrderListController {
	
private  static final Logger logger=LoggerFactory.getLogger(PurchaseOrderListController.class);
	
	@Autowired
	private PurchaseOrderListService purchaseOrderListService;
	
	@PostMapping("/add")
	// @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<String> addPurchaseOrderList(@RequestBody PurchaseOrderListEntity purchaseOrderList) {
		logger.info("Received request to add  PurchaseOrderList: {}", purchaseOrderList);
		purchaseOrderListService.savePurchaseOrderList(purchaseOrderList);
        logger.info("PurchaseOrderList saved successfully.");
        return ResponseEntity.ok("PurchaseOrderList saved successfully.");
    }

    @GetMapping("/all")
 // @PreAuthorize("hasRole('client_HR')")
    public List<PurchaseOrderListEntity> getAllPurchaseOrderList() {
    	logger.info("Received request to fetch all PurchaseOrderList.");
        List<PurchaseOrderListEntity> purchaseOrderList = purchaseOrderListService.getPurchaseOrderListAllDetails();
        logger.info("Fetched {} PurchaseOrderList.", purchaseOrderList.size());
        return purchaseOrderList;
    }

    @GetMapping("/{purchaseOrderId}")
 // @PreAuthorize("hasRole('client_HR')")
    public List<PurchaseOrderListEntity> getPurchaseOrderBypurchaseOrderId(@PathVariable Long purchaseOrderId) {
    	logger.info("Received request to fetch PurchaseOrder for purchaseOrderId: {}", purchaseOrderId);
        List<PurchaseOrderListEntity> purchaseOrderList = purchaseOrderListService.getPurchaseOrderListByPurchaseOrderId(purchaseOrderId);
        logger.info("Fetched {} PurchaseOrderList for purchaseOrderId: {}", purchaseOrderList.size(), purchaseOrderId);
        return purchaseOrderList;
    }

    @GetMapping("/detail/{purchaseOrderListId}")
 // @PreAuthorize("hasRole('client_HR')")
    public PurchaseOrderListEntity getPurchaseOrderListDetails(@PathVariable Long purchaseOrderListId) {
    	logger.info("Received request to fetch PurchaseOrderList details for PurchaseOrderList: {}", purchaseOrderListId);
    	PurchaseOrderListEntity purchaseOrderList = purchaseOrderListService.getPurchaseOrderListByPurchaseOrderListId(purchaseOrderListId);
        logger.info("Fetched PurchaseOrderList details: {}", purchaseOrderList);
        return purchaseOrderList;
    }
    
    
//    @PutMapping("/{purchaseOrderListId}")
 // @PreAuthorize("hasRole('client_HR')")
//    public ResponseEntity<PurchaseOrderListEntity> updatePurchaseOrderList(
//            @PathVariable Long purchaseOrderListId,
//            @RequestBody PurchaseOrderListEntity updatedLocation) {
//
//        PurchaseOrderListEntity updatedPurchaseOrderList = purchaseOrderListService.updateLocation(purchaseOrderListId, updatedLocation);
//
//        if (updatedPurchaseOrderList != null) {
//        	logger.info("Update PurchaseOrderList successfully by  purchaseOrderListId: {}", purchaseOrderListId);
//            return ResponseEntity.ok(updatedPurchaseOrderList);    
//        } else {
//        	logger.info("PurchaseOrderList with ID {} not found for update purchaseOrderListId: {}", purchaseOrderListId);
//            return ResponseEntity.notFound().build();
//        }
//    }

    @DeleteMapping("/delete/{purchaseOrderListId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<String> deletePurchaseOrderList(@PathVariable Long purchaseOrderListId) {
    	logger.info("Received request to delete PurchaseOrderList with ID: {}", purchaseOrderListId);
    	purchaseOrderListService.deletePurchaseOrderList(purchaseOrderListId);
        logger.info("PurchaseOrderList deleted successfully.");
        return ResponseEntity.ok("PurchaseOrderList deleted successfully.");
    }

}
