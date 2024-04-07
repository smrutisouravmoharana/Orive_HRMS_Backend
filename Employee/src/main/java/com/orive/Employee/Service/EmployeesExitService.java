package com.orive.Employee.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Employee.Dto.EmployeesExitDto;
import com.orive.Employee.Entity.EmployeesExitEntity;
import com.orive.Employee.Repository.EmployeesExitRepository;


@Service
public class EmployeesExitService {
	
private static final Logger logger=LoggerFactory.getLogger(EmployeesExitService.class);
	
	@Autowired
	private EmployeesExitRepository employeesExitRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	// Create
    public EmployeesExitDto createEmployeesExit(EmployeesExitDto employeesExitDto) {
    	EmployeesExitEntity employeesExitEntity = convertToEntity(employeesExitDto);
    	EmployeesExitEntity savedEmployeeExit = employeesExitRepository.save(employeesExitEntity);
        logger.info("Created  EmployeeExit with ID: {}", savedEmployeeExit.getEmployeeExitId());
        return convertToDTO(savedEmployeeExit);
    }

    // Read
    public List<EmployeesExitDto> getAllEmployeesExit() {
        List<EmployeesExitEntity> employeesExitEntities = employeesExitRepository.findAll();
        logger.info("Retrieved {}  EmployeeExit from the database", employeesExitEntities.size());
        return employeesExitEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by  EmployeeExitId
    public Optional<EmployeesExitDto> getEmployeesExitById(Long employeesExitId) {
        Optional<EmployeesExitEntity> employeesExit = employeesExitRepository.findById(employeesExitId);
        if (employeesExit.isPresent()) {
            return Optional.of(convertToDTO(employeesExit.get()));
        } else {
            logger.warn(" EmployeeExit with ID {} not found", employeesExitId);
            return Optional.empty();
        }
    }
    
    public List<EmployeesExitDto> getEmployeesExitByUsername(String username) {
        List<EmployeesExitEntity> transfersEntities = employeesExitRepository.findByUsername(username);
        return transfersEntities.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
 // Update list by id
    public EmployeesExitDto updateEmployeesExit(Long employeesExitId, EmployeesExitDto employeesExitDto) {
        Optional<EmployeesExitEntity> existingEmployeeExitOptional = employeesExitRepository.findById(employeesExitId);
        if (existingEmployeeExitOptional.isPresent()) {
        	EmployeesExitEntity existingEmployeesExit = existingEmployeeExitOptional.get();
        	existingEmployeesExit.setEmployeeName(employeesExitDto.getEmployeeName());
        	existingEmployeesExit.setExitDate(employeesExitDto.getExitDate());
        	existingEmployeesExit.setTypeOfExit(employeesExitDto.getTypeOfExit());
            modelMapper.map(employeesExitDto, existingEmployeeExitOptional);
            EmployeesExitEntity updatedEmployeesExit = employeesExitRepository.save(existingEmployeesExit);
            logger.info("Updated  EmployeeExit with ID: {}", updatedEmployeesExit.getEmployeeExitId());
            return convertToDTO(updatedEmployeesExit);
        } else {
            logger.warn(" EmployeeExit with ID {} not found for update", employeesExitId);
            return null;
        }
    }
    
    // Delete
    public void deleteEmployeeExit(Long employeesExitId) {
    	employeesExitRepository.deleteById(employeesExitId);
        logger.info("Deleted  EmployeeExit with ID: {}", employeesExitId);
    }

    //count the total  EmployeeExit
    public long countEmployeeExit()
	 {
		 return employeesExitRepository.count();
	 }
    
 // Helper method to convert  EmployeeExitDTo to  EmployeeExitEntity
    private EmployeesExitEntity convertToEntity(EmployeesExitDto employeesExitDto)
    {
    	return modelMapper.map(employeesExitDto, EmployeesExitEntity.class);
    }

    // Helper method to convert  EmployeeExitEntity to  EmployeeExitDTo
    private EmployeesExitDto convertToDTO(EmployeesExitEntity employeesExitEntity) 
    {
        return modelMapper.map(employeesExitEntity, EmployeesExitDto.class);
    } 
}