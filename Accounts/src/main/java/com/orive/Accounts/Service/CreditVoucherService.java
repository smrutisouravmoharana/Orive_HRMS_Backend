package com.orive.Accounts.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Accounts.Dto.CreditVoucherDto;
import com.orive.Accounts.Entity.CreditVoucherEntity;
import com.orive.Accounts.Repository.CreditVoucherRepository;



@Service
public class CreditVoucherService {

private static final Logger logger= LoggerFactory.getLogger(CreditVoucherService.class);
	
	@Autowired
	private CreditVoucherRepository creditVoucherRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public CreditVoucherDto createCreditVoucher(CreditVoucherDto creditVoucherDto) {
    	CreditVoucherEntity creditVoucherEntity = convertToEntity(creditVoucherDto);
    	CreditVoucherEntity savedCreditVoucher = creditVoucherRepository.save(creditVoucherEntity);
        logger.info("Created CreditVoucher with ID: {}", savedCreditVoucher.getCreditVoucherId());
        return convertToDTO(savedCreditVoucher);
    }

    // Read
    public List<CreditVoucherDto> getAllCreditVoucher() {
        List<CreditVoucherEntity> creditVoucherEntities = creditVoucherRepository.findAll();
        logger.info("Retrieved {} CreditVoucher from the database", creditVoucherEntities.size());
        return creditVoucherEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by CreditVoucherId
    public Optional<CreditVoucherDto> getCreditVoucherById(Long creditVoucherId) {
        Optional<CreditVoucherEntity> creditVoucher = creditVoucherRepository.findById(creditVoucherId);
        if (creditVoucher.isPresent()) {
            return Optional.of(convertToDTO(creditVoucher.get()));
        } else {
            logger.warn("CreditVoucher with ID {} not found", creditVoucherId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public CreditVoucherDto updateCreditVoucher(Long creditVoucherId, CreditVoucherDto creditVoucherDto) {
        Optional<CreditVoucherEntity> existingCreditVoucherOptional = creditVoucherRepository.findById(creditVoucherId);
        if (existingCreditVoucherOptional.isPresent()) {
        	CreditVoucherEntity existingCreditVoucher = existingCreditVoucherOptional.get();
            modelMapper.map(creditVoucherDto, existingCreditVoucherOptional);
            CreditVoucherEntity updatedCreditVoucher = creditVoucherRepository.save(existingCreditVoucher);
            logger.info("Updated CreditVoucher with ID: {}", updatedCreditVoucher.getCreditVoucherId());
            return convertToDTO(updatedCreditVoucher);
        } else {
            logger.warn("CreditVoucher with ID {} not found for update", creditVoucherId);
            return null;
        }
    }
    
    // Delete
    public void deleteCreditVoucher(Long creditVoucherId) {
    	creditVoucherRepository.deleteById(creditVoucherId);
        logger.info("Deleted CreditVoucher with ID: {}", creditVoucherId);
    }

    //count the total CreditVoucher
    public long countCreditVoucher()
	 {
		 return creditVoucherRepository.count();
	 }
    
	// Helper method to convert CreditVoucherDTo to CreditVoucherEntity
    private CreditVoucherEntity convertToEntity(CreditVoucherDto creditVoucherDto )
    {
    	return modelMapper.map(creditVoucherDto, CreditVoucherEntity.class);
    }

    // Helper method to convert CreditVoucherEntity  to CreditVoucherDTo
    private CreditVoucherDto convertToDTO(CreditVoucherEntity creditVoucherEntity) {
        return modelMapper.map(creditVoucherEntity, CreditVoucherDto.class);
    } 
}
