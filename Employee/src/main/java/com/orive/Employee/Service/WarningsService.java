package com.orive.Employee.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Employee.Dto.TransfersDto;
import com.orive.Employee.Dto.WarningsDto;
import com.orive.Employee.Entity.TransfersEntity;
import com.orive.Employee.Entity.WarningsEntity;
import com.orive.Employee.Repository.WarningsRepository;



@Service
public class WarningsService {
	
private static final Logger logger=LoggerFactory.getLogger(WarningsService.class);
	
	@Autowired
	private WarningsRepository warningsRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public WarningsDto createWarnings(WarningsDto warningsDto) {
    	WarningsEntity warningsEntity = convertToEntity(warningsDto);
    	WarningsEntity savedwarnings = warningsRepository.save(warningsEntity);
        logger.info("Created Warnings with ID: {}", savedwarnings.getWarningsId());
        return convertToDTO(savedwarnings);
    }

    // Read
    public List<WarningsDto> getAllWarnings() {
        List<WarningsEntity> warningsEntities = warningsRepository.findAll();
        logger.info("Retrieved {} Warnings from the database", warningsEntities.size());
        return warningsEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by WarningsId
    public Optional<WarningsDto> getWarningsById(Long warningsId) {
        Optional<WarningsEntity> warnings = warningsRepository.findById(warningsId);
        if (warnings.isPresent()) {
            return Optional.of(convertToDTO(warnings.get()));
        } else {
            logger.warn("Warnings with ID {} not found", warningsId);
            return Optional.empty();
        }
    }
    
    //get details by username
    public List<WarningsDto> getTransfersByUsername(String username) {
        List<WarningsEntity> transfersEntities = warningsRepository.findByUsername(username);
        return transfersEntities.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
 // Update list by id
    public WarningsDto updateWarnings(Long warningsId, WarningsDto warningsDto) {
        Optional<WarningsEntity> existingWarningsOptional = warningsRepository.findById(warningsId);
        if (existingWarningsOptional.isPresent()) {
        	WarningsEntity existingWarnings = existingWarningsOptional.get();
        	existingWarnings.setEmployeeName(warningsDto.getEmployeeName());
        	existingWarnings.setWarningByEmployee(warningsDto.getWarningByEmployee());
            modelMapper.map(warningsDto, existingWarningsOptional);
            WarningsEntity updatedWarnings = warningsRepository.save(existingWarnings);
            logger.info("Updated Warnings with ID: {}", updatedWarnings.getWarningsId());
            return convertToDTO(updatedWarnings);
        } else {
            logger.warn("Warnings with ID {} not found for update", warningsId);
            return null;
        }
    }
    
    // Delete
    public void deleteWarnings(Long warningsId) {
    	warningsRepository.deleteById(warningsId);
        logger.info("Deleted Warnings with ID: {}", warningsId);
    }

    //count the total Warnings
    public long countWarnings()
	 {
		 return warningsRepository.count();
	 }
    
 // Helper method to convert WarningsDTo to Warnings entity
    private WarningsEntity convertToEntity(WarningsDto warningsDto)
    {
    	return modelMapper.map(warningsDto, WarningsEntity.class);
    }

    // Helper method to convert Warnings entity entity to WarningsDTo
    private WarningsDto convertToDTO(WarningsEntity warningsEntity) {
        return modelMapper.map(warningsEntity, WarningsDto.class);
    } 


}