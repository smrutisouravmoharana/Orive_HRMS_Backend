package com.orive.bank.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orive.bank.dto.TrainersListDto;
import com.orive.bank.service.TrainersListService;


//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "trainerslist")
@CrossOrigin(origins = "*")
public class TrainersListController {
	
	private static final Logger logger= LoggerFactory.getLogger(TrainersListController.class);
	
	@Autowired
	private TrainersListService trainersListService;
	
	// Create a new TrainersList
    @PostMapping("/create/trainerslist")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<TrainersListDto> createTrainersList(@RequestBody TrainersListDto trainersListDto) {
    	TrainersListDto createdTrainersList = trainersListService.createTrainersList(trainersListDto);
        logger.info("Created TrainersList with name: {}", createdTrainersList.getTrainersFullName());
        return new ResponseEntity<>(createdTrainersList, HttpStatus.CREATED);
    }

    // Get all TrainersList   
    @GetMapping("/get/trainerslist")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<TrainersListDto>> getAllTrainersList() {
        List<TrainersListDto> trainersList = trainersListService.getAllTrainersList();
        logger.info("Retrieved {} TrainersList from the database", trainersList.size());
        return new ResponseEntity<>(trainersList, HttpStatus.OK);
    }

    // Get TrainersList by ID
    @GetMapping("/get/{trainersListId}")
    // @PreAuthorize("hasRole('client_HR')|| hasRole('client_Employee')")
    public ResponseEntity<TrainersListDto> getTrainersListById(@PathVariable String trainersListId) {
        Optional<TrainersListDto> trainersList = trainersListService.getTrainersListById(trainersListId);
        if (trainersList.isPresent()) {
            logger.info("Retrieved TrainersList with ID: {}", trainersListId);
            return new ResponseEntity<>(trainersList.get(), HttpStatus.OK);
        } else {
            logger.warn("TrainersList with ID {} not found", trainersListId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update TrainersList by ID
    @PutMapping("/update/{trainersListId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<TrainersListDto> updateTrainersList(@PathVariable String trainersListId, @RequestBody TrainersListDto updatedTrainersListDto) {
    	TrainersListDto updatedTrainersList = trainersListService.updateTrainersList(trainersListId, updatedTrainersListDto);
        if (updatedTrainersList != null) {
            logger.info("Updated TrainersList with ID: {}", trainersListId);
            return new ResponseEntity<>(updatedTrainersList, HttpStatus.OK);
        } else {
            logger.warn("TrainersList with ID {} not found for update", trainersListId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    


    // Delete TrainersList by ID
    @DeleteMapping("/delete/{trainersListId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteTrainersList(@PathVariable String trainersListId) {
  	  trainersListService.deleteTrainersList(trainersListId);
        logger.info("Deleted TrainersList with ID: {}", trainersListId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
	    @GetMapping("/count/trainerslist")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countTrainersList()
	    {
	    	return trainersListService.countTrainersList();
	    }
}
