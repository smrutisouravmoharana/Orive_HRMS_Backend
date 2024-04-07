package com.orive.PayeesAndPayers.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.PayeesAndPayers.Dto.PayersDto;
import com.orive.PayeesAndPayers.Entity.PayersEntity;
import com.orive.PayeesAndPayers.Repository.PayersRepository;

@Service
public class PayersService {
	
	private static final Logger logger = LoggerFactory.getLogger(PayersService.class);
	
	@Autowired
	private PayersRepository payersRepository;
	
	@Autowired
	private  ModelMapper modelMapper;
	
	// Create
    public PayersDto createPayers(PayersDto payersDto) {
    	PayersEntity payersEntity = convertToEntity(payersDto);
    	PayersEntity savedPayers = payersRepository.save(payersEntity);
        logger.info("Created Payers with ID: {}", savedPayers.getPayersId());
        return convertToDTO(savedPayers);
    }

    // Read
    public List<PayersDto> getAllPayers() {
        List<PayersEntity> payersEntities = payersRepository.findAll();
        logger.info("Retrieved {} Payers from the database", payersEntities.size());
        return payersEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by PayersId
    public Optional<PayersDto> getPayersById(Long payersId) {
        Optional<PayersEntity> payers = payersRepository.findById(payersId);
        if (payers.isPresent()) {
            return Optional.of(convertToDTO(payers.get()));
        } else {
            logger.warn("Payers with ID {} not found", payersId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public PayersDto updatePayers(Long payersId, PayersDto payersDto) {
        Optional<PayersEntity> existingPayersOptional = payersRepository.findById(payersId);
        if (existingPayersOptional.isPresent()) {
        	PayersEntity existingPayers = existingPayersOptional.get();
            modelMapper.map(payersDto, existingPayersOptional);
            PayersEntity updatedPayers = payersRepository.save(existingPayers);
            logger.info("Updated Payers with ID: {}", updatedPayers.getPayersId());
            return convertToDTO(updatedPayers);
        } else {
            logger.warn("Payers with ID {} not found for update", payersId);
            return null;
        }
    }
    
    // Delete
    public void deletePayers(Long payersId) {
    	payersRepository.deleteById(payersId);
        logger.info("Deleted Payers with ID: {}", payersId);
    }

    //count the total Payers
    public long countPayers()
	 {
		 return payersRepository.count();
	 }
    
	// Helper method to convert PayersDTo to PayersEntity
    private PayersEntity convertToEntity(PayersDto payersDto )
    {
    	return modelMapper.map(payersDto, PayersEntity.class);
    }

    // Helper method to convert PayersEntity to PayersDTo
    private PayersDto convertToDTO(PayersEntity payersEntity) 
    {
        return modelMapper.map(payersEntity, PayersDto.class);
    } 	

}
