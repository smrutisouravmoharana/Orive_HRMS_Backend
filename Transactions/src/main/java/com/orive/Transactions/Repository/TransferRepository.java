package com.orive.Transactions.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Transactions.Entity.TransferEntity;

public interface TransferRepository extends JpaRepository<TransferEntity, Long> {

}
