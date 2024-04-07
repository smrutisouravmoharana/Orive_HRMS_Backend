package com.orive.Employee.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Employee.Dto.CompanyStationaryDto;
import com.orive.Employee.Entity.CompanyStationaryEntity;
import com.orive.Employee.Repository.CompanyStationaryRepository;

@Service
public class CompanyStationaryService {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyStationaryService.class);
	
	@Autowired
	private CompanyStationaryRepository companyStationaryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public CompanyStationaryDto createCompanyStationary(CompanyStationaryDto companyStationaryDto) {
    	CompanyStationaryEntity companyStationaryEntity = convertToEntity(companyStationaryDto);
    	CompanyStationaryEntity savedCompanyStationary = companyStationaryRepository.save(companyStationaryEntity);
        logger.info("Created CompanyStationary with ID: {}", savedCompanyStationary.getCompanyStationaryId());
        return convertToDTO(savedCompanyStationary);
    }

    // Read
    public List<CompanyStationaryDto> getAllCompanyStationary() {
        List<CompanyStationaryEntity> companyStationaryEntities = companyStationaryRepository.findAll();
        logger.info("Retrieved {} CompanyStationary from the database", companyStationaryEntities.size());
        return companyStationaryEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by CompanyStationaryId
    public Optional<CompanyStationaryDto> getCompanyStationaryById(Long companyStationaryId) {
        Optional<CompanyStationaryEntity> companyStationary = companyStationaryRepository.findById(companyStationaryId);
        if (companyStationary.isPresent()) {
            return Optional.of(convertToDTO(companyStationary.get()));
        } else {
            logger.warn("CompanyStationary with ID {} not found", companyStationaryId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public CompanyStationaryDto updateCompanyStationary(Long companyStationaryId, CompanyStationaryDto companyStationaryDto) {
        Optional<CompanyStationaryEntity> existingCompanyStationaryOptional = companyStationaryRepository.findById(companyStationaryId);
        if (existingCompanyStationaryOptional.isPresent()) {
        	CompanyStationaryEntity existingCompanyStationary = existingCompanyStationaryOptional.get();
        	existingCompanyStationary.setEmployeeName(companyStationaryDto.getEmployeeName());
        	existingCompanyStationary.setItemName(companyStationaryDto.getItemName());
        	existingCompanyStationary.setAssignDate(companyStationaryDto.getAssignDate());
        	existingCompanyStationary.setReturnDate(companyStationaryDto.getReturnDate());
            modelMapper.map(companyStationaryDto, existingCompanyStationaryOptional);
            CompanyStationaryEntity updatedCompanyStationary = companyStationaryRepository.save(existingCompanyStationary);
            logger.info("Updated CompanyStationary with ID: {}", updatedCompanyStationary.getCompanyStationaryId());
            return convertToDTO(updatedCompanyStationary);
        } else {
            logger.warn("CompanyStationary with ID {} not found for update", companyStationaryId);
            return null;
        }
    }
    
    // Delete
    public void deleteCompanyStationary(Long companyStationaryId) {
    	companyStationaryRepository.deleteById(companyStationaryId);
        logger.info("Deleted CompanyStationary with ID: {}", companyStationaryId);
    }

    //count the total CompanyStationary
    public long countCompanyStationary()
	 {
		 return companyStationaryRepository.count();
	 }
    
	// Helper method to convert CompanyStationaryDTo to CompanyStationaryEntity
    private CompanyStationaryEntity convertToEntity(CompanyStationaryDto companyStationaryDto)
    {
    	return modelMapper.map(companyStationaryDto, CompanyStationaryEntity.class);
    }

    // Helper method to convert CompanyStationaryEntity to CompanyStationaryDTo
    private CompanyStationaryDto convertToDTO(CompanyStationaryEntity companyStationaryEntity) {
        return modelMapper.map(companyStationaryEntity, CompanyStationaryDto.class);
    } 
}
