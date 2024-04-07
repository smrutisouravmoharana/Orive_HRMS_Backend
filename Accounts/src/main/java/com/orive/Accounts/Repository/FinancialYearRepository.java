package com.orive.Accounts.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Accounts.Entity.FinancialYearEntity;

public interface FinancialYearRepository extends JpaRepository<FinancialYearEntity, Long>{

}
