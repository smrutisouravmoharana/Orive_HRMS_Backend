package com.orive.Sale.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Sale.Dto.ClientDto;
import com.orive.Sale.Entity.ClientEntity;
import com.orive.Sale.Repository.ClientRepository;



@Service
public class ClientService {

private static final Logger logger=LoggerFactory.getLogger(ClientService.class);
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public ClientDto createClient(ClientDto clientDto) {
    	ClientEntity  workSheetEntity = convertToEntity(clientDto);
    	ClientEntity savedWorkSheet = clientRepository.save(workSheetEntity);
        logger.info("Created client with ID: {}", savedWorkSheet.getClientId());
        return convertToDTO(savedWorkSheet);
    }

    // Read
    public List<ClientDto> getAllClient() {
        List<ClientEntity> workSheetEntities = clientRepository.findAll();
        logger.info("Retrieved {} client from the database", workSheetEntities.size());
        return workSheetEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by WorkSheetId
    public Optional<ClientDto> getClientId(String clientId) {
        Optional<ClientEntity> workSheet = clientRepository.findById(clientId);
        if (workSheet.isPresent()) {
            return Optional.of(convertToDTO(workSheet.get()));
        } else {
            logger.warn("client with ID {} not found", clientId);
            return Optional.empty();
        }
    }
    
    
    
 // Update list by id
    public ClientDto updateClient(String clientId, ClientDto clientDto) {
        Optional<ClientEntity> existingWorkSheetOptional = clientRepository.findById(clientId);
        if (existingWorkSheetOptional.isPresent()) {
        	ClientEntity existingWorkSheet= existingWorkSheetOptional.get();
        	existingWorkSheet.setStatus(clientDto.getStatus());
        	modelMapper.map(clientDto, existingWorkSheetOptional);
        	ClientEntity updatedWorkSheet = clientRepository.save(existingWorkSheet);
            logger.info("Updated client with ID: {}", updatedWorkSheet.getClientId());
            return convertToDTO(updatedWorkSheet);
        } else {
            logger.warn("client with ID {} not found for update", clientId);
            return null;
        }
    }
    
    // Delete
    public void deleteClient(String clientId) {
    	clientRepository.deleteById(clientId);
        logger.info("Deleted client with ID: {}", clientId);
    }

    //count the total WorkSheet
    public long countClient()
	 {
		 return clientRepository.count();
	 }
    
	// Helper method to convert WorkSheetDTo to WorkSheetEntity
    private ClientEntity convertToEntity(ClientDto clientDto)
    {
    	return modelMapper.map(clientDto, ClientEntity.class);
    }

 // Helper method to convert WorkSheetEntity entity to WorkSheetDTo
    private ClientDto convertToDTO(ClientEntity workSheetEntity) {
        return modelMapper.map(workSheetEntity, ClientDto.class);
    }
}
