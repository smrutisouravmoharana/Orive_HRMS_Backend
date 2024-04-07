package com.orive.Organisation.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import com.orive.Organisation.Dto.CompanyDto;
import com.orive.Organisation.Entity.CompanyEntity;
import com.orive.Organisation.Entity.ExpenceEntity;
import com.orive.Organisation.Entity.LocationEntity;
import com.orive.Organisation.Enum.Status;
import com.orive.Organisation.Exceptions.ResourceNotFoundException;
import com.orive.Organisation.Repository.CompanyRepository;
import com.orive.Organisation.Util.ImageUtils;
import com.orive.Organisation.Util.UploadDocumentUtils;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


@Service
public class CompanyService {

	private static final Logger logger=LoggerFactory.getLogger(CompanyService.class);
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
		
	
	//create
		public String saveCompanyEntity(
				String address,
				String cin,
				String city,
				String companyName,
				String companyType,
				Long contactNumber,
				String country,
                LocalDate createdDate,
                String email,
                MultipartFile fileDocument,
                String gst,
                String legalOrTradingName,
                String registrationNumber,
                String state,
                String uan,
                String website,
                int zipCode,
                String status) {
			
			 // Check if the company with the given name already exists
		    Optional<CompanyEntity> existingCompany = companyRepository.findByCompanyName(companyName);
		    if (existingCompany.isPresent()) {
		        return "Error: A company with the name '" + companyName + "' already exists";
		    }
			
		    try {
		        // Create a new CompanyEntity object and save it
		        CompanyEntity newCompany = CompanyEntity.builder()
						.address(address)
						.cin(cin)
						.city(city)
						.companyName(companyName)
						.companyType(companyType)
						.contactNumber(contactNumber)
						.country(country)
						.createdDate(createdDate)
						.email(email)
						.file(fileDocument != null ? UploadDocumentUtils.compressPdf(fileDocument.getBytes()) : null)
						.gst(gst)
						.legalOrTradingName(legalOrTradingName)
						.registrationNumber(registrationNumber)
						.state(state)
						.uan(uan)
						.website(website)
						.zipCode(zipCode)
						.status(status)
		                .build();

		        CompanyEntity savedCompany = companyRepository.save(newCompany);

		        if (savedCompany != null) {
		            return "Company saved successfully with ID: " + savedCompany.getCompanyId();
		        } else {
		            return "Error: Failed to save company";
		        }
				
		    } catch (IOException e) {
		        // Handle the IOException appropriately (e.g., log it, return an error message)
		        return "Error uploading file: " + e.getMessage();
		    }
		}
		
		 //Download Logo
		 public byte[] downloadImage(String companyName){
		        Optional<CompanyEntity> dbImageData = companyRepository.findByCompanyName(companyName);
		        byte[] images=ImageUtils.decompressImage(dbImageData.get().getFile());
		        return images;
		
		 }

    // Read
    public List<CompanyDto> getAllCompany() {
        List<CompanyEntity> companyEntities = companyRepository.findAll();
        logger.info("Retrieved {} company from the database", companyEntities.size());
        return companyEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by CompanyId
    public CompanyEntity getCompanyById(Long companyId) {
        CompanyEntity company = companyRepository.findById(companyId)
                .orElseThrow(() -> {
                    logger.warn("Company with ID {} not found", companyId);
                    return new ResourceNotFoundException("Company with ID " + companyId + " not found");
                });
//        ArrayList<LocationEntity> ratingsOfUser = restTemplate.getForObject("http://localhost:5000/candidateProfile/getcareer/" + company.get(), ArrayList.class);
//        logger.info("{} ", ratingsOfUser);
//        company.setLocationEntities(ratingsOfUser);

        return company;
    }
    
 // Update list by id
    public void partialUpdateCompany(Long companyId, CompanyEntity companyEntity) {
        Optional<CompanyEntity> existingCompanyOptional = companyRepository.findById(companyId);
        if (existingCompanyOptional.isPresent()) {
            CompanyEntity existingCompany = existingCompanyOptional.get();

            // Update fields conditionally only if they are not null in companyEntity
            if (companyEntity.getCompanyName() != null) {
                existingCompany.setCompanyName(companyEntity.getCompanyName());
            }

            if (companyEntity.getContactNumber() != null) {
                existingCompany.setContactNumber(companyEntity.getContactNumber());
            }

            if (companyEntity.getEmail() != null) {
                existingCompany.setEmail(companyEntity.getEmail());
            }

            if (companyEntity.getGst() != null) {
                existingCompany.setGst(companyEntity.getGst());
            }
            
            if (companyEntity.getCin() != null) {
                existingCompany.setCin(companyEntity.getCin());
            }
            
            if (companyEntity.getUan() != null) {
                existingCompany.setUan(companyEntity.getUan());
            }
            if (companyEntity.getStatus() != null) {
                existingCompany.setStatus(companyEntity.getStatus());
            }
            // Save the updated entity
            companyRepository.save(existingCompany);
            logger.info("Updated company with ID: {}", existingCompany.getCompanyId());
        } else {
            logger.warn("Company with ID {} not found for update", companyId);
        }
    }
    
    // Delete
    public void deleteCompany(Long companyId) {
    	companyRepository.deleteById(companyId);
        logger.info("Deleted company with ID: {}", companyId);
    }

    //count the total company
    public long countCompany()
	 {
		 return companyRepository.count();
	 }
    
    
//    //ALL DETAILS BY COMAPNYNAME
//    public List<CompanyEntity> getAllLocationsByCompanyName(String companyName) {
//    	logger.info("Request to get all Company for companyname: " + companyName);
//        List<CompanyEntity> locations = companyRepository.findAllByCompanyName(companyName);
//
//        if (locations.isEmpty()) {
//        	logger.warn("No Company found for companyname: " + companyName);
//            throw new ResourceNotFoundException("No Company found for companyname: " + companyName);
//        } else {
//        	logger.info("Retrieved " + locations.size() + " locations for companyname: " + companyName);
//        }
//
//        return locations;
//    }
    
    private void sendEmail(String recipientEmail, String companyName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("your-email@gmail.com"); // Replace with your email address
        message.setTo(recipientEmail);
        message.setSubject("Company Data Saved");
        message.setText("Dear " + companyName + ",\n\nYour company data has been successfully saved.");

        javaMailSender.send(message);

        System.out.println("Email sent successfully!");
    }
      
    
	// Helper method to convert CompanyDTo to CompanyEntity
    private CompanyEntity convertToEntity(CompanyDto companyDto)
    {
    	return modelMapper.map(companyDto, CompanyEntity.class);
    }

    // Helper method to convert CompanyEntity  to CompanyDTo
    private CompanyDto convertToDTO(CompanyEntity companyEntity) {
        return modelMapper.map(companyEntity, CompanyDto.class);
    } 
 
}
