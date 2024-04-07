package com.orive.Procurement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Procurement.Entity.GoodReceivedEntity;

public interface GoodReceivedRepository extends JpaRepository<GoodReceivedEntity, Long> {
	
	//find by companyName method
	Optional<GoodReceivedEntity> findByVendorName(String vendorName);

}
