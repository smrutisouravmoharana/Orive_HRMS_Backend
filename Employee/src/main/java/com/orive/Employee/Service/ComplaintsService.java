package com.orive.Employee.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Employee.Dto.ComplaintsDto;
import com.orive.Employee.Entity.ComplaintsEntity;
import com.orive.Employee.Repository.ComplaintsRepository;

@Service
public class ComplaintsService {
	
private static final Logger logger=LoggerFactory.getLogger(ComplaintsService.class);
	
	@Autowired
	private ComplaintsRepository complaintsRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public ComplaintsDto createComplaints(ComplaintsDto complaintsDto) {
    	ComplaintsEntity complaintsEntity = convertToEntity(complaintsDto);
    	ComplaintsEntity savedcomplaints = complaintsRepository.save(complaintsEntity);
        logger.info("Created Complaints with ID: {}", savedcomplaints.getComplaintsId());
        return convertToDTO(savedcomplaints);
    }

    // Read
    public List<ComplaintsDto> getAllComplaints() {
        List<ComplaintsEntity> complaintsEntities = complaintsRepository.findAll();
        logger.info("Retrieved {} Complaints from the database", complaintsEntities.size());
        return complaintsEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by ComplaintsId
    public Optional<ComplaintsDto> getComplaintsById(Long complaintsId) {
        Optional<ComplaintsEntity> complaints = complaintsRepository.findById(complaintsId);
        if (complaints.isPresent()) {
            return Optional.of(convertToDTO(complaints.get()));
        } else {
            logger.warn("Complaints with ID {} not found", complaintsId);
            return Optional.empty();
        }
    }
    
    //get complaints by username
    public List<ComplaintsDto> getTransfersByUsername(String username) {
        List<ComplaintsEntity> transfersEntities = complaintsRepository.findByUsername(username);
        return transfersEntities.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
 // Update list by id
    public ComplaintsDto updateComplaints(Long complaintsId, ComplaintsDto complaintsDto) {
        Optional<ComplaintsEntity> existingComplaintsOptional = complaintsRepository.findById(complaintsId);
        if (existingComplaintsOptional.isPresent()) {
        	ComplaintsEntity existingComplaints = existingComplaintsOptional.get();
        	existingComplaints.setEmployeeName(existingComplaints.getEmployeeName());
        	existingComplaints.setComplaintAgainst(complaintsDto.getComplaintAgainst());
            modelMapper.map(complaintsDto, existingComplaintsOptional);
            ComplaintsEntity updatedComplaints = complaintsRepository.save(existingComplaints);
            logger.info("Updated Complaints with ID: {}", updatedComplaints.getComplaintsId());
            return convertToDTO(updatedComplaints);
        } else {
            logger.warn("Complaints with ID {} not found for update", complaintsId);
            return null;
        }
    }
    
    // Delete
    public void deleteComplaints(Long complaintsId) {
    	complaintsRepository.deleteById(complaintsId);
        logger.info("Deleted Complaints with ID: {}", complaintsId);
    }

    //count the total Complaints
    public long countComplaints()
	 {
		 return complaintsRepository.count();
	 }
    
 // Helper method to convert ComplaintsDTo to ComplaintsEntity
    private ComplaintsEntity convertToEntity(ComplaintsDto complaintsDto)
    {
    	return modelMapper.map(complaintsDto, ComplaintsEntity.class);
    }

    // Helper method to convert ComplaintsEntity to ComplaintsDTo
    private ComplaintsDto convertToDTO(ComplaintsEntity complaintsEntity) {
        return modelMapper.map(complaintsEntity, ComplaintsDto.class);
    } 


}