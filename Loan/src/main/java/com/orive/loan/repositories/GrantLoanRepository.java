package com.orive.loan.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.orive.loan.entities.GrantLoanEntity;

public interface GrantLoanRepository extends JpaRepository<GrantLoanEntity, String> {

	List<GrantLoanEntity> findByUsername(String username);
}
