package com.orive.Employee.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Employee.Dto.AwardsDto;
import com.orive.Employee.Entity.AwardsEntity;
import com.orive.Employee.Repository.AwardsRepository;




@Service
public class AwardsService {

private static final Logger logger=LoggerFactory.getLogger(AwardsService.class);
	
	@Autowired
	private AwardsRepository awardsRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	// Create
    public AwardsDto createAwards(AwardsDto awardsDto) {
    	AwardsEntity awardsEntity = convertToEntity(awardsDto);
    	AwardsEntity savedAwards = awardsRepository.save(awardsEntity);
        logger.info("Created Award with ID: {}", savedAwards.getAwardId());
        return convertToDTO(savedAwards);
    }

    // Read
    public List<AwardsDto> getAllAwards() {
        List<AwardsEntity> awardsEntities = awardsRepository.findAll();
        logger.info("Retrieved {} Awards from the database", awardsEntities.size());
        return awardsEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by AwardId
    public Optional<AwardsDto> getAwardsById(Long awardId) {
        Optional<AwardsEntity> award = awardsRepository.findById(awardId);
        if (award.isPresent()) {
            return Optional.of(convertToDTO(award.get()));
        } else {
            logger.warn("Awards with ID {} not found", awardId);
            return Optional.empty();
        }
    }
    
    
    //get by Awards By  employeeId
    public List<AwardsDto> getAwardsByEmployeeId(String username) {
        List<AwardsEntity> award = awardsRepository.findAwardsByEmployeeId(username);

        if (award.isEmpty()) {
            logger.warn("Awards with employeeId {} not found", username);
            return Collections.emptyList();
        }
        return award.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    } 
    
    
 // Update list by id
    public AwardsDto updateAwards(Long awardId, AwardsDto awardsDto) {
        Optional<AwardsEntity> existingAwardOptional = awardsRepository.findById(awardId);
        if (existingAwardOptional.isPresent()) {
        	AwardsEntity existingAward = existingAwardOptional.get();
        	existingAward.setEmployeeName(awardsDto.getEmployeeName());
        	existingAward.setAwardBy(awardsDto.getAwardBy());
            modelMapper.map(awardsDto, existingAwardOptional);
            AwardsEntity updatedAward = awardsRepository.save(existingAward);
            logger.info("Updated Award with ID: {}", updatedAward.getAwardId());
            return convertToDTO(updatedAward);
        } else {
            logger.warn("Award with ID {} not found for update", awardId);
            return null;
        }
    }
    
    // Delete
    public void deleteAwards(Long awardId) {
    	awardsRepository.deleteById(awardId);
        logger.info("Deleted Award with ID: {}", awardId);
    }

    //count the total Award
    public long countAwards()
	 {
		 return awardsRepository.count();
	 }
    
	// Helper method to convert AwardDTo to AwardEntity
    private AwardsEntity convertToEntity(AwardsDto awardsDto)
    {
    	return modelMapper.map(awardsDto, AwardsEntity.class);
    }

    // Helper method to convert AwardEntity to AwardDTo
    private AwardsDto convertToDTO(AwardsEntity awardsEntity) {
        return modelMapper.map(awardsEntity, AwardsDto.class);
    } 
}
