package com.orive.Employee.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Employee.Dto.AwardsDto;
import com.orive.Employee.Dto.ResignationsDto;
import com.orive.Employee.Dto.TransfersDto;
import com.orive.Employee.Entity.AwardsEntity;
import com.orive.Employee.Entity.ResignationsEntity;
import com.orive.Employee.Entity.TransfersEntity;
import com.orive.Employee.Repository.ResignationsRepository;



@Service
public class ResignationsService {
	
	private static final Logger logger=LoggerFactory.getLogger(ResignationsService.class);
	
	@Autowired
	private ResignationsRepository resignationsRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
//	// Create
//    public ResignationsDto createResignations(ResignationsDto resignationsDto) {
//    	ResignationsEntity resignationsEntity = convertToEntity(resignationsDto);
//    	ResignationsEntity savedResignations = resignationsRepository.save(resignationsEntity);
//        logger.info("Created Resignation with ID: {}", savedResignations.getResignationId());
//        return convertToDTO(savedResignations);
//    }
	
	// Create
			public ResignationsDto createResignation(ResignationsDto resignationsDto) {
			    // Check if the department name already exists
			    Optional<ResignationsEntity> existingDepartment = resignationsRepository.findByEmployeeName(resignationsDto.getEmployeeName());
			    if (existingDepartment.isPresent()) {
			        // Department name already exists, handle the error as needed
			        throw new RuntimeException("Resignation with name '" + resignationsDto.getEmployeeName() + "' already exists");
			    }
			    // Department name is unique, proceed with saving
			    ResignationsEntity departmentEntity = convertToEntity(resignationsDto);
			    ResignationsEntity savedDepartment = resignationsRepository.save(departmentEntity);
			    logger.info("Created Resignation with ID: {}", savedDepartment.getResignationId());
			    return convertToDTO(savedDepartment);
			}

    // Read
    public List<ResignationsDto> getAllResignations() {
        List<ResignationsEntity> resignationsEntities = resignationsRepository.findAll();
        logger.info("Retrieved {} Resignation from the database", resignationsEntities.size());
        return resignationsEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by ResignationsId
    public Optional<ResignationsDto> getResignationsById(Long resignationId) {
        Optional<ResignationsEntity> resignation = resignationsRepository.findById(resignationId);
        if (resignation.isPresent()) {
            return Optional.of(convertToDTO(resignation.get()));
        } else {
            logger.warn("Resignation with ID {} not found", resignationId);
            return Optional.empty();
        }
    }
    
  //get by ResignationsByName
    public Optional<ResignationsDto> getResignationsByName(String employeeName) {
        Optional<ResignationsEntity> department = resignationsRepository.findByEmployeeName(employeeName);
        if (department.isPresent()) {
            return Optional.of(convertToDTO(department.get()));
        } else {
            logger.warn("Employee with Name {} not found", employeeName);
            return Optional.empty();
        }
    }
    
    //get resignationdetails by username
    public List<ResignationsDto> getTransfersByUsername(String username) {
        List<ResignationsEntity> transfersEntities = resignationsRepository.findByUsername(username);
        return transfersEntities.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
 // Update list by id
    public ResignationsDto updateResignations(Long resignationId, ResignationsDto resignationsDto) {
        Optional<ResignationsEntity> existingResignationOptional = resignationsRepository.findById(resignationId);
        if (existingResignationOptional.isPresent()) {
        	ResignationsEntity existingResignation = existingResignationOptional.get();
        	existingResignation.setEmployeeName(resignationsDto.getEmployeeName());
        	existingResignation.setNoticeDate(resignationsDto.getNoticeDate());
        	existingResignation.setResignationDate(resignationsDto.getResignationDate());
            modelMapper.map(resignationsDto, existingResignationOptional);
            ResignationsEntity updatedResignation = resignationsRepository.save(existingResignation);
            logger.info("Updated Resignation with ID: {}", updatedResignation.getResignationId());
            return convertToDTO(updatedResignation);
        } else {
            logger.warn("Resignation with ID {} not found for update", resignationId);
            return null;
        }
    }
    
    // Delete
    public void deleteResignations(Long resignationId) {
    	resignationsRepository.deleteById(resignationId);
        logger.info("Deleted Resignation with ID: {}", resignationId);
    }

    //count the total resignation
    public long countResignations()
	 {
		 return resignationsRepository.count();
	 }
    
 // Helper method to convert ResignationDTo to Resignation entity
    private ResignationsEntity convertToEntity(ResignationsDto resignationsDto)
    {
    	return modelMapper.map(resignationsDto, ResignationsEntity.class);
    }

    // Helper method to convert Resignation entity entity to ResignationDTo
    private ResignationsDto convertToDTO(ResignationsEntity resignationsEntity) {
        return modelMapper.map(resignationsEntity, ResignationsDto.class);
    } 

}
