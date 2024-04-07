package com.orive.Procurement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Procurement.Entity.BidAnalysisEntity;
import com.orive.Procurement.Entity.GoodReceivedEntity;

public interface BidAnalysisRepository extends JpaRepository<BidAnalysisEntity, Long> {

	//find by location method
	Optional<BidAnalysisEntity> findByLocation(String location);
}
