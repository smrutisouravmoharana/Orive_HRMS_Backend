package com.orive.Employee.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Employee.Dto.ResignationsDto;
import com.orive.Employee.Dto.TransfersDto;
import com.orive.Employee.Dto.TravelsDto;
import com.orive.Employee.Entity.ResignationsEntity;
import com.orive.Employee.Entity.TransfersEntity;
import com.orive.Employee.Entity.TravelsEntity;
import com.orive.Employee.Repository.TravelsRepository;



@Service
public class TravelsService {
	
	private static final Logger logger=LoggerFactory.getLogger(TravelsService.class);
	
	@Autowired
	private TravelsRepository travelsRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public TravelsDto createTravels(TravelsDto travelsDto) {
    	TravelsEntity travelsEntity = convertToEntity(travelsDto);
    	TravelsEntity savedTravels = travelsRepository.save(travelsEntity);
        logger.info("Created Travels with ID: {}", savedTravels.getTravelId());
        return convertToDTO(savedTravels);
    }

    // Read
    public List<TravelsDto> getAllTravels() {
        List<TravelsEntity> travelsEntities = travelsRepository.findAll();
        logger.info("Retrieved {} Travels from the database", travelsEntities.size());
        return travelsEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by travelsId
    public Optional<TravelsDto> getTravelsById(Long travelsId) {
        Optional<TravelsEntity> travels = travelsRepository.findById(travelsId);
        if (travels.isPresent()) {
            return Optional.of(convertToDTO(travels.get()));
        } else {
            logger.warn("Travels with ID {} not found", travelsId);
            return Optional.empty();
        }
    }
    
    public List<TravelsDto> getTravelsByUsername(String username) {
        List<TravelsEntity> transfersEntities = travelsRepository.findByUsername(username);
        return transfersEntities.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
 // Update list by id
    public TravelsDto updateTravels(Long travelsId, TravelsDto travelsDto) {
        Optional<TravelsEntity> existingTravelsOptional = travelsRepository.findById(travelsId);
        if (existingTravelsOptional.isPresent()) {
        	TravelsEntity existingTravels = existingTravelsOptional.get();
        	existingTravels.setEmployeeName(travelsDto.getEmployeeName());
        	existingTravels.setPlaceOfVisit(travelsDto.getPlaceOfVisit());
        	existingTravels.setStartDate(travelsDto.getStartDate());
        	existingTravels.setEndDate(travelsDto.getEndDate());
            modelMapper.map(travelsDto, existingTravelsOptional);
            TravelsEntity updatedTravel = travelsRepository.save(existingTravels);
            logger.info("Updated Travels with ID: {}", updatedTravel.getTravelId());
            return convertToDTO(updatedTravel);
        } else {
            logger.warn("Travels with ID {} not found for update", travelsId);
            return null;
        }
    }
    
    // Delete
    public void deleteTravels(Long travelsId) {
    	travelsRepository.deleteById(travelsId);
        logger.info("Deleted Travels with ID: {}", travelsId);
    }

    //count the total travels
    public long countTravels()
	 {
		 return travelsRepository.count();
	 }
    
 // Helper method to convert TravelsDTo to Travels entity
    private TravelsEntity convertToEntity(TravelsDto travelsDto)
    {
    	return modelMapper.map(travelsDto, TravelsEntity.class);
    }

    // Helper method to convert Travels entity entity to TravelsDTo
    private TravelsDto convertToDTO(TravelsEntity travelsEntity) {
        return modelMapper.map(travelsEntity, TravelsDto.class);
    } 
}
