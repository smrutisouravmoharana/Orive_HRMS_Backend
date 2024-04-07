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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


import com.orive.Procurement.Dto.BidAnalysisDto;
import com.orive.Procurement.Entity.BidAnalysisEntity;
import com.orive.Procurement.Entity.CommitteeListEntity;
import com.orive.Procurement.Entity.CompanyListEntity;
import com.orive.Procurement.Enum.Status;
import com.orive.Procurement.Exceptions.ResourceNotFoundException;
import com.orive.Procurement.Repository.BidAnalysisRepository;
import com.orive.Procurement.Util.PdfUtils;

@Service
public class BidAnalysisService {


	private static final Logger logger= LoggerFactory.getLogger(BidAnalysisService.class);
	
	@Autowired
	private BidAnalysisRepository bidAnalysisRepository;
	
	@Autowired
	private ModelMapper  modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	//create
			public String saveBidAnalysisEntity(
					String location,
					LocalDate date,
					String quotation,
					MultipartFile file,
					String status) {
				
				try {
					BidAnalysisEntity pdfData = bidAnalysisRepository.save(BidAnalysisEntity.builder()
							.location(location)
							.date(date)
							.quotation(quotation)
							.attachment(file != null ? PdfUtils.compressPdf(file.getBytes()) : null)
							.status(status)
			                .build());

			            if (pdfData != null) {
			                return "File uploaded successfully: " + (file != null ? file.getOriginalFilename() : "No file attached");
			            }
					
				}catch (Exception e) {
					// Handle the IOException appropriately (e.g., log it, return an error message)
			        return "Error uploading file: " + e.getMessage();
				}
				
				return null;
			}
				
		
	//Download pdf
	public byte[] downloadPdf(Long bidAnalysisId) {
		 Optional<BidAnalysisEntity> dbPdfData = bidAnalysisRepository.findById(bidAnalysisId);
	    
	    if (dbPdfData.isPresent()) {
	        return PdfUtils.decompressPdf(dbPdfData.get().getAttachment());
	    } else {
	        // Handle the case where the candidate profile is not found
	        return null;
	    }
	}
	
	
	    // Read
	    public List<BidAnalysisDto> getAllBidAnalysis() {
	        List<BidAnalysisEntity> bidAnalysisEntities = bidAnalysisRepository.findAll();
	        logger.info("Retrieved {} BidAnalysis from the database", bidAnalysisEntities.size());
	        return bidAnalysisEntities.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }
	
	
	
//	    //get by BidAnalysisId
//	    public Optional<BidAnalysisDto> getBidAnalysisById(Long bidAnalysisId) {
//	        Optional<BidAnalysisEntity> bidAnalysis = bidAnalysisRepository.findById(bidAnalysisId);
//	        if (bidAnalysis.isPresent()) {
//	            return Optional.of(convertToDTO(bidAnalysis.get()));
//	        } else {
//	            logger.warn("BidAnalysis with ID {} not found", bidAnalysisId);
//	            return Optional.empty();
//	        }
//	    }
	    
	    
//	    //get Company by BidAnalysisId
//	    public BidAnalysisEntity getCompanyByBidAnalysisId(Long bidAnalysisId) {
//	        //get BidAnalysis from database with the help  of CompanyList repository
//	    	BidAnalysisEntity bidAnalysis = bidAnalysisRepository.findById(bidAnalysisId).orElseThrow(() -> new ResourceNotFoundException("BidAnalysis with given id is not found on server !! : " + bidAnalysisId));
//	        ArrayList<CompanyListEntity> companyList = restTemplate.getForObject("http://localhost:8094/companylist/" + bidAnalysis.getBidAnalysisId(), ArrayList.class);
//	        logger.info("{} ", companyList);
//	        bidAnalysis.setCompanyListEntities(companyList);
//	        return bidAnalysis;
//	    }
//	    
//	  //get Committee by BidAnalysisId
//	    public BidAnalysisEntity getCommitteeByBidAnalysisId(Long bidAnalysisId) {
//	        //get BidAnalysis from database with the help  of CommitteeList repository
//	    	BidAnalysisEntity bidAnalysis = bidAnalysisRepository.findById(bidAnalysisId).orElseThrow(() -> new ResourceNotFoundException("BidAnalysis with given id is not found on server !! : " + bidAnalysisId));
//	        ArrayList<CommitteeListEntity> committeeList = restTemplate.getForObject("http://localhost:8094/committeelist/" + bidAnalysis.getBidAnalysisId(), ArrayList.class);
//	        logger.info("{} ", committeeList);
//	        bidAnalysis.setCommitteeListEntities(committeeList);
//	        return bidAnalysis;
//	    }
	    
	    
	    public BidAnalysisEntity getCombinedDataByBidAnalysisId(Long bidAnalysisId) {
	        BidAnalysisEntity bidAnalysis = bidAnalysisRepository.findById(bidAnalysisId)
	                .orElseThrow(() -> new ResourceNotFoundException("BidAnalysis with given id is not found on server !! : " + bidAnalysisId));

	        // Get CompanyListEntities
	        ArrayList<CompanyListEntity> companyList = restTemplate.getForObject(
	                "http://localhost:8094/companylist/" + bidAnalysis.getBidAnalysisId(), ArrayList.class);
	        logger.info("Company List: {}", companyList);
	        bidAnalysis.setCompanyListEntities(companyList);

	        // Get CommitteeListEntities
	        ArrayList<CommitteeListEntity> committeeList = restTemplate.getForObject(
	                "http://localhost:8094/committeelist/" + bidAnalysis.getBidAnalysisId(), ArrayList.class);
	        logger.info("Committee List: {}", committeeList);
	        bidAnalysis.setCommitteeListEntities(committeeList);

	        return bidAnalysis;
	    }
	    
	    
	 // Update list by id
	    public BidAnalysisDto updateBidAnalysis(Long bidAnalysisId, BidAnalysisDto bidAnalysisDto) {
	        Optional<BidAnalysisEntity> existingBidAnalysisOptional = bidAnalysisRepository.findById(bidAnalysisId);
	        if (existingBidAnalysisOptional.isPresent()) {
	        	BidAnalysisEntity existingBidAnalysis = existingBidAnalysisOptional.get();
	        	existingBidAnalysis.setLocation(bidAnalysisDto.getLocation());
                existingBidAnalysis.setDate(bidAnalysisDto.getDate());	          
                existingBidAnalysis.setStatus(bidAnalysisDto.getStatus());
                modelMapper.map(bidAnalysisDto, existingBidAnalysis);
	            BidAnalysisEntity updatedBidAnalysis = bidAnalysisRepository.save(existingBidAnalysis);
	            logger.info("Updated BidAnalysis with ID: {}", updatedBidAnalysis.getBidAnalysisId());
	            return convertToDTO(updatedBidAnalysis);
	        } else {
	            logger.warn("BidAnalysis with ID {} not found for update", bidAnalysisId);
	            return null;
	        }
	    }
	    
	    // Delete
	    public void deleteBidAnalysis(Long bidAnalysisId) {
	    bidAnalysisRepository.deleteById(bidAnalysisId);
	        logger.info("Deleted BidAnalysis with ID: {}", bidAnalysisId);
	    }

	    //count the total BidAnalysis
	    public long countBidAnalysis()
		 {
			 return bidAnalysisRepository.count();
		 }
	    
		// Helper method to convert BidAnalysisDTo to BidAnalysisEntity
	    private BidAnalysisEntity convertToEntity(BidAnalysisDto bidAnalysisDto )
	    {
	    	return modelMapper.map(bidAnalysisDto, BidAnalysisEntity.class);
	    }

	    // Helper method to convert BidAnalysisEntity  to BidAnalysisDTo
	    private BidAnalysisDto convertToDTO(BidAnalysisEntity bidAnalysisEntity ) 
	    {
	        return modelMapper.map(bidAnalysisEntity, BidAnalysisDto.class);
	    } 
}
