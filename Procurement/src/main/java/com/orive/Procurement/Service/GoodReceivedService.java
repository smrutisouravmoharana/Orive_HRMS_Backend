package com.orive.Procurement.Service;

import java.io.IOException;
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


import com.orive.Procurement.Dto.GoodReceivedDto;
import com.orive.Procurement.Entity.GoodReceivedEntity;
import com.orive.Procurement.Entity.GoodReceivedListEntity;
import com.orive.Procurement.Enum.Status;
import com.orive.Procurement.Exceptions.ResourceNotFoundException;
import com.orive.Procurement.Repository.GoodReceivedRepository;
import com.orive.Procurement.Util.PdfUtils;

@Service
public class GoodReceivedService {
	
private static final Logger logger= LoggerFactory.getLogger(GoodReceivedService.class);
	
	@Autowired
	private GoodReceivedRepository goodReceivedRepository;
	
	@Autowired
	private ModelMapper  modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	
	//create
			public String saveGoodReceivedEntity(
					String purchaseOrder,
					String paymentSource,
					String vendorName,
					LocalDate date,
					String receivedByName,
					String title,
					MultipartFile file,
					String status) {
				
				try {
					GoodReceivedEntity pdfData = goodReceivedRepository.save(GoodReceivedEntity.builder()
							.purchaseOrder(purchaseOrder)
							.paymentSource(paymentSource)
							.vendorName(vendorName)
							.date(date)
							.receivedByName(receivedByName)
							.title(title)
							.signatureAndStamp(file != null ? PdfUtils.compressPdf(file.getBytes()) : null)
							.status(status)
			                .build());

			            if (pdfData != null) {
			                return "Signature uploaded successfully: " + (file != null ? file.getOriginalFilename() : "No Signature attached");
			            }
					
				}catch (Exception e) {
					// Handle the IOException appropriately (e.g., log it, return an error message)
			        return "Error uploading file: " + e.getMessage();
				}
				
				return null;
			}
	
	
		 
//		 //Download Logo
//		 public byte[] downloadImage(String vendorName){
//		        Optional<GoodReceivedEntity> dbImageData = goodReceivedRepository.findByVendorName(vendorName);
//		        byte[] images=PhotoUtils.decompressImage(dbImageData.get().getSignatureAndStamp());
//		        return images;
//		    }
	
			//Download pdf
			public byte[] downloadPdf(String vendorName) {
				Optional<GoodReceivedEntity> dbPdfData = goodReceivedRepository.findByVendorName(vendorName);
			    
			    if (dbPdfData.isPresent()) {
			        return PdfUtils.decompressPdf(dbPdfData.get().getSignatureAndStamp());
			    } else {
			        // Handle the case where the PurchaseOrder profile is not found
			        return null;
			    }
			}
	
	    // Read
	    public List<GoodReceivedDto> getAllGoodReceived() {
	        List<GoodReceivedEntity> goodReceivedEntities = goodReceivedRepository.findAll();
	        logger.info("Retrieved {} GoodReceived from the database", goodReceivedEntities.size());
	        return goodReceivedEntities.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }
	    
	    
	    
	    //get by GoodReceivedId
	    public GoodReceivedEntity getByGoodReceivedId(Long goodReceivedId) {
	        //get GoodReceived from database with the help  of GoodReceivedList repository
	    	GoodReceivedEntity goodReceived = goodReceivedRepository.findById(goodReceivedId).orElseThrow(() -> new ResourceNotFoundException("GoodReceived with given id is not found on server !! : " + goodReceivedId));
	        

	        ArrayList<GoodReceivedListEntity> goodReceivedList = restTemplate.getForObject("http://localhost:8094/goodreceivedlist/" + goodReceived.getGoodReceivedId(), ArrayList.class);
	        logger.info("{} ", goodReceivedList);
	        goodReceived.setGoodReceivedListEntities(goodReceivedList);

	        return goodReceived;
	    }
	    
	    
	 // Update list by id
	    public GoodReceivedDto updateGoodReceived(Long goodReceivedId, GoodReceivedDto goodReceivedDto) {
	        Optional<GoodReceivedEntity> existingGoodReceivedOptional = goodReceivedRepository.findById(goodReceivedId);
	        if (existingGoodReceivedOptional.isPresent()) {
	        	GoodReceivedEntity existingGoodReceived = existingGoodReceivedOptional.get();
	            existingGoodReceived.setVendorName(goodReceivedDto.getVendorName());
	            existingGoodReceived.setPaymentSource(goodReceivedDto.getPaymentSource());
	            existingGoodReceived.setStatus(goodReceivedDto.getStatus());
	            modelMapper.map(goodReceivedDto, existingGoodReceived);
	        	GoodReceivedEntity updatedGoodReceived = goodReceivedRepository.save(existingGoodReceived);
	            logger.info("Updated GoodReceived with ID: {}", updatedGoodReceived.getGoodReceivedId());
	            return convertToDTO(updatedGoodReceived);
	        } else {
	            logger.warn("GoodReceived with ID {} not found for update", goodReceivedId);
	            return null;
	        }
	    }
	    
	    // Delete
	    public void deleteGoodReceived(Long goodReceivedId) {
	    	goodReceivedRepository.deleteById(goodReceivedId);
	        logger.info("Deleted GoodReceived with ID: {}", goodReceivedId);
	    }

	    //count the total GoodReceived
	    public long countGoodReceived()
		 {
			 return goodReceivedRepository.count();
		 }
	    
		// Helper method to convert GoodReceivedDTo to GoodReceivedEntity
	    private GoodReceivedEntity convertToEntity(GoodReceivedDto goodReceivedDto )
	    {
	    	return modelMapper.map(goodReceivedDto, GoodReceivedEntity.class);
	    }

	    // Helper method to convert GoodReceivedEntity  to GoodReceivedDTo
	    private GoodReceivedDto convertToDTO(GoodReceivedEntity goodReceivedEntity ) 
	    {
	        return modelMapper.map(goodReceivedEntity, GoodReceivedDto.class);
	    } 

}
