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


import com.orive.Employee.Dto.TerminationsDto;
import com.orive.Employee.Dto.TransfersDto;
import com.orive.Employee.Entity.TerminationsEntity;
import com.orive.Employee.Entity.TransfersEntity;
import com.orive.Employee.Repository.TerminationsRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class TerminationsService {
	
	private static final Logger logger = LoggerFactory.getLogger(TerminationsService.class);
	
	@Autowired
	private TerminationsRepository terminationsRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
//	// Create
//    public TerminationsDto createTerminations(TerminationsDto terminationsDto) {
//    	TerminationsEntity terminationsEntity = convertToEntity(terminationsDto);
//    	TerminationsEntity savedTermination = terminationsRepository.save(terminationsEntity);
//        logger.info("Created Terminations with ID: {}", savedTermination.getTerminationId());
//        return convertToDTO(savedTermination);
//    }
	
	 public TerminationsDto createTermination(TerminationsDto terminationsDto) {
	        // Check if the employee name already exists
	        Optional<TerminationsEntity> existingTermination = terminationsRepository.findByEmployeeName(terminationsDto.getEmployeeName());
	        if (existingTermination.isPresent()) {
	            // Employee name already exists, handle the error as needed
	            throw new RuntimeException("Termination record for employee '" + terminationsDto.getEmployeeName() + "' already exists");
	        }

	        TerminationsEntity terminationEntity = convertToEntity(terminationsDto);
	        TerminationsEntity savedTermination = terminationsRepository.save(terminationEntity);

	        // Send email
	        sendTerminationEmail(savedTermination);

	        return convertToDTO(savedTermination);
	    }
	

    // Read
    public List<TerminationsDto> getAllTerminations() {
        List<TerminationsEntity> terminationsEntities = terminationsRepository.findAll();
        logger.info("Retrieved {} Terminations from the database", terminationsEntities.size());
        return terminationsEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by TerminationsId
    public Optional<TerminationsDto> getTerminationsById(Long terminationsId) {
        Optional<TerminationsEntity> termination = terminationsRepository.findById(terminationsId);
        if (termination.isPresent()) {
            return Optional.of(convertToDTO(termination.get()));
        } else {
            logger.warn("Terminations with ID {} not found", terminationsId);
            return Optional.empty();
        }
    }
    //get by TerminationsByName
    public Optional<TerminationsDto> getTerminationsByName(String employeeName) {
        Optional<TerminationsEntity> department = terminationsRepository.findByEmployeeName(employeeName);
        if (department.isPresent()) {
            return Optional.of(convertToDTO(department.get()));
        } else {
            logger.warn("Employee with Name {} not found", employeeName);
            return Optional.empty();
        }
    }
    
    //get termination details by username
    public List<TerminationsDto> getTransfersByUsername(String username) {
        List<TerminationsEntity> transfersEntities = terminationsRepository.findByUsername(username);
        return transfersEntities.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
 // Update list by id
    public TerminationsDto updateTerminations(Long terminationsId, TerminationsDto terminationsDto) {
        Optional<TerminationsEntity> existingTerminationsOptional = terminationsRepository.findById(terminationsId);
        if (existingTerminationsOptional.isPresent()) {
        	TerminationsEntity existingTerminations = existingTerminationsOptional.get();
        	existingTerminations.setEmployeeName(terminationsDto.getEmployeeName());
        	existingTerminations.setTerminateDate(terminationsDto.getTerminateDate());
        	existingTerminations.setTerminatedBy(terminationsDto.getTerminatedBy());
            modelMapper.map(terminationsDto, existingTerminations);
            TerminationsEntity updatedTerminations = terminationsRepository.save(existingTerminations);
            logger.info("Updated Terminations with ID: {}", updatedTerminations.getTerminationId());
            return convertToDTO(updatedTerminations);
        } else {
            logger.warn("Terminations with ID {} not found for update", terminationsId);
            return null;
        }
    }
    
    // Delete
    public void deleteTerminations(Long terminationsId) {
    	terminationsRepository.deleteById(terminationsId);
        logger.info("Deleted Terminations with ID: {}", terminationsId);
    }

    //count the total Terminations
    public long countTerminations()
	 {
		 return terminationsRepository.count();
	 }
    
    // Termination email Auto generation
    private void sendTerminationEmail(TerminationsEntity terminationEntity) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(terminationEntity.getEmail());
            helper.setSubject("Termination of Employment - " + terminationEntity.getEmployeeName());

            String emailContent = "Dear " + terminationEntity.getEmployeeName() + ",\n\n"
                    + "I hope this message finds you well. It is with regret that I must inform you of the termination of your employment with Orive Solutions, effective "
                    + terminationEntity.getTerminateDate().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")) + ".\n\n"
                    + "This decision was made after careful consideration of various factors, including: " + terminationEntity.getReasonForTermination() + ".\n\n"
                    + "As per the company policy, your final paycheck will include payment for any accrued but unused vacation days, as well as any other outstanding dues. You will receive this payment by [Specify Payment Method and Date].\n\n"
                    + "Additionally, please be advised of the following:\n\n"
                    + "Return of Company Property: Kindly return all company property, including [list specific items such as keys, access cards, laptops, etc.], by [Return Date].\n\n"
                    + "Health Insurance and Benefits: Information regarding the continuation of health insurance coverage or any other benefits will be provided to you separately.\n\n"
                    + "Exit Interview: We value your feedback. An exit interview has been scheduled on [Exit Interview Date]. This will provide an opportunity for you to share your thoughts and experiences.\n\n"
                    + "We understand that this news may come as a shock, and we are available to answer any questions you may have during this transition period.\n\n"
                    + "We sincerely appreciate the contributions you have made during your time with Orive Solutions, and we wish you all the best in your future endeavors.\n\n"
                    + "Sincerely," + terminationEntity.getTerminatedBy();

            helper.setText(emailContent);

            javaMailSender.send(message);
        } catch (MessagingException | MailException e) {
            // Handle exception
            e.printStackTrace();
            // You might want to throw an exception or log the error here
        }
    }

    
	// Helper method to convert TerminationsDTo to TerminationsEntity
    private TerminationsEntity convertToEntity(TerminationsDto terminationsDto)
    {
    	return modelMapper.map(terminationsDto, TerminationsEntity.class);
    }

    // Helper method to convert TerminationsEntity entity to TerminationsDTo
    private TerminationsDto convertToDTO(TerminationsEntity terminationsEntity) 
    {
        return modelMapper.map(terminationsEntity, TerminationsDto.class);
    } 

}
