package com.orive.Accounts.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Accounts.Entity.AccountListEntity;

public interface AccountListRepository extends JpaRepository<AccountListEntity, Long> {

}
