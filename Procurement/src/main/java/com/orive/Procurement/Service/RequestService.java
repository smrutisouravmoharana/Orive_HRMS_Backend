package com.orive.Procurement.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Procurement.Dto.RequestDto;
import com.orive.Procurement.Entity.RequestEntity;
import com.orive.Procurement.Repository.RequestRepository;

@Service
public class RequestService {
	
	private static final Logger logger= LoggerFactory.getLogger(RequestService.class);
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private ModelMapper  modelMapper;
	
	// Create
		 public RequestDto createRequest(RequestDto requestDto) {
			 RequestEntity requestEntity = convertToEntity(requestDto);
			 RequestEntity savedRequest = requestRepository.save(requestEntity);
		        logger.info("Created Request with ID: {}", savedRequest.getRequestId());
		        return convertToDTO(savedRequest);
		    }

	    // Read
	    public List<RequestDto> getAllRequest() {
	        List<RequestEntity> requestEntities = requestRepository.findAll();
	        logger.info("Retrieved {} Request from the database", requestEntities.size());
	        return requestEntities.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }
	    
	    //get by RequestId
	    public Optional<RequestDto> getRequestById(Long requestId) {
	        Optional<RequestEntity> request = requestRepository.findById(requestId);
	        if (request.isPresent()) {
	            return Optional.of(convertToDTO(request.get()));
	        } else {
	            logger.warn("Request with ID {} not found", requestId);
	            return Optional.empty();
	        }
	    }
	    
	 // Update list by id
	    public RequestDto updateRequest(Long requestId, RequestDto requestDto) {
	        Optional<RequestEntity> existingRequestOptional = requestRepository.findById(requestId);
	        if (existingRequestOptional.isPresent()) {
	        	RequestEntity existingRequest = existingRequestOptional.get();
	            existingRequest.setRequestingPerson(requestDto.getRequestingPerson());
	        	existingRequest.setExpectedTimeToHaveTheGoodStarts(requestDto.getExpectedTimeToHaveTheGoodStarts());
	        	existingRequest.setExpectedTimeToHaveTheGoodEnds(requestDto.getExpectedTimeToHaveTheGoodEnds());
	        	existingRequest.setStatus(requestDto.getStatus());
	        	modelMapper.map(requestDto, existingRequestOptional);
	            RequestEntity updatedRequest = requestRepository.save(existingRequest);
	            logger.info("Updated Request with ID: {}", updatedRequest.getRequestId());
	            return convertToDTO(updatedRequest);
	        } else {
	            logger.warn("Request with ID {} not found for update", requestId);
	            return null;
	        }
	    }
	    
	    // Delete
	    public void deleteRequest(Long requestId) {
	    requestRepository.deleteById(requestId);
	        logger.info("Deleted Request with ID: {}", requestId);
	    }

	    //count the total Request
	    public long countRequest()
		 {
			 return requestRepository.count();
		 }
	    
		// Helper method to convert RequestDTo to RequestEntity
	    private RequestEntity convertToEntity(RequestDto requestDto )
	    {
	    	return modelMapper.map(requestDto, RequestEntity.class);
	    }

	    // Helper method to convert RequestEntity  to RequestDTo
	    private RequestDto convertToDTO(RequestEntity requestEntity ) 
	    {
	        return modelMapper.map(requestEntity, RequestDto.class);
	    } 
}
