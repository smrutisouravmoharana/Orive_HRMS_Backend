package com.orive.Transactions.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Transactions.Dto.DepositDto;
import com.orive.Transactions.Entity.DepositEntity;
import com.orive.Transactions.Repository.DepositRepository;

@Service
public class DepositService {
	
	private static final Logger logger = LoggerFactory.getLogger(DepositService.class);
	
	@Autowired
	private DepositRepository depositRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public DepositDto createDeposit(DepositDto depositDto) {
    	DepositEntity depositEntity = convertToEntity(depositDto);
    	DepositEntity savedDeposit = depositRepository.save(depositEntity);
        logger.info("Created Deposit with ID: {}", savedDeposit.getDepositId());
        return convertToDTO(savedDeposit);
    }

    // Read
    public List<DepositDto> getAllDeposit() {
        List<DepositEntity> depositEntities = depositRepository.findAll();
        logger.info("Retrieved {} Deposit from the database", depositEntities.size());
        return depositEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by DepositId
    public Optional<DepositDto> getDepositById(Long depositId) {
        Optional<DepositEntity> deposit = depositRepository.findById(depositId);
        if (deposit.isPresent()) {
            return Optional.of(convertToDTO(deposit.get()));
        } else {
            logger.warn("Deposit with ID {} not found", depositId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public DepositDto updateDeposit(Long depositId, DepositDto depositDto) {
        Optional<DepositEntity> existingDepositOptional = depositRepository.findById(depositId);
        if (existingDepositOptional.isPresent()) {
        	DepositEntity existingDeposit = existingDepositOptional.get();
        	existingDeposit.setEmployeeFullName(depositDto.getEmployeeFullName());
            existingDeposit.setBankName(depositDto.getBankName());
        	existingDeposit.setDepartment(depositDto.getDepartment());
            existingDeposit.setDepositType(depositDto.getDepositType());
        	existingDeposit.setEmployeePosition(depositDto.getEmployeePosition());
            existingDeposit.setReasonOfReturn(depositDto.getReasonOfReturn());
        	modelMapper.map(depositDto, existingDepositOptional);
            DepositEntity updatedDeposit = depositRepository.save(existingDeposit);
            logger.info("Updated Deposit with ID: {}", updatedDeposit.getDepositId());
            return convertToDTO(updatedDeposit);
        } else {
            logger.warn("Deposit with ID {} not found for update", depositId);
            return null;
        }
    }
    
    // Delete
    public void deleteDeposit(Long depositId) {
    	depositRepository.deleteById(depositId);
        logger.info("Deleted Deposit with ID: {}", depositId);
    }

    //count the total Deposit
    public long countDeposit()
	 {
		 return depositRepository.count();
	 }
    
	// Helper method to convert DepositDTo to DepositEntity
    private DepositEntity convertToEntity(DepositDto depositDto )
    {
    	return modelMapper.map(depositDto, DepositEntity.class);
    }

    // Helper method to convert DepositEntity to DepositDTo
    private DepositDto convertToDTO(DepositEntity depositEntity) 
    {
        return modelMapper.map(depositEntity, DepositDto.class);
    } 	
}
