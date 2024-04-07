package com.orive.loan.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.orive.loan.dto.GrantLoanDto;
import com.orive.loan.entities.GrantLoanEntity;
import com.orive.loan.repositories.GrantLoanRepository;

@Service
public class GrantLoanService {
	
	private static final Logger logger=LoggerFactory.getLogger(GrantLoanService.class);
	
	@Autowired
	private GrantLoanRepository grantLoanRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public GrantLoanDto createGrantLoan(GrantLoanDto grantLoanDto) {
    	GrantLoanEntity grantLoanEntity = convertToEntity(grantLoanDto);
    	
    	 grantLoanEntity.setAmount(grantLoanDto.getAmount());
    	    grantLoanEntity.setInterestPersentage(grantLoanDto.getInterestPersentage());

    	    // Calculate and set the repaymentTotal
    	    double repaymentTotal = (grantLoanEntity.getAmount() * grantLoanEntity.getInterestPersentage() / 100)
    	            + grantLoanEntity.getAmount();
    	    grantLoanEntity.setRepaymentTotal(repaymentTotal);
    	    
    	    //Calculate and set the installment period
    	    double installment = (grantLoanEntity.getRepaymentTotal()/grantLoanEntity.getInstallmentPeriod());
    	    grantLoanEntity.setInstallment(installment);
    	    
    	  //Calculate and set the total Payment cleared
    	    double totalPaymentCleared =(grantLoanEntity.getInstallment() * grantLoanEntity.getInstallmentCleared());
    	    grantLoanEntity.setTotalPaymentCleared(totalPaymentCleared);
    	    
    	GrantLoanEntity savedGrantLoan = grantLoanRepository.save(grantLoanEntity);
        logger.info("Created GrantLoan with ID: {}", savedGrantLoan.getEmployeeName());
        return convertToDTO(savedGrantLoan);
    }

    
    // Read
    public List<GrantLoanDto> getAllGrantLoan() {
        List<GrantLoanEntity> grantLoanEntities = grantLoanRepository.findAll();
        logger.info("Retrieved {} GrantLoan from the database", grantLoanEntities.size());
        return grantLoanEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by GrantLoanId
    public Optional<GrantLoanDto> getGrantLoanById(String grantLoanId) {
        Optional<GrantLoanEntity> grantLoan = grantLoanRepository.findById(grantLoanId);
        if (grantLoan.isPresent()) {
            return Optional.of(convertToDTO(grantLoan.get()));
        } else {
            logger.warn("GrantLoan with ID {} not found", grantLoanId);
            return Optional.empty();
        }
    }
    
    //get details by username
    public List<GrantLoanDto> getGrantLoanByUsername(String username) {
        List<GrantLoanEntity> transfersEntities = grantLoanRepository.findByUsername(username);
        return transfersEntities.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
 // Update list by id
    public GrantLoanDto updateGrantLoan(String grantLoanId, GrantLoanDto grantLoanDto) {
        Optional<GrantLoanEntity> existingGrantLoanOptional = grantLoanRepository.findById(grantLoanId);
        if (existingGrantLoanOptional.isPresent()) {
        	GrantLoanEntity existingGrantLoan = existingGrantLoanOptional.get();
        	existingGrantLoan.setAmount(grantLoanDto.getAmount());
        	existingGrantLoan.setInterestPersentage(grantLoanDto.getInterestPersentage());
        	existingGrantLoan.setInstallmentPeriod(grantLoanDto.getInstallmentPeriod());
        	existingGrantLoan.setInstallmentCleared(grantLoanDto.getInstallmentCleared());
        	existingGrantLoan.setStatus(grantLoanDto.getStatus());
        	modelMapper.map(grantLoanDto, existingGrantLoanOptional);
            GrantLoanEntity updatedGrantLoan = grantLoanRepository.save(existingGrantLoan);
            logger.info("Updated GrantLoan with ID: {}", updatedGrantLoan.getGrantLoanId());
            return convertToDTO(updatedGrantLoan);
        } else {
            logger.warn("GrantLoan with ID {} not found for update", grantLoanId);
            return null;
        }
    }
    
    // Delete
    public void deleteGrantLoan(String grantLoanId) {
    	grantLoanRepository.deleteById(grantLoanId);
        logger.info("Deleted GrantLoan with ID: {}", grantLoanId);
    }

    //count the total GrantLoan
    public long countGrantLoan()
	 {
		 return grantLoanRepository.count();
	 }
    
	// Helper method to convert GrantLoanDTo to GrantLoanEntity
    private GrantLoanEntity convertToEntity(GrantLoanDto grantLoanDto )
    {
    	return modelMapper.map(grantLoanDto, GrantLoanEntity.class);
    }

    // Helper method to convert GrantLoanEntity  to GrantLoanDTo
    private GrantLoanDto convertToDTO(GrantLoanEntity grantLoanEntity) {
        return modelMapper.map(grantLoanEntity, GrantLoanDto.class);
    } 
}
