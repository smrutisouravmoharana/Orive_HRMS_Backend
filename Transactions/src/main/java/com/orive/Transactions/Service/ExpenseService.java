package com.orive.Transactions.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Transactions.Dto.ExpenseDto;
import com.orive.Transactions.Entity.ExpenseEntity;
import com.orive.Transactions.Repository.ExpenseRepository;

@Service
public class ExpenseService {
	
	private static final Logger logger=LoggerFactory.getLogger(ExpenseService.class);
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public ExpenseDto createExpense(ExpenseDto expenseDto) {
    	ExpenseEntity expenseEntity = convertToEntity(expenseDto);
    	ExpenseEntity savedExpense = expenseRepository.save(expenseEntity);
        logger.info("Created Expense with ID: {}", savedExpense.getExpenseId());
        return convertToDTO(savedExpense);
    }

    // Read
    public List<ExpenseDto> getAllExpense() {
        List<ExpenseEntity> expenseEntities = expenseRepository.findAll();
        logger.info("Retrieved {} Expense from the database", expenseEntities.size());
        return expenseEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by ExpenseId
    public Optional<ExpenseDto> getExpenseById(Long expenseId) {
        Optional<ExpenseEntity> expense = expenseRepository.findById(expenseId);
        if (expense.isPresent()) {
            return Optional.of(convertToDTO(expense.get()));
        } else {
            logger.warn("Expense with ID {} not found", expenseId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public ExpenseDto updateExpense(Long expenseId, ExpenseDto expenseDto) {
        Optional<ExpenseEntity> existingExpenseOptional = expenseRepository.findById(expenseId);
        if (existingExpenseOptional.isPresent()) {
        	ExpenseEntity existingExpense = existingExpenseOptional.get();
            existingExpense.setAccountType(expenseDto.getAccountType());
        	existingExpense.setBankName(expenseDto.getBankName());
            existingExpense.setDepartment(expenseDto.getDepartment());
        	existingExpense.setEmployeePosition(expenseDto.getEmployeePosition());
            existingExpense.setEmployeeFullName(expenseDto.getEmployeeFullName());
        	existingExpense.setExpenseCategory(expenseDto.getExpenseCategory());
            existingExpense.setPaymentMethod(expenseDto.getPaymentMethod());
        	modelMapper.map(expenseDto, existingExpenseOptional);
            ExpenseEntity updatedExpense = expenseRepository.save(existingExpense);
            logger.info("Updated Expense with ID: {}", updatedExpense.getExpenseId());
            return convertToDTO(updatedExpense);
        } else {
            logger.warn("Expense with ID {} not found for update", expenseId);
            return null;
        }
    }
    
    // Delete
    public void deleteExpense(Long expenseId) {
    	expenseRepository.deleteById(expenseId);
        logger.info("Deleted Expense with ID: {}", expenseId);
    }

    //count the total Expense
    public long countExpense()
	 {
		 return expenseRepository.count();
	 }
    
	// Helper method to convert ExpenseDTo to ExpenseEntity
    private ExpenseEntity convertToEntity(ExpenseDto expenseDto )
    {
    	return modelMapper.map(expenseDto, ExpenseEntity.class);
    }

    // Helper method to convert ExpenseEntity to ExpenseDTo
    private ExpenseDto convertToDTO(ExpenseEntity expenseEntity) 
    {
        return modelMapper.map(expenseEntity, ExpenseDto.class);
    } 	
}
