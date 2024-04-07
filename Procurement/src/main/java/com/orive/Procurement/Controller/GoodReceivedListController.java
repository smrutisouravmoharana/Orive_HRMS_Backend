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

import com.orive.Procurement.Entity.GoodReceivedListEntity;
import com.orive.Procurement.Entity.QuotationListEntity;
import com.orive.Procurement.Service.GoodReceivedListService;
import com.orive.Procurement.Service.QuotationListService;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "goodreceivedlist")
@CrossOrigin(origins = "*")
public class GoodReceivedListController {
	
private  static final Logger logger=LoggerFactory.getLogger(GoodReceivedListController.class);
	
	@Autowired
	private GoodReceivedListService goodReceivedListService ;
	
	@PostMapping("/add")
	// @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<String> addGoodReceivedList(@RequestBody GoodReceivedListEntity goodReceivedList) {
		logger.info("Received request to add GoodReceivedList: {}", goodReceivedList);
		goodReceivedListService.saveGoodReceivedList(goodReceivedList);
        logger.info("GoodReceivedList saved successfully.");
        return ResponseEntity.ok("GoodReceivedList saved successfully.");
    }

    @GetMapping("/all")
 // @PreAuthorize("hasRole('client_HR')")
    public List<GoodReceivedListEntity> getAllGoodReceivedList() {
    	logger.info("Received request to fetch all GoodReceivedList.");
        List<GoodReceivedListEntity> goodReceivedList = goodReceivedListService.getGoodReceivedListAllDetails();
        logger.info("Fetched {} GoodReceivedList.", goodReceivedList.size());
        return goodReceivedList;
    }

    @GetMapping("/{goodReceivedId}")
 // @PreAuthorize("hasRole('client_HR')")
    public List<GoodReceivedListEntity> getGoodReceivedByGoodReceivedId(@PathVariable Long goodReceivedId) {
    	logger.info("Received request to fetch GoodReceived for goodReceivedId: {}", goodReceivedId);
        List<GoodReceivedListEntity> goodReceivedList = goodReceivedListService.getGoodReceivedListByGoodReceivedId(goodReceivedId);
        logger.info("Fetched {} GoodReceivedList for goodReceivedId: {}", goodReceivedList.size(), goodReceivedId);
        return goodReceivedList;
    }

    @GetMapping("/detail/{goodReceivedListId}")
 // @PreAuthorize("hasRole('client_HR')")
    public GoodReceivedListEntity getGoodReceivedListDetails(@PathVariable Long goodReceivedListId) {
    	logger.info("Received request to fetch GoodReceivedList details for goodReceivedListId: {}", goodReceivedListId);
    	GoodReceivedListEntity goodReceivedList = goodReceivedListService.getGoodReceivedListByGoodReceivedListId(goodReceivedListId);
        logger.info("Fetched GoodReceivedList details: {}", goodReceivedList);
        return goodReceivedList;
    }
    
    
//    @PutMapping("/{goodReceivedListId}")
 // @PreAuthorize("hasRole('client_HR')")
//    public ResponseEntity<GoodReceivedListEntity> updateGoodReceivedList(
//            @PathVariable Long goodReceivedListId,
//            @RequestBody GoodReceivedListEntity updatedUnit) {
//
//        GoodReceivedListEntity updatedGoodReceivedList = goodReceivedListService.updatedUnit(goodReceivedListId, updatedUnit);
//
//        if (updatedGoodReceivedList != null) {
//        	logger.info("Update GoodReceivedList successfully by  goodReceivedListId: {}", goodReceivedListId);
//            return ResponseEntity.ok(updatedGoodReceivedList);    
//        } else {
//        	logger.info("GoodReceivedList with ID {} not found for update goodReceivedListId: {}", goodReceivedListId);
//            return ResponseEntity.notFound().build();
//        }
//    }

    @DeleteMapping("/delete/{goodReceivedListId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<String> deleteGoodReceivedList(@PathVariable Long goodReceivedListId) {
    	logger.info("Received request to delete GoodReceivedList with ID: {}", goodReceivedListId);
    	goodReceivedListService.deleteGoodReceivedList(goodReceivedListId);
        logger.info("GoodReceivedList deleted successfully.");
        return ResponseEntity.ok("GoodReceivedList deleted successfully.");
    }

}
