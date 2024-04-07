package com.orive.Procurement.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Procurement.Entity.QuotationListEntity;
import com.orive.Procurement.Exceptions.ResourceNotFoundException;
import com.orive.Procurement.Repository.QuotationListRepository;


@Service
public class QuotationListService {
	
private  static final Logger logger=LoggerFactory.getLogger(QuotationListService.class);
	
	@Autowired
	private QuotationListRepository quotationListRepository;
	
	 public void saveQuotationList(QuotationListEntity quotationList) {
	        try {
	        	quotationListRepository.save(quotationList);
	        	logger.info("Quotation saved successfully: {}", quotationList);
	        } catch (Exception e) {
	        	logger.error("Error saving Quotation: {}", e.getMessage(), e);
	            // Handle the exception as needed
	        }
	    }
	 
	 // Read all QuotationList
	    public List<QuotationListEntity> getQuotationListAllDetails() {
	    	logger.info("Fetching all QuotationList");
	        List<QuotationListEntity> quotationList = quotationListRepository.findAll();
	        logger.info("Fetched {} Quotation", quotationList.size());
	        return quotationList;
	    }
		

	    // Get QuotationList by quotationId
	    public List<QuotationListEntity> getQuotationListByQuotationId(Long quotationId) {
	    	logger.info("Fetching QuotationList for quotationId: {}", quotationId);
	        List<QuotationListEntity> quotationList = quotationListRepository.findByQuotationId(quotationId);
	        logger.info("Fetched {} quotationList for quotationId: {}", quotationList.size(), quotationId);
	        return quotationList;
	    }

	    // Get QuotationList by quotationListId
	    public QuotationListEntity getQuotationListByquotationListId(Long quotationListId) {
	    	logger.info("Fetching QuotationList for quotationListId: {}", quotationListId);
	    	QuotationListEntity quotationList = quotationListRepository.findById(quotationListId)
	                .orElseThrow(() -> {
	                	logger.error("QuotationList not found for quotationListId: {}", quotationListId);
	                    return new ResourceNotFoundException("QuotationList with the given id is not found: " + quotationListId);
	                });
	        logger.info("Fetched quotation for quotationListId: {}", quotationListId);
	        return quotationList;
	    }
	    
	    
	 // Update list by id
//	    public QuotationListEntity updateQuotationList(Long quotationListId, QuotationListEntity updatedQuotationList) {
//	    	QuotationListEntity existingQuotationList = quotationListRepository.findById(quotationListId)
//	                .orElse(null);
//
//	        if (existingQuotationList != null) {
//	        	existingQuotationList.setname(updatedLocation.getPurchaseDate());
//	        	existingQuotationList.setPurchasedBy(updatedLocation.getPurchasedBy());
//	        	existingQuotationList.setDescription(updatedLocation.getDescription());
//	        	existingQuotationList.setAmount(updatedLocation.getAmount());
//
//	            ExpenseListEntity updatedLocationEntity = expenseListRepository.save(existingLocation);
//	            logger.info("Updated QuotationList with ID: {}", updatedLocationEntity.getExpenceListId());
//	            return updatedLocationEntity;
//	        } else {
//	            logger.warn("QuotationList with ID {} not found for update", quotationListId);
//	            return null;
//	        }
//	    }
	
	    

	    
	    // Delete
	    public void deleteQuotationList(Long quotationListId) {
	    	quotationListRepository.deleteById(quotationListId);
	        logger.info("Deleted QuotationList with ID: {}", quotationListId);
	    }

}
