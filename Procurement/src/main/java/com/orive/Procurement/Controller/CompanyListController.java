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

import com.orive.Procurement.Entity.CompanyListEntity;
import com.orive.Procurement.Entity.QuotationListEntity;
import com.orive.Procurement.Service.CompanyListService;
import com.orive.Procurement.Service.QuotationListService;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "companylist")
@CrossOrigin(origins = "*")
public class CompanyListController {
	
private  static final Logger logger=LoggerFactory.getLogger(CompanyListController.class);
	
	@Autowired
	private CompanyListService companyListService;
	
	@PostMapping("/add")
	// @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<String> addCompanyList(@RequestBody CompanyListEntity companyList) {
		logger.info("Received request to add CompanyList: {}", companyList);
		companyListService.saveCompanyList(companyList);
        logger.info("CompanyList saved successfully.");
        return ResponseEntity.ok("CompanyList saved successfully.");
    }

    @GetMapping("/all")
 // @PreAuthorize("hasRole('client_HR')")
    public List<CompanyListEntity> getAllCompanyList() {
    	logger.info("Received request to fetch all CompanyList.");
        List<CompanyListEntity> companyList = companyListService.getCompanyListAllDetails();
        logger.info("Fetched {} company.", companyList.size());
        return companyList;
    }

    @GetMapping("/{bidAnalysisId}")
 // @PreAuthorize("hasRole('client_HR')")
    public List<CompanyListEntity> getCompanyByBidAnalysisId(@PathVariable Long bidAnalysisId) {
    	logger.info("Received request to fetch company for bidAnalysisId: {}", bidAnalysisId);
        List<CompanyListEntity> companyList = companyListService.getCompanyListByBidAnalysisId(bidAnalysisId);
        logger.info("Fetched {} company for bidAnalysisId: {}", companyList.size(), bidAnalysisId);
        return companyList;
    }

    @GetMapping("/detail/{companyListId}")
 // @PreAuthorize("hasRole('client_HR')")
    public CompanyListEntity getCompanyListDetails(@PathVariable Long companyListId) {
    	logger.info("Received request to fetch CompanyList details for companyListId: {}", companyListId);
    	CompanyListEntity companyList = companyListService.getCompanyListByCompanyListId(companyListId);
        logger.info("Fetched company details: {}", companyListId);
        return companyList;
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

    @DeleteMapping("/delete/{companyListId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<String> deleteCompanyList(@PathVariable Long companyListId) {
    	logger.info("Received request to delete CompanyList with ID: {}", companyListId);
    	companyListService.deleteCompanyList(companyListId);
        logger.info("CompanyList deleted successfully.");
        return ResponseEntity.ok("CompanyList deleted successfully.");
    }

}
