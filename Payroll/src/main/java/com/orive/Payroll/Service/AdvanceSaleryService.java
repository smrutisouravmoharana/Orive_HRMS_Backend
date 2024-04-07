package com.orive.Payroll.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Payroll.Dto.AdvanceSaleryDto;
import com.orive.Payroll.Entity.AdvanceSaleryEntity;
import com.orive.Payroll.Repository.AdvanceSaleryRepository;



@Service
public class AdvanceSaleryService {
	
private static final Logger logger=LoggerFactory.getLogger(AdvanceSaleryService.class);
	
	@Autowired
	private AdvanceSaleryRepository advanceSaleryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	// Create
    public AdvanceSaleryDto createAdvanceSalery(AdvanceSaleryDto advanceSaleryDto) {
    	AdvanceSaleryEntity  advanceSaleryEntity = convertToEntity(advanceSaleryDto);
    	AdvanceSaleryEntity savedAdvanceSalery = advanceSaleryRepository.save(advanceSaleryEntity);
        logger.info("Created AdvanceSalery with ID: {}", savedAdvanceSalery.getAdvanceSaleryId());
        return convertToDTO(savedAdvanceSalery);
    }

    // Read
    public List<AdvanceSaleryDto> getAllAdvanceSalerys() {
        List<AdvanceSaleryEntity>advanceSaleryEntities = advanceSaleryRepository.findAll();
        logger.info("Retrieved {} AdvanceSalery from the database", advanceSaleryEntities.size());
        return advanceSaleryEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by AdvanceSaleryId
    public Optional<AdvanceSaleryDto> getAdvanceSaleryById(String AdvanceSaleryId) {
        Optional<AdvanceSaleryEntity> advanceSalery = advanceSaleryRepository.findById(AdvanceSaleryId);
        if (advanceSalery.isPresent()) {
            return Optional.of(convertToDTO(advanceSalery.get()));
        } else {
            logger.warn("AdvanceSalery with ID {} not found", AdvanceSaleryId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public AdvanceSaleryDto updateAdvanceSalery(String AdvanceSaleryId, AdvanceSaleryDto advanceSaleryDto) {
        Optional<AdvanceSaleryEntity> existingAdvanceSaleryOptional = advanceSaleryRepository.findById(AdvanceSaleryId);
        if (existingAdvanceSaleryOptional.isPresent()) {
        	AdvanceSaleryEntity existingAdvanceSalery= existingAdvanceSaleryOptional.get();
        	existingAdvanceSalery.setAdvanceAmount(advanceSaleryDto.getAdvanceAmount());
            modelMapper.map(advanceSaleryDto, existingAdvanceSaleryOptional);
            AdvanceSaleryEntity updatedAdvanceSalery = advanceSaleryRepository.save(existingAdvanceSalery);
            logger.info("Updated AdvanceSalery with ID: {}", updatedAdvanceSalery.getAdvanceSaleryId());
            return convertToDTO(updatedAdvanceSalery);
        } else {
            logger.warn("AdvanceSalery with ID {} not found for update", AdvanceSaleryId);
            return null;
        }
    }
    
    // Delete
    public void deleteAdvanceSalery(String AdvanceSaleryId) {
    	advanceSaleryRepository.deleteById(AdvanceSaleryId);
        logger.info("Deleted AdvanceSalery with ID: {}", AdvanceSaleryId);
    }

    //count the total AdvanceSalery
    public long countAdvanceSalery()
	 {
		 return advanceSaleryRepository.count();
	 }
    
	// Helper method to convert AdvanceSaleryDTo to AdvanceSaleryEntity
    private AdvanceSaleryEntity convertToEntity(AdvanceSaleryDto advanceSaleryDto)
    {
    	return modelMapper.map(advanceSaleryDto, AdvanceSaleryEntity.class);
    }

 // Helper method to convert AdvanceSaleryEntity  to AdvanceSaleryDTo
    private AdvanceSaleryDto convertToDTO(AdvanceSaleryEntity advanceSaleryEntity) {
        return modelMapper.map(advanceSaleryEntity, AdvanceSaleryDto.class);
    } 


}
