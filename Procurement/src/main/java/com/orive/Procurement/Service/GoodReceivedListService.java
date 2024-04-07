package com.orive.Procurement.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Procurement.Entity.GoodReceivedListEntity;
import com.orive.Procurement.Exceptions.ResourceNotFoundException;
import com.orive.Procurement.Repository.GoodReceivedListRepository;



@Service
public class GoodReceivedListService {

private  static final Logger logger=LoggerFactory.getLogger(GoodReceivedListService.class);
	
	@Autowired
	private GoodReceivedListRepository goodReceivedListRepository;
	
	//create method
	 public void saveGoodReceivedList(GoodReceivedListEntity goodReceivedList) {
	        try {
	        	goodReceivedListRepository.save(goodReceivedList);
	        	logger.info("GoodReceived saved successfully: {}", goodReceivedList);
	        } catch (Exception e) {
	        	logger.error("Error saving GoodReceived: {}", e.getMessage(), e);
	            // Handle the exception as needed
	        }
	    }
	 
	 // Read all GoodReceivedList
	    public List<GoodReceivedListEntity> getGoodReceivedListAllDetails() {
	    	logger.info("Fetching all GoodReceivedList");
	        List<GoodReceivedListEntity> goodReceivedList = goodReceivedListRepository.findAll();
	        logger.info("Fetched {} GoodReceivedList", goodReceivedList.size());
	        return goodReceivedList;
	    }
		

	    // Get GoodReceivedList by goodReceivedId
	    public List<GoodReceivedListEntity> getGoodReceivedListByGoodReceivedId(Long goodReceivedId) {
	    	logger.info("Fetching GoodReceivedList for goodReceivedId: {}", goodReceivedId);
	        List<GoodReceivedListEntity> goodReceivedList = goodReceivedListRepository.findByGoodReceivedId(goodReceivedId);
	        logger.info("Fetched {} GoodReceivedList for goodReceivedId: {}", goodReceivedList.size(), goodReceivedId);
	        return goodReceivedList;
	    }

	    // Get GoodReceivedList by goodReceivedListId
	    public GoodReceivedListEntity getGoodReceivedListByGoodReceivedListId(Long goodReceivedListId) {
	    	logger.info("Fetching GoodReceivedList for goodReceivedListId: {}", goodReceivedListId);
	    	GoodReceivedListEntity goodReceivedList = goodReceivedListRepository.findById(goodReceivedListId)
	                .orElseThrow(() -> {
	                	logger.error("GoodReceivedList not found for goodReceivedListId: {}", goodReceivedListId);
	                    return new ResourceNotFoundException("goodReceivedList with the given id is not found: " + goodReceivedListId);
	                });
	        logger.info("Fetched GoodReceived for goodReceivedListId: {}", goodReceivedListId);
	        return goodReceivedList;
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
	    public void deleteGoodReceivedList(Long goodReceivedListId) {
	    	goodReceivedListRepository.deleteById(goodReceivedListId);
	        logger.info("Deleted GoodReceivedList with ID: {}", goodReceivedListId);
	    }
}
