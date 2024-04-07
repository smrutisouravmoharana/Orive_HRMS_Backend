package com.orive.Employee.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Employee.Dto.ExperienceLetterDto;
import com.orive.Employee.Dto.TransfersDto;
import com.orive.Employee.Entity.ExperienceLetterEntity;
import com.orive.Employee.Entity.TransfersEntity;
import com.orive.Employee.Repository.ExperienceLetterRepository;

@Service
public class ExperienceLetterService {
	
	public static final Logger logger = LoggerFactory.getLogger(ExperienceLetterService.class);
	
	@Autowired
	private ExperienceLetterRepository experienceLetterRepository; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public ExperienceLetterDto createExperienceLetter(ExperienceLetterDto experienceLetterDto) {
    	ExperienceLetterEntity experienceLetterEntity = convertToEntity(experienceLetterDto);
    	ExperienceLetterEntity savedExperienceLetter = experienceLetterRepository.save(experienceLetterEntity);
        logger.info("Created ExperienceLetter with ID: {}", savedExperienceLetter.getExperienceLetterId());
        return convertToDTO(savedExperienceLetter);
    }

    // Read
    public List<ExperienceLetterDto> getAllExperienceLetter() {
        List<ExperienceLetterEntity> experienceLetterEntities = experienceLetterRepository.findAll();
        logger.info("Retrieved {} ExperienceLetter from the database", experienceLetterEntities.size());
        return experienceLetterEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by ExperienceLetterId
    public Optional<ExperienceLetterDto> getExperienceLetterById(Long experienceLetterId) {
        Optional<ExperienceLetterEntity> experienceLetter = experienceLetterRepository.findById(experienceLetterId);
        if (experienceLetter.isPresent()) {
            return Optional.of(convertToDTO(experienceLetter.get()));
        } else {
            logger.warn("ExperienceLetter with ID {} not found", experienceLetterId);
            return Optional.empty();
        }
    }
    
    //get letter details by username
    public List<ExperienceLetterDto> getTransfersByUsername(String username) {
        List<ExperienceLetterEntity> transfersEntities = experienceLetterRepository.findByUsername(username);
        return transfersEntities.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
 // Update list by id
    public ExperienceLetterDto updateExperienceLetter(Long experienceLetterId, ExperienceLetterDto experienceLetterDto ) {
        Optional<ExperienceLetterEntity> existingExperienceLetterOptional = experienceLetterRepository.findById(experienceLetterId);
        if (existingExperienceLetterOptional.isPresent()) {
        	ExperienceLetterEntity existingExperienceLetter = existingExperienceLetterOptional.get();
        	existingExperienceLetter.setJoiningDate(experienceLetterDto.getJoiningDate());
            modelMapper.map(experienceLetterDto, existingExperienceLetterOptional);
            ExperienceLetterEntity updatedExperienceLetter = experienceLetterRepository.save(existingExperienceLetter);
            logger.info("Updated ExperienceLetter with ID: {}", updatedExperienceLetter.getExperienceLetterId());
            return convertToDTO(updatedExperienceLetter);
        } else {
            logger.warn("ExperienceLetter with ID {} not found for update", experienceLetterId);
            return null;
        }
    }
    
    // Delete
    public void deleteExperienceLetter(Long experienceLetterId) {
    	experienceLetterRepository.deleteById(experienceLetterId);
        logger.info("Deleted ExperienceLetter with ID: {}", experienceLetterId);
    }

    //count the total ExperienceLetter
    public long countExperienceLetter()
	 {
		 return experienceLetterRepository.count();
	 }
    
	// Helper method to convert ExperienceLetterDTo to ExperienceLetterEntity
    private ExperienceLetterEntity convertToEntity(ExperienceLetterDto experienceLetterDto)
    {
    	return modelMapper.map(experienceLetterDto, ExperienceLetterEntity.class);
    }

    // Helper method to convert ExperienceLetterEntity  to ExperienceLetterDTo
    private ExperienceLetterDto convertToDTO(ExperienceLetterEntity experienceLetterEntity) {
        return modelMapper.map(experienceLetterEntity, ExperienceLetterDto.class);
    } 

}
