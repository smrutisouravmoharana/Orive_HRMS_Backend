package com.orive.Employee.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Employee.Dto.InternJoiningLetterDto;
import com.orive.Employee.Dto.TransfersDto;
import com.orive.Employee.Entity.InternJoiningLetterEntity;
import com.orive.Employee.Entity.TransfersEntity;
import com.orive.Employee.Repository.InternJoiningLetterRepository;

@Service
public class InternJoiningLetterService {

	private static final Logger logger = LoggerFactory.getLogger(InternJoiningLetterService.class);
	
	@Autowired
	private InternJoiningLetterRepository internJoiningLetterRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	// Create
    public InternJoiningLetterDto createInternJoiningLetter(InternJoiningLetterDto internJoiningLetterDto) {
    	InternJoiningLetterEntity internJoiningLetterEntity = convertToEntity(internJoiningLetterDto);
    	InternJoiningLetterEntity savedInternJoiningLetter = internJoiningLetterRepository.save(internJoiningLetterEntity);
        logger.info("Created  InternJoiningLetter with ID: {}", savedInternJoiningLetter.getInternJoiningLetterId());
        return convertToDTO(savedInternJoiningLetter);
    }

    // Read
    public List<InternJoiningLetterDto> getAllInternJoiningLetter() {
        List<InternJoiningLetterEntity> internJoiningLetterEntities = internJoiningLetterRepository.findAll();
        logger.info("Retrieved {}  InternJoiningLetter from the database", internJoiningLetterEntities.size());
        return internJoiningLetterEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by  InternJoiningLetterId
    public Optional<InternJoiningLetterDto> getInternJoiningLetterId(Long internJoiningLetterId) {
        Optional<InternJoiningLetterEntity> internJoiningLetter = internJoiningLetterRepository.findById(internJoiningLetterId);
        if (internJoiningLetter.isPresent()) {
            return Optional.of(convertToDTO(internJoiningLetter.get()));
        } else {
            logger.warn(" InternJoiningLetter with ID {} not found", internJoiningLetterId);
            return Optional.empty();
        }
    }
    
    //get details by username
    public List<InternJoiningLetterDto> getInternJoiningLetterByUsername(String username) {
        List<InternJoiningLetterEntity> transfersEntities = internJoiningLetterRepository.findByUsername(username);
        return transfersEntities.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
 // Update list by id
    public InternJoiningLetterDto updateInternJoiningLetter(Long internJoiningLetterId, InternJoiningLetterDto internJoiningLetterDto) {
        Optional<InternJoiningLetterEntity> existingInternJoiningLetterOptional = internJoiningLetterRepository.findById(internJoiningLetterId);
        if (existingInternJoiningLetterOptional.isPresent()) {
        	InternJoiningLetterEntity existingInternJoiningLetter = existingInternJoiningLetterOptional.get();
        	existingInternJoiningLetter.setCandidateName(internJoiningLetterDto.getCandidateName());    	
            modelMapper.map(internJoiningLetterDto, existingInternJoiningLetterOptional);
            InternJoiningLetterEntity updatedInternJoiningLetter = internJoiningLetterRepository.save(existingInternJoiningLetter);
            logger.info("Updated  InternJoiningLetter with ID: {}", updatedInternJoiningLetter.getInternJoiningLetterId());
            return convertToDTO(updatedInternJoiningLetter);
        } else {
            logger.warn(" InternJoiningLetter with ID {} not found for update", internJoiningLetterId);
            return null;
        }
    }
    
    // Delete
    public void deleteInternJoiningLetter(Long internJoiningLetterId) {
    	internJoiningLetterRepository.deleteById(internJoiningLetterId);
        logger.info("Deleted  InternJoiningLetter with ID: {}", internJoiningLetterId);
    }

    //count the total  InternJoiningLetter
    public long countInternJoiningLetter()
	 {
		 return internJoiningLetterRepository.count();
	 }
    
 // Helper method to convert  InternJoiningLetterDTo to  InternJoiningLetterEntity
    private InternJoiningLetterEntity convertToEntity(InternJoiningLetterDto internJoiningLetterDto)
    {
    	return modelMapper.map(internJoiningLetterDto, InternJoiningLetterEntity.class);
    }

    // Helper method to convert  InternJoiningLetterEntity to  InternJoiningLetterDTo
    private InternJoiningLetterDto convertToDTO(InternJoiningLetterEntity internJoiningLetterEntity) 
    {
        return modelMapper.map(internJoiningLetterEntity, InternJoiningLetterDto.class);
    } 
}
