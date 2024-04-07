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

import com.orive.Procurement.Dto.PurchaseOrderDto;
import com.orive.Procurement.Entity.PurchaseOrderEntity;
import com.orive.Procurement.Entity.PurchaseOrderListEntity;
import com.orive.Procurement.Entity.QuotationEntity;
import com.orive.Procurement.Entity.QuotationListEntity;
import com.orive.Procurement.Enum.Status;
import com.orive.Procurement.Exceptions.ResourceNotFoundException;
import com.orive.Procurement.Repository.PurchaseOrderRepository;
import com.orive.Procurement.Util.PdfUtils;



@Service
public class PurchaseOrderService {
	
private static final Logger logger= LoggerFactory.getLogger(PurchaseOrderService.class);
	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	private ModelMapper  modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	//create
		public String savePurchaseOrderEntity(
				String quotation,
				String location,
				String vendorName,
				String address,
				String notes,
				String authorizedByName,
				String title,
				MultipartFile fileDocument,
				LocalDate date,
				String status) {
				
				try {
					PurchaseOrderEntity pdfData = purchaseOrderRepository.save(PurchaseOrderEntity.builder()
							.quotation(quotation)
							.location(location)
							.vendorName(vendorName)
							.address(address)
							.notes(notes)
							.authorizedByName(authorizedByName)
							.title(title)
							.signatureAndStamp(fileDocument != null ? PdfUtils.compressPdf(fileDocument.getBytes()) : null)
							.date(date)
							.status(status)
			                .build());

			            if (pdfData != null) {
			                return "Signature uploaded successfully: " + (fileDocument != null ? fileDocument.getOriginalFilename() : "No Signature attached");
			            }
					
				}catch (Exception e) {
					// Handle the IOException appropriately (e.g., log it, return an error message)
			        return "Error uploading file: " + e.getMessage();
				}
				
				return null;
			}
				
			
			
			//Download pdf
			public byte[] downloadPdf(Long purchaseOrderId) {
				 Optional<PurchaseOrderEntity> dbPdfData = purchaseOrderRepository.findById(purchaseOrderId);
			    
			    if (dbPdfData.isPresent()) {
			        return PdfUtils.decompressPdf(dbPdfData.get().getSignatureAndStamp());
			    } else {
			        // Handle the case where the PurchaseOrder profile is not found
			        return null;
			    }
			}

	    // Read
	    public List<PurchaseOrderDto> getAllPurchaseOrder() {
	        List<PurchaseOrderEntity> purchaseOrderEntities = purchaseOrderRepository.findAll();
	        logger.info("Retrieved {} PurchaseOrder from the database", purchaseOrderEntities.size());
	        return purchaseOrderEntities.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }
	    
//	    //get by PurchaseOrderId
//	    public Optional<PurchaseOrderDto> getPurchaseOrderById(Long purchaseOrderId) {
//	        Optional<PurchaseOrderEntity> purchaseOrder = purchaseOrderRepository.findById(purchaseOrderId);
//	        if (purchaseOrder.isPresent()) {
//	            return Optional.of(convertToDTO(purchaseOrder.get()));
//	        } else {
//	            logger.warn("PurchaseOrder with ID {} not found", purchaseOrderId);
//	            return Optional.empty();
//	        }
//	    }
	    
	    
	    //get by PurchaseOrderId
	    public PurchaseOrderEntity getByPurchaseOrderId(Long purchaseOrderId) {
	        //get PurchaseOrder from database with the help  of PurchaseOrderList repository
	    	PurchaseOrderEntity purchaseOrder = purchaseOrderRepository.findById(purchaseOrderId).orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder with given id is not found on server !! : " + purchaseOrderId));
	        

	        ArrayList<PurchaseOrderListEntity> purchaseOrderList = restTemplate.getForObject("http://localhost:8094/purchaseorderlist/" + purchaseOrder.getPurchaseOrderId(), ArrayList.class);
	        logger.info("{} ", purchaseOrderList);
	        purchaseOrder.setPurchaseOrderListEntities(purchaseOrderList);

	        return purchaseOrder;
	    }
	    
	    
	    
	 // Update list by id
	    public PurchaseOrderDto updatePurchaseOrder(Long purchaseOrderId, PurchaseOrderDto purchaseOrderDto) {
	        Optional<PurchaseOrderEntity> existingPurchaseOrderOptional = purchaseOrderRepository.findById(purchaseOrderId);
	        if (existingPurchaseOrderOptional.isPresent()) {
	        	PurchaseOrderEntity existingPurchaseOrder = existingPurchaseOrderOptional.get();
	            existingPurchaseOrder.setVendorName(purchaseOrderDto.getVendorName());
	            existingPurchaseOrder.setLocation(purchaseOrderDto.getLocation());
	            existingPurchaseOrder.setStatus(purchaseOrderDto.getStatus());
	            modelMapper.map(purchaseOrderDto, existingPurchaseOrder);
	            PurchaseOrderEntity updatedPurchaseOrder = purchaseOrderRepository.save(existingPurchaseOrder);
	            logger.info("Updated PurchaseOrder with ID: {}", updatedPurchaseOrder.getPurchaseOrderId());
	            return convertToDTO(updatedPurchaseOrder);
	        } else {
	            logger.warn("PurchaseOrder with ID {} not found for update", purchaseOrderId);
	            return null;
	        }
	    }
	    
	    // Delete
	    public void deletePurchaseOrder(Long purchaseOrderId) {
	    purchaseOrderRepository.deleteById(purchaseOrderId);
	        logger.info("Deleted PurchaseOrder with ID: {}", purchaseOrderId);
	    }

	    //count the total PurchaseOrder
	    public long countPurchaseOrder()
		 {
			 return purchaseOrderRepository.count();
		 }
	    
		// Helper method to convertPurchaseOrderDTo to PurchaseOrderEntity
	    private PurchaseOrderEntity convertToEntity(PurchaseOrderDto purchaseOrderDto )
	    {
	    	return modelMapper.map(purchaseOrderDto, PurchaseOrderEntity.class);
	    }

	    // Helper method to convert PurchaseOrderEntity  to PurchaseOrderDTo
	    private PurchaseOrderDto convertToDTO(PurchaseOrderEntity purchaseOrderEntity ) 
	    {
	        return modelMapper.map(purchaseOrderEntity, PurchaseOrderDto.class);
	    } 

}
