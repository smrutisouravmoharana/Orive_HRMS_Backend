package com.orive.Organisation.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Organisation.Entity.ExpenseListEntity;

public interface ExpenseListRepository extends JpaRepository<ExpenseListEntity, Long>{

	List<ExpenseListEntity> findByexpenceId(Long expenceId);
}
