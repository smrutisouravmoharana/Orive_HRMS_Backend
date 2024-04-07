package com.orive.orive_website.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.orive_website.DTO.CareersGetJobAlertsDto;
import com.orive.orive_website.Entity.CareersGetJobAlertsEntity;
import com.orive.orive_website.Repository.CareersGetJobAlertsRepository;




@Service
public class CareersGetJobAlertsService {
	
  private static final Logger logger = LoggerFactory.getLogger(CareersGetJobAlertsService.class);
  
  @Autowired
  private CareersGetJobAlertsRepository careersGetJobAlertsRepository; 
  
  @Autowired
  private ModelMapper modelMapper;
  
////Create
//  public CareersGetJobAlertsDto createCareersGetJobAlerts(CareersGetJobAlertsDto careersGetJobAlertsDto) {
//	  CareersGetJobAlertsEntity careersGetJobAlertsEntity = convertToEntity(careersGetJobAlertsDto);
//	  CareersGetJobAlertsEntity savedCareersGetJobAlerts = careersGetJobAlertsRepository.save(careersGetJobAlertsEntity);
//      logger.info("Created CareersGetJobAlerts with ID: {}", savedCareersGetJobAlerts.getCareersGetJobAlertsId());
//      return convertToDTO(savedCareersGetJobAlerts);
//  }

  // Read
  public List<CareersGetJobAlertsDto> getAllCareersGetJobAlerts() {
      List<CareersGetJobAlertsEntity> careersGetJobAlertsEntities = careersGetJobAlertsRepository.findAll();
      logger.info("Retrieved {} CareersGetJobAlerts from the database", careersGetJobAlertsEntities.size());
      return careersGetJobAlertsEntities.stream()
              .map(this::convertToDTO)
              .collect(Collectors.toList());
  }
  
  
//  //get by CareersGetJobAlertsId
//  public Optional<CareersGetJobAlertsDto> getCareersGetJobAlertsId(Long careersGetJobAlertsId) {
//      Optional<CareersGetJobAlertsEntity> CareersGetJobAlerts = careersGetJobAlertsRepository.findById(careersGetJobAlertsId);
//      if (CareersGetJobAlerts.isPresent()) {
//          return Optional.of(convertToDTO(CareersGetJobAlerts.get()));
//      } else {
//          logger.warn("CareersGetJobAlerts with ID {} not found", careersGetJobAlertsId);
//          return Optional.empty();
//      }
//  }
//  
  
  
//// Update list by id
//  public CareersGetJobAlertsDto updateCareersGetJobAlerts(Long careersGetJobAlertsId, CareersGetJobAlertsDto careersGetJobAlertsDto) {
//      Optional<CareersGetJobAlertsEntity> existingCareersGetJobAlertsOptional = careersGetJobAlertsRepository.findById(careersGetJobAlertsId);
//      if (existingCareersGetJobAlertsOptional.isPresent()) {
//    	  CareersGetJobAlertsEntity existingCareersGetJobAlerts= existingCareersGetJobAlertsOptional.get();
//      	  modelMapper.map(careersGetJobAlertsDto, existingCareersGetJobAlertsOptional);
//      	  CareersGetJobAlertsEntity updatedCareersGetJobAlerts = careersGetJobAlertsRepository.save(existingCareersGetJobAlerts);
//          logger.info("Updated CareersGetJobAlerts with ID: {}", updatedCareersGetJobAlerts.getCareersGetJobAlertsId());
//          return convertToDTO(updatedCareersGetJobAlerts);
//      } else {
//          logger.warn("CareersGetJobAlerts with ID {} not found for update", careersGetJobAlertsId);
//          return null;
//      }
//  }
  
  
  // Delete
  public void deleteCareersGetJobAlerts(Long careersGetJobAlertsId) {
	  careersGetJobAlertsRepository.deleteById(careersGetJobAlertsId);
      logger.info("Deleted CareersGetJobAlerts with ID: {}", careersGetJobAlertsId);
  }

  
//  //count the total CareersGetJobAlerts
//  public long countCareersGetJobAlerts()
//	 {
//		 return careersGetJobAlertsRepository.count();
//	 }
  
  
	// Helper method to convert CareersGetJobAlertsDto to CareersGetJobAlertsEntity
  private CareersGetJobAlertsEntity convertToEntity(CareersGetJobAlertsDto careersGetJobAlertsDto)
  {
  	return modelMapper.map(careersGetJobAlertsDto, CareersGetJobAlertsEntity.class);
  }

 // Helper method to convert CareersGetJobAlertsEntity  to CareersGetJobAlertsDto
  private CareersGetJobAlertsDto convertToDTO(CareersGetJobAlertsEntity careersGetJobAlertsEntity) {
      return modelMapper.map(careersGetJobAlertsEntity, CareersGetJobAlertsDto.class);
  }

}
