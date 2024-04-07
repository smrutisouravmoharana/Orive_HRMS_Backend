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

import com.orive.Accounts.Dto.AccountListDto;
import com.orive.Accounts.Service.AccountListService;
//import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping(value = "accountlist")
@CrossOrigin(origins = "*")
public class AccountListController {
	
	private static final Logger logger=LoggerFactory.getLogger(AccountListController.class);
	
	@Autowired
	private AccountListService accountListService;
	
	
	// Create a new AccountList
    @PostMapping("/create/accountlist")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<AccountListDto> createAccountList(@RequestBody AccountListDto accountListDto) {
    	AccountListDto createdAccountList = accountListService.createAccountList(accountListDto);
        logger.info("Created AccountList with name: {}", createdAccountList.getEmployeeName());
        return new ResponseEntity<>(createdAccountList, HttpStatus.CREATED);
    }

    // Get all AccountList   
    @GetMapping("/get/accountlist")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<AccountListDto>> getAllAccountList() {
        List<AccountListDto> accountList = accountListService.getAllAccountList();
        logger.info("Retrieved {} AccountList from the database", accountList.size());
        return new ResponseEntity<>(accountList, HttpStatus.OK);
    }

    // Get AccountList by ID
    @GetMapping("/get/{accountListId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<AccountListDto> getAccountListById(@PathVariable Long accountListId) {
        Optional<AccountListDto> accountList = accountListService.getAccountListById(accountListId);
        if (accountList.isPresent()) {
            logger.info("Retrieved AccountList with ID: {}", accountListId);
            return new ResponseEntity<>(accountList.get(), HttpStatus.OK);
        } else {
            logger.warn("AccountList with ID {} not found", accountListId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update AccountList by ID
    @PutMapping("/update/{accountListId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<AccountListDto> updateAccountList(@PathVariable Long accountListId, @RequestBody AccountListDto updatedAccountListDto) {
    	AccountListDto updatedAccountList = accountListService.updateAccountList(accountListId, updatedAccountListDto);
        if (updatedAccountList != null) {
            logger.info("Updated AccountList with ID: {}", accountListId);
            return new ResponseEntity<>(updatedAccountList, HttpStatus.OK);
        } else {
            logger.warn("AccountList with ID {} not found for update", accountListId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete AccountList by ID
    @DeleteMapping("/delete/{accountListId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteAccountList(@PathVariable Long accountListId) {
    	accountListService.deleteAccountList(accountListId);
        logger.info("Deleted AccountList with ID: {}", accountListId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
    // Count the total AccountList 
	    @GetMapping("/count/accountlist")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countAccountList()
	    {
	    	return accountListService.countAccountList();
	    }
}
