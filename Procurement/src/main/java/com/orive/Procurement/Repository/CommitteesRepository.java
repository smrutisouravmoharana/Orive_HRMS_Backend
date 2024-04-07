package com.orive.Procurement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Procurement.Entity.CommitteesEntity;
import com.orive.Procurement.Entity.GoodReceivedEntity;

public interface CommitteesRepository extends JpaRepository<CommitteesEntity, Long> {
	
	//find by Name method
	Optional<CommitteesEntity> findByName(String name);

}
