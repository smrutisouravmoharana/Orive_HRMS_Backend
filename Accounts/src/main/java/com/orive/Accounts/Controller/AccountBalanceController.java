package com.orive.Accounts.Controller;

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

import com.orive.Accounts.Dto.AccountBalanceDto;
import com.orive.Accounts.Service.AccountBalanceService;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "accountbalance")
@CrossOrigin(origins = "*")
public class AccountBalanceController {
	
	private static final Logger logger= LoggerFactory.getLogger(AccountBalanceController.class);
	
	@Autowired
	private AccountBalanceService accountBalanceService;
	
	// Create a new AccountBalance
    @PostMapping("/create/accountbalance")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<AccountBalanceDto> createAccountBalance(@RequestBody AccountBalanceDto accountBalanceDto) {
    	AccountBalanceDto createdAccountBalance = accountBalanceService.createAccountBalance(accountBalanceDto);
        logger.info("Created AccountBalance with name: {}", createdAccountBalance.getEmployeeName());
        return new ResponseEntity<>(createdAccountBalance, HttpStatus.CREATED);
    }

    // Get all AccountBalance   
    @GetMapping("/get/accountbalance")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<AccountBalanceDto>> getAllAccountBalance() {
        List<AccountBalanceDto> accountBalance = accountBalanceService.getAllAccountBalance();
        logger.info("Retrieved {} AccountBalance from the database", accountBalance.size());
        return new ResponseEntity<>(accountBalance, HttpStatus.OK);
    }

    // Get AccountBalance by ID
    @GetMapping("/get/{accountBalanceId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<AccountBalanceDto> getAccountBalanceById(@PathVariable Long accountBalanceId) {
        Optional<AccountBalanceDto> accountBalance = accountBalanceService.getAccountBalanceById(accountBalanceId);
        if (accountBalance.isPresent()) {
            logger.info("Retrieved AccountBalance with ID: {}", accountBalanceId);
            return new ResponseEntity<>(accountBalance.get(), HttpStatus.OK);
        } else {
            logger.warn("AccountBalance with ID {} not found", accountBalanceId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update AccountBalance by ID
    @PutMapping("/update/{accountBalanceId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<AccountBalanceDto> updateAccountBalance(@PathVariable Long accountBalanceId, @RequestBody AccountBalanceDto updatedAccountBalanceDto) {
    	AccountBalanceDto updatedAccountBalance = accountBalanceService.updateAccountBalance(accountBalanceId, updatedAccountBalanceDto);
        if (updatedAccountBalance != null) {
            logger.info("Updated AccountBalance with ID: {}", accountBalanceId);
            return new ResponseEntity<>(updatedAccountBalance, HttpStatus.OK);
        } else {
            logger.warn("AccountBalance with ID {} not found for update", accountBalanceId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete AccountBalance by ID
    @DeleteMapping("/delete/{accountBalanceId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteAccountBalance(@PathVariable Long accountBalanceId) {
    	accountBalanceService.deleteAccountBalance(accountBalanceId);
        logger.info("Deleted AccountBalance with ID: {}", accountBalanceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
 // Count the total AccountBalance
	    @GetMapping("/count/accountbalance")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countAccountBalance()
	    {
	    	return accountBalanceService.countAccountBalance();
	    }
}
