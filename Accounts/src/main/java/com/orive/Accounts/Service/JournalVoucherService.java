package com.orive.Accounts.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Accounts.Dto.JournalVoucherDto;
import com.orive.Accounts.Entity.JournalVoucherEntity;
import com.orive.Accounts.Repository.JournalVoucherRepository;

@Service
public class JournalVoucherService {

	 private static final Logger logger= LoggerFactory.getLogger(FinancialYearService.class);
		
	 @Autowired
		private JournalVoucherRepository journalVoucherRepository;
		
		@Autowired
		private ModelMapper modelMapper;
		
		// Create
	    public JournalVoucherDto createJournalVoucher(JournalVoucherDto journalVoucherDto) {
	    	JournalVoucherEntity journalVoucherEntity = convertToEntity(journalVoucherDto);
	    	JournalVoucherEntity savedJournalVoucher = journalVoucherRepository.save(journalVoucherEntity);
	        logger.info("Created JournalVoucher with ID: {}", savedJournalVoucher.getJournalVoucherId());
	        return convertToDTO(savedJournalVoucher);
	    }

	    // Read
	    public List<JournalVoucherDto> getAllJournalVoucher() {
	        List<JournalVoucherEntity> journalVoucherEntities = journalVoucherRepository.findAll();
	        logger.info("Retrieved {} JournalVoucher from the database", journalVoucherEntities.size());
	        return journalVoucherEntities.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }
	    
	    //get by JournalVoucherId
	    public Optional<JournalVoucherDto> getJournalVoucherById(Long journalVoucherId) {
	        Optional<JournalVoucherEntity> journalVoucher = journalVoucherRepository.findById(journalVoucherId);
	        if (journalVoucher.isPresent()) {
	            return Optional.of(convertToDTO(journalVoucher.get()));
	        } else {
	            logger.warn("JournalVoucher with ID {} not found", journalVoucherId);
	            return Optional.empty();
	        }
	    }
	    
	 // Update list by id
	    public JournalVoucherDto updateJournalVoucher(Long journalVoucherId, JournalVoucherDto journalVoucherDto) {
	        Optional<JournalVoucherEntity> existingJournalVoucherOptional = journalVoucherRepository.findById(journalVoucherId);
	        if (existingJournalVoucherOptional.isPresent()) {
	        	JournalVoucherEntity existingJournalVoucher = existingJournalVoucherOptional.get();
	            modelMapper.map(journalVoucherDto, existingJournalVoucherOptional);
	            JournalVoucherEntity updatedJournalVoucher = journalVoucherRepository.save(existingJournalVoucher);
	            logger.info("Updated JournalVoucher with ID: {}", updatedJournalVoucher.getJournalVoucherId());
	            return convertToDTO(updatedJournalVoucher);
	        } else {
	            logger.warn("JournalVoucher with ID {} not found for update",journalVoucherId );
	            return null;
	        }
	    }
	    
	    // Delete
	    public void deleteJournalVoucher(Long journalVoucherId) {
	    	journalVoucherRepository.deleteById(journalVoucherId);
	        logger.info("Deleted JournalVoucher with ID: {}", journalVoucherId);
	    }

	    //count the total JournalVoucher
	    public long countJournalVoucherList()
		 {
			 return journalVoucherRepository.count();
		 }
	    
		// Helper method to convert JournalVoucherDTo to JournalVoucherEntity
	    private JournalVoucherEntity convertToEntity(JournalVoucherDto journalVoucherDto )
	    {
	    	return modelMapper.map(journalVoucherDto, JournalVoucherEntity.class);
	    }

	    // Helper method to convert JournalVoucherEntity  to JournalVoucherDTo
	    private JournalVoucherDto convertToDTO(JournalVoucherEntity journalVoucherEntity) {
	        return modelMapper.map(journalVoucherEntity, JournalVoucherDto.class);
	    } 
}
