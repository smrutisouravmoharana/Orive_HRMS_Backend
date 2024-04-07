package com.orive.Accounts.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Accounts.Dto.OpeningBalanceDTo;
import com.orive.Accounts.Entity.OpeningBalanceEntity;
import com.orive.Accounts.Repository.OpeningBalanceRepository;

@Service
public class OpeningBalanceService {

    private static final Logger logger= LoggerFactory.getLogger(OpeningBalanceService.class);
	
	@Autowired
	private OpeningBalanceRepository openingBalanceRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public OpeningBalanceDTo createOpeningBalance(OpeningBalanceDTo openingBalanceDTo) {
    	OpeningBalanceEntity openingBalanceEntity = convertToEntity(openingBalanceDTo);
    	OpeningBalanceEntity savedOpeningBalance = openingBalanceRepository.save(openingBalanceEntity);
        logger.info("Created OpeningBalance with ID: {}", savedOpeningBalance.getOpeningBalanceId());
        return convertToDTO(savedOpeningBalance);
    }

    // Read
    public List<OpeningBalanceDTo> getAllOpeningBalance() {
        List<OpeningBalanceEntity> openingBalanceEntities = openingBalanceRepository.findAll();
        logger.info("Retrieved {} OpeningBalance from the database", openingBalanceEntities.size());
        return openingBalanceEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by OpeningBalanceId
    public Optional<OpeningBalanceDTo> getOpeningBalanceById(Long openingBalanceId) {
        Optional<OpeningBalanceEntity> openingBalance = openingBalanceRepository.findById(openingBalanceId);
        if (openingBalance.isPresent()) {
            return Optional.of(convertToDTO(openingBalance.get()));
        } else {
            logger.warn("OpeningBalance with ID {} not found", openingBalanceId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public OpeningBalanceDTo updateOpeningBalance(Long openingBalanceId, OpeningBalanceDTo openingBalanceDTo) {
        Optional<OpeningBalanceEntity> existingOpeningBalanceOptional = openingBalanceRepository.findById(openingBalanceId);
        if (existingOpeningBalanceOptional.isPresent()) {
        	OpeningBalanceEntity existingtOpeningBalance = existingOpeningBalanceOptional.get();
            modelMapper.map(openingBalanceDTo, existingOpeningBalanceOptional);
            OpeningBalanceEntity updatedOpeningBalance = openingBalanceRepository.save(existingtOpeningBalance);
            logger.info("Updated OpeningBalance with ID: {}", updatedOpeningBalance.getOpeningBalanceId());
            return convertToDTO(updatedOpeningBalance);
        } else {
            logger.warn("OpeningBalance with ID {} not found for update", openingBalanceId);
            return null;
        }
    }
    
    // Delete
    public void deleteOpeningBalance(Long openingBalanceId) {
    	openingBalanceRepository.deleteById(openingBalanceId);
        logger.info("Deleted OpeningBalance with ID: {}", openingBalanceId);
    }

    //count the total OpeningBalance
    public long countOpeningBalance()
	 {
		 return openingBalanceRepository.count();
	 }
    
	// Helper method to convert OpeningBalanceDTo to OpeningBalanceEntity
    private  OpeningBalanceEntity convertToEntity(OpeningBalanceDTo openingBalanceDTo )
    {
    	return modelMapper.map(openingBalanceDTo,OpeningBalanceEntity.class);
    }

    // Helper method to convert OpeningBalanceEntity to OpeningBalanceDTo
    private OpeningBalanceDTo convertToDTO(OpeningBalanceEntity openingBalanceEntity) 
    {
        return modelMapper.map(openingBalanceEntity, OpeningBalanceDTo.class);
    } 	
}
