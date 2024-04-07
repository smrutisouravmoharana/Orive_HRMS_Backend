package com.orive.PayeesAndPayers.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.PayeesAndPayers.Entity.PayeesEntity;

public interface PayeesRepository extends JpaRepository<PayeesEntity, Long> {

}
