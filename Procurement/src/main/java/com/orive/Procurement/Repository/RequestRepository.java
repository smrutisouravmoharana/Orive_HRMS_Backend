package com.orive.Procurement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Procurement.Entity.RequestEntity;

public interface RequestRepository extends JpaRepository<RequestEntity, Long> {

}
