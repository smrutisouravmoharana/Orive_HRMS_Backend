package com.orive.Employee.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.orive.Employee.Dto.TransfersDto;
import com.orive.Employee.Entity.TransfersEntity;
import com.orive.Employee.Repository.TransfersRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;



@Service
public class TransfersService {

private static final Logger logger=LoggerFactory.getLogger(TransfersService.class);
	
	@Autowired
	private TransfersRepository transfersRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	// Create
    public TransfersDto createTransfers(TransfersDto transfersDto) {
        TransfersEntity transfersEntity = convertToEntity(transfersDto);
        TransfersEntity savedTransfer = transfersRepository.save(transfersEntity);
        logger.info("Created Transfers with ID: {}", savedTransfer.getTransferId());
        // Send email with the generated token
        sendTransferEmail(savedTransfer);
        return convertToDTO(savedTransfer);
    }

    // Read
    public List<TransfersDto> getAllTransfers() {
        List<TransfersEntity> transfersEntities = transfersRepository.findAll();
        logger.info("Retrieved {} Transfers from the database", transfersEntities.size());
        return transfersEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by TransfersId
    public Optional<TransfersDto> getTransfersById(Long transferId) {
        Optional<TransfersEntity> transfer = transfersRepository.findById(transferId);
        if (transfer.isPresent()) {
            return Optional.of(convertToDTO(transfer.get()));
        } else {
            logger.warn("Transfers with ID {} not found", transferId);
            return Optional.empty();
        }
    }
    
    public List<TransfersDto> getTransfersByUsername(String username) {
        List<TransfersEntity> transfersEntities = transfersRepository.findByUsername(username);
        return transfersEntities.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
 // Update list by id
    public TransfersDto updateTransfers(Long transferId, TransfersDto transfersDto) {
        Optional<TransfersEntity> existingTransferOptional = transfersRepository.findById(transferId);
        if (existingTransferOptional.isPresent()) {
        	TransfersEntity existingTransfer = existingTransferOptional.get();
        	existingTransfer.setEmployeeName(transfersDto.getEmployeeName());
        	existingTransfer.setDepartmentName(transfersDto.getDepartmentName());
        	existingTransfer.setLocationName(transfersDto.getLocationName());
            modelMapper.map(transfersDto, existingTransferOptional);
            TransfersEntity updatedTransfer = transfersRepository.save(existingTransfer);
            logger.info("Updated Transfers with ID: {}", updatedTransfer.getTransferId());
            return convertToDTO(updatedTransfer);
        } else {
            logger.warn("Transfers with ID {} not found for update", transferId);
            return null;
        }
    }
    
    // Delete
    public void deleteTransfers(Long transferId) {
    	transfersRepository.deleteById(transferId);
        logger.info("Deleted Transfers with ID: {}", transferId);
    }

    //count the total Transfers
    public long countTransfers()
	 {
		 return transfersRepository.count();
	 }
    
    private void sendTransferEmail(TransfersEntity transferEntity) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(transferEntity.getEmail());
            helper.setSubject("Employee Transfer - " + transferEntity.getEmployeeName());

            String emailContent = "Dear " + transferEntity.getEmployeeName() + ",\n\n"
                    + "I hope this message finds you well. I am writing to inform you of the transfer of "
                    + transferEntity.getEmployeeName() + " to " + transferEntity.getLocationName() + ", effective "
                    + transferEntity.getTransferDate().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")) + ".\n\n"
                    + transferEntity.getEmployeeName() + " has been an integral part of " + transferEntity.getDepartmentName()
                    + " for [Duration of Employment], contributing significantly to various projects and demonstrating exceptional skills and dedication. The decision for this transfer aligns with "
                    + transferEntity.getDescription() + ".\n\n"
                    + "In " + transferEntity.getLocationName() + ", " + transferEntity.getEmployeeName()
                    + " will be taking on the role of [New Position/Title], where they will continue to excel and bring value to our organization. I have full confidence in [his/her] abilities to adapt quickly and make meaningful contributions in "
                    + transferEntity.getLocationName() + ".\n\n"
                    + transferEntity.getEmployeeName() + " has been briefed on the details of the transfer and is prepared to fulfill any necessary requirements or procedures associated with this move. We kindly request your support and assistance in ensuring a smooth transition for [him/her].\n\n"
                    + "Please find attached [his/her] updated contact information and relevant documents regarding the transfer. Should you have any questions or need further information, please do not hesitate to reach out to me or " + transferEntity.getEmployeeName() + " directly.\n\n"
                    + "We are excited about the opportunities that lie ahead for " + transferEntity.getEmployeeName()
                    + " and are grateful for [his/her] continued dedication to [Company Name].\n\n"
                    + "Thank you for your attention to this matter.\n\n"
                    + "Warm regards,\n\n"
                    + "[Your Name]";

            helper.setText(emailContent);

            javaMailSender.send(message);
        } catch (MessagingException | MailException e) {
            // Handle exception
            e.printStackTrace();
            // You might want to throw an exception or log the error here
        }
    }
    
	// Helper method to convert TransfersDTo to TransfersEntity
    private TransfersEntity convertToEntity(TransfersDto transfersDto)
    {
    	return modelMapper.map(transfersDto, TransfersEntity.class);
    }

    // Helper method to convert TransfersEntity  to TransfersDTo
    private TransfersDto convertToDTO(TransfersEntity transfersEntity) {
        return modelMapper.map(transfersEntity, TransfersDto.class);
    } 
}
