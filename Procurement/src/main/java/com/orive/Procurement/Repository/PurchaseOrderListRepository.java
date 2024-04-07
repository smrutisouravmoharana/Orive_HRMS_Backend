package com.orive.Procurement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Procurement.Entity.PurchaseOrderListEntity;


public interface PurchaseOrderListRepository extends JpaRepository<PurchaseOrderListEntity, Long>{

	List<PurchaseOrderListEntity> findByPurchaseOrderId(Long purchaseOrderId);
}
