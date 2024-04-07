package com.orive.Organisation.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.orive.Organisation.Dto.CompanyDto;
import com.orive.Organisation.Dto.ExpenceDto;
import com.orive.Organisation.Dto.ExpenseListDto;
import com.orive.Organisation.Entity.CompanyEntity;
import com.orive.Organisation.Entity.ExpenceEntity;
import com.orive.Organisation.Entity.ExpenseListEntity;
import com.orive.Organisation.Exceptions.ResourceNotFoundException;
import com.orive.Organisation.Repository.ExpenceRepository;
import com.orive.Organisation.Util.ImageUtils;
import com.orive.Organisation.Util.UploadDocumentUtils;
import com.orive.Organisation.Util.UploadPdfUtils;



@Service
public class ExpenceService {

private static final Logger logger=LoggerFactory.getLogger(ExpenceService.class);
	
	@Autowired
	private ExpenceRepository expenceRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	

	//create
	public String saveExpenceEntity(
			String expenceType,
			LocalDate createdDate,
			Long total,
			MultipartFile fileDocument) {
		
		try {
			ExpenceEntity pdfData = expenceRepository.save(ExpenceEntity.builder()
					.expenceType(expenceType)
					.createdDate(createdDate)
					.total(total)
					.uploadDocument(fileDocument != null ? UploadDocumentUtils.compressPdf(fileDocument.getBytes()) : null)
	                .build());

	            if (pdfData != null) {
	                return "File uploaded successfully: " + (fileDocument != null ? fileDocument.getOriginalFilename() : "No file attached");
	            }
			
		}catch (Exception e) {
			// Handle the IOException appropriately (e.g., log it, return an error message)
	        return "Error uploading file: " + e.getMessage();
		}
		
		return null;
	}
		
	
	
	//Download pdf
	public byte[] downloadPdf(Long expenceId) {
		 Optional<ExpenceEntity> dbPdfData = expenceRepository.findById(expenceId);
	    
	    if (dbPdfData.isPresent()) {
	        return UploadPdfUtils.decompressPdf(dbPdfData.get().getUploadDocument());
	    } else {
	        // Handle the case where the candidate profile is not found
	        return null;
	    }
	}
	
    // Read
    public List<ExpenceDto> getAllExpence() {
        List<ExpenceEntity> expenceEntities = expenceRepository.findAll();
        logger.info("Retrieved {} Expence from the database", expenceEntities.size());
        return expenceEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by AddBankId
    public ExpenceEntity getByCareerSiteId(Long expenceId) {
        //get user from database with the help  of user repository
    	ExpenceEntity user = expenceRepository.findById(expenceId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + expenceId));
        // fetch rating of the above  user from RATING SERVICE
        //http://localhost:8083/ratings/users/47e38dac-c7d0-4c40-8582-11d15f185fad

        ArrayList<ExpenseListEntity> ratingsOfUser = restTemplate.getForObject("http://localhost:8081/expencelist/" + user.getExpenceId(), ArrayList.class);
        logger.info("{} ", ratingsOfUser);
//        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
//        List<Rating> ratingList = ratings.stream().map(rating -> {
//            //api call to hotel service to get the hotel
//            //http://localhost:8082/hotels/1cbaf36d-0b28-4173-b5ea-f1cb0bc0a791
//            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel = hotelService.getHotel(rating.getHotelId());
//            // logger.info("response status code: {} ",forEntity.getStatusCode());
//            //set the hotel to rating
//            rating.setHotel(hotel);
//            //return the rating
//            return rating;
//        }).collect(Collectors.toList());

        user.setExpenseListEntities(ratingsOfUser);

        return user;
    }
    
 
    
    // Delete
    public void deleteExpence(Long expenceId) {
    	expenceRepository.deleteById(expenceId);
        logger.info("Deleted Expence with ID: {}", expenceId);
    }

    //count the total Expence
    public long countExpence()
	 {
		 return expenceRepository.count();
	 }
    
	// Helper method to convert ExpenceDTo to ExpenceEntity
    private ExpenceEntity convertToEntity(ExpenceDto expenceDto)
    {
    	return modelMapper.map(expenceDto, ExpenceEntity.class);
    }

    // Helper method to convert ExpenceEntity to ExpenceDTo
    private ExpenceDto convertToDTO(ExpenceEntity  expenceEntity) {
        return modelMapper.map(expenceEntity, ExpenceDto.class);
    } 
}

 

