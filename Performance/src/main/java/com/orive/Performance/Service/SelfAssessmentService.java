package com.orive.Performance.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Performance.Dto.SelfAssessmentDto;
import com.orive.Performance.Entity.SelfAssessmentEntity;
import com.orive.Performance.Repository.SelfAssessmentRepository;


@Service
public class SelfAssessmentService {
	
	
private static final Logger logger=LoggerFactory.getLogger(SelfAssessmentService.class);
	
	@Autowired
	private SelfAssessmentRepository selfAssessmentRepository ;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	// Create
    public SelfAssessmentDto createSelfAssessment(SelfAssessmentDto selfAssessmentDto) {
    	SelfAssessmentEntity selfAssessmentEntity = convertToEntity(selfAssessmentDto);
    	SelfAssessmentEntity savedSelfAssessment = selfAssessmentRepository.save(selfAssessmentEntity);
        logger.info("Created SelfAssessment with ID: {}", savedSelfAssessment.getSelfAssessmentId());
        return convertToDTO(savedSelfAssessment);
    }

    
    // Read
    public List<SelfAssessmentDto> getAllSelfAssessment() {
        List<SelfAssessmentEntity> selfAssessmentEntities = selfAssessmentRepository.findAll();
        logger.info("Retrieved {} SelfAssessment from the database", selfAssessmentEntities.size());
        return selfAssessmentEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    
    //get by selfAssessmentId
    public Optional<SelfAssessmentDto> getSelfAssessmentById(String selfAssessmentId) {
        Optional<SelfAssessmentEntity> selfAssessment = selfAssessmentRepository.findById(selfAssessmentId);
        if (selfAssessment.isPresent()) {
            return Optional.of(convertToDTO(selfAssessment.get()));
        } else {
            logger.warn("SelfAssessment with ID {} not found", selfAssessmentId);
            return Optional.empty();
        }
    }
    
    
 // Update list by selfAssessmentId
    public SelfAssessmentDto updateSelfAssessment(String selfAssessmentId, SelfAssessmentDto selfAssessmentDto) {
        Optional<SelfAssessmentEntity> existingSelfAssessmentOptional = selfAssessmentRepository.findById(selfAssessmentId);
        if (existingSelfAssessmentOptional.isPresent()) {
        	SelfAssessmentEntity existingSelfAssessment = existingSelfAssessmentOptional.get();
            modelMapper.map(selfAssessmentDto, existingSelfAssessmentOptional);
            SelfAssessmentEntity updatedSelfAssessment = selfAssessmentRepository.save(existingSelfAssessment);
            logger.info("Updated SelfAssessment with ID: {}", updatedSelfAssessment.getSelfAssessmentId());
            return convertToDTO(updatedSelfAssessment);
        } else {
            logger.warn("SelfAssessment with ID {} not found for update", selfAssessmentId);
            return null;
        }
    }
    
    
    // Delete
    public void deleteSelfAssessment(String selfAssessmentId) {
    	selfAssessmentRepository.deleteById(selfAssessmentId);
        logger.info("Deleted SelfAssessment with ID: {}", selfAssessmentId);
    }
    

    //count the total SelfAssessment
    public long CountSelfAssessment()
	 {
		 return selfAssessmentRepository.count();
	 }
    
    
   // Helper method to convert SelfAssessmentDTo to SelfAssessmentEntity
    private SelfAssessmentEntity convertToEntity(SelfAssessmentDto selfAssessmentDto)
    {
    	return modelMapper.map(selfAssessmentDto, SelfAssessmentEntity.class);
    }
    

    // Helper method to convert SelfAssessmentEntity  to SelfAssessmentDTo
    private SelfAssessmentDto convertToDTO(SelfAssessmentEntity selfAssessmentEntity) 
    {
        return modelMapper.map(selfAssessmentEntity, SelfAssessmentDto.class);
    } 

}
