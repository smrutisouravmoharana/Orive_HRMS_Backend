package com.orive.Organisation.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Organisation.Dto.ExpenceDto;
import com.orive.Organisation.Entity.ExpenceEntity;
import com.orive.Organisation.Entity.ExpenseListEntity;
import com.orive.Organisation.Exceptions.ResourceNotFoundException;
import com.orive.Organisation.Repository.ExpenseListRepository;

@Service
public class ExpenseListService {
	
private  static final Logger logger=LoggerFactory.getLogger(ExpenseListService.class);
	
	@Autowired
	private ExpenseListRepository expenseListRepository;
	
	//create method
	 public void saveExpenseList(ExpenseListEntity expenseList) {
	        try {
	        	expenseListRepository.save(expenseList);
	        	logger.info("Expense saved successfully: {}", expenseList);
	        } catch (Exception e) {
	        	logger.error("Error saving expense: {}", e.getMessage(), e);
	            // Handle the exception as needed
	        }
	    }
	 
	 // Read all ExpenseList
	    public List<ExpenseListEntity> getExpenceListAllDetails() {
	    	logger.info("Fetching all ExpenseList");
	        List<ExpenseListEntity> expenseList = expenseListRepository.findAll();
	        logger.info("Fetched {} expenses", expenseList.size());
	        return expenseList;
	    }
		

	    // Get ExpenseList by expenceId
	    public List<ExpenseListEntity> getExpenseListByExpenceId(Long expenceId) {
	    	logger.info("Fetching expenseList for expenceId: {}", expenceId);
	        List<ExpenseListEntity> expenseList = expenseListRepository.findByexpenceId(expenceId);
	        logger.info("Fetched {} expenseList for expenceId: {}", expenseList.size(), expenceId);
	        return expenseList;
	    }

	    // Get ExpenseList by expenceListId
	    public ExpenseListEntity getExpenseListByxpenceId(Long expenceListId) {
	    	logger.info("Fetching ExpenseList for expenceListId: {}", expenceListId);
	        ExpenseListEntity expenseList = expenseListRepository.findById(expenceListId)
	                .orElseThrow(() -> {
	                	logger.error("ExpenseList not found for expenceListId: {}", expenceListId);
	                    return new ResourceNotFoundException("ExpenseList with the given id is not found: " + expenceListId);
	                });
	        logger.info("Fetched expense for expenceListId: {}", expenceListId);
	        return expenseList;
	    }
	    
	    
	 // Update list by id
	    public ExpenseListEntity updateLocation(Long expenceListId, ExpenseListEntity updatedLocation) {
	    	ExpenseListEntity existingLocation = expenseListRepository.findById(expenceListId)
	                .orElse(null);

	        if (existingLocation != null) {
	            existingLocation.setPurchaseDate(updatedLocation.getPurchaseDate());
	            existingLocation.setPurchasedBy(updatedLocation.getPurchasedBy());
	            existingLocation.setDescription(updatedLocation.getDescription());
	            existingLocation.setAmount(updatedLocation.getAmount());

	            ExpenseListEntity updatedLocationEntity = expenseListRepository.save(existingLocation);
	            logger.info("Updated ExpencList with ID: {}", updatedLocationEntity.getExpenceListId());
	            return updatedLocationEntity;
	        } else {
	            logger.warn("ExpencList with ID {} not found for update", expenceListId);
	            return null;
	        }
	    }
	
	       
	    // Delete
	    public void deleteExpeceList(Long expenceListId) {
	    	expenseListRepository.deleteById(expenceListId);
	        logger.info("Deleted ExpenceList with ID: {}", expenceListId);
	    }
}

