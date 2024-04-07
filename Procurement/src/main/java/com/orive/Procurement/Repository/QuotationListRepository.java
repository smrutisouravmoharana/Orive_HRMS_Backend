package com.orive.Procurement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.orive.Procurement.Entity.QuotationListEntity;

public interface QuotationListRepository extends JpaRepository<QuotationListEntity, Long> {
	
	List<QuotationListEntity> findByQuotationId(Long quotationId);

}
