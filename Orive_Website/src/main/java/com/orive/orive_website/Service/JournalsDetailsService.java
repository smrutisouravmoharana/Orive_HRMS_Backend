package com.orive.orive_website.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.orive_website.DTO.JournalsDetailsDto;
import com.orive.orive_website.Entity.JournalsDetailsEntity;
import com.orive.orive_website.Repository.JournalsDetailsRepository;



@Service
public class JournalsDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(JournalsDetailsService.class);
	  
	  @Autowired
	  private JournalsDetailsRepository journalsDetailsRepository; 
	  
	  @Autowired
	  private ModelMapper modelMapper;
	  
//	//Create
//	  public JournalsDetailsDto createJournalsDetails(JournalsDetailsDto journalsDetailsDto) {
//		  JournalsDetailsEntity journalsDetailsEntity = convertToEntity(journalsDetailsDto);
//		  JournalsDetailsEntity savedJournalsDetails = journalsDetailsRepository.save(journalsDetailsEntity);
//	      logger.info("Created JournalsDetails with ID: {}", savedJournalsDetails.getJournalsDetailsId());
//	      return convertToDTO(savedJournalsDetails);
//	  }
	  
	  

	  // Read
	  public List<JournalsDetailsDto> getAllJournalsDetails() {
	      List<JournalsDetailsEntity> journalsDetailsEntities = journalsDetailsRepository.findAll();
	      logger.info("Retrieved {} JournalsDetails from the database", journalsDetailsEntities.size());
	      return journalsDetailsEntities.stream()
	              .map(this::convertToDTO)
	              .collect(Collectors.toList());
	  }
	  
	  
//	  //get by JournalsDetailsId
//	  public Optional<JournalsDetailsDto> getJournalsDetailsId(Long journalsDetailsId) {
//	      Optional<JournalsDetailsEntity> journalsDetails = journalsDetailsRepository.findById(journalsDetailsId);
//	      if (journalsDetails.isPresent()) {
//	          return Optional.of(convertToDTO(journalsDetails.get()));
//	      } else {
//	          logger.warn("JournalsDetails with ID {} not found", journalsDetailsId);
//	          return Optional.empty();
//	      }
//	  }
	  
	  
	  
//	// Update list by id
//	  public JournalsDetailsDto updateJournalsDetails(Long journalsDetailsId, JournalsDetailsDto journalsDetailsDto) {
//	      Optional<JournalsDetailsEntity> existingJournalsDetailsOptional = journalsDetailsRepository.findById(journalsDetailsId);
//	      if (existingJournalsDetailsOptional.isPresent()) {
//	    	  JournalsDetailsEntity existingJournalsDetails= existingJournalsDetailsOptional.get();
//	      	  modelMapper.map(journalsDetailsDto, existingJournalsDetailsOptional);
//	      	JournalsDetailsEntity updatedJournalsDetails = journalsDetailsRepository.save(existingJournalsDetails);
//	          logger.info("Updated JournalsDetails with ID: {}", updatedJournalsDetails.getJournalsDetailsId());
//	          return convertToDTO(updatedJournalsDetails);
//	      } else {
//	          logger.warn("JournalsDetails with ID {} not found for update", journalsDetailsId);
//	          return null;
//	      }
//	  }
	  
	  
	  
	  // Delete
	  public void deleteJournalsDetails(Long journalsDetailsId) {
		  journalsDetailsRepository.deleteById(journalsDetailsId);
	      logger.info("Deleted JournalsDetails with ID: {}", journalsDetailsId);
	  }
	  
	  

//	  //count the total JournalsDetails
//	  public long countJournalsDetails()
//		 {
//			 return journalsDetailsRepository.count();
//		 }
	  
	  
	  
		// Helper method to convert JournalsDetailsDto to JournalsDetailsEntity
	  private JournalsDetailsEntity convertToEntity(JournalsDetailsDto journalsDetailsDto)
	  {
	  	return modelMapper.map(journalsDetailsDto, JournalsDetailsEntity.class);
	  }

	 // Helper method to convert JournalsDetailsEntity  to JournalsDetailsDto
	  private JournalsDetailsDto convertToDTO(JournalsDetailsEntity journalsDetailsEntity) {
	      return modelMapper.map(journalsDetailsEntity, JournalsDetailsDto.class);
	  }
}
