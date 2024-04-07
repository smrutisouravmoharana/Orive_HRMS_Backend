package com.orive.Procurement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Procurement.Entity.QuotationEntity;

public interface QuotationRepository extends JpaRepository<QuotationEntity, Long> {

}
