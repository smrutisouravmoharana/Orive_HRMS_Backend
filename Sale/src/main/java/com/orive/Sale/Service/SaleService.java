package com.orive.Sale.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Sale.Dto.SaleDto;
import com.orive.Sale.Entity.SaleEntity;
import com.orive.Sale.Repository.SaleRepository;



@Service
public class SaleService {

private static final Logger logger=LoggerFactory.getLogger(SaleService.class);
	
	@Autowired
	private SaleRepository saleRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public SaleDto createSale(SaleDto saleDto) {
    	SaleEntity  workSheetEntity = convertToEntity(saleDto);
    	SaleEntity savedWorkSheet = saleRepository.save(workSheetEntity);
        logger.info("Created Sale with ID: {}", savedWorkSheet.getSaleId());
        return convertToDTO(savedWorkSheet);
    }

    // Read
    public List<SaleDto> getAllSale() {
        List<SaleEntity> workSheetEntities = saleRepository.findAll();
        logger.info("Retrieved {} Sale from the database", workSheetEntities.size());
        return workSheetEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by WorkSheetId
    public Optional<SaleDto> getSaleId(String saleId) {
        Optional<SaleEntity> workSheet = saleRepository.findById(saleId);
        if (workSheet.isPresent()) {
            return Optional.of(convertToDTO(workSheet.get()));
        } else {
            logger.warn("Sale with ID {} not found", saleId);
            return Optional.empty();
        }
    }
    
    
    
 // Update list by id
    public SaleDto updateSale(String saleId, SaleDto saleDto) {
        Optional<SaleEntity> existingWorkSheetOptional = saleRepository.findById(saleId);
        if (existingWorkSheetOptional.isPresent()) {
        	SaleEntity existingWorkSheet= existingWorkSheetOptional.get();
        	existingWorkSheet.setStatus(saleDto.getStatus());
        	modelMapper.map(saleDto, existingWorkSheetOptional);
        	SaleEntity updatedWorkSheet = saleRepository.save(existingWorkSheet);
            logger.info("Updated WorkSheet with ID: {}", updatedWorkSheet.getSaleId());
            return convertToDTO(updatedWorkSheet);
        } else {
            logger.warn("Sale with ID {} not found for update", saleId);
            return null;
        }
    }
    
    // Delete
    public void deleteSale(String saleId) {
    	saleRepository.deleteById(saleId);
        logger.info("Deleted Sale with ID: {}", saleId);
    }

    //count the total WorkSheet
    public long countSale()
	 {
		 return saleRepository.count();
	 }
    
	// Helper method to convert WorkSheetDTo to WorkSheetEntity
    private SaleEntity convertToEntity(SaleDto saleDto)
    {
    	return modelMapper.map(saleDto, SaleEntity.class);
    }

 // Helper method to convert WorkSheetEntity entity to WorkSheetDTo
    private SaleDto convertToDTO(SaleEntity workSheetEntity) {
        return modelMapper.map(workSheetEntity, SaleDto.class);
    }
}
