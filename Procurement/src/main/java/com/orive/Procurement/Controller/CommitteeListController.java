package com.orive.Procurement.Controller;

import java.time.LocalDate;
import java.util.List;

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

import com.orive.Procurement.Entity.CommitteeListEntity;
import com.orive.Procurement.Service.CommitteeListService;
//import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping(value = "committeelist")
@CrossOrigin(origins = "*")
public class CommitteeListController {
	
private  static final Logger logger=LoggerFactory.getLogger(CommitteeListController.class);
	
	@Autowired
	private CommitteeListService committeeListService;
	
//	@PostMapping("/add")
	// @PreAuthorize("hasRole('client_HR')")
//    public ResponseEntity<String> addCommitteeList(@RequestBody CommitteeListEntity committeeList) {
//		logger.info("Received request to add CommitteeList: {}", committeeList);
//		committeeListService.saveCommitteeList(committeeList);
//        logger.info("CommitteeList saved successfully.");
//        return ResponseEntity.ok("CommitteeList saved successfully.");
//    }

	
	 
    // Create a new CommitteeList
    @PostMapping(value = "/create/committeelist", consumes = "multipart/form-data")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<String> saveCommitteeListEntity(
    		@RequestParam("bidAnalysisId") Long bidAnalysisId,
            @RequestParam("name") String name,
            @RequestParam(value = "signature", required = false) MultipartFile filePhoto,
            @RequestParam("date") LocalDate date){
    	
    	String result = committeeListService.saveCommitteeListEntity( 
    			bidAnalysisId,name,filePhoto,date );
    
    	if(result != null) {
    		 return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to save CommitteeList entity", HttpStatus.INTERNAL_SERVER_ERROR);
       
    	}
    }
    
    
 // Get CommitteeList Photo by bidAnalysisId  
    @GetMapping("/download/{bidAnalysisId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<byte[]> downloadsPhoto(@PathVariable Long bidAnalysisId) {
        byte[] photo = committeeListService.downloadImage(bidAnalysisId);

        if (photo != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("signature").filename(bidAnalysisId + "bidAnalysisId.pdf").build());
            return new ResponseEntity<>(photo, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	
	
    @GetMapping("/all")
 // @PreAuthorize("hasRole('client_HR')")
    public List<CommitteeListEntity> getAllCommitteeList() {
    	logger.info("Received request to fetch all CommitteeList.");
        List<CommitteeListEntity> committeeList = committeeListService.getCommitteeListAllDetails();
        logger.info("Fetched {} committee.", committeeList.size());
        return committeeList;
    }

    @GetMapping("/{bidAnalysisId}")
 // @PreAuthorize("hasRole('client_HR')")
    public List<CommitteeListEntity> getCommitteeByBidAnalysisId(@PathVariable Long bidAnalysisId) {
    	logger.info("Received request to fetch committee for bidAnalysisId: {}", bidAnalysisId);
        List<CommitteeListEntity> committeeList = committeeListService.getCommitteeListByBidAnalysisId(bidAnalysisId);
        logger.info("Fetched {} committee for bidAnalysisId: {}", committeeList.size(), bidAnalysisId);
        return committeeList;
    }

    @GetMapping("/detail/{committeeListId}")
 // @PreAuthorize("hasRole('client_HR')")
    public CommitteeListEntity getCommitteeListDetails(@PathVariable Long committeeListId) {
    	logger.info("Received request to fetch committeeList details for committeeListId: {}", committeeListId);
    	CommitteeListEntity committeeList = committeeListService.getCommitteeListByCommitteeListId(committeeListId);
        logger.info("Fetched committee details: {}", committeeListId);
        return committeeList;
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

    @DeleteMapping("/delete/{committeeListId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<String> deleteCommitteeList(@PathVariable Long committeeListId) {
    	logger.info("Received request to delete CommitteeList with ID: {}", committeeListId);
    	committeeListService.deleteCommitteeList(committeeListId);
        logger.info("CommitteeList deleted successfully.");
        return ResponseEntity.ok("CommitteeList deleted successfully.");
    }


}
