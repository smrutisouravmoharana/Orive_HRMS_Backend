package com.orive.Procurement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Procurement.Entity.GoodReceivedListEntity;


public interface GoodReceivedListRepository extends JpaRepository<GoodReceivedListEntity, Long>{

	List<GoodReceivedListEntity> findByGoodReceivedId(Long goodReceivedId);
}
