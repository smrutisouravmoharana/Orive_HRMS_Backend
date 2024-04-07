package com.orive.Procurement.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Procurement.Entity.PurchaseOrderListEntity;
import com.orive.Procurement.Exceptions.ResourceNotFoundException;
import com.orive.Procurement.Repository.PurchaseOrderListRepository;


@Service
public class PurchaseOrderListService {

private  static final Logger logger=LoggerFactory.getLogger(PurchaseOrderListService.class);
	
	@Autowired
	private PurchaseOrderListRepository purchaseOrderListRepository;
	
	 public void savePurchaseOrderList(PurchaseOrderListEntity purchaseOrderList ) {
	        try {
	        	purchaseOrderListRepository.save(purchaseOrderList);
	        	logger.info("PurchaseOrderList saved successfully: {}", purchaseOrderList);
	        } catch (Exception e) {
	        	logger.error("Error saving PurchaseOrderList: {}", e.getMessage(), e);
	            // Handle the PurchaseOrderList as needed
	        }
	    }
	 
	 // Read all PurchaseOrderList
	    public List<PurchaseOrderListEntity> getPurchaseOrderListAllDetails() {
	    	logger.info("Fetching all PurchaseOrderList");
	        List<PurchaseOrderListEntity> purchaseOrderList = purchaseOrderListRepository.findAll();
	        logger.info("Fetched {} PurchaseOrderList", purchaseOrderList.size());
	        return purchaseOrderList;
	    }
		

	    // Get PurchaseOrderList by purchaseOrderId
	    public List<PurchaseOrderListEntity> getPurchaseOrderListByPurchaseOrderId(Long purchaseOrderId) {
	    	logger.info("Fetching PurchaseOrderList for purchaseOrderId: {}", purchaseOrderId);
	        List<PurchaseOrderListEntity> purchaseOrderList = purchaseOrderListRepository.findByPurchaseOrderId(purchaseOrderId);
	        logger.info("Fetched {} PurchaseOrderList for purchaseOrderId: {}", purchaseOrderList.size(), purchaseOrderId);
	        return purchaseOrderList;
	    }

	    // Get PurchaseOrderList by purchaseOrderListId
	    public PurchaseOrderListEntity getPurchaseOrderListByPurchaseOrderListId(Long purchaseOrderListId) {
	    	logger.info("Fetching PurchaseOrderList for purchaseOrderListId: {}", purchaseOrderListId);
	    	PurchaseOrderListEntity purchaseOrderList = purchaseOrderListRepository.findById(purchaseOrderListId)
	                .orElseThrow(() -> {
	                	logger.error("PurchaseOrderList not found for purchaseOrderListId: {}", purchaseOrderListId);
	                    return new ResourceNotFoundException("PurchaseOrderList with the given id is not found: " + purchaseOrderListId);
	                });
	        logger.info("Fetched PurchaseOrderList for purchaseOrderListId: {}", purchaseOrderListId);
	        return purchaseOrderList;
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
	    public void deletePurchaseOrderList(Long purchaseOrderListId) {
	    	purchaseOrderListRepository.deleteById(purchaseOrderListId);
	        logger.info("Deleted PurchaseOrderList with ID: {}", purchaseOrderListId);
	    }
}
