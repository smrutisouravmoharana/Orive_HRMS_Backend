package com.orive.Accounts.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Accounts.Dto.DebitVoucherDto;
import com.orive.Accounts.Entity.DebitVoucherEntity;
import com.orive.Accounts.Repository.DebitVoucherRepository;



@Service
public class DebitVoucherService {

    private static final Logger logger= LoggerFactory.getLogger(DebitVoucherService.class);
	
	@Autowired
	private DebitVoucherRepository debitVoucherRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public DebitVoucherDto createDebitVoucher(DebitVoucherDto debitVoucherDto) {
    	DebitVoucherEntity debitVoucherEntity = convertToEntity(debitVoucherDto);
    	DebitVoucherEntity savedDebitVoucher = debitVoucherRepository.save(debitVoucherEntity);
        logger.info("Created DebitVoucher with ID: {}", savedDebitVoucher.getDebitVoucherId());
        return convertToDTO(savedDebitVoucher);
    }

    // Read
    public List<DebitVoucherDto> getAllDebitVoucher() {
        List<DebitVoucherEntity> debitVoucherEntities = debitVoucherRepository.findAll();
        logger.info("Retrieved {} DebitVoucher from the database", debitVoucherEntities.size());
        return debitVoucherEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by DebitVoucherId
    public Optional<DebitVoucherDto> getDebitVoucherById(Long debitVoucherId) {
        Optional<DebitVoucherEntity> debitVoucher = debitVoucherRepository.findById(debitVoucherId);
        if (debitVoucher.isPresent()) {
            return Optional.of(convertToDTO(debitVoucher.get()));
        } else {
            logger.warn("DebitVoucher with ID {} not found", debitVoucherId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public DebitVoucherDto updateDebitVoucher(Long debitVoucherId, DebitVoucherDto debitVoucherDto) {
        Optional<DebitVoucherEntity> existingDebitVoucherOptional = debitVoucherRepository.findById(debitVoucherId);
        if (existingDebitVoucherOptional.isPresent()) {
        	DebitVoucherEntity existingDebitVoucher = existingDebitVoucherOptional.get();
            modelMapper.map(debitVoucherDto, existingDebitVoucherOptional);
            DebitVoucherEntity updatedDebitVoucher = debitVoucherRepository.save(existingDebitVoucher);
            logger.info("Updated DebitVoucher with ID: {}", updatedDebitVoucher.getDebitVoucherId());
            return convertToDTO(updatedDebitVoucher);
        } else {
            logger.warn("DebitVoucher with ID {} not found for update", debitVoucherId);
            return null;
        }
    }
    
    // Delete
    public void deleteDebitVoucher(Long debitVoucherId) {
    	debitVoucherRepository.deleteById(debitVoucherId);
        logger.info("Deleted DebitVoucher with ID: {}", debitVoucherId);
    }

    //count the total DebitVoucher
    public long countDebitVoucher()
	 {
		 return debitVoucherRepository.count();
	 }
    
	// Helper method to convert DebitVoucherDTo to DebitVoucherEntity
    private DebitVoucherEntity convertToEntity(DebitVoucherDto debitVoucherDto )
    {
    	return modelMapper.map(debitVoucherDto, DebitVoucherEntity.class);
    }

    // Helper method to convert DebitVoucherEntity  to DebitVoucherDTo
    private DebitVoucherDto convertToDTO(DebitVoucherEntity debitVoucherEntity) {
        return modelMapper.map(debitVoucherEntity, DebitVoucherDto.class);
    } 
}
