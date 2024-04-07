package com.orive.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.bank.dto.TrainersListDto;
import com.orive.bank.entities.TrainersListEntity;
import com.orive.bank.repository.TrainersListRepository;



@Service
public class TrainersListService {
	
	private static final Logger logger = LoggerFactory.getLogger(TrainersListService.class);
	
	@Autowired
	private TrainersListRepository trainersListRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public TrainersListDto createTrainersList(TrainersListDto trainersListDto) {
    	TrainersListEntity trainersListEntity = convertToEntity(trainersListDto);
    	TrainersListEntity savedTrainersList = trainersListRepository.save(trainersListEntity);
        logger.info("Created TrainersList with ID: {}", savedTrainersList.getTrainersListId());
        return convertToDTO(savedTrainersList);
    }

    // Read
    public List<TrainersListDto> getAllTrainersList() {
        List<TrainersListEntity> trainersListEntities = trainersListRepository.findAll();
        logger.info("Retrieved {} TrainersList from the database", trainersListEntities.size());
        return trainersListEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by TrainersListId
    public Optional<TrainersListDto> getTrainersListById(String trainersListId) {
        Optional<TrainersListEntity> trainersList = trainersListRepository.findById(trainersListId);
        if (trainersList.isPresent()) {
            return Optional.of(convertToDTO(trainersList.get()));
        } else {
            logger.warn("TrainersList with ID {} not found", trainersListId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public TrainersListDto updateTrainersList(String trainersListId, TrainersListDto trainersListDto) {
        Optional<TrainersListEntity> existingTrainersListOptional = trainersListRepository.findById(trainersListId);
        if (existingTrainersListOptional.isPresent()) {
        	TrainersListEntity existingTrainersList = existingTrainersListOptional.get();
        	existingTrainersList.setTrainersFullName(trainersListDto.getTrainersFullName());
        	existingTrainersList.setEmailAddress(trainersListDto.getEmailAddress());
        	existingTrainersList.setPhoneNo(trainersListDto.getPhoneNo());
        	existingTrainersList.setStatus(trainersListDto.getStatus());
        	modelMapper.map(trainersListDto, existingTrainersListOptional);
            TrainersListEntity updatedTrainersList = trainersListRepository.save(existingTrainersList);
            logger.info("Updated TrainersList with ID: {}", updatedTrainersList.getTrainersListId());
            return convertToDTO(updatedTrainersList);
        } else {
            logger.warn("TrainersList with ID {} not found for update", trainersListId);
            return null;
        }
    }
    
    // Delete
    public void deleteTrainersList(String trainersListId) {
    	trainersListRepository.deleteById(trainersListId);
        logger.info("Deleted TrainersList with ID: {}", trainersListId);
    }

    //count the total TrainersList
    public long countTrainersList()
	 {
		 return trainersListRepository.count();
	 }
    
	// Helper method to convert TrainersListDTo to TrainersListEntity
    private TrainersListEntity convertToEntity(TrainersListDto trainersListDto)
    {
    	return modelMapper.map(trainersListDto, TrainersListEntity.class);
    }

    // Helper method to convert TrainersListEntity  to TrainersListDTo
    private TrainersListDto convertToDTO(TrainersListEntity trainersListEntity) {
        return modelMapper.map(trainersListEntity, TrainersListDto.class);
    } 
}
