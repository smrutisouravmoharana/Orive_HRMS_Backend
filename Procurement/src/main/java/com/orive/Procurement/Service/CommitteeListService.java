package com.orive.Procurement.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.orive.Procurement.Entity.CommitteeListEntity;
import com.orive.Procurement.Exceptions.ResourceNotFoundException;
import com.orive.Procurement.Repository.CommitteeListRepository;
import com.orive.Procurement.Util.PhotoUtils;


@Service
public class CommitteeListService {

private  static final Logger logger=LoggerFactory.getLogger(CommitteeListService.class);
	
	@Autowired
	private CommitteeListRepository committeeListRepository;
	
	
	//create	
	public String saveCommitteeListEntity(
			Long bidAnalysisId,
			String name,
			MultipartFile filePhoto,
			LocalDate date) {
				
	try {
		CommitteeListEntity imageData = committeeListRepository.save(CommitteeListEntity.builder()
				
			.bidAnalysisId(bidAnalysisId)
			.name(name)
			.signature(filePhoto != null ? PhotoUtils.compressImage(filePhoto.getBytes()) : null)
			.date(date)
			.build());

			 if (imageData != null) {
			        return "signature uploaded successfully: " + (filePhoto != null ? filePhoto.getOriginalFilename() : "No Signature attached");
			        }
					
		    }catch (Exception e) {
					// Handle the IOException appropriately (e.g., log it, return an error message)
			        return "Error uploading Signature: " + e.getMessage();
				}
				
				return null;
			}
				
			
			
			//Download signature
			public byte[] downloadImage(Long bidAnalysisId) {
				 Optional<CommitteeListEntity> dbImageData = committeeListRepository.findById(bidAnalysisId);
			    
			    if (dbImageData.isPresent()) {
			        return PhotoUtils.decompressImage(dbImageData.get().getSignature());
			    } else {
			        // Handle the case where the Quotation profile is not found
			        return null;
			    }
			}
	
	
	 // Read all CommitteeList
	    public List<CommitteeListEntity> getCommitteeListAllDetails() {
	    	logger.info("Fetching all CommitteeList");
	        List<CommitteeListEntity> committeeList = committeeListRepository.findAll();
	        logger.info("Fetched {} Committee", committeeList.size());
	        return committeeList;
	    }
		

	    // Get CommitteeList by bidAnalysisId
	    public List<CommitteeListEntity> getCommitteeListByBidAnalysisId(Long bidAnalysisId) {
	    	logger.info("Fetching CommitteeList for bidAnalysisId: {}", bidAnalysisId);
	        List<CommitteeListEntity> committeeList = committeeListRepository.findByBidAnalysisId(bidAnalysisId);
	        logger.info("Fetched {} CommitteeList for bidAnalysisId: {}", committeeList.size(), bidAnalysisId);
	        return committeeList;
	    }

	    // Get CommitteeList by committeeListId
	    public CommitteeListEntity getCommitteeListByCommitteeListId(Long committeeListId) {
	    	logger.info("Fetching CommitteeList for committeeListId: {}", committeeListId);
	    	CommitteeListEntity committeeList = committeeListRepository.findById(committeeListId)
	                .orElseThrow(() -> {
	                	logger.error("CommitteeList not found for committeeListId: {}", committeeListId);
	                    return new ResourceNotFoundException("CommitteeList with the given id is not found: " + committeeListId);
	                });
	        logger.info("Fetched Committee for committeeListId: {}", committeeListId);
	        return committeeList;
	    }
	    
	    
	 // Update list by id
//	    public QuotationListEntity updateQuotationList(Long quotationListId, QuotationListEntity updatedQuotationList) {
//	    	QuotationListEntity existingQuotationList = quotationListRepository.findById(quotationListId)
//	                .orElse(null);
//
//	        if (existingQuotationList != null) {
//	            ExpenseListEntity updatedLocationEntity = expenseListRepository.save(existingLocation);
//	            logger.info("Updated QuotationList with ID: {}", updatedLocationEntity.getExpenceListId());
//	            return updatedLocationEntity;
//	        } else {
//	            logger.warn("QuotationList with ID {} not found for update", quotationListId);
//	            return null;
//	        }
//	    }
	
	    

	    
	    // Delete
	    public void deleteCommitteeList(Long committeeListId) {
	    	committeeListRepository.deleteById(committeeListId);
	        logger.info("Deleted CommitteeList with ID: {}", committeeListId);
	    }
}
