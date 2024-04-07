package com.orive.Payroll.Service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.orive.Payroll.Dto.PaySlipGenerateDto;
import com.orive.Payroll.Entity.PaySlipGenerateEntity;
import com.orive.Payroll.Repository.PaySlipGenerateRepository;

@Service
public class PaySlipGenerateService {

private static final Logger logger=LoggerFactory.getLogger(PaySlipGenerateService.class);
	
	@Autowired
	private PaySlipGenerateRepository paySlipGenerateRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	// Create
    public PaySlipGenerateDto createPaySlipGenerate(PaySlipGenerateDto advanceSaleryDto) {
    	PaySlipGenerateEntity  advanceSaleryEntity = convertToEntity(advanceSaleryDto);
    	PaySlipGenerateEntity savedAdvanceSalery = paySlipGenerateRepository.save(advanceSaleryEntity);
        logger.info("Created PaySlip Generate with ID: {}", savedAdvanceSalery.getPaySlipGenerateId());
        return convertToDTO(savedAdvanceSalery);
    }

    // Read
    public List<PaySlipGenerateDto> getAllPaySlipGenerate() {
        List<PaySlipGenerateEntity>advanceSaleryEntities = paySlipGenerateRepository.findAll();
        logger.info("Retrieved {} PaySlip Generate from the database", advanceSaleryEntities.size());
        return advanceSaleryEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    //get by AdvanceSaleryId
    public Optional<PaySlipGenerateDto> getPaySlipGenerateById(String PaySlipGenerateId) {
        Optional<PaySlipGenerateEntity> advanceSalery = paySlipGenerateRepository.findById(PaySlipGenerateId);
        if (advanceSalery.isPresent()) {
            return Optional.of(convertToDTO(advanceSalery.get()));
        } else {
            logger.warn("PaySlip Generate with ID {} not found", PaySlipGenerateId);
            return Optional.empty();
        }
    }
    
    
    //get details by username
    public List<PaySlipGenerateDto> getPaySlipGenerateByUsername(String username) {
        List<PaySlipGenerateEntity> transfersEntities = paySlipGenerateRepository.findByUsername(username);
        return transfersEntities.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
// // Update list by id
//    public PaySlipGenerateDto updatePaySlipGenerate(Long PaySlipGenerateId, PaySlipGenerateDto advanceSaleryDto) {
//        Optional<PaySlipGenerateEntity> existingAdvanceSaleryOptional = paySlipGenerateRepository.findById(PaySlipGenerateId);
//        if (existingAdvanceSaleryOptional.isPresent()) {
//        	PaySlipGenerateEntity existingAdvanceSalery= existingAdvanceSaleryOptional.get();
//        	existingAdvanceSalery.setAdvanceAmount(advanceSaleryDto.getAdvanceAmount());
//            modelMapper.map(advanceSaleryDto, existingAdvanceSaleryOptional);
//            PaySlipGenerateEntity updatedAdvanceSalery = paySlipGenerateRepository.save(existingAdvanceSalery);
//            logger.info("Updated AdvanceSalery with ID: {}", updatedAdvanceSalery.getAdvanceSaleryId());
//            return convertToDTO(updatedAdvanceSalery);
//        } else {
//            logger.warn("AdvanceSalery with ID {} not found for update", PaySlipGenerateId);
//            return null;
//        }
//    }
    
    // Delete
    public void deletePaySlipGenerate(String PaySlipGenerateId) {
    	paySlipGenerateRepository.deleteById(PaySlipGenerateId);
        logger.info("Deleted PaySlip Generate with ID: {}", PaySlipGenerateId);
    }

    //count the total AdvanceSalery
    public long countPaySlipGenerate()
	 {
		 return paySlipGenerateRepository.count();
	 }
    
    public Map<String, Double> getTotalNetSalaryForDepartmentsInMonth(int year, int month) {
        Map<String, Double> netSalaryByDepartment = new HashMap<>();
        List<Object[]> results = paySlipGenerateRepository.getTotalNetSalaryForDepartmentsInMonth(year, month);
        for (Object[] result : results) {
            String department = (String) result[0];
            Double netSalary = (Double) result[1];
            netSalaryByDepartment.put(department, netSalary);
        }
        return netSalaryByDepartment;
    }
    
	// Helper method to convert AdvanceSaleryDTo to AdvanceSaleryEntity
    private PaySlipGenerateEntity convertToEntity(PaySlipGenerateDto advanceSaleryDto)
    {
    	return modelMapper.map(advanceSaleryDto, PaySlipGenerateEntity.class);
    }

 // Helper method to convert AdvanceSaleryEntity  to AdvanceSaleryDTo
    private PaySlipGenerateDto convertToDTO(PaySlipGenerateEntity advanceSaleryEntity) {
        return modelMapper.map(advanceSaleryEntity, PaySlipGenerateDto.class);
    } 
}