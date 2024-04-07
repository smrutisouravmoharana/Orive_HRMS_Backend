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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orive.Procurement.Entity.QuotationListEntity;
import com.orive.Procurement.Service.QuotationListService;
//import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping(value = "quotationlist")
@CrossOrigin(origins = "*")
public class QuotationListController {

private  static final Logger logger=LoggerFactory.getLogger(QuotationListController.class);
	
	@Autowired
	private QuotationListService quotationListService;
	
	@PostMapping("/add")
	// @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<String> addQuotationList(@RequestBody QuotationListEntity quotationList) {
		logger.info("Received request to add QuotationList: {}", quotationList);
		quotationListService.saveQuotationList(quotationList);
        logger.info("QuotationList saved successfully.");
        return ResponseEntity.ok("QuotationList saved successfully.");
    }

    @GetMapping("/all")
 // @PreAuthorize("hasRole('client_HR')")
    public List<QuotationListEntity> getAllQuotationList() {
    	logger.info("Received request to fetch all QuotationList.");
        List<QuotationListEntity> quotationList = quotationListService.getQuotationListAllDetails();
        logger.info("Fetched {} Quotation.", quotationList.size());
        return quotationList;
    }

    @GetMapping("/{quotationId}")
 // @PreAuthorize("hasRole('client_HR')")
    public List<QuotationListEntity> getQuotationByQuotationId(@PathVariable Long quotationId) {
    	logger.info("Received request to fetch quotation for quotationId: {}", quotationId);
        List<QuotationListEntity> quotationList = quotationListService.getQuotationListByQuotationId(quotationId);
        logger.info("Fetched {} Quotation for quotationId: {}", quotationList.size(), quotationId);
        return quotationList;
    }

    @GetMapping("/detail/{quotationListId}")
 // @PreAuthorize("hasRole('client_HR')")
    public QuotationListEntity getQuotationListDetails(@PathVariable Long quotationListId) {
    	logger.info("Received request to fetch QuotationList details for quotationListId: {}", quotationListId);
    	QuotationListEntity quotationList = quotationListService.getQuotationListByquotationListId(quotationListId);
        logger.info("Fetched Quotation details: {}", quotationList);
        return quotationList;
    }
    
    
//    @PutMapping("/{quotationListId}")
 // @PreAuthorize("hasRole('client_HR')")
//    public ResponseEntity<ExpenseListEntity> updateExpenseList(
//            @PathVariable Long expenceListId,
//            @RequestBody ExpenseListEntity updatedLocation) {
//
//        ExpenseListEntity updatedExpenseList = expenseListService.updateLocation(expenceListId, updatedLocation);
//
//        if (updatedExpenseList != null) {
//        	logger.info("Update QuotationList successfully by  expenceListId: {}", expenceListId);
//            return ResponseEntity.ok(updatedExpenseList);    
//        } else {
//        	logger.info("QuotationList with ID {} not found for update expenceListId: {}", expenceListId);
//            return ResponseEntity.notFound().build();
//        }
//    }

    @DeleteMapping("/delete/{quotationListId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<String> deleteQuotationList(@PathVariable Long quotationListId) {
    	logger.info("Received request to delete QuotationList with ID: {}", quotationListId);
    	quotationListService.deleteQuotationList(quotationListId);
        logger.info("QuotationList deleted successfully.");
        return ResponseEntity.ok("QuotationList deleted successfully.");
    }
}
