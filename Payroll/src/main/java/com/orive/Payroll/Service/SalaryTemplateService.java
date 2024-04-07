package com.orive.Payroll.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Payroll.Dto.SalaryTemplateDto;
import com.orive.Payroll.Entity.SalaryTemplateEntity;
import com.orive.Payroll.Repository.SalaryTemplateRepository;





@Service
public class SalaryTemplateService {

private static final Logger logger=LoggerFactory.getLogger(SalaryTemplateService.class);
	
	@Autowired
	private SalaryTemplateRepository salaryTemplateRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	// Create
    public SalaryTemplateDto createSalaryTemplate(SalaryTemplateDto salaryTemplateDto) {
    	SalaryTemplateEntity  salaryTemplateEntity = convertToEntity(salaryTemplateDto);
    	SalaryTemplateEntity savedSalaryTemplate = salaryTemplateRepository.save(salaryTemplateEntity);
        logger.info("Created SalaryTemplate with ID: {}", savedSalaryTemplate.getSalaryTemplateId());
        return convertToDTO(savedSalaryTemplate);
    }

    // Read
    public List<SalaryTemplateDto> getAllSalaryTemplate() {
        List<SalaryTemplateEntity> salaryTemplateEntities = salaryTemplateRepository.findAll();
        logger.info("Retrieved {} SalaryTemplate from the database", salaryTemplateEntities.size());
        return salaryTemplateEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by SalaryTemplateId
    public Optional<SalaryTemplateDto> getSalaryTemplateById(String salaryTemplateId) {
        Optional<SalaryTemplateEntity> salaryTemplate = salaryTemplateRepository.findById(salaryTemplateId);
        if (salaryTemplate.isPresent()) {
            return Optional.of(convertToDTO(salaryTemplate.get()));
        } else {
            logger.warn("SalaryTemplate with ID {} not found", salaryTemplateId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public SalaryTemplateDto updateSalaryTemplate(String salaryTemplateId, SalaryTemplateDto salaryTemplateDto) {
        Optional<SalaryTemplateEntity> existingSalaryTemplateOptional = salaryTemplateRepository.findById(salaryTemplateId);
        if (existingSalaryTemplateOptional.isPresent()) {
        	SalaryTemplateEntity existingSalaryTemplate= existingSalaryTemplateOptional.get();
        	existingSalaryTemplate.setBasicSalary(salaryTemplateDto.getBasicSalary());
            modelMapper.map(salaryTemplateDto, existingSalaryTemplateOptional);
            SalaryTemplateEntity updatedSalaryTemplate = salaryTemplateRepository.save(existingSalaryTemplate);
            logger.info("Updated SalaryTemplate with ID: {}", updatedSalaryTemplate.getSalaryTemplateId());
            return convertToDTO(updatedSalaryTemplate);
        } else {
            logger.warn("SalaryTemplate with ID {} not found for update", salaryTemplateId);
            return null;
        }
    }
    
    // Delete
    public void deleteSalaryTemplate(String salaryTemplateId) {
    	salaryTemplateRepository.deleteById(salaryTemplateId);
        logger.info("Deleted SalaryTemplate with ID: {}", salaryTemplateId);
    }

    //count the total SalaryTemplate
    public long countSalaryTemplate()
	 {
		 return salaryTemplateRepository.count();
	 }
    
	// Helper method to convert SalaryTemplateDTo to SalaryTemplateEntity
    private SalaryTemplateEntity convertToEntity(SalaryTemplateDto salaryTemplateDto)
    {
    	return modelMapper.map(salaryTemplateDto, SalaryTemplateEntity.class);
    }

    // Helper method to convert SalaryTemplateEntity  to SalaryTemplateDTo
    private SalaryTemplateDto convertToDTO(SalaryTemplateEntity salaryTemplateEntity) {
        return modelMapper.map(salaryTemplateEntity, SalaryTemplateDto.class);
    } 
}
