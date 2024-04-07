package com.orive.Accounts.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Accounts.Dto.AccountBalanceDto;
import com.orive.Accounts.Dto.AccountListDto;
import com.orive.Accounts.Entity.AccountBalancesEntity;
import com.orive.Accounts.Entity.AccountListEntity;
import com.orive.Accounts.Repository.AccountBalanceRepository;

@Service
public class AccountBalanceService {

	private static final Logger logger= LoggerFactory.getLogger(AccountBalanceService.class);
	
	@Autowired
	private AccountBalanceRepository accountBalanceRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
	public AccountBalanceDto createAccountBalance(AccountBalanceDto accountBalanceDto) {
    	AccountBalancesEntity accountBalancesEntity = convertToEntity(accountBalanceDto);
    	AccountBalancesEntity savedAccountBalance = accountBalanceRepository.save(accountBalancesEntity);
        logger.info("Created AccountBalance with ID: {}", savedAccountBalance.getAccountBalancesId());
        return convertToDTO(savedAccountBalance);
    }

    // Read
	public List<AccountBalanceDto> getAllAccountBalance() {
        List<AccountBalancesEntity> accountBalancesEntities = accountBalanceRepository.findAll();
        logger.info("Retrieved {} AccountBalance from the database", accountBalancesEntities.size());
        return accountBalancesEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by AccountBalanceId
	 public Optional<AccountBalanceDto> getAccountBalanceById(Long accountBalanceId) {
	        Optional<AccountBalancesEntity> accountBalance = accountBalanceRepository.findById(accountBalanceId);
	        if (accountBalance.isPresent()) {
	            return Optional.of(convertToDTO(accountBalance.get()));
	        } else {
	            logger.warn("AccountBalance with ID {} not found", accountBalanceId);
	            return Optional.empty();
	        }
	    }
    
 // Update list by id
	 public AccountBalanceDto updateAccountBalance(Long accountBalanceId, AccountBalanceDto accountBalanceDto) {
	        Optional<AccountBalancesEntity> existingAccountBalanceOptional = accountBalanceRepository.findById(accountBalanceId);
	        if (existingAccountBalanceOptional.isPresent()) {
	        	AccountBalancesEntity existingAccountBalance = existingAccountBalanceOptional.get();
	        	existingAccountBalance.setEmployeeName(accountBalanceDto.getEmployeeName());
	        	existingAccountBalance.setHsaBalance(accountBalanceDto.getHsaBalance());
	        	existingAccountBalance.setFsaBalance(accountBalanceDto.getFsaBalance());
	        	existingAccountBalance.setStatus(accountBalanceDto.getStatus());
	            modelMapper.map(accountBalanceDto, existingAccountBalanceOptional);
	            AccountBalancesEntity updatedAccountBalance = accountBalanceRepository.save(existingAccountBalance);
	            logger.info("Updated AccountBalance with ID: {}", updatedAccountBalance.getAccountBalancesId());
	            return convertToDTO(updatedAccountBalance);
	        } else {
	            logger.warn("AccountBalance with ID {} not found for update", accountBalanceId);
	            return null;
	        }
	    }
    
    // Delete
	 public void deleteAccountBalance(Long accountBalanceId) {
	    	accountBalanceRepository.deleteById(accountBalanceId);
	        logger.info("Deleted AccountBalance with ID: {}", accountBalanceId);
	    }

    //count the total AccountBalance
	 public long countAccountBalance()
	 {
		 return accountBalanceRepository.count();
	 }
    
	// Helper method to convert AccountBalanceDTo to AccountBalanceEntity
	 private AccountBalancesEntity convertToEntity(AccountBalanceDto accountBalanceDto )
	    {
	    	return modelMapper.map(accountBalanceDto, AccountBalancesEntity.class);
	    }

    // Helper method to convert AccountBalanceEntity to AccountBalanceDTo
	 private AccountBalanceDto convertToDTO(AccountBalancesEntity accountBalancesEntity) 
	    {
	        return modelMapper.map(accountBalancesEntity, AccountBalanceDto.class);
	    }	
}
