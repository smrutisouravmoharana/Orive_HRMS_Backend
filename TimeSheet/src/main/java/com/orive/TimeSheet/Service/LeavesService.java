package com.orive.TimeSheet.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.orive.TimeSheet.Dto.LeaveDto;
import com.orive.TimeSheet.Entity.LeavesEntity;
import com.orive.TimeSheet.Repository.LeavesRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LeavesService {

private static final Logger logger=LoggerFactory.getLogger(LeavesService.class);
	
	@Autowired
	private LeavesRepository leavesRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	// Create
    public LeaveDto createLeaves(LeaveDto leaveDto) {
    	LeavesEntity leavesEntity = convertToEntity(leaveDto);
    	LeavesEntity savedCompany = leavesRepository.save(leavesEntity);
        logger.info("Created Leaves with ID: {}", savedCompany.getLeaveId());
        return convertToDTO(savedCompany);
    }

    // Read
    public List<LeaveDto> getAllLeaves() {
        List<LeavesEntity> leavesEntities = leavesRepository.findAll();
        logger.info("Retrieved {} Leaves from the database", leavesEntities.size());
        return leavesEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by LeavesId
    public Optional<LeaveDto> getLeavesById(String leaveId) {
        Optional<LeavesEntity> leaves = leavesRepository.findById(leaveId);
        if (leaves.isPresent()) {
            return Optional.of(convertToDTO(leaves.get()));
        } else {
            logger.warn("Leaves with ID {} not found", leaveId);
            return Optional.empty();
        }
    }
    
    //get by employeeId
    public List<LeaveDto> getLeavesByEmployeeId(String username) {
        List<LeavesEntity> leaves = leavesRepository.findByEmployeeId(username);

        if (leaves.isEmpty()) {
            logger.warn("Leaves with ID {} not found", username);
            return Collections.emptyList();
        }
        return leaves.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    } 
    
    
 // Update list by id
    public LeaveDto updateLeaves(String leaveId, LeaveDto leaveDto) {
        Optional<LeavesEntity> existingLeavesOptional = leavesRepository.findById(leaveId);
        if (existingLeavesOptional.isPresent()) {
        	LeavesEntity existingLeave = existingLeavesOptional.get();
//            existingLeave.setEmployeeName(leaveDto.getEmployeeName());
//        	existingLeave.setStartDate(leaveDto.getStartDate());
//        	existingLeave.setEndDate(leaveDto.getEndDate());
        	existingLeave.setApproval(leaveDto.getApproval());
        	modelMapper.map(leaveDto, existingLeavesOptional);
            LeavesEntity updatedLeave = leavesRepository.save(existingLeave);
            logger.info("Updated Leaves with ID: {}", updatedLeave.getLeaveId());
            return convertToDTO(updatedLeave);
        } else {
            logger.warn("Leaves with ID {} not found for update", leaveId);
            return null;
        }
    }
    
    
    // Update projects by employeeId
    public List<LeavesEntity> updateLeavesByEmployeeId(String username, List<LeavesEntity> updatedLeaves) {
        List<LeavesEntity> existingLeavesList = leavesRepository.findByEmployeeId(username);

        if (!existingLeavesList.isEmpty()) {
            for (LeavesEntity existingLeaves : existingLeavesList) {
                // Find the corresponding updated Leave
                Optional<LeavesEntity> updatedLeaveOptional = updatedLeaves.stream()
                        .filter(l -> l.getLeaveId() == existingLeaves.getLeaveId())
                        .findFirst();

                if (updatedLeaveOptional.isPresent()) {
                	LeavesEntity updatedLeave = updatedLeaveOptional.get();

                    // Update fields based on your requirements
                    existingLeaves.setApproval(updatedLeave.getApproval());

                    // Save the updated project
                    leavesRepository.save(existingLeaves);
                }
            }

            return updatedLeaves;
        } else {
            logger.warn("Leaves with EmployeeId {} not found for update", username);
            throw new EntityNotFoundException("Leaves not found with EmployeeId: " + username);
        }
    }
    
       
    
    // Delete
    public void deleteLeaves(String leaveId) {
    	leavesRepository.deleteById(leaveId);
        logger.info("Deleted Leaves with ID: {}", leaveId);
    }

    //count the total Leaves
    public long countLeaves()
	 {
		 return leavesRepository.count();
	 }
    
	// Helper method to convert LeavesDTo to LeavesEntity
    private LeavesEntity convertToEntity(LeaveDto leaveDto)
    {
    	return modelMapper.map(leaveDto, LeavesEntity.class);
    }

    // Helper method to convert LeavesEntity entity to LeavesEntity
    private LeaveDto convertToDTO(LeavesEntity leavesEntity) {
        return modelMapper.map(leavesEntity, LeaveDto.class);
    } 
}
