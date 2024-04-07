package com.orive.Procurement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Procurement.Entity.CompanyListEntity;


public interface CompanyListRepository extends JpaRepository<CompanyListEntity, Long> {
   
	List<CompanyListEntity> findByBidAnalysisId(Long bidAnalysisId);

}
