package com.orive.Procurement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Procurement.Entity.PurchaseOrderEntity;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderEntity, Long>{

}
