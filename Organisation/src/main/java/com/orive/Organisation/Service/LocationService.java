package com.orive.Organisation.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Organisation.Dto.DesignationDto;
import com.orive.Organisation.Dto.LocationDto;
import com.orive.Organisation.Entity.DesignationEntity;
import com.orive.Organisation.Entity.LocationEntity;
import com.orive.Organisation.Exceptions.ResourceNotFoundException;
import com.orive.Organisation.Repository.LocationRepository;

@Service
public class LocationService {

   private static final Logger logger=LoggerFactory.getLogger(LocationService.class);
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
//	// Create
//    public LocationDto createLocation(LocationDto locationDto) {
//    	LocationEntity locationEntity = convertToEntity(locationDto);
//    	LocationEntity savedLocation = locationRepository.save(locationEntity);
//        logger.info("Created Location with ID: {}", savedLocation.getLocationId());
//        return convertToDTO(savedLocation);
//    }
	
	// Create
			public LocationDto createLocation(LocationDto locationDto) {
			    // Check if the department name already exists
			    Optional<LocationEntity> existingDepartment = locationRepository.findByLocationName(locationDto.getLocationName());
			    if (existingDepartment.isPresent()) {
			        // Department name already exists, handle the error as needed
			        throw new RuntimeException("Location with name '" + locationDto.getLocationName() + "' already exists");
			    }
			    // Department name is unique, proceed with saving
			    LocationEntity departmentEntity = convertToEntity(locationDto);
			    LocationEntity savedDepartment = locationRepository.save(departmentEntity);
			    logger.info("Created Location with ID: {}", savedDepartment.getLocationName());
			    return convertToDTO(savedDepartment);
			}

    // Read
    public List<LocationDto> getAllLocation() {
        List<LocationEntity> locationEntities = locationRepository.findAll();
        logger.info("Retrieved {} Location from the database", locationEntities.size());
        return locationEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by LocationId
    public Optional<LocationDto> getLocationById(Long locationId) {
        Optional<LocationEntity> location = locationRepository.findById(locationId);
        if (location.isPresent()) {
            return Optional.of(convertToDTO(location.get()));
        } else {
            logger.warn("Location with ID {} not found", locationId);
            return Optional.empty();
        }
    }
    
    //get by LocationByName
    public Optional<LocationDto> getLocationByName(String locationName) {
        Optional<LocationEntity> department = locationRepository.findByLocationName(locationName);
        if (department.isPresent()) {
            return Optional.of(convertToDTO(department.get()));
        } else {
            logger.warn("Location with Name {} not found", locationName);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public LocationDto updateLocation(Long locationId, LocationDto locationDto) {
        Optional<LocationEntity> existingLocationOptional = locationRepository.findById(locationId);
        if (existingLocationOptional.isPresent()) {
        	LocationEntity existingLocation = existingLocationOptional.get();
        	existingLocation.setCompanyName(locationDto.getCompanyName());
        	existingLocation.setLocationName(locationDto.getLocationName());
        	existingLocation.setEmail(locationDto.getEmail());
        	existingLocation.setStatus(locationDto.getStatus());
            modelMapper.map(locationDto, existingLocationOptional);
            LocationEntity updatedLocation = locationRepository.save(existingLocation);
            logger.info("Updated Location with ID: {}", updatedLocation.getLocationId());
            return convertToDTO(updatedLocation);
        } else {
            logger.warn("Location with ID {} not found for update", locationId);
            return null;
        }
    }
    
    // Delete
    public void deleteLocation(Long locationId) {
    	locationRepository.deleteById(locationId);
        logger.info("Deleted Location with ID: {}", locationId);
    }

    //count the total Location
    public long countLocation()
	 {
		 return locationRepository.count();
	 }
    
    //find all details by CompanyName
    public List<LocationEntity> getAllLocationsByCompanyName(String companyName) {
    	logger.info("Request to get all locations for company: " + companyName);
        List<LocationEntity> locations = locationRepository.findAllByCompanyName(companyName);

        if (locations.isEmpty()) {
        	logger.warn("No locations found for company: " + companyName);
            throw new ResourceNotFoundException("No locations found for company: " + companyName);
        } else {
        	logger.info("Retrieved " + locations.size() + " locations for company: " + companyName);
        }

        return locations;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	// Helper method to convert LocationDTo to LocationEntity
    private LocationEntity convertToEntity(LocationDto locationDto)
    {
    	return modelMapper.map(locationDto, LocationEntity.class);
    }

    // Helper method to convert LocationEntity to LocationDTo
    private LocationDto convertToDTO(LocationEntity locationEntity) {
        return modelMapper.map(locationEntity, LocationDto.class);
    } 
}
