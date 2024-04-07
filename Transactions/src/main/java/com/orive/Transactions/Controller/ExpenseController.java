package com.orive.Transactions.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.orive.Transactions.Dto.DepositDto;
import com.orive.Transactions.Dto.ExpenseDto;
import com.orive.Transactions.Service.ExpenseService;

@RestController
@RequestMapping(value = "expense")
@CrossOrigin(origins = "*")
public class ExpenseController {
	
	private static final Logger logger=LoggerFactory.getLogger(ExpenseController.class);
	
	@Autowired
	private ExpenseService expenseService;
	
	// Create a new Expense
    @PostMapping("/create/expense")
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expenseDto) {
    	ExpenseDto createdExpense = expenseService.createExpense(expenseDto);
        logger.info("Created Expense with name: {}", createdExpense.getEmployeeFullName());
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }

    // Get all Expense   
    @GetMapping("/get/expense")
    public ResponseEntity<List<ExpenseDto>> getAllExpense() {
        List<ExpenseDto> expense = expenseService.getAllExpense();
        logger.info("Retrieved {} Expense from the database", expense.size());
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    // Get Expense by ID
    @GetMapping("/get/{expenseId}")
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable Long expenseId) {
        Optional<ExpenseDto> expense = expenseService.getExpenseById(expenseId);
        if (expense.isPresent()) {
            logger.info("Retrieved Expense with ID: {}", expenseId);
            return new ResponseEntity<>(expense.get(), HttpStatus.OK);
        } else {
            logger.warn("Expense with ID {} not found", expenseId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Expense by ID
    @PutMapping("/update/{expenseId}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable Long expenseId, @RequestBody ExpenseDto updatedExpenseDto) {
    	ExpenseDto updatedExpense = expenseService.updateExpense(expenseId, updatedExpenseDto);
        if (updatedExpense != null) {
            logger.info("Updated Expense with ID: {}", expenseId);
            return new ResponseEntity<>(updatedExpense, HttpStatus.OK);
        } else {
            logger.warn("Expense with ID {} not found for update", expenseId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete Expense by ID
    @DeleteMapping("/delete/{expenseId}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long expenseId) {
    	expenseService.deleteExpense(expenseId);
        logger.info("Deleted Expense with ID: {}", expenseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
	    @GetMapping("/count/expense")
	    public long countExpense()
	    {
	    	return expenseService.countExpense();
	    }
}
