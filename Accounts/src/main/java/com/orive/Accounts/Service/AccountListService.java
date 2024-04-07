package com.orive.Accounts.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Accounts.Dto.AccountListDto;
import com.orive.Accounts.Entity.AccountListEntity;
import com.orive.Accounts.Repository.AccountListRepository;

@Service
public class AccountListService {
	
	private static final Logger logger= LoggerFactory.getLogger(AccountListService.class);
	
	@Autowired
	private AccountListRepository accountListRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public AccountListDto createAccountList(AccountListDto accountListDto) {
    	AccountListEntity accountListEntity = convertToEntity(accountListDto);
    	AccountListEntity savedAccountList = accountListRepository.save(accountListEntity);
        logger.info("Created AccountList with ID: {}", savedAccountList.getAccountListId());
        return convertToDTO(savedAccountList);
    }

 // Read
    public List<AccountListDto> getAllAccountList() {
        List<AccountListEntity> accountListEntities = accountListRepository.findAll();
        logger.info("Retrieved {} AccountList from the database", accountListEntities.size());
        return accountListEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
  //get by AccountListId
    public Optional<AccountListDto> getAccountListById(Long accountListId) {
        Optional<AccountListEntity> accountList = accountListRepository.findById(accountListId);
        if (accountList.isPresent()) {
            return Optional.of(convertToDTO(accountList.get()));
        } else {
            logger.warn("AccountList with ID {} not found", accountListId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public AccountListDto updateAccountList(Long accountListId, AccountListDto accountListDto) {
        Optional<AccountListEntity> existingAccountListOptional = accountListRepository.findById(accountListId);
        if (existingAccountListOptional.isPresent()) {
        	AccountListEntity existingAccountList = existingAccountListOptional.get();
        	existingAccountList.setEmployeeName(accountListDto.getEmployeeName());
        	existingAccountList.setBankName(accountListDto.getBankName());
        	existingAccountList.setEmailAddress(accountListDto.getEmailAddress());
        	existingAccountList.setUsername(accountListDto.getUsername());
        	existingAccountList.setPassword(accountListDto.getPassword());
        	existingAccountList.setStatus(accountListDto.getStatus());
            modelMapper.map(accountListDto, existingAccountListOptional);
            AccountListEntity updatedAccountList = accountListRepository.save(existingAccountList);
            logger.info("Updated AccountList with ID: {}", updatedAccountList.getAccountListId());
            return convertToDTO(updatedAccountList);
        } else {
            logger.warn("AccountList with ID {} not found for update", accountListId);
            return null;
        }
    }
    
 // Delete
    public void deleteAccountList(Long accountListId) {
    	accountListRepository.deleteById(accountListId);
        logger.info("Deleted AccountList with ID: {}", accountListId);
    }

    //count the total AccountList
    public long countAccountList()
	 {
		 return accountListRepository.count();
	 }
    
	// Helper method to convert AccountListDTo to AccountListEntity
    private AccountListEntity convertToEntity(AccountListDto accountListDto )
    {
    	return modelMapper.map(accountListDto, AccountListEntity.class);
    }

    // Helper method to convert AccountListEntity  to AccountListDTo
    private AccountListDto convertToDTO(AccountListEntity accountListEntity) {
        return modelMapper.map(accountListEntity, AccountListDto.class);
    } 
}
