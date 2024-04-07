package com.orive.Employee.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Employee.Dto.ExperienceJoiningLetterDto;
import com.orive.Employee.Dto.TerminationsDto;
import com.orive.Employee.Dto.TransfersDto;
import com.orive.Employee.Entity.ExperienceJoiningLetterEntity;
import com.orive.Employee.Entity.TerminationsEntity;
import com.orive.Employee.Entity.TransfersEntity;
import com.orive.Employee.Repository.ExperienceJoiningLetterRepository;

@Service
public class ExperienceJoiningLetterService {
	
	private static final Logger logger = LoggerFactory.getLogger(ExperienceJoiningLetterService.class);
	
	@Autowired
	private ExperienceJoiningLetterRepository experienceJoiningLetterRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public ExperienceJoiningLetterDto createExperienceJoiningLetter(ExperienceJoiningLetterDto experienceJoiningLetterDto) {
    	ExperienceJoiningLetterEntity experienceJoiningLetterEntity = convertToEntity(experienceJoiningLetterDto);
    	ExperienceJoiningLetterEntity savedExperienceJoiningLetter = experienceJoiningLetterRepository.save(experienceJoiningLetterEntity);
        logger.info("Created ExperienceJoiningLetter with ID: {}", savedExperienceJoiningLetter.getExperienceJoiningLetterId());
        return convertToDTO(savedExperienceJoiningLetter);
    }

    // Read
    public List<ExperienceJoiningLetterDto> getAllExperienceJoiningLetter() {
        List<ExperienceJoiningLetterEntity> experienceJoiningLetterEntities = experienceJoiningLetterRepository.findAll();
        logger.info("Retrieved {} ExperienceJoiningLetter from the database", experienceJoiningLetterEntities.size());
        return experienceJoiningLetterEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by ExperienceJoiningLetterId
    public Optional<ExperienceJoiningLetterDto> getExperienceJoiningLetterById(Long experienceJoiningLetterId) {
        Optional<ExperienceJoiningLetterEntity> experienceJoiningLetter = experienceJoiningLetterRepository.findById(experienceJoiningLetterId);
        if (experienceJoiningLetter.isPresent()) {
            return Optional.of(convertToDTO(experienceJoiningLetter.get()));
        } else {
            logger.warn("ExperienceJoiningLetter with ID {} not found", experienceJoiningLetterId);
            return Optional.empty();
        }
    }
    
    //get details by username
    public List<ExperienceJoiningLetterDto> getExperienceJoiningLetterByUsername(String username) {
        List<ExperienceJoiningLetterEntity> transfersEntities = experienceJoiningLetterRepository.findByUsername(username);
        return transfersEntities.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
 // Update list by id
    public ExperienceJoiningLetterDto updateExperienceJoiningLetter(Long experienceJoiningLetterId, ExperienceJoiningLetterDto experienceJoiningLetterDto) {
        Optional<ExperienceJoiningLetterEntity> existingExperienceJoiningLetterOptional = experienceJoiningLetterRepository.findById(experienceJoiningLetterId);
        if (existingExperienceJoiningLetterOptional.isPresent()) {
        	ExperienceJoiningLetterEntity experienceJoiningLetter = existingExperienceJoiningLetterOptional.get();
        	experienceJoiningLetter.setDepartment(experienceJoiningLetterDto.getDepartment());
            modelMapper.map(experienceJoiningLetterDto, experienceJoiningLetter);
            ExperienceJoiningLetterEntity updatedExperienceJoiningLetter = experienceJoiningLetterRepository.save(experienceJoiningLetter);
            logger.info("Updated ExperienceJoiningLetter with ID: {}", updatedExperienceJoiningLetter.getExperienceJoiningLetterId());
            return convertToDTO(updatedExperienceJoiningLetter);
        } else {
            logger.warn("ExperienceJoiningLetter with ID {} not found for update", experienceJoiningLetterId);
            return null;
        }
    }
    
    // Delete
    public void deleteExperienceJoiningLetter(Long experienceJoiningLetterId) {
    	experienceJoiningLetterRepository.deleteById(experienceJoiningLetterId);
        logger.info("Deleted ExperienceJoiningLetter with ID: {}", experienceJoiningLetterId);
    }

    //count the total ExperienceJoiningLetter
    public long countExperienceJoiningLetter()
	 {
		 return experienceJoiningLetterRepository.count();
	 }
    
	// Helper method to convert ExperienceJoiningLetterDTo to ExperienceJoiningLetterEntity
    private ExperienceJoiningLetterEntity convertToEntity(ExperienceJoiningLetterDto experienceJoiningLetterDto)
    {
    	return modelMapper.map(experienceJoiningLetterDto, ExperienceJoiningLetterEntity.class);
    }

    // Helper method to convert ExperienceJoiningLetterEntity entity to ExperienceJoiningLetterDTo
    private ExperienceJoiningLetterDto convertToDTO(ExperienceJoiningLetterEntity experienceJoiningLetterEntity) 
    {
        return modelMapper.map(experienceJoiningLetterEntity, ExperienceJoiningLetterDto.class);
    } 
}
