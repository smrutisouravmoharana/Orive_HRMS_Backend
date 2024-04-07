package com.orive.PayeesAndPayers.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.PayeesAndPayers.Entity.PayersEntity;

public interface PayersRepository extends JpaRepository<PayersEntity, Long> {

}
