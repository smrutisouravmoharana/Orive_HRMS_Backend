package com.orive.bank.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.bank.dto.WorkSheetDto;
import com.orive.bank.entities.WorkSheetEntity;
import com.orive.bank.repository.WorkSheetRepository;




@Service
public class WorkSheetService {
	
	private static final Logger logger=LoggerFactory.getLogger(WorkSheetService.class);
	
	@Autowired
	private WorkSheetRepository workSheetRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
//	// Create
//    public WorkSheetDto createWorkSheet(WorkSheetDto workSheetDto) {
//    	WorkSheetEntity  workSheetEntity = convertToEntity(workSheetDto);
//    	WorkSheetEntity savedWorkSheet = workSheetRepository.save(workSheetEntity);
//        logger.info("Created WorkSheet with ID: {}", savedWorkSheet.getWorkSheetId());
//        return convertToDTO(savedWorkSheet);
//    }
	
	// Create
		public WorkSheetDto createWorkSheet(WorkSheetDto workSheetDto) {
		    // Check if the workSheetTitle And project already exists
		    Optional<WorkSheetEntity> existingWorkSheetTitleAndProject = workSheetRepository.findByWorkSheetTitleAndProjectName(workSheetDto.getWorkSheetTitle(), workSheetDto.getProjectName());
		    if (existingWorkSheetTitleAndProject.isPresent()) {
		        // workSheetTitle And project name already exists, handle the error as needed
		        throw new RuntimeException("WorkSheetTitle And Project with name '" + workSheetDto.getWorkSheetTitle() + workSheetDto.getProjectName() + "' already exists");
		    }
		    // WorkSheetTitle And Project  name is unique, proceed with saving
		    WorkSheetEntity workSheetEntity = convertToEntity(workSheetDto);
		    WorkSheetEntity savedWorkSheet = workSheetRepository.save(workSheetEntity);
		    logger.info("Created WorkSheet with ID: {}", savedWorkSheet.getWorkSheetId());
		    return convertToDTO(savedWorkSheet);
		}
		
		

    // Read
    public List<WorkSheetDto> getAllWorkSheets() {
        List<WorkSheetEntity> workSheetEntities = workSheetRepository.findAll();
        logger.info("Retrieved {} WorkSheet from the database", workSheetEntities.size());
        return workSheetEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by WorkSheetId
    public Optional<WorkSheetDto> getWorkSheetId(String workSheetId) {
        Optional<WorkSheetEntity> workSheet = workSheetRepository.findById(workSheetId);
        if (workSheet.isPresent()) {
            return Optional.of(convertToDTO(workSheet.get()));
        } else {
            logger.warn("WorkSheet with ID {} not found", workSheetId);
            return Optional.empty();
        }
    }
    
    
    
    //get by WorkSheetByWorkSheetTitleAndProject
    public Optional<WorkSheetDto> getWorkSheetByWorkSheetTitleAndProject(String workSheetTitle, String projectName) {
        Optional<WorkSheetEntity> workSheet = workSheetRepository.findByWorkSheetTitleAndProjectName(workSheetTitle,projectName);
        if (workSheet.isPresent()) {
            return Optional.of(convertToDTO(workSheet.get()));
        } else {
            logger.warn("WorkSheet with Name {} not found", workSheetTitle,projectName);
            return Optional.empty();
        }
    }
    
    
    
  //get by employeeId
    public List<WorkSheetDto> getEmployeeId(String username) {
        List<WorkSheetEntity> employees = workSheetRepository.findByEmployeeId(username);

        if (employees.isEmpty()) {
            logger.warn("WorkSheet with ID {} not found", username);
            return Collections.emptyList();
        }

        return employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    } 
    
    
 // Update list by id
    public WorkSheetDto updateWorkSheet(String workSheetId, WorkSheetDto workSheetDto) {
        Optional<WorkSheetEntity> existingWorkSheetOptional = workSheetRepository.findById(workSheetId);
        if (existingWorkSheetOptional.isPresent()) {
        	WorkSheetEntity existingWorkSheet= existingWorkSheetOptional.get();
        	existingWorkSheet.setStatus(workSheetDto.getStatus());
        	modelMapper.map(workSheetDto, existingWorkSheetOptional);
            WorkSheetEntity updatedWorkSheet = workSheetRepository.save(existingWorkSheet);
            logger.info("Updated WorkSheet with ID: {}", updatedWorkSheet.getWorkSheetId());
            return convertToDTO(updatedWorkSheet);
        } else {
            logger.warn("WorkSheet with ID {} not found for update", workSheetId);
            return null;
        }
    }
    
    // Delete
    public void deleteWorkSheet(String workSheetId) {
    	workSheetRepository.deleteById(workSheetId);
        logger.info("Deleted WorkSheet with ID: {}", workSheetId);
    }

    //count the total WorkSheet
    public long countWorkSheet()
	 {
		 return workSheetRepository.count();
	 }
    
	// Helper method to convert WorkSheetDTo to WorkSheetEntity
    private WorkSheetEntity convertToEntity(WorkSheetDto workSheetDto)
    {
    	return modelMapper.map(workSheetDto, WorkSheetEntity.class);
    }

 // Helper method to convert WorkSheetEntity entity to WorkSheetDTo
    private WorkSheetDto convertToDTO(WorkSheetEntity workSheetEntity) {
        return modelMapper.map(workSheetEntity, WorkSheetDto.class);
    } 	
}
