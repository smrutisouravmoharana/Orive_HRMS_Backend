package com.orive.Organisation.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Organisation.Entity.ExpenceEntity;
import com.orive.Organisation.Entity.ExpenseListEntity;

public interface ExpenceRepository extends JpaRepository<ExpenceEntity, Long>{

	
}
