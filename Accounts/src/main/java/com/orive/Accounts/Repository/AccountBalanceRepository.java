package com.orive.Accounts.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Accounts.Entity.AccountBalancesEntity;

public interface AccountBalanceRepository extends JpaRepository<AccountBalancesEntity, Long> {

}
