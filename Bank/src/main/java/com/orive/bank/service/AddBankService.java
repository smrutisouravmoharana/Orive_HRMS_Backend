package com.orive.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.orive.bank.dto.AddBankDto;
import com.orive.bank.entities.AddBankEntity;
import com.orive.bank.repository.AddBankRepository;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//import org.springframework.core.env.Environment;

@Service
public class AddBankService {
	
	private  static final Logger logger=LoggerFactory.getLogger(AddBankService.class);
	
	@Autowired
	private AddBankRepository addBankRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
//	 @Autowired
//	    private Environment env; 
	
	
//	// Create
//    public AddBankDto createAddBank(AddBankDto addBankDto) {
//    	AddBankEntity addBankEntity = convertToEntity(addBankDto);
//    	AddBankEntity savedAddBank = addBankRepository.save(addBankEntity);
//        logger.info("Created AddBank with ID: {}", savedAddBank.getAccountName());
//        return convertToDTO(savedAddBank);
//    }
	
	// Create
			public AddBankDto createBank(AddBankDto addBankDto) {
			    // Check if the Bank name already exists
			    Optional<AddBankEntity> existingDepartment = addBankRepository.findByAccountNumber(addBankDto.getAccountNumber());
			    if (existingDepartment.isPresent()) {
			        // Bank name already exists, handle the error as needed
			        throw new RuntimeException("Designation with name '" + addBankDto.getAccountNumber() + "' already exists");
			    }
			    // Bank name is unique, proceed with saving
			    AddBankEntity departmentEntity = convertToEntity(addBankDto);
			    AddBankEntity savedDepartment = addBankRepository.save(departmentEntity);
			    logger.info("Created Designation with ID: {}", savedDepartment.getAddBankId());
			    return convertToDTO(savedDepartment);
			}
	 
//	// Create
//	 public AddBankDto createPromotions(AddBankDto promotionsDto) {
//		 AddBankEntity promotionsEntity = convertToEntity(promotionsDto);
//
////	     // Generate a unique email token
////	     String emailToken = UUID.randomUUID().toString();
////	     promotionsEntity.setEmailToken(emailToken);
//
//		 AddBankEntity savedPromotions = addBankRepository.save(promotionsEntity);
//	     logger.info("Created bank with ID: {}", savedPromotions.getAddBankId());
//
////	     // Send promotion email
////	     sendPromotionEmail(savedPromotions);
//
//	     // Send SMS message
//	     sendPromotionSMS(savedPromotions);
//
//	     return convertToDTO(savedPromotions);
//	 }

    // Read
    public List<AddBankDto> getAllAddBank() {
        List<AddBankEntity> addBankEntities = addBankRepository.findAll();
        logger.info("Retrieved {} AddBank from the database", addBankEntities.size());
        return addBankEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by AddBankId
    public Optional<AddBankDto> getAddBankById(String addBankId) {
        Optional<AddBankEntity> addBank = addBankRepository.findById(addBankId);
        if (addBank.isPresent()) {
            return Optional.of(convertToDTO(addBank.get()));
        } else {
            logger.warn("AddBank with ID {} not found", addBankId);
            return Optional.empty();
        }
    }
    
  //get by AccountByNumber
    public Optional<AddBankDto> getBankByAccountNumber(Long accountNumber) {
        Optional<AddBankEntity> department = addBankRepository.findByAccountNumber(accountNumber);
        if (department.isPresent()) {
            return Optional.of(convertToDTO(department.get()));
        } else {
            logger.warn("Designation with AccountNumber {} not found", accountNumber);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public AddBankDto updateAddBank(String addBankId, AddBankDto addBankDto) {
        Optional<AddBankEntity> existingAddBankOptional = addBankRepository.findById(addBankId);
        if (existingAddBankOptional.isPresent()) {
        	AddBankEntity existingAddBank = existingAddBankOptional.get();
        	existingAddBank.setBankName(addBankDto.getBankName());
        	existingAddBank.setAccountName(addBankDto.getAccountName());
        	existingAddBank.setAccountNumber(addBankDto.getAccountNumber());
        	existingAddBank.setStatus(addBankDto.getStatus());
        	modelMapper.map(addBankDto, existingAddBankOptional);
            AddBankEntity updatedAddBank = addBankRepository.save(existingAddBank);
            logger.info("Updated AddBank with ID: {}", updatedAddBank.getAddBankId());
            return convertToDTO(updatedAddBank);
        } else {
            logger.warn("AddBank with ID {} not found for update", addBankId);
            return null;
        }
    }
    
    // Delete
    public void deleteAddBank(String addBankId) {
    	addBankRepository.deleteById(addBankId);
        logger.info("Deleted AddBank with ID: {}", addBankId);
    }

    //count the total AddBank
    public long countAddBank()
	 {
		 return addBankRepository.count();
	 }
    
    
//    private void sendPromotionSMS(AddBankEntity promotionsEntity) {
//        String messageBody = "Congratulations  your bank information added on orive hrms software to " + promotionsEntity.getAccountNumber() + "!";
//
//        Twilio.init(
//            env.getProperty("twilio.account.sid"),
//            env.getProperty("twilio.auth.token")
//        );
//
//        Message message = Message.creator(
//                new PhoneNumber(promotionsEntity.getPhoneNumber()), // Employee's phone number
//                new PhoneNumber(env.getProperty("twilio.from.number")),
//                messageBody
//            ).create();
//
//        logger.info("Sent SMS message to {}: {}", promotionsEntity.getPhoneNumber(), message.getSid());
//    }
    
	// Helper method to convert AddBankDTo to AddBankEntity
    private AddBankEntity convertToEntity(AddBankDto addBankDto )
    {
    	return modelMapper.map(addBankDto, AddBankEntity.class);
    }

    // Helper method to convert AddBankEntity  to AddBankDTo
    private AddBankDto convertToDTO(AddBankEntity addBankEntity) {
        return modelMapper.map(addBankEntity, AddBankDto.class);
    } 
}
