package com.orive.Accounts.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Accounts.Entity.DebitVoucherEntity;

public interface DebitVoucherRepository extends JpaRepository<DebitVoucherEntity, Long>{

}
