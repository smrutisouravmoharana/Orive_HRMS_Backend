package com.orive.Procurement.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Procurement.Dto.RequestDto;
import com.orive.Procurement.Dto.UnitsDto;
import com.orive.Procurement.Entity.RequestEntity;
import com.orive.Procurement.Entity.UnitsEntity;
import com.orive.Procurement.Repository.UnitsRepository;

@Service
public class UnitsService {
	
	private static final Logger logger = LoggerFactory.getLogger(UnitsService.class);
	
	@Autowired
	private UnitsRepository unitsRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
			 public UnitsDto createUnits(UnitsDto unitsDto) {
				 UnitsEntity unitsEntity = convertToEntity(unitsDto);
				 UnitsEntity savedUnits = unitsRepository.save(unitsEntity);
			        logger.info("Created Units with ID: {}", savedUnits.getUnitsId());
			        return convertToDTO(savedUnits);
			    }

		    // Read
		    public List<UnitsDto> getAllUnits() {
		        List<UnitsEntity> unitsEntities = unitsRepository.findAll();
		        logger.info("Retrieved {} Units from the database", unitsEntities.size());
		        return unitsEntities.stream()
		                .map(this::convertToDTO)
		                .collect(Collectors.toList());
		    }
		    
		    //get by UnitsId
		    public Optional<UnitsDto> getUnitsById(Long unitsId) {
		        Optional<UnitsEntity> units = unitsRepository.findById(unitsId);
		        if (units.isPresent()) {
		            return Optional.of(convertToDTO(units.get()));
		        } else {
		            logger.warn("Units with ID {} not found", unitsId);
		            return Optional.empty();
		        }
		    }
		    
		 // Update list by id
		    public UnitsDto updateUnits(Long unitsId, UnitsDto unitsDto) {
		        Optional<UnitsEntity> existingUnitsOptional = unitsRepository.findById(unitsId);
		        if (existingUnitsOptional.isPresent()) {
		        	UnitsEntity existingUnits = existingUnitsOptional.get();
//		            existingUnits.setUnitName(unitsDto.getUnitName());
//		        	existingUnits.setStatus(unitsDto.getStatus());
		        	modelMapper.map(unitsDto, existingUnitsOptional);
		            UnitsEntity updatedUnits = unitsRepository.save(existingUnits);
		            logger.info("Updated Units with ID: {}", updatedUnits.getUnitsId());
		            return convertToDTO(updatedUnits);
		        } else {
		            logger.warn("Units with ID {} not found for update", unitsId);
		            return null;
		        }
		    }
		    
		    // Delete
		    public void deleteUnits(Long unitsId) {
		    unitsRepository.deleteById(unitsId);
		        logger.info("Deleted Units with ID: {}", unitsId);
		    }

		    //count the total Units
		    public long countUnits()
			 {
				 return unitsRepository.count();
			 }
		    
			// Helper method to convert UnitsDTo to UnitsEntity
		    private UnitsEntity convertToEntity(UnitsDto unitsDto )
		    {
		    	return modelMapper.map(unitsDto, UnitsEntity.class);
		    }

		    // Helper method to convert UnitsEntity  to UnitsDTo
		    private UnitsDto convertToDTO(UnitsEntity unitsEntity  ) 
		    {
		        return modelMapper.map(unitsEntity, UnitsDto.class);
		    } 

}
