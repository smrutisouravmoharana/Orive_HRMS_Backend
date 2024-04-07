package com.orive.Transactions.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.orive.Transactions.Dto.TransferDto;
import com.orive.Transactions.Entity.TransferEntity;
import com.orive.Transactions.Repository.TransferRepository;

@Service
public class TransferService {
	
	private static final Logger logger=LoggerFactory.getLogger(TransferService.class);
	
	@Autowired
	private TransferRepository transferRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public TransferDto createTransfer(TransferDto transferDto) {
    	TransferEntity transferEntity = convertToEntity(transferDto);
    	TransferEntity savedTransfer = transferRepository.save(transferEntity);
        logger.info("Created Transfer with ID: {}", savedTransfer.getTransferId());
        return convertToDTO(savedTransfer);
    }

    // Read
    public List<TransferDto> getAllTransfer() {
        List<TransferEntity> transferEntities = transferRepository.findAll();
        logger.info("Retrieved {} Transfer from the database", transferEntities.size());
        return transferEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by TransferId
    public Optional<TransferDto> getTransferById(Long transferId) {
        Optional<TransferEntity> transfer = transferRepository.findById(transferId);
        if (transfer.isPresent()) {
            return Optional.of(convertToDTO(transfer.get()));
        } else {
            logger.warn("Transfer with ID {} not found", transferId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public TransferDto updateTransfer(Long transferId, TransferDto transferDto) {
        Optional<TransferEntity> existingTransferOptional = transferRepository.findById(transferId);
        if (existingTransferOptional.isPresent()) {
        	TransferEntity existingTransfer = existingTransferOptional.get();
            existingTransfer.setCurrentDepartment(transferDto.getCurrentDepartment());
        	existingTransfer.setCurrentLocation(transferDto.getCurrentLocation());
        	existingTransfer.setCurrentManagersName(transferDto.getCurrentManagersName());
        	existingTransfer.setCurrentPosition(transferDto.getCurrentPosition());
        	existingTransfer.setEmployeeFullName(transferDto.getEmployeeFullName());
        	existingTransfer.setHrRepresentivesName(transferDto.getHrRepresentivesName());
        	existingTransfer.setReasonOfTransfer(transferDto.getReasonOfTransfer());
        	existingTransfer.setNewmanagersName(transferDto.getNewmanagersName());
        	modelMapper.map(transferDto, existingTransferOptional);
            TransferEntity updatedTransfer = transferRepository.save(existingTransfer);
            logger.info("Updated Transfer with ID: {}", updatedTransfer.getTransferId());
            return convertToDTO(updatedTransfer);
        } else {
            logger.warn("Transfer with ID {} not found for update", transferId);
            return null;
        }
    }
    
    // Delete
    public void deleteTransfer(Long transferId) {
    	transferRepository.deleteById(transferId);
        logger.info("Deleted Transfer with ID: {}", transferId);
    }

    //count the total Transfer
    public long countTransfer()
	 {
		 return transferRepository.count();
	 }
    
	// Helper method to convert TransferDTo to TransferEntity
    private TransferEntity convertToEntity(TransferDto transferDto )
    {
    	return modelMapper.map(transferDto, TransferEntity.class);
    }

    // Helper method to convert TransferEntity to TransferDTo
    private TransferDto convertToDTO(TransferEntity transferEntity) 
    {
        return modelMapper.map(transferEntity, TransferDto.class);
    } 	
}
