package com.orive.PayeesAndPayers.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.PayeesAndPayers.Dto.PayeesDto;
import com.orive.PayeesAndPayers.Entity.PayeesEntity;
import com.orive.PayeesAndPayers.Repository.PayeesRepository;

@Service
public class PayeesService {

	private static final Logger logger= LoggerFactory.getLogger(PayeesService.class);
	
	@Autowired
	private PayeesRepository payeesRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	// Create
    public PayeesDto createPayees(PayeesDto payeesDto) {
    	PayeesEntity payeesEntity = convertToEntity(payeesDto);
    	PayeesEntity savedPayees = payeesRepository.save(payeesEntity);
        logger.info("Created Payees with ID: {}", savedPayees.getPayeesId());
        return convertToDTO(savedPayees);
    }

    // Read
    public List<PayeesDto> getAllPayees() {
        List<PayeesEntity> payeesEntities = payeesRepository.findAll();
        logger.info("Retrieved {} Payees from the database", payeesEntities.size());
        return payeesEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by PayeesId
    public Optional<PayeesDto> getPayeesById(Long payeesId) {
        Optional<PayeesEntity> payees = payeesRepository.findById(payeesId);
        if (payees.isPresent()) {
            return Optional.of(convertToDTO(payees.get()));
        } else {
            logger.warn("Payees with ID {} not found", payeesId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public PayeesDto updatePayees(Long payeesId, PayeesDto payeesDto) {
        Optional<PayeesEntity> existingPayeesOptional = payeesRepository.findById(payeesId);
        if (existingPayeesOptional.isPresent()) {
        	PayeesEntity existingPayees = existingPayeesOptional.get();
            modelMapper.map(payeesDto, existingPayeesOptional);
            PayeesEntity updatedPayees = payeesRepository.save(existingPayees);
            logger.info("Updated Payees with ID: {}", updatedPayees.getPayeesId());
            return convertToDTO(updatedPayees);
        } else {
            logger.warn("Payees with ID {} not found for update", payeesId);
            return null;
        }
    }
    
    // Delete
    public void deletePayees(Long payeesId) {
    	payeesRepository.deleteById(payeesId);
        logger.info("Deleted Payees with ID: {}", payeesId);
    }

    //count the total Payees
    public long countPayees()
	 {
		 return payeesRepository.count();
	 }
    
	// Helper method to convert PayeesDTo to PayeesEntity
    private PayeesEntity convertToEntity(PayeesDto payeesDto )
    {
    	return modelMapper.map(payeesDto, PayeesEntity.class);
    }

    // Helper method to convert PayeesEntity to PayeesDTo
    private PayeesDto convertToDTO(PayeesEntity payeesEntity) 
    {
        return modelMapper.map(payeesEntity, PayeesDto.class);
    } 	
}
