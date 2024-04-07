package com.orive.Procurement.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


import com.orive.Procurement.Dto.QuotationDto;
import com.orive.Procurement.Entity.BidAnalysisEntity;
import com.orive.Procurement.Entity.QuotationEntity;
import com.orive.Procurement.Entity.QuotationListEntity;
import com.orive.Procurement.Enum.Status;
import com.orive.Procurement.Exceptions.ResourceNotFoundException;
import com.orive.Procurement.Repository.QuotationRepository;
import com.orive.Procurement.Util.PdfUtils;

@Service
public class QuotationService {

	private static final Logger logger = LoggerFactory.getLogger(QuotationService.class);
	
	@Autowired
	private QuotationRepository quotationRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	//create
		public String saveQuotationEntity(
				String nameOfCompany,
				String address,
				Long pinOrEquivalent,
				LocalDate expectedDateOfDelivery,
				String placeOfDelivery,
				MultipartFile fileDocument,
				LocalDate date,
				String status) {
			
			try {
				QuotationEntity pdfData = quotationRepository.save(QuotationEntity.builder()
						.nameOfCompany(nameOfCompany)
						.address(address)
						.pinOrEquivalent(pinOrEquivalent)
						.expectedDateOfDelivery(expectedDateOfDelivery)
						.placeOfDelivery(placeOfDelivery)
						.signatureAndStamp(fileDocument != null ? PdfUtils.compressPdf(fileDocument.getBytes()) : null)
						.date(date)
						.status(status)
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
		public byte[] downloadPdf(Long quotationId) {
			 Optional<QuotationEntity> dbPdfData = quotationRepository.findById(quotationId);
		    
		    if (dbPdfData.isPresent()) {
		        return PdfUtils.decompressPdf(dbPdfData.get().getSignatureAndStamp());
		    } else {
		        // Handle the case where the Quotation profile is not found
		        return null;
		    }
		}
		
	    // Read
	    public List<QuotationDto> getAllQuotation() {
	        List<QuotationEntity> quotationEntities = quotationRepository.findAll();
	        logger.info("Retrieved {} Quotation from the database", quotationEntities.size());
	        return quotationEntities.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }
	    

	    
	    
	    //get by QuotationId
	    public QuotationEntity getByQuotationId(Long quotationId) {
	        //get Quotation from database with the help  of QuotationList repository
	    	QuotationEntity quotation = quotationRepository.findById(quotationId).orElseThrow(() -> new ResourceNotFoundException("Quotation with given id is not found on server !! : " + quotationId));
	        

	        ArrayList<QuotationListEntity> quotationList = restTemplate.getForObject("http://localhost:8094/quotationlist/" + quotation.getQuotationId(), ArrayList.class);
	        logger.info("{} ", quotationList);
	        quotation.setQuotationListEntities(quotationList);

	        return quotation;
	    }
	    
	    
	 // Update QuotationEntity by quotationId using QuotationDto
	    public QuotationDto updateQuotationEntity(Long quotationId, QuotationDto updatedQuotationDto) {
	        try {
	            Optional<QuotationEntity> optionalQuotationEntity = quotationRepository.findById(quotationId);

	            if (optionalQuotationEntity.isPresent()) {
	                QuotationEntity existingQuotation = optionalQuotationEntity.get();

	                // Update the fields with the values from the updatedQuotationDto
	                existingQuotation.setStatus(updatedQuotationDto.getStatus());
	                // Save the updated QuotationEntity
	                QuotationEntity savedQuotation = quotationRepository.save(existingQuotation);

	                if (savedQuotation != null) {
	                    return convertToDTO(savedQuotation); // Convert the updated entity to QuotationDto and return
	                }
	            } else {
	                // You can throw an exception or return null based on your design
	                throw new ResourceNotFoundException("Quotation not found with ID: " + quotationId);
	            }
	        } catch (Exception e) {
	            // Handle the exception appropriately (e.g., log it, return an error message)
	            throw new RuntimeException("Error updating quotation: " + e.getMessage());
	        }
			return updatedQuotationDto;
	    }
	    
	    // Delete
	    public void deleteQuotation(Long quotationId) {
	    	quotationRepository.deleteById(quotationId);
	        logger.info("Deleted Quotation with ID: {}", quotationId);
	    }

	    //count the total Quotation
	    public long countQuotation()
		 {
			 return quotationRepository.count();
		 }
	    
		// Helper method to convert QuotationDTo to QuotationEntity
	    private QuotationEntity convertToEntity(QuotationDto quotationDto)
	    {
	    	return modelMapper.map(quotationDto, QuotationEntity.class);
	    }

	    // Helper method to convert QuotationEntity to QuotationDTo
	    private QuotationDto convertToDTO(QuotationEntity  quotationEntity) {
	        return modelMapper.map(quotationEntity, QuotationDto.class);
	    } 
	
}
