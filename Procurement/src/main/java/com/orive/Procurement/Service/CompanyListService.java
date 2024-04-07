package com.orive.Procurement.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.orive.Procurement.Entity.CompanyListEntity;
import com.orive.Procurement.Entity.QuotationListEntity;
import com.orive.Procurement.Exceptions.ResourceNotFoundException;
import com.orive.Procurement.Repository.CompanyListRepository;
import com.orive.Procurement.Repository.QuotationListRepository;

@Service
public class CompanyListService {
	
private  static final Logger logger=LoggerFactory.getLogger(CompanyListService.class);
	
	@Autowired
	private CompanyListRepository companyListRepository;
	

	//create
	 public void saveCompanyList(CompanyListEntity companyList ) {
	        try {
	        	companyListRepository.save(companyList);
	        	logger.info("Company saved successfully: {}", companyList);
	        } catch (Exception e) {
	        	logger.error("Error saving Company: {}", e.getMessage(), e);
	            // Handle the exception as needed
	        }
	    }
	 
	 // Read all CompanyList
	    public List<CompanyListEntity> getCompanyListAllDetails() {
	    	logger.info("Fetching all CompanyList");
	        List<CompanyListEntity> companyList = companyListRepository.findAll();
	        logger.info("Fetched {} Company", companyList.size());
	        return companyList;
	    }
		

	    // Get CompanyList by bidAnalysisId
	    public List<CompanyListEntity> getCompanyListByBidAnalysisId(Long bidAnalysisId) {
	    	logger.info("Fetching CompanyList for bidAnalysisId: {}", bidAnalysisId);
	        List<CompanyListEntity> companyList = companyListRepository.findByBidAnalysisId(bidAnalysisId);
	        logger.info("Fetched {} companyList for bidAnalysisId: {}", companyList.size(), bidAnalysisId);
	        return companyList;
	    }

	    // Get CompanyList by companyListId
	    public CompanyListEntity getCompanyListByCompanyListId(Long companyListId) {
	    	logger.info("Fetching CompanyList for companyListId: {}", companyListId);
	    	CompanyListEntity companyList = companyListRepository.findById(companyListId)
	                .orElseThrow(() -> {
	                	logger.error("CompanyList not found for companyListId: {}", companyListId);
	                    return new ResourceNotFoundException("CompanyList with the given id is not found: " + companyListId);
	                });
	        logger.info("Fetched Company for companyListId: {}", companyListId);
	        return companyList;
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
	    public void deleteCompanyList(Long companyListId) {
	    	companyListRepository.deleteById(companyListId);
	        logger.info("Deleted CompanyList with ID: {}", companyListId);
	    }

}
