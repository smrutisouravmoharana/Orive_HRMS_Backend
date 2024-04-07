package com.orive.Procurement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Procurement.Entity.VendorEntity;

public interface VendorRepository extends JpaRepository<VendorEntity, Long> {

}
