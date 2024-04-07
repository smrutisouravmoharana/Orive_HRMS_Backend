package com.orive.Accounts.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Accounts.Dto.FinancialYearDto;
import com.orive.Accounts.Entity.FinancialYearEntity;
import com.orive.Accounts.Repository.FinancialYearRepository;



@Service
public class FinancialYearService {

    private static final Logger logger= LoggerFactory.getLogger(FinancialYearService.class);
	
	@Autowired
	private FinancialYearRepository financialYearRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public FinancialYearDto createFinancialYearList(FinancialYearDto financialYearDto) {
    	FinancialYearEntity financialYearEntity = convertToEntity(financialYearDto);
    	FinancialYearEntity savedFinancialYear = financialYearRepository.save(financialYearEntity);
        logger.info("Created FinancialYear with ID: {}", savedFinancialYear.getFinancialYearId());
        return convertToDTO(savedFinancialYear);
    }

    // Read
    public List<FinancialYearDto> getAllFinancialYearList() {
        List<FinancialYearEntity> financialYearEntities = financialYearRepository.findAll();
        logger.info("Retrieved {} FinancialYear from the database", financialYearEntities.size());
        return financialYearEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by FinancialYearId
    public Optional<FinancialYearDto> getFinancialYearById(Long financialYearId) {
        Optional<FinancialYearEntity> financialYear = financialYearRepository.findById(financialYearId);
        if (financialYear.isPresent()) {
            return Optional.of(convertToDTO(financialYear.get()));
        } else {
            logger.warn("FinancialYear with ID {} not found", financialYearId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public FinancialYearDto updateFinancialYearList(Long financialYearId, FinancialYearDto financialYearDto) {
        Optional<FinancialYearEntity> existingFinancialYearOptional = financialYearRepository.findById(financialYearId);
        if (existingFinancialYearOptional.isPresent()) {
        	FinancialYearEntity existingFinancialYear = existingFinancialYearOptional.get();
        	existingFinancialYear.setFinancialYear(financialYearDto.getFinancialYear());
        	existingFinancialYear.setFinancialYearStartDate(financialYearDto.getFinancialYearStartDate());
        	existingFinancialYear.setFinancialYearEndDate(financialYearDto.getFinancialYearEndDate());
            modelMapper.map(financialYearDto, existingFinancialYearOptional);
            FinancialYearEntity updatedFinancialYear = financialYearRepository.save(existingFinancialYear);
            logger.info("Updated FinancialYear with ID: {}", updatedFinancialYear.getFinancialYearId());
            return convertToDTO(updatedFinancialYear);
        } else {
            logger.warn("FinancialYear with ID {} not found for update",financialYearId );
            return null;
        }
    }
    
    // Delete
    public void deleteFinancialYearList(Long financialYearId) {
    	financialYearRepository.deleteById(financialYearId);
        logger.info("Deleted FinancialYear with ID: {}", financialYearId);
    }

    //count the total FinancialYear
    public long countFinancialYearList()
	 {
		 return financialYearRepository.count();
	 }
    
	// Helper method to convert FinancialYearDTo to FinancialYearEntity
    private FinancialYearEntity convertToEntity(FinancialYearDto financialYearDto )
    {
    	return modelMapper.map(financialYearDto, FinancialYearEntity.class);
    }

    // Helper method to convert FinancialYearEntity  to FinancialYearDTo
    private FinancialYearDto convertToDTO(FinancialYearEntity financialYearEntity) {
        return modelMapper.map(financialYearEntity, FinancialYearDto.class);
    } 
}
