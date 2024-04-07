package com.orive.Organisation.Controller;

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

import com.orive.Organisation.Entity.ExpenseListEntity;
import com.orive.Organisation.Service.ExpenseListService;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "expencelist")
@CrossOrigin(origins = "*")
public class ExpenseListController {

private  static final Logger logger=LoggerFactory.getLogger(ExpenseListController.class);
	
	@Autowired
	private ExpenseListService expenseListService;
	
	@PostMapping("/add")
	// @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<String> addExpenseList(@RequestBody ExpenseListEntity expenseList) {
		logger.info("Received request to add expenseList: {}", expenseList);
		expenseListService.saveExpenseList(expenseList);
        logger.info("ExpenseList saved successfully.");
        return ResponseEntity.ok("ExpenseList saved successfully.");
    }

    @GetMapping("/all")
 // @PreAuthorize("hasRole('client_HR')")
    public List<ExpenseListEntity> getAllExpensesList() {
    	logger.info("Received request to fetch all expenseList.");
        List<ExpenseListEntity> expenseList = expenseListService.getExpenceListAllDetails();
        logger.info("Fetched {} expenses.", expenseList.size());
        return expenseList;
    }

    @GetMapping("/{expenceId}")
 // @PreAuthorize("hasRole('client_HR')")
    public List<ExpenseListEntity> getExpensesByExpenceId(@PathVariable Long expenceId) {
    	logger.info("Received request to fetch expenses for expenceId: {}", expenceId);
        List<ExpenseListEntity> expenseList = expenseListService.getExpenseListByExpenceId(expenceId);
        logger.info("Fetched {} expenses for expenceId: {}", expenseList.size(), expenceId);
        return expenseList;
    }

    @GetMapping("/detail/{expenceListId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ExpenseListEntity getExpenseListDetails(@PathVariable Long expenceListId) {
    	logger.info("Received request to fetch expenseList details for expenceListId: {}", expenceListId);
        ExpenseListEntity expenseList = expenseListService.getExpenseListByxpenceId(expenceListId);
        logger.info("Fetched expense details: {}", expenseList);
        return expenseList;
    }
    
    
    @PutMapping("/{expenceListId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<ExpenseListEntity> updateExpenseList(
            @PathVariable Long expenceListId,
            @RequestBody ExpenseListEntity updatedLocation) {

        ExpenseListEntity updatedExpenseList = expenseListService.updateLocation(expenceListId, updatedLocation);

        if (updatedExpenseList != null) {
        	logger.info("Update expenseList successfully by  expenceListId: {}", expenceListId);
            return ResponseEntity.ok(updatedExpenseList);    
        } else {
        	logger.info("expenseList with ID {} not found for update expenceListId: {}", expenceListId);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{expenceListId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<String> deleteExpenseList(@PathVariable Long expenceListId) {
    	logger.info("Received request to delete expenseList with ID: {}", expenceListId);
    	expenseListService.deleteExpeceList(expenceListId);
        logger.info("ExpenseList deleted successfully.");
        return ResponseEntity.ok("ExpenseList deleted successfully.");
    }
}
