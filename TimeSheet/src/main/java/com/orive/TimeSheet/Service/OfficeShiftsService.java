package com.orive.TimeSheet.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.TimeSheet.Dto.OfficeShiftsDto;
import com.orive.TimeSheet.Entity.OfficeShiftsEntity;
import com.orive.TimeSheet.Repository.OfficeShiftsRepository;



@Service
public class OfficeShiftsService {

	 private static final Logger logger=LoggerFactory.getLogger(OfficeShiftsService.class);
		
		@Autowired
		private OfficeShiftsRepository officeShiftsRepository;
		
		@Autowired
		private ModelMapper modelMapper;
		
		
		// Create
	    public OfficeShiftsDto createOfficeShifts(OfficeShiftsDto officeShiftsDto) {
	    	OfficeShiftsEntity officeShiftsEntity = convertToEntity(officeShiftsDto);
	    	OfficeShiftsEntity savedOfficeShifts = officeShiftsRepository.save(officeShiftsEntity);
	        logger.info("Created OfficeShifts with ID: {}", savedOfficeShifts.getOfficeShiftsId());
	        return convertToDTO(savedOfficeShifts);
	    }

	    // Read
	    public List<OfficeShiftsDto> getAllOfficeShifts() {
	        List<OfficeShiftsEntity> officeShiftsEntities = officeShiftsRepository.findAll();
	        logger.info("Retrieved {} OfficeShifts from the database", officeShiftsEntities.size());
	        return officeShiftsEntities.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }
	    
	    //get by OfficeShiftId
	    public Optional<OfficeShiftsDto> getOfficeShiftsById(String OfficeShiftsId) {
	        Optional<OfficeShiftsEntity> OfficeShifts = officeShiftsRepository.findById(OfficeShiftsId);
	        if (OfficeShifts.isPresent()) {
	            return Optional.of(convertToDTO(OfficeShifts.get()));
	        } else {
	            logger.warn("OfficeShifts with ID {} not found", OfficeShiftsId);
	            return Optional.empty();
	        }
	    }
	    
	 // Update list by id
	    public OfficeShiftsDto updateOfficeShifts(String OfficeShiftsId, OfficeShiftsDto officeShiftsDto) {
	        Optional<OfficeShiftsEntity> existingofficeShiftsOptional = officeShiftsRepository.findById(OfficeShiftsId);
	        if (existingofficeShiftsOptional.isPresent()) {
	        	OfficeShiftsEntity existingOfficeShifts = existingofficeShiftsOptional.get();
	        	existingOfficeShifts.setOfficeClockIn(officeShiftsDto.getOfficeClockIn());
	        	existingOfficeShifts.setOfficeClockOut(officeShiftsDto.getOfficeClockOut());
	        	modelMapper.map(officeShiftsDto, existingofficeShiftsOptional);
	            OfficeShiftsEntity updatedOfficeShifts = officeShiftsRepository.save(existingOfficeShifts);
	            logger.info("Updated OfficeShifts with ID: {}", updatedOfficeShifts.getOfficeShiftsId());
	            return convertToDTO(updatedOfficeShifts);
	        } else {
	            logger.warn("OfficeShifts with ID {} not found for update", OfficeShiftsId);
	            return null;
	        }
	    }
	    
	    // Delete
	    public void deleteOfficeShifts(String OfficeShiftsId) {
	    	officeShiftsRepository.deleteById(OfficeShiftsId);
	        logger.info("Deleted OfficeShifts with ID: {}", OfficeShiftsId);
	    }

	    //count the total OfficeShifts
	    public long countOfficeShifts()
		 {
			 return officeShiftsRepository.count();
		 }
	    
		// Helper method to convert OfficeShiftsDTo to  OfficeShiftsEntity
	    private OfficeShiftsEntity convertToEntity(OfficeShiftsDto officeShiftsDto)
	    {
	    	return modelMapper.map(officeShiftsDto, OfficeShiftsEntity.class);
	    }

	    // Helper method to convert OfficeShiftsEntity to OfficeShiftsDTo
	    private OfficeShiftsDto convertToDTO(OfficeShiftsEntity officeShiftsEntity) {
	        return modelMapper.map(officeShiftsEntity, OfficeShiftsDto.class);
	    } 
}
