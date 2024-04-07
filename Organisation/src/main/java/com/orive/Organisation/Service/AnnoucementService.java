package com.orive.Organisation.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Organisation.Dto.AnnoucementDto;

import com.orive.Organisation.Entity.AnnoucementEntity;

import com.orive.Organisation.Repository.AnnoucementRepository;



@Service
public class AnnoucementService {

    private static final Logger logger=LoggerFactory.getLogger(AnnoucementService.class);
	
	@Autowired
	private AnnoucementRepository annoucementsRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	// Create
    public AnnoucementDto createAnnouncements(AnnoucementDto annoucementDto) {
    	AnnoucementEntity annoucementEntity = convertToEntity(annoucementDto);
    	AnnoucementEntity savedAnnoucement = annoucementsRepository.save(annoucementEntity);
        logger.info("Created Annoucement with ID: {}", savedAnnoucement.getAnnouncementsId());
        return convertToDTO(savedAnnoucement);
    }

    // Read
    public List<AnnoucementDto> getAllAnnouncements() {
        List<AnnoucementEntity> announcementEntities = annoucementsRepository.findAll();
        logger.info("Retrieved {} Annoucement from the database", announcementEntities.size());
        return announcementEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by AnnoucementsId
    public Optional<AnnoucementDto> getAnnouncementsById(Long announcementId) {
        Optional<AnnoucementEntity> announcement = annoucementsRepository.findById(announcementId);
        if (announcement.isPresent()) {
            return Optional.of(convertToDTO(announcement.get()));
        } else {
            logger.warn("Annoucements with ID {} not found", announcementId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public AnnoucementDto updateAnnouncement(Long announcementId, AnnoucementDto annoucementDto) {
        Optional<AnnoucementEntity> existingAnnoucementOptional = annoucementsRepository.findById(announcementId);
        if (existingAnnoucementOptional.isPresent()) {
        	AnnoucementEntity existingAnnoucement = existingAnnoucementOptional.get();
        	existingAnnoucement.setTitle(annoucementDto.getTitle());
        	existingAnnoucement.setStartDate(annoucementDto.getStartDate());
        	existingAnnoucement.setEndDate(annoucementDto.getEndDate());
        	existingAnnoucement.setDescription(annoucementDto.getDescription());
            modelMapper.map(annoucementDto, existingAnnoucementOptional);
            AnnoucementEntity updatedAnnoucement = annoucementsRepository.save(existingAnnoucement);
            logger.info("Updated Annoucement with ID: {}", updatedAnnoucement.getAnnouncementsId());
            return convertToDTO(updatedAnnoucement);
        } else {
            logger.warn("Annoucement with ID {} not found for update", announcementId);
            return null;
        }
    }
    
    // Delete
    public void deleteAnnouncement(Long announcementId) {
    	annoucementsRepository.deleteById(announcementId);
        logger.info("Deleted Annoucement with ID: {}", announcementId);
    }

    //count the total Annoucement
    public long countAnnouncement()
	 {
		 return annoucementsRepository.count();
	 }
    
	// Helper method to convert AnnoucementDTo to AnnoucementEntity
    private AnnoucementEntity convertToEntity(AnnoucementDto annoucementDto)
    {
    	return modelMapper.map(annoucementDto, AnnoucementEntity.class);
    }

    // Helper method to convert AnnoucementEntity  to AnnoucementDTo
    private AnnoucementDto convertToDTO(AnnoucementEntity annoucementEntity) {
        return modelMapper.map(annoucementEntity, AnnoucementDto.class);
    } 
}
