package com.orive.Sale.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Sale.Entity.SaleEntity;

public interface SaleRepository extends JpaRepository<SaleEntity, String>{

}
