package com.orive.Sale.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.orive.Sale.Entity.RevenueEntity;

public interface RevenueRepository extends JpaRepository<RevenueEntity, Long>{
	
	@Query("SELECT SUM(r.grossRevenue - (r.returns + r.discounts + r.allowances)) FROM RevenueEntity r WHERE MONTH(r.saleDate) = :month")
    Double getTotalNetRevenueForMonth(@Param("month") int month);

    List<RevenueEntity> findBySaleDateBetween(LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT SUM(r.netRevenue) FROM RevenueEntity r WHERE r.moneyAddedBankName = :moneyAddedBankName")
    Optional<Double> getTotalMoneyAddedInBank(@Param("moneyAddedBankName") String moneyAddedBankName);
    
    @Query("SELECT SUM(r.noOfClients) FROM RevenueEntity r WHERE YEAR(r.saleDate) = :year AND r.productName = :productName")
    Optional<Double> getTotalSalesForProductInYear(@Param("year") int year, @Param("productName") String productName);
    
    @Query("SELECT SUM(r1.netRevenue - r2.netRevenue) FROM RevenueEntity r1 JOIN RevenueEntity r2 ON MONTH(r1.saleDate) = MONTH(r2.saleDate) + 1 WHERE MONTH(r1.saleDate) = :currentMonth AND YEAR(r1.saleDate) = YEAR(r2.saleDate)")
    Double calculateProfitOrLossForMonth(@Param("currentMonth") int currentMonth);


    
    @Query("SELECT (SUM(r.netRevenue) / SUM(r.grossRevenue)) * 100 FROM RevenueEntity r")
    Double calculateNetRevenuePercentage();
    
    @Query("SELECT SUM(r.netRevenue) FROM RevenueEntity r")
    Double calculateTotalNetRevenue();

}
