package com.orive.orive_website.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.orive_website.DTO.ReachUsDto;
import com.orive.orive_website.Entity.ReachUsEntity;
import com.orive.orive_website.Repository.ReachUsRepository;

@Service
public class ReachUsService {

	private static final Logger logger = LoggerFactory.getLogger(ReachUsService.class);
	  
	  @Autowired
	  private ReachUsRepository reachUsRepository; 
	  
	  @Autowired
	  private ModelMapper modelMapper;
	  
//	//Create
//	  public ReachUsDto createReachUs(ReachUsDto reachUsDto) {
//		  ReachUsEntity reachUsEntity = convertToEntity(reachUsDto);
//		  ReachUsEntity savedReachUs = reachUsRepository.save(reachUsEntity);
//	      logger.info("Created ReachUs with ID: {}", savedReachUs.getReachUsId());
//	      return convertToDTO(savedReachUs);
//	  }
	  
	  

	  // Read
	  public List<ReachUsDto> getAllReachUs() {
	      List<ReachUsEntity> reachUsEntities = reachUsRepository.findAll();
	      logger.info("Retrieved {} ReachUs from the database", reachUsEntities.size());
	      return reachUsEntities.stream()
	              .map(this::convertToDTO)
	              .collect(Collectors.toList());
	  }
	  
	  
	  
//	  //get by ReachUsId
//	  public Optional<ReachUsDto> getReachUsId(Long reachUsId) {
//	      Optional<ReachUsEntity> reachUs = reachUsRepository.findById(reachUsId);
//	      if (reachUs.isPresent()) {
//	          return Optional.of(convertToDTO(reachUs.get()));
//	      } else {
//	          logger.warn("ReachUs with ID {} not found", reachUsId);
//	          return Optional.empty();
//	      }
//	  }
	  
	  
	  
	  
//	// Update list by id
//	  public ReachUsDto updateReachUs(Long reachUsId, ReachUsDto reachUsDto) {
//	      Optional<ReachUsEntity> existingReachUsOptional = reachUsRepository.findById(reachUsId);
//	      if (existingReachUsOptional.isPresent()) {
//	    	  ReachUsEntity existingReachUs= existingReachUsOptional.get();
//	      	  modelMapper.map(reachUsDto, existingReachUsOptional);
//	      	ReachUsEntity updatedReachUs = reachUsRepository.save(existingReachUs);
//	          logger.info("Updated ReachUs with ID: {}", updatedReachUs.getReachUsId());
//	          return convertToDTO(updatedReachUs);
//	      } else {
//	          logger.warn("ReachUs with ID {} not found for update", reachUsId);
//	          return null;
//	      }
//	  }
	  
	  
	  
	  // Delete
	  public void deleteReachUs(Long reachUsId) {
		  reachUsRepository.deleteById(reachUsId);
	      logger.info("Deleted ReachUs with ID: {}", reachUsId);
	  }

	  
	  
//	  //count the total ReachUs
//	  public long countReachUs()
//		 {
//			 return reachUsRepository.count();
//		 }
	  
	  
	  
		// Helper method to convert ReachUsDto to ReachUsEntity
	  private ReachUsEntity convertToEntity(ReachUsDto reachUsDto)
	  {
	  	return modelMapper.map(reachUsDto, ReachUsEntity.class);
	  }

	 // Helper method to convert ReachUsEntity  to ReachUsDto
	  private ReachUsDto convertToDTO(ReachUsEntity reachUsEntity) {
	      return modelMapper.map(reachUsEntity, ReachUsDto.class);
	  }
}
