package com.orive.orive_website.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.orive_website.DTO.LandingPagePopUpDto;
import com.orive.orive_website.Entity.LandingPagePopUpEntity;
import com.orive.orive_website.Repository.LandingPagePopUpRepository;



@Service
public class LandingPagePopUpService {
	
	private static final Logger logger = LoggerFactory.getLogger(LandingPagePopUpService.class);
	  
	  @Autowired
	  private LandingPagePopUpRepository landingPagePopUpRepository ; 
	  
	  @Autowired
	  private ModelMapper modelMapper;
	  
//	//Create
//	  public LandingPagePopUpDto createLandingPagePopUp(LandingPagePopUpDto landingPagePopUpDto) {
//		  LandingPagePopUpEntity landingPagePopUpEntity = convertToEntity(landingPagePopUpDto);
//		  LandingPagePopUpEntity savedLandingPagePopUp = landingPagePopUpRepository.save(landingPagePopUpEntity);
//	      logger.info("Created LandingPagePopUp with ID: {}", savedLandingPagePopUp.getLandingPagePopUpId());
//	      return convertToDTO(savedLandingPagePopUp);
//	  }
	  
	  

	  // Read
	  public List<LandingPagePopUpDto> getAllLandingPagePopUp() {
	      List<LandingPagePopUpEntity> landingPagePopUpEntities = landingPagePopUpRepository.findAll();
	      logger.info("Retrieved {} LandingPagePopUp from the database", landingPagePopUpEntities.size());
	      return landingPagePopUpEntities.stream()
	              .map(this::convertToDTO)
	              .collect(Collectors.toList());
	  }
	  
	  
	  
//	  //get by LandingPagePopUpId
//	  public Optional<LandingPagePopUpDto> getLandingPagePopUpId(Long landingPagePopUpId) {
//	      Optional<LandingPagePopUpEntity> landingPagePopUp = landingPagePopUpRepository.findById(landingPagePopUpId);
//	      if (landingPagePopUp.isPresent()) {
//	          return Optional.of(convertToDTO(landingPagePopUp.get()));
//	      } else {
//	          logger.warn("LandingPagePopUp with ID {} not found", landingPagePopUpId);
//	          return Optional.empty();
//	      }
//	  }
	  
	  
//	// Update list by id
//	  public LandingPagePopUpDto updateLandingPagePopUp(Long landingPagePopUpId, LandingPagePopUpDto landingPagePopUpDto) {
//	      Optional<LandingPagePopUpEntity> existingLandingPagePopUpOptional = landingPagePopUpRepository.findById(landingPagePopUpId);
//	      if (existingLandingPagePopUpOptional.isPresent()) {
//	    	  LandingPagePopUpEntity existingLandingPagePopUp= existingLandingPagePopUpOptional.get();
//	      	  modelMapper.map(landingPagePopUpDto, existingLandingPagePopUpOptional);
//	      	  LandingPagePopUpEntity updatedLandingPagePopUp = landingPagePopUpRepository.save(existingLandingPagePopUp);
//	          logger.info("Updated LandingPagePopUp with ID: {}", updatedLandingPagePopUp.getLandingPagePopUpId());
//	          return convertToDTO(updatedLandingPagePopUp);
//	      } else {
//	          logger.warn("LandingPagePopUp with ID {} not found for update", landingPagePopUpId);
//	          return null;
//	      }
//	  }
	  
	  
	  
	  // Delete
	  public void deleteLandingPagePopUp(Long landingPagePopUpId) {
		  landingPagePopUpRepository.deleteById(landingPagePopUpId);
	      logger.info("Deleted LandingPagePopUp with ID: {}", landingPagePopUpId);
	  }

	  
	  
//	  //count the total LandingPagePopUp
//	  public long countLandingPagePopUp()
//		 {
//			 return landingPagePopUpRepository.count();
//		 }
	  
	  
	  
		// Helper method to convert LandingPagePopUpDto to LandingPagePopUpEntity
	  private LandingPagePopUpEntity convertToEntity(LandingPagePopUpDto landingPagePopUpDto)
	  {
	  	return modelMapper.map(landingPagePopUpDto, LandingPagePopUpEntity.class);
	  }

	 // Helper method to convert LandingPagePopUpEntity  to LandingPagePopUpDto
	  private LandingPagePopUpDto convertToDTO(LandingPagePopUpEntity landingPagePopUpEntity) {
	      return modelMapper.map(landingPagePopUpEntity, LandingPagePopUpDto.class);
	  }

}
