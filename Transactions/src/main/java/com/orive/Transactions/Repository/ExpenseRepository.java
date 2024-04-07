package com.orive.Transactions.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Transactions.Entity.ExpenseEntity;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long>{

}
