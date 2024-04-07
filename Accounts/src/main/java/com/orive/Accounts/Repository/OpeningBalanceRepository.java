package com.orive.Accounts.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Accounts.Entity.OpeningBalanceEntity;

public interface OpeningBalanceRepository extends JpaRepository<OpeningBalanceEntity, Long>{

}
