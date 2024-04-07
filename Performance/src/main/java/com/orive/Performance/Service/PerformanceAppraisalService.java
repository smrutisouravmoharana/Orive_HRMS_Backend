package com.orive.Performance.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.orive.Performance.Dto.PerformanceAppraisalDto;
import com.orive.Performance.Entity.PerformanceAppraisalEntity;
import com.orive.Performance.Exceptions.ResourceNotFoundException;
import com.orive.Performance.Repository.PerformanceAppraisalRepository;

@Service
public class PerformanceAppraisalService {
	
	private static final Logger logger = LoggerFactory.getLogger(PerformanceAppraisalService.class);
	
	@Autowired
	private PerformanceAppraisalRepository performanceAppraisalRepository;
	
	@Autowired
	private ModelMapper  modelMapper;
	
	// Create
	 public PerformanceAppraisalDto createPerformanceAppraisal(PerformanceAppraisalDto performanceAppraisalDto) {
		 PerformanceAppraisalEntity performanceAppraisalEntity = convertToEntity(performanceAppraisalDto);
		 PerformanceAppraisalEntity savedPerformanceAppraisal = performanceAppraisalRepository.save(performanceAppraisalEntity);
	        logger.info("Created PerformanceAppraisal with ID: {}", savedPerformanceAppraisal.getPerformanceAppraisalId());
	        return convertToDTO(savedPerformanceAppraisal);
	    }

    // Read
    public List<PerformanceAppraisalDto> getAllPerformanceAppraisal() {
        List<PerformanceAppraisalEntity> performanceAppraisalEntities = performanceAppraisalRepository.findAll();
        logger.info("Retrieved {} PerformanceAppraisal from the database", performanceAppraisalEntities.size());
        return performanceAppraisalEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by PerformanceAppraisalId
    public Optional<PerformanceAppraisalDto> getPerformanceAppraisalById(String performanceAppraisalId) {
        Optional<PerformanceAppraisalEntity> performanceAppraisal = performanceAppraisalRepository.findById(performanceAppraisalId);
        if (performanceAppraisal.isPresent()) {
            return Optional.of(convertToDTO(performanceAppraisal.get()));
        } else {
            logger.warn("PerformanceAppraisal with ID {} not found", performanceAppraisalId);
            return Optional.empty();
        }
    }
    
    
   //get by EmployeeId
    public List<PerformanceAppraisalEntity> getPerformanceAppraisalByEmployeeId(String username) {
        List<PerformanceAppraisalEntity> performanceAppraisal = performanceAppraisalRepository.findPerformanceAppraisalByUsername(username);
        if (performanceAppraisal.isEmpty()) {
            logger.warn("No PerformanceAppraisal found with EmployeeID: {}", username);
            throw new ResourceNotFoundException("No PerformanceAppraisal found with EmployeeId: " + username);
        }
        return performanceAppraisal;
    }
    
    
 // Update list by id
    public PerformanceAppraisalDto updatePerformanceAppraisal(String performanceAppraisalId, PerformanceAppraisalDto performanceAppraisalDto) {
        Optional<PerformanceAppraisalEntity> existingPerformanceAppraisalOptional = performanceAppraisalRepository.findById(performanceAppraisalId);
        if (existingPerformanceAppraisalOptional.isPresent()) {
        	PerformanceAppraisalEntity existingPerformanceAppraisal = existingPerformanceAppraisalOptional.get();
            modelMapper.map(performanceAppraisalDto, existingPerformanceAppraisal);
            PerformanceAppraisalEntity updatedPerformanceAppraisal = performanceAppraisalRepository.save(existingPerformanceAppraisal);
            logger.info("Updated PerformanceAppraisal with ID: {}", updatedPerformanceAppraisal.getPerformanceAppraisalId());
            return convertToDTO(updatedPerformanceAppraisal);
        } else {
            logger.warn("PerformanceAppraisal with ID {} not found for update", performanceAppraisalId);
            return null;
        }
    }
    
    // Delete
    public void deletePerformanceAppraisal(String performanceAppraisalId) {
    performanceAppraisalRepository.deleteById(performanceAppraisalId);
        logger.info("Deleted PerformanceAppraisal with ID: {}", performanceAppraisalId);
    }

    //count the total PerformanceAppraisal
    public long countPerformanceAppraisal()
	 {
		 return performanceAppraisalRepository.count();
	 }
    
    
	// Helper method to convert PerformanceAppraisalDTo to PerformanceAppraisalEntity
    private PerformanceAppraisalEntity convertToEntity(PerformanceAppraisalDto performanceAppraisalDto)
    {
    	return modelMapper.map(performanceAppraisalDto, PerformanceAppraisalEntity.class);
    }

    // Helper method to convert PerformanceAppraisalEntity entity to PerformanceAppraisalDTo
    private PerformanceAppraisalDto convertToDTO(PerformanceAppraisalEntity performanceAppraisalEntity) 
    {
        return modelMapper.map(performanceAppraisalEntity, PerformanceAppraisalDto.class);
    } 


}
