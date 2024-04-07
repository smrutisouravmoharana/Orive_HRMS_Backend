package com.orive.Procurement.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.orive.Procurement.Dto.CommitteesDto;
import com.orive.Procurement.Entity.CommitteesEntity;
import com.orive.Procurement.Entity.GoodReceivedEntity;
import com.orive.Procurement.Entity.GoodReceivedListEntity;
import com.orive.Procurement.Enum.Status;
import com.orive.Procurement.Repository.CommitteesRepository;
import com.orive.Procurement.Util.PhotoUtils;

@Service
public class CommitteesService {
	
	public static final Logger logger= LoggerFactory.getLogger(CommitteesService.class);
	
	@Autowired
	private CommitteesRepository committeesRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
//			 public CommitteesDto createCommittees(CommitteesDto committeesDto) {
//				 CommitteesEntity committeesEntity = convertToEntity(committeesDto);
//				 CommitteesEntity savedCommittees = committeesRepository.save(committeesEntity);
//			        logger.info("Created Committees with ID: {}", savedCommittees.getCommitteesId());
//			        return convertToDTO(savedCommittees);
//			    }
	
	
	
	//create
	public String saveCommitteesEntity(
			String name,		
			MultipartFile file,
			String status) {
		
		try {
			CommitteesEntity uploadImage = committeesRepository.save(CommitteesEntity.builder()
					.name(name)
					.signature(file != null ? PhotoUtils.compressImage(file.getBytes()) : null)
					.status(status)
					.build());

	            if (uploadImage != null) {
	                return "File uploaded successfully: " + (file != null ? file.getOriginalFilename() : "No file attached");
	            }
			
		}catch (Exception e) {
			// Handle the IOException appropriately (e.g., log it, return an error message)
	        return "Error uploading file: " + e.getMessage();
		}
		
		return null;
	}
	
			 
			 //Download Logo
			 public byte[] downloadImage(String name){
			        Optional<CommitteesEntity> dbImageData = committeesRepository.findByName(name);
			        byte[] images=PhotoUtils.decompressImage(dbImageData.get().getSignature());
			        return images;
			    }
		
		    // Read
		    public List<CommitteesDto> getAllCommittees() {
		        List<CommitteesEntity> committeesEntities = committeesRepository.findAll();
		        logger.info("Retrieved {} Committees from the database", committeesEntities.size());
		        return committeesEntities.stream()
		                .map(this::convertToDTO)
		                .collect(Collectors.toList());
		    }
		    
		    //get by CommitteesId
		    public Optional<CommitteesDto> getCommitteesById(Long committeesId) {
		        Optional<CommitteesEntity> committees = committeesRepository.findById(committeesId);
		        if (committees.isPresent()) {
		            return Optional.of(convertToDTO(committees.get()));
		        } else {
		            logger.warn("Committees with ID {} not found", committeesId);
		            return Optional.empty();
		        }
		    }
		    
		 // Update list by id
		    public CommitteesDto updateCommittees(Long committeesId, CommitteesDto committeesDto) {
		        Optional<CommitteesEntity> existingCommitteesOptional = committeesRepository.findById(committeesId);
		        if (existingCommitteesOptional.isPresent()) {
		        	CommitteesEntity existingCommittees = existingCommitteesOptional.get();
//		            existingCommittees.setName(committeesDto.getName());   	
		        	existingCommittees.setStatus(committeesDto.getStatus());
		        	modelMapper.map(committeesDto, existingCommitteesOptional);
		            CommitteesEntity updatedCommittees = committeesRepository.save(existingCommittees);
		            logger.info("Updated Committees with ID: {}", updatedCommittees.getCommitteesId());
		            return convertToDTO(updatedCommittees);
		        } else {
		            logger.warn("Committees with ID {} not found for update", committeesId);
		            return null;
		        }
		    }
		    
		    // Delete
		    public void deleteCommittees(Long committeesId) {
		    committeesRepository.deleteById(committeesId);
		        logger.info("Deleted Committees with ID: {}", committeesId);
		    }

		    //count the total Committees
		    public long countCommittees()
			 {
				 return committeesRepository.count();
			 }
		    
			// Helper method to convert RequestDTo to RequestEntity
		    private CommitteesEntity convertToEntity(CommitteesDto committeesDto )
		    {
		    	return modelMapper.map(committeesDto, CommitteesEntity.class);
		    }

		    // Helper method to convert CommitteesEntity  to CommitteesDTo
		    private CommitteesDto convertToDTO(CommitteesEntity committeesEntity ) 
		    {
		        return modelMapper.map(committeesEntity, CommitteesDto.class);
		    } 


}
