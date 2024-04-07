package com.orive.Sale.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Sale.Dto.RevenueDto;
import com.orive.Sale.Entity.RevenueEntity;
import com.orive.Sale.Repository.RevenueRepository;



@Service
public class RevenueService {

private static final Logger logger=LoggerFactory.getLogger(RevenueService.class);
	
	@Autowired
	private RevenueRepository revenueRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public RevenueDto createRevenue(RevenueDto revenueDto) {
    	RevenueEntity  workSheetEntity = convertToEntity(revenueDto);
    	RevenueEntity savedWorkSheet = revenueRepository.save(workSheetEntity);
        logger.info("Created Revenue with ID: {}", savedWorkSheet.getRevenueId());
        return convertToDTO(savedWorkSheet);
    }

    // Read
    public List<RevenueDto> getAllRevenue() {
        List<RevenueEntity> workSheetEntities = revenueRepository.findAll();
        logger.info("Retrieved {} Revenue from the database", workSheetEntities.size());
        return workSheetEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by WorkSheetId
    public Optional<RevenueDto> getRevenueId(Long revenueId) {
        Optional<RevenueEntity> workSheet = revenueRepository.findById(revenueId);
        if (workSheet.isPresent()) {
            return Optional.of(convertToDTO(workSheet.get()));
        } else {
            logger.warn("Revenue with ID {} not found", revenueId);
            return Optional.empty();
        }
    }
    
    
    
 // Update list by id
    public RevenueDto updateRevenue(Long revenueId, RevenueDto revenueDto) {
        Optional<RevenueEntity> existingWorkSheetOptional = revenueRepository.findById(revenueId);
        if (existingWorkSheetOptional.isPresent()) {
        	RevenueEntity existingWorkSheet= existingWorkSheetOptional.get();
        	existingWorkSheet.setStatus(revenueDto.getStatus());
        	modelMapper.map(revenueDto, existingWorkSheetOptional);
        	RevenueEntity updatedWorkSheet = revenueRepository.save(existingWorkSheet);
            logger.info("Updated Revenue with ID: {}", updatedWorkSheet.getRevenueId());
            return convertToDTO(updatedWorkSheet);
        } else {
            logger.warn("Revenue with ID {} not found for update", revenueId);
            return null;
        }
    }
    
    // Delete
    public void deleteRevenue(Long revenueId) {
    	revenueRepository.deleteById(revenueId);
        logger.info("Deleted Revenue with ID: {}", revenueId);
    }

    //count the total WorkSheet
    public long countRevenue()
	 {
		 return revenueRepository.count();
	 }
    
    
    public Double getTotalNetRevenueForMonth(int month) {
        return revenueRepository.getTotalNetRevenueForMonth(month);
    }

    public List<RevenueEntity> getNetRevenuesInMonth(LocalDate startDate, LocalDate endDate) {
        return revenueRepository.findBySaleDateBetween(startDate, endDate);
    }
    
    
    
    public Optional<Double> getTotalMoneyAddedInBank(String moneyAddedBankName) {
        return revenueRepository.getTotalMoneyAddedInBank(moneyAddedBankName);
    }
    
    public Optional<Double> getTotalSalesForProductInYear(int year, String productName) {
        return revenueRepository.getTotalSalesForProductInYear(year, productName);
    }
    
    public String calculateProfitOrLossMessageForMonth(int currentMonth) {
        Double profitOrLoss = revenueRepository.calculateProfitOrLossForMonth(currentMonth);
        
        if (profitOrLoss > 0) {
            return "Profit: $" + profitOrLoss;
        } else if (profitOrLoss < 0) {
            return "Loss: $" + Math.abs(profitOrLoss);
        } else {
            return "No Profit or Loss";
        }
    }
    
    public Double calculateNetRevenuePercentage() {
        return revenueRepository.calculateNetRevenuePercentage();
    }
    
    public Double calculateTotalNetRevenue() {
        return revenueRepository.calculateTotalNetRevenue();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	// Helper method to convert WorkSheetDTo to WorkSheetEntity
    private RevenueEntity convertToEntity(RevenueDto saleDto)
    {
    	return modelMapper.map(saleDto, RevenueEntity.class);
    }

 // Helper method to convert WorkSheetEntity entity to WorkSheetDTo
    private RevenueDto convertToDTO(RevenueEntity workSheetEntity) {
        return modelMapper.map(workSheetEntity, RevenueDto.class);
    }
}
