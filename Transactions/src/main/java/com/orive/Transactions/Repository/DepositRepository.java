package com.orive.Transactions.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Transactions.Entity.DepositEntity;

public interface DepositRepository extends JpaRepository<DepositEntity, Long> {

}
