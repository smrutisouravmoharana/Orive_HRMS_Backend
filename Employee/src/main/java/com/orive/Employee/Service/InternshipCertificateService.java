package com.orive.Employee.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Employee.Dto.InternshipCertificateDto;
import com.orive.Employee.Entity.InternshipCertificateEntity;
import com.orive.Employee.Repository.InternshipCertificateRepository;

@Service
public class InternshipCertificateService {
	
	private static final Logger logger = LoggerFactory.getLogger(InternshipCertificateService.class);
	
	@Autowired
	private InternshipCertificateRepository internshipCertificateRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	// Create
    public InternshipCertificateDto createInternshipCertificate(InternshipCertificateDto internshipCertificateDto) {
    	InternshipCertificateEntity internshipCertificateEntity = convertToEntity(internshipCertificateDto);
    	InternshipCertificateEntity savedInternshipCertificate = internshipCertificateRepository.save(internshipCertificateEntity);
        logger.info("Created InternshipCertificate with ID: {}", savedInternshipCertificate.getInternshipCertificateId());
        return convertToDTO(savedInternshipCertificate);
    }

    
    // Read
    public List<InternshipCertificateDto> getAllInternshipCertificate() {
        List<InternshipCertificateEntity> internshipCertificateEntities = internshipCertificateRepository.findAll();
        logger.info("Retrieved {} InternshipCertificate from the database", internshipCertificateEntities.size());
        return internshipCertificateEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
   
    
    //get by InternshipCertificateId
    public Optional<InternshipCertificateDto> getInternshipCertificateById(Long internshipCertificateId) {
        Optional<InternshipCertificateEntity> internshipCertificate = internshipCertificateRepository.findById(internshipCertificateId);
        if (internshipCertificate.isPresent()) {
            return Optional.of(convertToDTO(internshipCertificate.get()));
        } else {
            logger.warn("InternshipCertificate with ID {} not found", internshipCertificateId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public InternshipCertificateDto updateInternshipCertificate(Long internshipCertificateId, InternshipCertificateDto internshipCertificateDto) {
        Optional<InternshipCertificateEntity> existingInternshipCertificateOptional = internshipCertificateRepository.findById(internshipCertificateId);
        if (existingInternshipCertificateOptional.isPresent()) {
        	InternshipCertificateEntity existingInternshipCertificate = existingInternshipCertificateOptional.get();
        	existingInternshipCertificate.setInternsName(internshipCertificateDto.getInternsName());
            modelMapper.map(internshipCertificateDto, existingInternshipCertificate);
            InternshipCertificateEntity updatedInternshipCertificate = internshipCertificateRepository.save(existingInternshipCertificate);
            logger.info("Updated InternshipCertificate with ID: {}", updatedInternshipCertificate.getInternshipCertificateId());
            return convertToDTO(updatedInternshipCertificate);
        } else {
            logger.warn("InternshipCertificate with ID {} not found for update", internshipCertificateId);
            return null;
        }
    }
    
    // Delete
    public void deleteInternshipCertificate(Long internshipCertificateId) {
    	internshipCertificateRepository.deleteById(internshipCertificateId);
        logger.info("Deleted InternshipCertificate with ID: {}", internshipCertificateId);
    }

    //count the total InternshipCertificate
    public long countInternshipCertificate()
	 {
		 return internshipCertificateRepository.count();
	 }
    
 // Helper method to convert InternshipCertificateDto to InternshipCertificateEntity
    private InternshipCertificateEntity convertToEntity(InternshipCertificateDto internshipCertificateDto)
    {
    	return modelMapper.map(internshipCertificateDto, InternshipCertificateEntity.class);
    }

    // Helper method to convert InternshipCertificateEntity to InternshipCertificateDto
    private InternshipCertificateDto convertToDTO(InternshipCertificateEntity internshipCertificateEntity) {
        return modelMapper.map(internshipCertificateEntity, InternshipCertificateDto.class);
    }
}
