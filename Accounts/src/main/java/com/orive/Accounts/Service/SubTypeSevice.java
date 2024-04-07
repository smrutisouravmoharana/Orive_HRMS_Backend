package com.orive.Accounts.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Accounts.Dto.SubTypeDto;
import com.orive.Accounts.Entity.SubTypeEntity;
import com.orive.Accounts.Repository.SubTypeRepository;

@Service
public class SubTypeSevice {

	 private static final Logger logger= LoggerFactory.getLogger(SubTypeSevice.class);
		
		@Autowired
		private SubTypeRepository subTypeRepository;
		
		@Autowired
		private ModelMapper modelMapper;
		
		// Create
	    public SubTypeDto createSubTypeList(SubTypeDto subTypeDto) {
	    	SubTypeEntity subTypeEntity = convertToEntity(subTypeDto);
	    	SubTypeEntity savedSubType = subTypeRepository.save(subTypeEntity);
	        logger.info("Created SubType with ID: {}", savedSubType.getSubTypeId());
	        return convertToDTO(savedSubType);
	    }

	    // Read
	    public List<SubTypeDto> getAllSubTypeList() {
	        List<SubTypeEntity> subTypeEntities = subTypeRepository.findAll();
	        logger.info("Retrieved {} SubType from the database", subTypeEntities.size());
	        return subTypeEntities.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }
	    
	    //get by SubTypeId
	    public Optional<SubTypeDto> getSubTypeById(Long subTypeId) {
	        Optional<SubTypeEntity> subType = subTypeRepository.findById(subTypeId);
	        if (subType.isPresent()) {
	            return Optional.of(convertToDTO(subType.get()));
	        } else {
	            logger.warn("SubType with ID {} not found", subTypeId);
	            return Optional.empty();
	        }
	    }
	    
	 // Update list by id
	    public SubTypeDto updateSubTypeList(Long subTypeId, SubTypeDto subTypeDto) {
	        Optional<SubTypeEntity> existingSubTypeOptional = subTypeRepository.findById(subTypeId);
	        if (existingSubTypeOptional.isPresent()) {
	        	SubTypeEntity existingSubType = existingSubTypeOptional.get();
	        	existingSubType.setAccountName(subTypeDto.getAccountName());
	            modelMapper.map(subTypeDto, existingSubTypeOptional);
	            SubTypeEntity updatedSubType = subTypeRepository.save(existingSubType);
	            logger.info("Updated SubType with ID: {}", updatedSubType.getSubTypeId());
	            return convertToDTO(updatedSubType);
	        } else {
	            logger.warn("SubType with ID {} not found for update",subTypeId );
	            return null;
	        }
	    }
	    
	    // Delete
	    public void deleteSubTypeList(Long subTypeId) {
	    	subTypeRepository.deleteById(subTypeId);
	        logger.info("Deleted SubType with ID: {}", subTypeId);
	    }

	    //count the total SubType
	    public long countSubTypeList()
		 {
			 return subTypeRepository.count();
		 }
	    
		// Helper method to convert SubTypeDTo to SubTypeEntity
	    private SubTypeEntity convertToEntity(SubTypeDto subTypeDto )
	    {
	    	return modelMapper.map(subTypeDto, SubTypeEntity.class);
	    }

	    // Helper method to convert SubTypeEntity  to SubTypeDTo
	    private SubTypeDto convertToDTO(SubTypeEntity subTypeEntity) {
	        return modelMapper.map(subTypeEntity, SubTypeDto.class);
	    } 
}
