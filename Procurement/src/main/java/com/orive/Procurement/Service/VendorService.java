package com.orive.Procurement.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Procurement.Dto.VendorDto;
import com.orive.Procurement.Entity.VendorEntity;
import com.orive.Procurement.Repository.VendorRepository;




@Service
public class VendorService {
	
	private static final Logger logger= LoggerFactory.getLogger(VendorService.class);
	
	@Autowired
	private VendorRepository vendorRepository;
	
	@Autowired
	private ModelMapper  modelMapper;
	
	// Create
		 public VendorDto createVendor(VendorDto vendorDto) {
			 VendorEntity vendorEntity = convertToEntity(vendorDto);
			 VendorEntity savedVendor = vendorRepository.save(vendorEntity);
		        logger.info("Created Vendor with ID: {}", savedVendor.getVendorId());
		        return convertToDTO(savedVendor);
		    }

	    // Read
	    public List<VendorDto> getAllVendor() {
	        List<VendorEntity> vendorEntities = vendorRepository.findAll();
	        logger.info("Retrieved {} Vendor from the database", vendorEntities.size());
	        return vendorEntities.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }
	    
	    //get by VendorId
	    public Optional<VendorDto> getVendorById(Long vendorId) {
	        Optional<VendorEntity> vendor = vendorRepository.findById(vendorId);
	        if (vendor.isPresent()) {
	            return Optional.of(convertToDTO(vendor.get()));
	        } else {
	            logger.warn("Vendor with ID {} not found", vendorId);
	            return Optional.empty();
	        }
	    }
	    
	 // Update list by id
	    public VendorDto updateVendor(Long vendorId, VendorDto vendorDto) {
	        Optional<VendorEntity> existingVendorOptional = vendorRepository.findById(vendorId);
	        if (existingVendorOptional.isPresent()) {
	        	VendorEntity existingVendor = existingVendorOptional.get();
	        	existingVendor.setVendorName(vendorDto.getVendorName());
	        	existingVendor.setMobileNo(vendorDto.getMobileNo());
	        	existingVendor.setEmailAddress(vendorDto.getEmailAddress());
	        	existingVendor.setStatus(vendorDto.getStatus());
	        	modelMapper.map(vendorDto, existingVendorOptional);
	            VendorEntity updatedVendor = vendorRepository.save(existingVendor);
	            logger.info("Updated Vendor with ID: {}", updatedVendor.getVendorId());
	            return convertToDTO(updatedVendor);
	        } else {
	            logger.warn("Vendor with ID {} not found for update", vendorId);
	            return null;
	        }
	    }
	    
	    // Delete
	    public void deleteVendor(Long vendorId) {
	    vendorRepository.deleteById(vendorId);
	        logger.info("Deleted Vendor with ID: {}", vendorId);
	    }

	    //count the total Vendor
	    public long countVendor()
		 {
			 return vendorRepository.count();
		 }
	    
		// Helper method to convert VendorDTo to VendorEntity
	    private VendorEntity convertToEntity(VendorDto vendorDto )
	    {
	    	return modelMapper.map(vendorDto, VendorEntity.class);
	    }

	    // Helper method to convert VendorEntity  to VendorDTo
	    private VendorDto convertToDTO(VendorEntity vendorEntity ) 
	    {
	        return modelMapper.map(vendorEntity, VendorDto.class);
	    } 

}
