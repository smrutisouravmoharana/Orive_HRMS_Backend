package com.orive.Accounts.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Accounts.Dto.ContraVoucherDto;
import com.orive.Accounts.Entity.ContraVoucherEntity;
import com.orive.Accounts.Repository.ContraVoucherRepository;

@Service
public class ContraVoucherService {

private static final Logger logger= LoggerFactory.getLogger(ContraVoucherService.class);
	
	@Autowired
	private ContraVoucherRepository contraVoucherRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public ContraVoucherDto createContraVoucher(ContraVoucherDto contraVoucherDto) {
    	ContraVoucherEntity contraVoucherEntity = convertToEntity(contraVoucherDto);
    	ContraVoucherEntity savedContraVoucher = contraVoucherRepository.save(contraVoucherEntity);
        logger.info("Created ContraVoucher with ID: {}", savedContraVoucher.getReversedAccountHead());
        return convertToDTO(savedContraVoucher);
    }

    // Read
    public List<ContraVoucherDto> getAllContraVoucher() {
        List<ContraVoucherEntity> contraVoucherEntities = contraVoucherRepository.findAll();
        logger.info("Retrieved {} ContraVoucher from the database", contraVoucherEntities.size());
        return contraVoucherEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by ContraVoucherId
    public Optional<ContraVoucherDto> getContraVoucherById(Long contraVoucherId) {
        Optional<ContraVoucherEntity> contraVoucher = contraVoucherRepository.findById(contraVoucherId);
        if (contraVoucher.isPresent()) {
            return Optional.of(convertToDTO(contraVoucher.get()));
        } else {
            logger.warn("ContraVoucher with ID {} not found", contraVoucherId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public ContraVoucherDto updateContraVoucher(Long contraVoucherId, ContraVoucherDto contraVoucherDto) {
        Optional<ContraVoucherEntity> existingContraVoucherOptional = contraVoucherRepository.findById(contraVoucherId);
        if (existingContraVoucherOptional.isPresent()) {
        	ContraVoucherEntity existingContraVoucher = existingContraVoucherOptional.get();
            modelMapper.map(contraVoucherDto, existingContraVoucherOptional);
            ContraVoucherEntity updatedContraVoucher = contraVoucherRepository.save(existingContraVoucher);
            logger.info("Updated ContraVoucher with ID: {}", updatedContraVoucher.getContraVoucherId());
            return convertToDTO(updatedContraVoucher);
        } else {
            logger.warn("ContraVoucher with ID {} not found for update", contraVoucherId);
            return null;
        }
    }
    
    // Delete
    public void deleteContraVoucher(Long contraVoucherId) {
    	contraVoucherRepository.deleteById(contraVoucherId);
        logger.info("Deleted ContraVoucher with ID: {}", contraVoucherId);
    }

    //count the total ContraVoucher
    public long countContraVoucher()
	 {
		 return contraVoucherRepository.count();
	 }
    
	// Helper method to convert ContraVoucherDTo to ContraVoucherEntity
    private ContraVoucherEntity convertToEntity(ContraVoucherDto contraVoucherDto )
    {
    	return modelMapper.map(contraVoucherDto, ContraVoucherEntity.class);
    }

    // Helper method to convert ContraVoucherEntity  to ContraVoucherDTo
    private ContraVoucherDto convertToDTO(ContraVoucherEntity contraVoucherEntity) {
        return modelMapper.map(contraVoucherEntity, ContraVoucherDto.class);
    } 
}
